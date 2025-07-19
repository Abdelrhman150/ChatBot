package chatbot
import scala.io.Source
import scala.util.{Try, Success, Failure}
import java.io.File
import scala.util.Random
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import chatbot.Game
import chatbot.Chatbot
import java.io.File
import javax.sound.sampled._
object SimplifiedQuiz {

  // Add currentUser as a static variable
  var currentUser: Option[String] = None

  // Update currentUser setter
   def setCurrentUser(username: Option[String]): Unit = {
    currentUser = username
  }

  // Function for logging
  def log(msg: String, write: Boolean = false): Unit = {
    if (write) {
      writeLogToFile(msg)
    }
  }

  // Helper function to write logs to file without recursive logging
  private def writeLogToFile(msg: String): Unit = {
    val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    val logMessage = s"[$timestamp] $msg\n"

    try {
      // Save to global log file
      val globalWriter = new java.io.FileWriter(new File("app.log"), true)
      try globalWriter.write(logMessage) finally globalWriter.close()

      // If there's a current user, save to their log file
      currentUser.foreach { username =>
        val userLogFile = s"${username}_system.log"
        val userWriter = new java.io.FileWriter(new File(userLogFile), true)
        try userWriter.write(logMessage) finally userWriter.close()
      }
    } catch {
      case e: Exception => println(s"[ERROR] Failed to write log: ${e.getMessage}")
    }
  }

  def playSound(soundFilePath: String): Unit = {
    val soundFile = new File(soundFilePath)
    val audioStream = AudioSystem.getAudioInputStream(soundFile)
    val clip = AudioSystem.getClip
    clip.open(audioStream)
    clip.start()
  }
  // ----- BASIC DATA STRUCTURES -----

  // User state
  case class AppState(currentUser: Option[String] = None, users: Map[String, String] = Map.empty)

  // Chat message
  case class ChatMessage(sender: String, content: String, timestamp: LocalDateTime)
  case class ChatHistory(messages: List[ChatMessage] = List.empty) {
    def addMessage(sender: String, content: String): ChatHistory = {
      log(s"Adding new message from $sender")
      ChatHistory(messages :+ ChatMessage(sender, content, LocalDateTime.now()))
    }
  }

  // Quiz data representation
  case class Question(question: String, answer: String)
  case class MultipleChoiceQuestion(question: String, options: Array[(Char, String)], correctAnswer: Char)
  case class QuizResult(score: Int, total: Int, wrongQuestions: List[(String, String)])

  // Score record
  case class ScoreRecord(roundNumber: Int, category: String, score: Int, totalQuestions: Int, wrongQuestions: List[(String, String)])
  case class ScoreHistory(scores: List[ScoreRecord]) {
    def addScore(category: String, score: Int, total: Int, wrong: List[(String, String)]): ScoreHistory = {
      log(s"Adding new score record for category: $category, score: $score/$total")
      val categoryScores = scores.filter(_.category == category)
      val nextRound = if (categoryScores.isEmpty) 1 else categoryScores.map(_.roundNumber).max + 1
      ScoreHistory(scores :+ ScoreRecord(nextRound, category, score, total, wrong))
    }

    def categoryAverage(category: String): Double = {
      log(s"Calculating average for category: $category")
      val categoryScores = scores.filter(_.category == category)
      if (categoryScores.isEmpty) 0.0 else {
        val totalScore = categoryScores.map(_.score).sum
        val totalQuestions = categoryScores.map(_.totalQuestions).sum
        totalScore.toDouble / totalQuestions
      }
    }

    def overallAverage(): Double = {
      log("Calculating overall average")
      if (scores.isEmpty) 0.0 else {
        val totalScore = scores.map(_.score).sum
        val totalQuestions = scores.map(_.totalQuestions).sum
        totalScore.toDouble / totalQuestions
      }
    }
  }

  // ----- FILE OPERATIONS -----

  // Simple file read/write
  def readFile(path: String): List[String] = {
    log(s"Reading file: $path")
    Try(Source.fromFile(path).getLines().toList) match {
      case Success(lines) =>
        log(s"Successfully read ${lines.size} lines from $path")
        lines
      case Failure(e) =>
        log(s"Failed to read file $path: ${e.getMessage}")
        List.empty
    }
  }

