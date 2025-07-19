package chatbot
import chatbot.Chatbot._
import chatbot.SimplifiedQuiz._

import scala.util.control.Breaks._
import scala.io.{Source, StdIn}
import scala.util.Random

object Game {


  type QA = (String, String)

  private def loadQuestions(path: String): List[List[QA]] = {
    val content = Source.fromFile(path).mkString.trim
    content
      .split("\\r?\\n\\s*\\r?\\n")
      .toList
      .map { block =>
        block
          .split("\\r?\\n")
          .toList
          .grouped(2)
          .collect {
            case List(q, t) => (q.trim, t.trim)
          }
          .toList
      }
  }

  // Generic loader for text files with records separated by blank lines
  private def loadEntities(path: String): Map[String, Set[String]] = {
    val content = Source.fromFile(path).mkString.trim
    val records = content.split("\\r?\\n\\s*\\r?\\n")
    records.foldLeft(Map.empty[String, Set[String]]) { (acc, rec) =>
      val lines = rec.split("\\r?\\n").map(_.trim)
      if (lines.length >= 2) {
        acc + (lines(0) -> lines(1).split(",").map(_.trim).toSet)
      } else acc
    }
  }

  val allCategories = List(
    ("an Arab Actor ", "arabActor_core_questions.txt", "arabActor_core_data.txt"),
    ("a Singer", "singer_core_questions.txt", "singer_core_data.txt"),
    ("an Instructor  ", "instructor_core_questions.txt", "instructor_core_data.txt"),
    ("a Football Player", "players_ques.txt", "players.txt"),
    ("a Cartoon", "cartoon_core_questions.txt", "cartoon_core_data.txt"),
    ("an Anime", "anime_core_questions.txt", "anime_core_data.txt"),
    ("a Foreign Actor", "foreignActor_core_questions.txt", "foreignActor_core_data.txt"),
    ("a Scientist", "scientist_core_questions.txt", "scientist_core_data.txt")
  )

  def addelem(list: List[String], stg: String): List[String] = {
    log(s"addelem: adding '$stg' to $list")
    val temp = stg :: list
    log(s"addelem result: $temp")
    temp
  }

  def NotOnlyYesNo(input: String, allTraits: List[String]): Option[String] = {
    log(s"NotOnlyYesNo: input='$input'")
    for (traitItem <- allTraits) {
      log(s"Checking traitItem='$traitItem'")
      if (input.toLowerCase.contains(traitItem.toLowerCase)) {
        log(s"Matched trait: $traitItem")
        return Some(traitItem)
      }
    }
    log("No trait matched")
    None
  }

  def NewData(ans: List[String], groupedQues: List[List[(String, String)]], CharData: Map[String, Set[String]]): List[List[(String, String)]] = {
    log(s"NewData: answers=$ans")
    val NewQues = groupedQues.map { GQues => // list of each group of questions
    val filtered = GQues.filter { case (_, traitName) =>  // a trait of each question
    val exists = CharData.exists { case (_, traits) => traits.contains(traitName) } // go over every list of characters traits and filtering if the question contains the trait name
      log(s"Trait '$traitName' exists in filtered chars? $exists")
        exists
      }
      filtered
    }
    log(s"NewData questions: $NewQues")
    NewQues
  }

  // el 3ks b2a
  def NewDataExcluding(exclude: String, groupedQues: List[List[(String, String)]], CharData: Map[String, Set[String]]): List[List[(String, String)]] = {
    log(s"NewDataExcluding: exclude='$exclude'")
    val NewExcludingChar = CharData.filter { case (name, traits) =>
      val keep = !traits.contains(exclude)
      log(s"Char '$name': !contains('$exclude') => $keep")
      keep
    }
    log(s"Characters after exclusion: ${NewExcludingChar.keys}")

    val NewQues = groupedQues.map { GQues =>
      val filtered = GQues.filter { case (_, traitName) =>
        val keep = traitName != exclude && NewExcludingChar.exists { case (_, traits) => traits.contains(traitName) }
        log(s"Trait '$traitName': keep? $keep")
        keep
      }
      filtered
    }
    log(s"NewDataExcluding questions: $NewQues")
    NewQues
  }

