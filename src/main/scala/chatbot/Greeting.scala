package chatbot

import chatbot.Game
import chatbot.SimplifiedQuiz._

object Chatbot {

  val userName: Option[String] = currentUser
  val jokes = List(
    "Why do programmers prefer dark mode? Because the light attracts bugs! Hope that gave you a good laugh!",
    "Why did the developer go broke? Because he used up all his cache. Hope that gave you a good laugh!",
    "Why do Java developers wear glasses? Because they can't C#. Hope that gave you a good laugh!",
    "Why was the computer cold? It forgot to close its Windows.",
    "Why do programmers hate nature? Too many bugs.",
    "Why did the programmer quit his job? Because he didn't get arrays (a raise)!"
  )
  // A list of facts/informations the bot can tell
  val facts = List(
    "Did you know? The first computer bug was an actual moth found in a computer!",
    "Here's a fact: The first programmer was Ada Lovelace in the 1800s.",
    "Fun fact: More than 80% of the world's data is unstructured.",
    "Did you know? The first computer virus was created in 1983 and called 'Elk Cloner'.",
    "Here's something cool: The first-ever website is still online at info.cern.ch.",
    "Random fact: Over 500 new programming languages have been created.",
    "Did you know? The word 'robot' comes from the Czech word 'robota' meaning 'forced labor'.",
    "Hello, World! is the most famous first program. Almost every programming tutorial starts by printing it to check the environment.",
    "The first computer programmer was a woman — Ada Lovelace, who wrote an algorithm for a mechanical computer in the 1800s.",
    "Python was named after Monty Python's Flying Circus, not the snake!",
    "The first 'bug' was a real bug — a moth stuck in a 1947 Harvard Mark II relay, which led to the term 'debugging'.",
    "JavaScript and Java are unrelated languages despite the similar names.",
    "There are over 700 programming languages, from popular ones like Python to very niche or experimental languages.",
    "Coding is often compared to writing poetry or music, combining syntax rules with creativity.",
    "The first video game, 'Tennis for Two', was created in 1958 and shown on an oscilloscope.",
    "Code comments are crucial for understanding and maintaining code, but often programmers neglect them."
  )
  def handleUserInput(userInput: List[String]): (String, Boolean) = {
    log(s"handleUserInput called with: $userInput", write = true)

    userInput match {
      case words if words.exists(w => Set("game","play","guessing").contains(w)) =>
        log("Matched game pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        Thread.sleep(500)
        println("🤖 loading game....")
        Thread.sleep(1000)
        (s"Alright ${userName.getOrElse("")}! Let's start the game!👻 ",true)

      case words if words.exists(w => Set("quiz","take").contains(w)) =>
        log("Matched quiz pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        Thread.sleep(500)
        println("🤖 loading quiz...")
        Thread.sleep(1000)
        SimplifiedQuiz.main(Array())
        ("Starting the quiz now!", false)

      case words if words.exists(w => Set("log").contains(w)) =>
        log("Viewing logs", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        viewLogHistory(s"${userName.getOrElse("")}")
        ("let's view your logs!", false)

      case words if words.exists(w => Set("call", "name", "called").contains(w)) =>
        log("Matched call/name pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        ("I don't have a name, but you can call me 'Chatbot'!", false)

      case words if words.exists(w => Set("hi", "hello", "hey", "ahlan").contains(w)) =>
        log("Matched greeting pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        (s"Hello ${userName.getOrElse("")}! How can I help you today?", false)

      case words if words.contains("how") && words.contains("you") =>
        log("Matched 'how are you' pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        (s"I'm doing great, thanks! How about you ${userName.getOrElse("")}?", false)

      case words if words.contains("who") && words.contains("you") =>
        log("Matched 'who are you' pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        ("I’m a chatbot, I was programmed just for your entertainment!", false)

      case words if words.contains("thanks") || words.contains("thank you") =>
        log("Matched thanks pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        (s"You're welcome ${userName.getOrElse("")}! Let me know if you need anything else.", false)

      case words if words.contains("bye") || words.contains("goodbye") =>
        log("Matched goodbye pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        (s"Goodbye ${userName.getOrElse("")}! Have a great day!", false)

      case words if words.contains("help") || words.contains("assist") =>
        log("Matched help pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        ("I can help you with the 'Guess Who I Am' game! You can either play a guessing game where I try to guess what you're thinking, or take a quiz where you answer my questions. Just type 'quiz' or 'guess' to get started!", false)

      case words if words.contains("features") || words.contains("what can you do") =>
        log("Matched features pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)

        // define frame width once
        val frameWidth  = 35
        val border      = "═" * frameWidth

        // build the boxed features text
        val featuresText = s"""
          $border
          🤖 FEATURES
          $border
          🎲 Guessing Game
          • I try to guess what you're thinking
          $border
          ❓ Fun Quiz
          • You answer my playful questions
          $border
          """

        (featuresText, false)

      case words if words.contains("ok") || words.contains("okay") =>
        log("Matched ok/okay pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        ("Okay! Yalla bena Specify what you want — 'quiz' wala 'guess'?", false)

      case words if words.contains("where") && words.contains("from") =>
        log("Matched 'where from' pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        ("I'm from the world of code, living on your device!", false)

      case words if words.contains("joke") || words.contains("funny") =>
        log("Matched joke pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        val jokeIndex = scala.util.Random.nextInt(jokes.length)
        log(s"Selected joke at index $jokeIndex", write = true)
        val jokeToTell = jokes(jokeIndex)
        (jokeToTell + " 😄", false)

      case words if words.contains("i'm good") || words.contains("i'm great") =>
        log("Matched good/great pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        ("I'm glad to hear you're doing well!", false)

      case words if words.contains("rules") || words.contains("how to play") =>
        log("Matched rules pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        ("In 'Guess Who I Am?', you answer questions, and I try to guess the object you're thinking of! Type 'ready' to start. 🚑 Only one rule: answer with (yes, no, not sure,or you can even answer with a sentance)!", false)

      case words if words.exists(word => Set("sad", "upset", "down", "hazen", "medaye2").contains(word)) =>
        log("Matched sad mood pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        (s"It's okay ${userName.getOrElse("")} to feel upset sometimes. Want to play a game to cheer up?", false)

      case words if words.exists(word => Set("sa3eed", "motahmes", "excited", "happy").contains(word)) =>
        log("Matched happy mood pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        ("Great to hear you're excited! Let's get started then!", false)

      case words if words.contains("sing") =>
        log("Matched sing pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        val songLinks = List(
          "Blinding Lights by The Weeknd" -> "https://www.youtube.com/watch?v=4NRXx6U8ABQ",
          "Stay by The Kid LAROI & Justin Bieber" -> "https://www.youtube.com/watch?v=kTJczUoc26U",
          "Tula8te - Habeeby Leh" -> "https://youtu.be/MCbM1PwjA5Y?si=fyy5XznUyyAwQvdJ",
          "Cairokee - Costa Rica" -> "https://www.youtube.com/watch?v=JwCmC0Vl9gA",
          "Hamza Namira - Reyah El Hayah" -> "https://www.youtube.com/watch?v=V2W7IuoyH4g",
          "Amr Diab - Helwa El Ayam" -> "https://youtu.be/VizbOHL69s0?si=As2LCW-mDFtqnFaj",
          "Sherine - Hobbo Ganna" -> "https://www.youtube.com/watch?v=1hT6Z5HEUpQ",
          "Elissa - Hanghani Kaman W Kaman" -> "https://www.youtube.com/watch?v=ihF2HMIYX04",
          "Billie Eilish - Blue" -> "https://youtu.be/_IjWFq1c5M4?si=10yST6el236jUJc9"
        )
        log(s"Prepared ${songLinks.length} song links", write = true)
        val links = songLinks.map { case (title, url) => s"$title: $url" }.mkString("\n")

        (s"I can't really sing, but here are some trending songs you might enjoy:\n$links", false)

      case words if words.contains("dance") =>
        log("Matched dance pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        ("I'm more of a thinker than a dancer, but I can certainly get the quiz going!", false)

      case words if words.exists(word => Set("awesome", "cool", "good job", "wow").contains(word)) =>
        log("Matched compliment pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        (s"Thanks ${userName.getOrElse("")}! You're awesome too!", false)

      case words if words.contains("learn") || words.contains("improve") =>
        log("Matched learn/improve pattern", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        ("I keep learning and improving with each conversation! Let's see how well I can guess today!", false)

      case _ =>
        log("No pattern matched, using default response", write = true)
        Thread.sleep(500)
        println("🤖 Typing...")
        Thread.sleep(1000)
        (s"Sorry ${userName.getOrElse("")} but I didn't quite understand that. Can you please clarify?", false)
    }
  }

  def parseInput(userInput: String): List[String] = {
    log(s"Parsing input: '$userInput'", write = true)
    val result = userInput.toLowerCase.split("\\s+").toList
    log(s"Parsed into: $result", write = true)
    result
  }

  def generateResponse(query: String): (String, Boolean) = {
    log(s"generateResponse called with query: '$query'", write = true)
    val parsedInput = parseInput(query)
    val (response, startGame) = handleUserInput(parsedInput)
    log(s"Response generated: '$response', startGame: $startGame", write = true)
    (response, startGame)
  }
}