  def writeFile(path: String, content: String, append: Boolean = false): Unit = {
    log(s"Writing to file: $path, append: $append")
    Try {
      val writer = new java.io.FileWriter(new File(path), append)
      try writer.write(content) finally writer.close()
      log(s"Successfully wrote to file $path")
    }
  }

  // ----- USER MANAGEMENT -----

  // Load users from file
  def loadUsers(filePath: String = "users.txt"): Map[String, String] = {
    log(s"Loading users from $filePath")
    val lines = readFile(filePath)

    val users = lines.grouped(2).collect {
      case List(userLine, passwordLine)
        if userLine.trim.startsWith("user") && passwordLine.trim.startsWith("password") =>
        val username = userLine.split(":")(1).trim
        val password = passwordLine.split(":")(1).trim
        log(s"Found user: $username")
        (username, password)
    }.toMap

    log(s"Loaded ${users.size} users")
    users
  }

  // Register new user
  def registerUser(): Option[String] = {
    log("Starting user registration")
    println("=== User Registration ===")

    val users = loadUsers()
    val nextUserNumber = users.size + 1

    print("Enter username: ")
    val username = scala.io.StdIn.readLine().trim
    log(s"Registration attempt with username: $username")

    if (username.isEmpty) {
      log("Registration failed: Empty username")
      println("Username cannot be empty.")
      return None
    }

    if (users.contains(username)) {
      log("Registration failed: Username already exists")
      println("Username already exists.")
      return None
    }

    print("Enter password: ")
    val password = scala.io.StdIn.readLine().trim

    if (password.isEmpty) {
      log("Registration failed: Empty password")
      println("Password cannot be empty.")
      return None
    }

    // Format and save the new user
    val newUserEntry = s"user $nextUserNumber : $username\npassword $nextUserNumber : $password\n"
    writeFile("users.txt", newUserEntry, append = true)

    log(s"User $username registered successfully")
    println(s"User $username registered successfully!")
    Some(username)
  }

  // Login user
  def loginUser(): Option[String] = {
    log("Starting user login")
    println("=== User Login ===")

    val users = loadUsers()

    print("Enter username: ")
    val username = scala.io.StdIn.readLine().trim
    log(s"Login attempt with username: $username")

    print("Enter password: ")
    val password = scala.io.StdIn.readLine().trim

    users.get(username) match {
      case Some(storedPassword) if storedPassword == password =>
        log(s"Login successful for user: $username")
        println(s"Login successful. Welcome, $username!")
        Some(username)
      case _ =>
        log("Login failed: Invalid credentials")
        println("Invalid username or password.")
        None
    }
  }

  // ----- QUIZ FUNCTIONALITY -----

  // Load questions from file
  def loadQuestions(filePath: String): List[Question] = {
    log(s"Loading questions from $filePath")
    val lines = readFile(filePath)

    val questions = lines.sliding(2, 2).collect {
      case List(q, a) => Question(q.trim, a.trim)
    }.toList

    log(s"Loaded ${questions.size} questions")
    questions
  }

  // Convert simple question to multiple choice
  def createMultipleChoice(question: Question, allQuestions: List[Question]): MultipleChoiceQuestion = {
    log(s"Creating multiple choice question for: ${question.question}")
    // Get all unique answers except the correct one
    val otherAnswers = allQuestions.map(_.answer).distinct.filterNot(_== question.answer)

    // Get 3 random wrong answers (or fewer if not enough)
    val wrongOptions = Random.shuffle(otherAnswers).take(3)

    // Combine with correct answer and shuffle
    val allOptions = Random.shuffle((question.answer :: wrongOptions).take(4)).toArray

    // Map options to letters a, b, c, d
    val lettersAndOptions = Array('a', 'b', 'c', 'd').take(allOptions.length).zip(allOptions)

    // Find which letter corresponds to the correct answer
    val correctLetter = lettersAndOptions.find(_._2 == question.answer).map(_._1).getOrElse('a')

    log(s"Created MCQ with correct answer: $correctLetter")
    MultipleChoiceQuestion(question.question, lettersAndOptions, correctLetter)
  }