  def UniqueTraits(ans: List[String], traitSets: List[Set[String]]): Boolean = {
    log(s"UniqueTraits: ans=$ans")
    // count of all characters having all traits of ans
    val matchCount = traitSets.count(traits => ans.forall(elem => traits.contains(elem)))
    log(s"UniqueTraits matchCount=$matchCount")
    matchCount == 1
  }

  def Getcharacter(ans: List[String], characterNames: List[String], charactersTraits: List[Set[String]], index: Int = 0): Option[String] = {
    log(s"Getcharacter: index=$index, ans=$ans")
    if (index >= characterNames.length) {
      log("Getcharacter: No match found at end of list")
      Some("No match found")
    } else if (ans.forall(elem => charactersTraits(index).contains(elem))) {
      val name = characterNames(index)
      log(s"Getcharacter: Found match '$name'")
      Some(name)
    } else {
      log(s"Getcharacter: '$index' not match, advancing")
      Getcharacter(ans, characterNames, charactersTraits, index + 1)
    }
  }

  def findBestMatch(ans: List[String], characterNames: List[String], charactersTraits: List[Set[String]]): Option[String] = {
    log(s"findBestMatch: ans=$ans")
    var maxMatchCount = 0
    var bestMatchIndex = -1
    for (i <- characterNames.indices) {
      val matchCount = charactersTraits(i).count(elem => ans.contains(elem))
      log(s"Character '${characterNames(i)}' matchCount=$matchCount")
      if (matchCount > maxMatchCount) {
        maxMatchCount = matchCount
        bestMatchIndex = i
        log(s"New best matchIndex=$bestMatchIndex count=$maxMatchCount")
      }
    }
    if (bestMatchIndex != -1) {
      val best = characterNames(bestMatchIndex)
      log(s"findBestMatch: returning best='$best'")
      Some(best)
    } else {
      log("findBestMatch: No match found")
      Some("No match found")
    }
  }

  def askQuestions(categories: List[(String, String, String)]): Option[String] = {
    log("Starting askQuestions with categories", write = true)

    val allCategoryNames: List[String] = categories.map(_._1.toLowerCase)

    def askCategory(currentCategory: String): String = {
      log(s"Asking about category: $currentCategory", write = true)
      var answer = ""
      var valid = false
      val thinkingPhrases = List(
        "Hmm... are you thinking about",
        "Let me think... is it",
        "Uhh... could it be",
        "Hmmm... are you imagining",
        "... are you picturing",
        "I'm guessing... could you be thinking of",
        "Hmm... do you have in mind",
        "Let me guess... is your mind on",
        "Thinking... maybe it's",
        "Could it be... you're thinking about"
      )
      val phrase = thinkingPhrases(Random.nextInt(thinkingPhrases.length))
      while (!valid) {
        println(s"$phrase a $currentCategory?")
        answer = scala.io.StdIn.readLine().toLowerCase()
        log(s"User answered: $answer", write = true)

        val matched = allCategoryNames.exists(cat => answer.contains(cat))
        if (answer == "yes" || answer == "no" || matched) {
          valid = true
        } else {
          println("Please answer with 'yes' or 'no', or mention a category.")
        }
      }

      answer
    }

    categories match {
      case Nil =>
        log("No more categories to check", write = true)
        None

      case (categoryName, questionsPath, dataPath) :: tail =>
        log(s"Processing category: $categoryName", write = true)
        val answer = askCategory(categoryName)
        val lowerAnswer = answer.toLowerCase

        if (lowerAnswer == "yes") {
          log(s"User selected category: $categoryName", write = true)
          val questionList = loadQuestions(questionsPath)
          val members = loadEntities(dataPath)
          askDistinctGroupQuestions(questionList, members)
        } else {
          val matchedCategory = categories.find { case (name, _, _) =>
            lowerAnswer.contains(name.toLowerCase)
          }

          matchedCategory match {
            case Some((matchedName, matchedQPath, matchedDPath)) =>
              log(s"User directly mentioned category: $matchedName", write = true)
              val questionList = loadQuestions(matchedQPath)
              val members = loadEntities(matchedDPath)
              askDistinctGroupQuestions(questionList, members)

            case None =>
              log(s"User rejected category: $categoryName, moving to next", write = true)
              askQuestions(tail)
          }
        }
    }
  }