  // Ask a single quiz question
  def askQuestion(mcq: MultipleChoiceQuestion, index: Int): Option[Boolean] = {
    log(s"Asking question ${index + 1}: ${mcq.question}")
    println(s"\nQuestion ${index + 1}: ${mcq.question}")

    // Show options
    mcq.options.foreach { case (letter, option) =>
      println(s"$letter) $option")
    }

    print("Your choice (a/b/c/d) or 'quit' to exit: ")
    val answer = scala.io.StdIn.readLine().trim.toLowerCase
    log(s"User answered: $answer")

    answer match {
      case "quit" =>
        log("User quit the quiz")
        None  // Signal to quit
      case a if a.length == 1 && mcq.options.map(_._1).contains(a.head) =>
        val isCorrect = a.head == mcq.correctAnswer
        log(s"Answer is ${if (isCorrect) "correct" else "wrong"}")
        if (isCorrect) {println("Correct!")
          playSound("C:\\Users\\abdel\\OneDrive\\Documents\\ChatBot\\ChatBot\\right.wav") }
        else { println(s"Wrong! The correct answer is ${mcq.correctAnswer}.")
          playSound("C:\\Users\\abdel\\OneDrive\\Documents\\ChatBot\\ChatBot\\wrong.wav")}
        Some(isCorrect)
      case _ =>
        log("Invalid choice, asking again")
        println("Invalid choice. Please enter a, b, c, or d.")
        askQuestion(mcq, index)  // Try again
    }
  }

  // Run a complete quiz
  def runQuiz(category: String, filePath: String): Option[QuizResult] = {
    log(s"Starting quiz for category: $category from file: $filePath")
    val questions = loadQuestions(filePath)

    if (questions.isEmpty) {
      log("No questions available for category")
      println(s"No questions available for $category category.")
      return None
    }

    println(s"\n--- $category Quiz (10 Random Questions) ---")
    println("Select the correct answer (a, b, c, d) or type 'quit' to exit.")

    // Select 10 random questions
    val selectedQuestions = Random.shuffle(questions).take(10)
    log(s"Selected ${selectedQuestions.size} random questions")

    // Convert to multiple choice format
    val mcQuestions = selectedQuestions.map(q => createMultipleChoice(q, questions))

    var score = 0
    val wrongQuestions = scala.collection.mutable.ListBuffer[(String, String)]()

    // Ask each question
    for ((mcq, index) <- mcQuestions.zipWithIndex) {
      askQuestion(mcq, index) match {
        case None =>
          log("Quiz aborted by user")
          return None  // User quit
        case Some(true) =>
          score += 1
          log(s"Question ${index + 1} correct, current score: $score")
        case Some(false) =>
          // Find the correct answer text
          val correctAnswer = mcq.options.find(_._1 == mcq.correctAnswer).map(_._2).getOrElse("")
          log(s"Question ${index + 1} wrong, correct answer was: $correctAnswer")
          wrongQuestions += ((mcq.question, correctAnswer))
      }
    }

    val result = QuizResult(score, selectedQuestions.length, wrongQuestions.toList)
    log(s"Quiz completed with score: ${result.score}/${result.total}")
    Some(result)
  }

  // ----- CHAT HISTORY MANAGEMENT -----

  // Save chat message to history
  def saveChatMessage(username: String, content: String, isSystem: Boolean = false): Unit = {
    log(s"Saving chat message for user: $username, system message: $isSystem")
    val sender = if (isSystem) "System" else username
    val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    val chatFile = s"${username}_chat.txt"

    val formattedMessage = s"[$timestamp] $sender: $content\n"
    writeFile(chatFile, formattedMessage, append = true)
  }

  // Load chat history
  def loadChatHistory(username: String): List[String] = {
    log(s"Loading chat history for user: $username")
    val chatFile = s"${username}_chat.txt"
    val history = readFile(chatFile)
    log(s"Loaded ${history.size} chat messages")
    history
  }

  // Display chat history with pagination
  def viewChatHistory(username: String): Unit = {
    log(s"Viewing chat history for user: $username")
    val history = loadChatHistory(username)

    if (history.isEmpty) {
      log("No chat history found")
      println(s"No chat history found for $username.")
      return
    }

    // Split history into "chats" (sessions)
    val sessionMarker = "=== New Session ==="
    val sessions = history.foldLeft(List(List.empty[String])) { (acc, line) =>
      if (line.contains(sessionMarker)) {
        log("Found session marker, starting new session")
        List.empty[String] :: acc  // Start a new session
      } else {
        (line :: acc.head) :: acc.tail  // Add to current session
      }
    }.map(_.reverse)  // Fix the order within each session

    log(s"Found ${sessions.size} chat sessions")

    // Display sessions with pagination
    var currentPage = 0
    val sessionsPerPage = 1

    def displayPage(page: Int): Unit = {
      val maxPage = math.ceil(sessions.size.toDouble / sessionsPerPage).toInt - 1
      val validPage = math.max(0, math.min(page, maxPage))

      log(s"Displaying chat history page ${validPage + 1}/${maxPage + 1}")
      println("\n" + "=" * 60)
      println(s"CHAT HISTORY FOR: $username (Page ${validPage + 1}/${maxPage + 1})")
      println("=" * 60)

      val startIdx = validPage * sessionsPerPage
      val endIdx = math.min((validPage + 1) * sessionsPerPage, sessions.size)

      for (i <- startIdx until endIdx) {
        println(s"\nChat ${sessions.size - i}:")
        println("-" * 40)

        sessions(i).foreach(println)

        println("-" * 40)
      }

      println("\nNavigation: [P]revious | [N]ext | [B]ack to Menu")

      val input = scala.io.StdIn.readLine().trim.toLowerCase
      log(s"Navigation input: $input")
      input match {
        case "p" =>
          log("Moving to previous page")
          displayPage(validPage - 1)
        case "n" =>
          log("Moving to next page")
          displayPage(validPage + 1)
        case "b" =>
          log("Returning to menu")
        // Return to menu
        case _ =>
          log("Invalid navigation, staying on current page")
          displayPage(validPage)  // Stay on current page
      }
    }

    displayPage(0)
  }

  // Start a new chat session marker
  def startNewChatSession(username: String): Unit = {
    log(s"Starting new chat session for user: $username")
    val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    val chatFile = s"${username}_chat.txt"
    val sessionMarker = s"\n=== New Session: $timestamp ===\n"
    writeFile(chatFile, sessionMarker, append = true)
  }

  // Load user's score history
  def loadScoreHistory(username: String): ScoreHistory = {
    log(s"Loading score history for user: $username")
    val scoreFile = s"${username}_scores.txt"
    val lines = readFile(scoreFile)

    if (lines.isEmpty) {
      log("No score history found")
      return ScoreHistory(List.empty)
    }

    // Pattern to match score lines like "Category - round X : score/total"
    val scorePattern = "(.+) - round (\\d+) : (\\d+)/(\\d+)".r

    // Helper function to split the content into chunks for each record
    def groupScoreRecords(lines: List[String]): List[List[String]] = {
      log("Grouping score records")
      val recordStartIndices = lines.zipWithIndex
        .filter(_._1.contains(" round "))
        .map(_._2)

      if (recordStartIndices.isEmpty) List.empty
      else {
        recordStartIndices.zipWithIndex.map { case (startIdx, i) =>
          val endIdx = if (i == recordStartIndices.length - 1) lines.length else recordStartIndices(i + 1)
          lines.slice(startIdx, endIdx)
        }
      }
    }

    // Extract wrong questions from a record chunk
    def extractWrongQuestions(lines: List[String]): List[(String, String)] = {
      log("Extracting wrong questions")
      val wrongQuestionsIndex = lines.indexWhere(_.contains("wrong questions are :"))
      if (wrongQuestionsIndex < 0 || lines.drop(wrongQuestionsIndex + 1).exists(_.trim == "no wrong questions")) {
        log("No wrong questions found")
        List.empty
      } else {
        // Simple parsing of question/answer pairs
        val wrongQuestionsContent = lines.drop(wrongQuestionsIndex + 1)
        val result = scala.collection.mutable.ListBuffer[(String, String)]()
        var currentQuestion: Option[String] = None

        wrongQuestionsContent.foreach { line =>
          if (line.contains("the correct ans (")) {
            val answer = line.split("\\(")(1).split("\\)")(0).trim
            log(s"Found wrong question answer: $answer")
            currentQuestion.foreach(q => result += ((q, answer)))
            currentQuestion = None
          } else if (line.trim.nonEmpty && !line.contains("wrong questions")) {
            currentQuestion = Some(line.trim)
            log(s"Found wrong question: ${line.trim}")
          }
        }

        log(s"Extracted ${result.size} wrong questions")
        result.toList
      }
    }

    // Parse all score records
    val scoreRecords = groupScoreRecords(lines).flatMap { recordLines =>
      recordLines.headOption.flatMap { firstLine =>
        scorePattern.findFirstMatchIn(firstLine).map { m =>
          val category = m.group(1)
          val round = m.group(2).toInt
          val score = m.group(3).toInt
          val total = m.group(4).toInt
          log(s"Found score record: $category - round $round : $score/$total")
          val wrongQuestions = extractWrongQuestions(recordLines)

          ScoreRecord(round, category, score, total, wrongQuestions)
        }
      }
    }

    log(s"Loaded ${scoreRecords.size} score records")
    ScoreHistory(scoreRecords)
  }