  def askDistinctGroupQuestions(groupedQuestions: List[List[(String, String)]], Characters: Map[String, Set[String]]): Option[String] = {
    log("askDistinctGroupQuestions: start")
    var answers = List[String]()
    // 1) Introduce a cumulative filteredChars, starting with all characters
    var filteredChars = Characters

    var currentGroupedQues = groupedQuestions
    val characterNames = Characters.keys.toList
    val charactersTraits = characterNames.map(Characters)
    val size = groupedQuestions.map(_.length).max
    var excludedTrait: Option[String] = None

    for (j <- 0 until size) {  // cover all traits
      log(s"Outer loop iteration j=$j")
      var fieldIndex = 0
      while (fieldIndex < currentGroupedQues.length) {
        log(s"Field index $fieldIndex, currentGroupedQues size=${currentGroupedQues.length}")
        val fieldLists = currentGroupedQues(fieldIndex) //list of grouped questions
        val traitsNames = fieldLists.map { case (_, t) => t } // stores traits list of the fields question

        if (
          j < fieldLists.length &&
            !answers.exists(answer => traitsNames.contains(answer)) &&
            !excludedTrait.exists(traitName => traitsNames.contains(traitName))) {
          val (question, traitName) = fieldLists(j)
          log(s"Asking question at ($fieldIndex,$j): $question with trait '$traitName'")
          var valid = false
          while (!valid) {
            println(question)
            val userInput = StdIn.readLine().toLowerCase()
            log(s"User input: $userInput for trait '$traitName'")
            userInput match {
              case "yes" =>
                answers = addelem(answers, traitName)
                excludedTrait = None
                // 2) Narrow down filteredChars by requiring this trait
                filteredChars = filteredChars.filter { case (_, traits) =>
                  answers.forall(traits.contains)
                }
                if (UniqueTraits(answers, charactersTraits)) {
                  return Getcharacter(answers, characterNames, charactersTraits)
                } else {
                  // 3) Pass filteredChars into NewData so questions stay in sync
                  currentGroupedQues = NewData(answers, groupedQuestions, filteredChars)
                  valid = true
                }

              case "no" =>
                excludedTrait = Some(traitName)
                NotOnlyYesNo(userInput, traitsNames) match {
                  case Some(matched) =>
                    answers = addelem(answers, matched)
                    // update filteredChars by positive match as well
                    filteredChars = filteredChars.filter { case (_, traits) =>
                      answers.forall(traits.contains)
                    }
                    if (UniqueTraits(answers, charactersTraits)) {
                      return Getcharacter(answers, characterNames, charactersTraits)
                    } else {
                      currentGroupedQues = NewData(answers, groupedQuestions, filteredChars)
                      valid = true
                    }

                  case None =>
                    // 2b) Exclude this trait from filteredChars
                    filteredChars = filteredChars.filter { case (_, traits) =>
                      !traits.contains(traitName)
                    }
                    // 3b) And update questions accordingly
                    currentGroupedQues = NewDataExcluding(traitName, currentGroupedQues, filteredChars)
                    valid = true
                }

              case input if
                input == "not sure" ||
                  input == "i don't know" ||
                  input == "idk" ||
                  input == "maybe"
              =>
                if (UniqueTraits(answers, charactersTraits)) {
                  return Getcharacter(answers, characterNames, charactersTraits)
                }
                valid = true

              case input if input == "quit" || input == "exit" || input == "end game" =>
                println("👋 Exiting the game. See you next time!")
                return Some("QUIT")

              case other =>
                NotOnlyYesNo(other, traitsNames) match {
                  case Some(matched) =>
                    answers = addelem(answers, matched)
                    filteredChars = filteredChars.filter { case (_, traits) =>
                      answers.forall(traits.contains)
                    }
                    if (UniqueTraits(answers, charactersTraits)) {
                      return Getcharacter(answers, characterNames, charactersTraits)
                    } else {
                      currentGroupedQues = NewData(answers, groupedQuestions, filteredChars)
                      valid = true
                    }

                  case None =>
                    println("Please answer with (yes, no, not sure), or mention any related word.")
                }
            }
          }
        }
        fieldIndex += 1
      }
    }

    findBestMatch(answers, characterNames, charactersTraits) match {
      case Some(best) =>
        val idx = characterNames.indexOf(best)
        val matchCount = if (idx >= 0) charactersTraits(idx).count(elem => answers.contains(elem)) else 0
        val totalTraits = if (idx >= 0) charactersTraits(idx).size else 1
        val confidence = matchCount.toDouble * 100 / totalTraits
        println(f"Your character is $best. Confidence: ${confidence}%.2f%% – looks like you fed me some wrong traits 🤔")
        log(s"Final best match: $best with confidence $confidence")
        Some(best)
      case None =>
        log("No match found after questions")
        println("No match found")
        None
    }
  }