  // Save quiz result to user's score history
  def saveScore(username: String, category: String, result: QuizResult): Unit = {
    log(s"Saving score for user: $username, category: $category")
    val scoreFile = s"${username}_scores.txt"

    // Load existing history
    val history = loadScoreHistory(username)

    // Add new score
    val updatedHistory = history.addScore(
      category,
      result.score,
      result.total,
      result.wrongQuestions
    )

    // Format score record
    def formatScoreRecord(record: ScoreRecord): String = {
      log(s"Formatting score record for ${record.category} round ${record.roundNumber}")
      val wrongQuestionsStr = if (record.wrongQuestions.isEmpty) "no wrong questions"
      else record.wrongQuestions.map { case (q, a) =>
        s"$q\nthe correct ans ($a)"
      }.mkString("\n")

      s"${record.category} - round ${record.roundNumber} : ${record.score}/${record.totalQuestions}\nwrong questions are : \n$wrongQuestionsStr"
    }

    // Format complete history
    val scoreOutput = updatedHistory.scores.map(formatScoreRecord).mkString("\n\n")

    // Calculate statistics
    val overallAvg = updatedHistory.overallAverage()
    val overallPerc = overallAvg * 100

    // Add category-specific averages
    val categories = updatedHistory.scores.map(_.category).distinct
    val categoryStats = categories.map { cat =>
      val avg = updatedHistory.categoryAverage(cat)
      val perc = avg * 100
      log(s"Category $cat average: $avg (${perc.formatted("%.1f")}%)")
      s"$cat Average: $avg (${perc.formatted("%.1f")}%%)"
    }.mkString("\n")

    val fullOutput = s"$scoreOutput\n\nAverage Score: ${overallAvg.formatted("%.2f")} (${overallPerc.formatted("%.1f")}%%)\n$categoryStats"

    // Write to file
    log("Writing score history to file")
    writeFile(scoreFile, fullOutput, append = false)
  }

  // Display quiz results
  def displayQuizResults(username: String, category: String, result: QuizResult): Unit = {
    log(s"Displaying quiz results for user: $username, category: $category")
    val percentage = (result.score.toDouble / result.total) * 100

    println("\n" + "=" * 50)
    println(s"QUIZ RESULTS FOR: $username - $category")
    println("=" * 50)
    println(s"Score: ${result.score}/${result.total} (${percentage.formatted("%.1f")}%)")

    // Performance message
    val performanceMessage = percentage match {
      case p if p >= 90 => "Outstanding performance!"
      case p if p >= 80 => "Excellent work!"
      case p if p >= 70 => "Good job!"
      case p if p >= 60 => "Not bad!"
      case p if p >= 50 => "You passed!"
      case _ => "Keep practicing!"
    }
    log(s"Performance assessment: $performanceMessage")
    println(s"$performanceMessage")
    println("=" * 50)

    // Save score
    saveScore(username, category, result)
    println("Score saved to your history!")

    println("\nPress Enter to continue...")
    scala.io.StdIn.readLine()
  }

  // Display user's score history
  def showScoreHistory(username: String): Unit = {
    log(s"Showing score history for user: $username")
    val history = loadScoreHistory(username)

    if (history.scores.isEmpty) {
      log("No score history found")
      println(s"No score history found for $username.")
      return
    }

    println("\n" + "=" * 60)
    println(s"SCORE HISTORY FOR: $username")
    println("=" * 60)

    // Group scores by category
    val scoresByCategory = history.scores.groupBy(_.category)
    log(s"Found scores for ${scoresByCategory.size} categories")

    // Display each category
    scoresByCategory.foreach { case (category, records) =>
      log(s"Displaying scores for category: $category")
      println(s"\nCATEGORY: $category")
      println("-" * 40)

      // Sort by round number
      val sortedRecords = records.sortBy(_.roundNumber)

      sortedRecords.foreach { record =>
        log(s"Displaying round ${record.roundNumber}: ${record.score}/${record.totalQuestions}")
        println(s"Round ${record.roundNumber}: ${record.score}/${record.totalQuestions}")

        if (record.wrongQuestions.isEmpty) {
          println("  No wrong questions")
        } else {
          println("  Wrong questions:")
          record.wrongQuestions.foreach { case (q, a) =>
            println(s"  - $q (correct: $a)")
          }
        }
        println()
      }

      // Category average
      val avgPercentage = history.categoryAverage(category) * 100
      log(s"Category $category average: ${avgPercentage.formatted("%.1f")}%")
      println(s"$category Average: ${avgPercentage.formatted("%.1f")}%")
      println("-" * 40)
    }

    // Overall statistics
    val overallPercentage = history.overallAverage() * 100
    log(s"Overall average: ${overallPercentage.formatted("%.1f")}%")
    println(s"\nOVERALL AVERAGE: ${overallPercentage.formatted("%.1f")}%")
    println("=" * 60)

    println("\nPress Enter to continue...")
    scala.io.StdIn.readLine()
  }

  // ----- MENU SYSTEM -----

  // Category selection menu
  def selectCategory(): Option[String] = {
    log("Displaying category selection menu")
    println("\n" + "=" * 40)
    println("SELECT QUIZ CATEGORY")
    println("=" * 40)
    println("1. Global Actors")
    println("2. Egyptian Actors")
    println("3. Egyptian Singers")
    println("4. Cartoon Characters")
    println("5. Anime Characters")
    println("6. Footballers")
    println("7. Scientists")
    println("0. Back to Main Menu")
    println("=" * 40)

    print("Enter your choice: ")
    val choice = scala.io.StdIn.readLine().trim
    log(s"Category selection choice: $choice")

    choice match {
      case "1" =>
        log("Selected category: Global Actors")
        Some("Global Actors")
      case "2" =>
        log("Selected category: Egyptian Actors")
        Some("Egyptian Actors")
      case "3" =>
        log("Selected category: Egyptian Singers")
        Some("Egyptian Singers")
      case "4" =>
        log("Selected category: Cartoon Characters")
        Some("Cartoon Characters")
      case "5" =>
        log("Selected category: Anime Characters")
        Some("Anime Characters")
      case "6" =>
        log("Selected category: Footballers")
        Some("Footballers")
      case "7" =>
        log("Selected category: Scientists")
        Some("Scientists")
      case "0" =>
        log("Returning to main menu")
        None
      case _ =>
        log("Invalid category choice")
        println("Invalid option. Please try again.")
        selectCategory()
    }
  }

  // Load and display user's log history
  def viewLogHistory(username: String): Unit = {
    log(s"Viewing log history for user: $username", write = true)
    val logFile = s"${username}_system.log"
    val logs = readFile(logFile)

    if (logs.isEmpty) {
      println(s"No log history found for $username.")
      return
    }

    var currentPage = 0
    val logsPerPage = 10

    def displayPage(page: Int): Unit = {
      val maxPage = math.ceil(logs.size.toDouble / logsPerPage).toInt - 1
      val validPage = math.max(0, math.min(page, maxPage))

      println("\n" + "=" * 60)
      println(s"LOG HISTORY FOR: $username (Page ${validPage + 1}/${maxPage + 1})")
      println("=" * 60)

      val startIdx = validPage * logsPerPage
      val endIdx = math.min((validPage + 1) * logsPerPage, logs.size)

      logs.slice(startIdx, endIdx).foreach(println)

      println("\nNavigation: [P]revious | [N]ext | [B]ack to Menu")

      val input = scala.io.StdIn.readLine().trim.toLowerCase
      input match {
        case "p" => displayPage(validPage - 1)
        case "n" => displayPage(validPage + 1)
        case "b" => // Return to menu
        case _ => displayPage(validPage) // Stay on current page
      }
    }

    displayPage(0)
  }