  def menu(): Unit = {
    var running = true
    while (running) {
      log("Displaying main menu")
      println("\n" + "═" * 40)
      println("MAIN MENU")
      println("═" * 40)
      println("1. Register User")
      println("2. Login")
      println("3. Logout")
      println("=" * 40)
      println(s"Status: ${currentUser.getOrElse("Not logged in")}")
      println("=" * 40)
      print("Enter your choice: ")
      val choice = scala.io.StdIn.readLine().trim
      log(s"Main menu choice: $choice", write = true)

      // Log the user's choice if logged in
      currentUser.foreach { username =>
        saveChatMessage(username, s"Selected menu option: $choice")
      }
      choice match {
        case "1" =>
          log("Selected: Register User")
          registerUser() match {
            case Some(username) =>
              log(s"Registration successful for user: $username", write = true)
              setCurrentUser(Some(username))
              startNewChatSession(username)

              saveChatMessage(username, "User registered successfully", isSystem = true)
              running = false

            case None =>
              log("Registration failed", write = true)
          }

        case "2" =>
          log("Selected: Login")
          loginUser() match {
            case Some(username) =>
              log(s"Login successful for user: $username", write = true)
              setCurrentUser(Some(username))
              startNewChatSession(username)
              saveChatMessage(username, "User logged in successfully", isSystem = true)
              running = false

            case None =>
              log("Login failed", write = true)
          }

        case "3" if currentUser.isDefined =>
          log("Selected: Logout", write = true)
          saveChatMessage(currentUser.get, "User logged out", isSystem = true)
          println(s"Logging out ${currentUser.get}...")
          setCurrentUser(None)
          println("Logged out successfully.")

        case "3" =>
          log("Selected: Logout (not logged in)", write = true)
          println("You are not currently logged in.")

      }
    }
  }

  def main(args:Array[String]): Unit = {
    log("Starting chatbot application", write = true)
    menu()
    var continue = true

    while (continue) {
      print("You: ")
      val userInput = scala.io.StdIn.readLine().toLowerCase()
      log(s"Received user input: '$userInput'", write = true)

      if (Set("exit", "bye", "salam").contains(userInput)) {
        log("Exit command detected", write = true)
        println("Bot: Goodbye!")
        continue = false
      } else {
        log("Generating response for user input", write = true)
        val (botResponse, startGame) = generateResponse(userInput)
        log(s"Response generated: '$botResponse', startGame=$startGame", write = true)
        println(s"Bot: $botResponse")

        if (startGame) {
          log("Starting game sequence", write = true)
          var keepPlaying = true

          while (keepPlaying) {
            log("Asking questions to identify character", write = true)

            Game.askQuestions(Game.allCategories) match {
              case Some("QUIT") =>
                log("User chose to quit the game", write = true)
                keepPlaying = false
                continue = true

              case Some(character) =>
                log(s"Character identified: $character", write = true)
                println(s"🎯 Great! You are thinking of: $character!")

              case None =>
                log("No unique character identified", write = true)
                println("❌ No unique character could be identified")
            }

            if (keepPlaying) {
              println("Do you want to play again? (yes/no)")
              val playAgainInput = scala.io.StdIn.readLine().toLowerCase()
              log(s"Play again input: '$playAgainInput'", write = true)

              if (Set("exit", "bye", "salam").contains(playAgainInput)) {
                log("Exit command detected during play again prompt", write = true)
                println("Bot: Goodbye!")
                keepPlaying = false
                continue = false
              } else if (playAgainInput != "yes") {
                log("Player declined to play again", write = true)
                keepPlaying = false
                println("👍 Ok!")
              } else {
                log("Player chose to play again", write = true)
              }
            }
          }
        }
      }
    }

    log("Chatbot application terminated")
  }
}