  // Main application loop
  def mainLoop(): Unit = {
    log("Starting main application loop", write = true)
    var running = true

    while (running) {
      log("Displaying main menu")
      println("\n" + "=" * 40)
      println("QUIZ APPLICATION")
      println("=" * 40)
      println("1. Take Quiz")
      println("2. View Score History")
      println("3. View Chat History")
      println("4. View System Logs")
      println("5. Exit")
      println("=" * 40)
      println(s"Status: ${currentUser.getOrElse("Not logged in")}")
      println("=" * 40)

      print("Enter your choice: ")
      val choice = scala.io.StdIn.readLine().trim
      log(s"Main menu choice: $choice")

      // Log the user's choice if logged in
      currentUser.foreach { username =>
        saveChatMessage(username, s"Selected menu option: $choice")
      }

      choice match {

        case "1" if currentUser.isDefined =>
          log("Selected: Take Quiz")
          selectCategory() match {
            case Some(category) =>
              val filePath = category match {
                case "Global Actors" => "global_actors.txt"
                case "Egyptian Actors" => "egyptian_actors.txt"
                case "Egyptian Singers" => "egyptian_singers.txt"
                case "Cartoon Characters" => "cartoon_characters.txt"
                case "Anime Characters" => "anime_characters.txt"
                case "Footballers" => "footballers.txt"
                case "Scientists" => "scientists.txt"
                case _ => "global_actors.txt"
              }

              log(s"Starting quiz for category: $category", write = true)
              saveChatMessage(currentUser.get, s"Started quiz: $category")

              runQuiz(category, filePath) match {
                case Some(result) =>
                  log(s"Quiz completed with score: ${result.score}/${result.total}", write = true)
                  displayQuizResults(currentUser.get, category, result)
                  saveChatMessage(currentUser.get, s"Completed quiz: $category with score ${result.score}/${result.total}", isSystem = true)
                case None =>
                  log("Quiz cancelled", write = true)
                  println("Quiz cancelled.")
                  saveChatMessage(currentUser.get, s"Cancelled quiz: $category", isSystem = true)
              }

            case None =>
              log("Returned to main menu without selecting category")
          }

        case "1" =>
          log("Selected: Take Quiz (not logged in)", write = true)
          println("You must login first to access the quiz.")

        case "2" if currentUser.isDefined =>
          log("Selected: View Score History")
          saveChatMessage(currentUser.get, "Viewed score history")
          showScoreHistory(currentUser.get)

        case "2" =>
          log("Selected: View Score History (not logged in)", write = true)
          println("You must login first to view score history.")

        case "3" if currentUser.isDefined =>
          log("Selected: View Chat History")
          saveChatMessage(currentUser.get, "Viewed chat history")
          viewChatHistory(currentUser.get)

        case "3" =>
          log("Selected: View Chat History (not logged in)", write = true)
          println("You must login first to view chat history.")

        case "4" if currentUser.isDefined =>
          log("Selected: View System Logs")
          saveChatMessage(currentUser.get, "Viewed system logs")
          viewLogHistory(currentUser.get)

        case "4" =>
          log("Selected: View System Logs (not logged in)", write = true)
          println("You must login first to view system logs.")

        case "5" =>
          log("Selected: Exit Application", write = true)
          currentUser.foreach { username =>
            saveChatMessage(username, "Application closed", isSystem = true)
          }
          println("Thank you for using the Quiz Application. Goodbye!")
          running = false

        case _ =>
          log(s"Invalid menu option: $choice", write = true)
          println("Invalid option. Please try again.")
      }
    }
  }


  // Main entry point
  def main(args: Array[String]): Unit = {
    log("Application starting")
    println("Welcome to the Quiz Application!")
    mainLoop()
    log("Application terminated", write = true)
  }
}