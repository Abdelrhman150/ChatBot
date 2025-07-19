object Data {
  // === Foreign Actors Dataset ===
  val foreignActors = Map(
    "Leonardo DiCaprio" -> Set("American", "Titanic", "Drama", "Male", "Famous", "Hollywood", "Blonde"),
    "Rami Malek" -> Set("Egyptian", "Mr. Robot", "Action", "Male", "Young", "Famous"),
    "Rowan Atkinson" -> Set("British", "Mr. Bean", "Comedy", "Male", "Funny", "TV Star"),
    "Jean Reno" -> Set("French", "Leon", "Action", "Male", "Serious", "Classic Actor"),
    "Will Smith" -> Set("American", "Men in Black", "Drama", "Action", "Male", "Funny", "Hollywood"),
    "Tom Hanks" -> Set("American", "Forrest Gump", "Drama", "Male", "Famous", "Friendly"),
    "Johnny Depp" -> Set("American", "Pirates", "Fantasy", "Male", "Popular", "Unique"),
    "Brad Pitt" -> Set("American", "Fight Club", "Drama", "Male", "Famous", "Stylish"),
    "Keanu Reeves" -> Set("Canadian", "John Wick", "Action", "Male", "Cool", "Popular"),
    "Robert Downey Jr." -> Set("American", "Iron Man", "Sci-Fi", "Male", "Famous", "Marvel"),
    "Chris Hemsworth" -> Set("Australian", "Thor", "Action", "Male", "Marvel", "Strong"),
    "Ryan Reynolds" -> Set("Canadian", "Deadpool", "Comedy", "Male", "Funny", "Marvel"),
    "Matt Damon" -> Set("American", "The Martian", "Drama", "Male", "Realistic", "Hollywood"),
    "Daniel Radcliffe" -> Set("British", "Harry Potter", "Fantasy", "Male", "Famous", "Young"),
    "Christian Bale" -> Set("British", "Batman", "Drama", "Male", "Serious", "Dark")
  )

  // === Singers Dataset ===
  val singers = Map(
    "Adele" -> Set("British", "Female", "Hello", "English", "Emotional", "Famous"),
    "Amr Diab" -> Set("Egyptian", "Male", "Tamally Maak", "Arabic", "Romantic", "Pop"),
    "The Weekend" -> Set("Canadian", "Male", "Blinding Lights", "English", "Modern", "Pop"),
    "Nancy Ajram" -> Set("Lebanese", "Female", "Ah W Noss", "Arabic", "Famous", "Pop"),
    "Michael Jackson" -> Set("American", "Male", "Thriller", "English", "Legend", "Pop"),
    "Elissa" -> Set("Lebanese", "Female", "Ayshalak", "Arabic", "Romantic", "Famous"),
    "Tamer Hosny" -> Set("Egyptian", "Male", "180 Daraga", "Arabic", "Modern", "Pop"),
    "Shakira" -> Set("Colombian", "Female", "Waka Waka", "English", "Dance", "Famous"),
    "Rihanna" -> Set("Barbadian", "Female", "Diamonds", "English", "Modern", "R&B"),
    "Ed Sheeran" -> Set("British", "Male", "Shape of You", "English", "Pop", "Guitar"),
    "Wael Kfoury" -> Set("Lebanese", "Male", "Omri Kello", "Arabic", "Classic", "Romantic"),
    "Miley Cyrus" -> Set("American", "Female", "Wrecking Ball", "English", "Pop", "Bold"),
    "Hamaki" -> Set("Egyptian", "Male", "Mabrook", "Arabic", "Modern", "Romantic"),
    "Beyoncé" -> Set("American", "Female", "Halo", "English", "Famous", "Powerful Voice"),
    "Asala Nasri" -> Set("Syrian", "Female", "Aktar", "Arabic", "Emotional", "Strong Voice")
  )

  // === Cartoon Characters Dataset ===
  val cartoonCharacters = Map(
    "SpongeBob" -> Set("Yellow", "Funny", "Has Powers", "Human", "Underwater", "Square"),
    "Tom" -> Set("Cat", "Grey", "Silent", "Animal", "Chases Jerry", "Cartoon Classic"),
    "Jerry" -> Set("Mouse", "Brown", "Smart", "Animal", "Escapes Tom", "Cartoon Classic"),
    "Elsa" -> Set("Blond Hair", "Queen", "Has Powers", "Human", "Frozen", "Ice"),
    "Dora" -> Set("Explorer", "Human", "Spanish", "Female", "Backpack", "Child Show"),
    "Homer Simpson" -> Set("Male", "Yellow", "Lazy", "Human", "Cartoon Dad", "Funny"),
    "Bart Simpson" -> Set("Male", "Yellow", "Mischievous", "Human", "Child", "Skateboard"),
    "Bugs Bunny" -> Set("Rabbit", "Smart", "Funny", "Animal", "Carrot", "Classic"),
    "Scooby Doo" -> Set("Dog", "Funny", "Animal", "Solves Mysteries", "Brown", "Cartoon Classic"),
    "Patrick Star" -> Set("Pink", "Dumb", "Funny", "Starfish", "Underwater", "Best Friend"),
    "Mickey Mouse" -> Set("Mouse", "Iconic", "Black", "Red Shorts", "Human-Like", "Classic"),
    "Goofy" -> Set("Dog", "Funny", "Tall", "Hat", "Silly Voice", "Classic"),
    "Timmy Turner" -> Set("Boy", "Fairy Parents", "Magic", "Cap", "Pink Shirt", "Wishes"),
    "Phineas" -> Set("Inventor", "Triangle Head", "Red Hair", "Summer", "Funny", "Smart"),
    "Ferb" -> Set("Green Hair", "Silent", "British", "Inventor", "Smart", "Straight Face")
  )

  // === Anime Characters Dataset ===
  val animeCharacters = Map(
    "Naruto" -> Set("Ninja", "Blond Hair", "Has Powers", "Naruto Universe", "Male", "Headband"),
    "Sasuke" -> Set("Ninja", "Dark Hair", "Sword", "Naruto Universe", "Male", "Revenge"),
    "Goku" -> Set("Fighter", "Transformation", "Dragon Ball", "Male", "Saiyan", "Kamehameha"),
    "Luffy" -> Set("Pirate", "One Piece", "Has Powers", "Male", "Straw Hat", "Funny"),
    "Zoro" -> Set("Sword", "One Piece", "Fighter", "Green Hair", "Silent", "Three Swords"),
    "Ichigo" -> Set("Sword", "Bleach", "Has Powers", "Orange Hair", "Shinigami", "Teen"),
    "Light Yagami" -> Set("Notebook", "Death Note", "Smart", "Male", "Dark", "Strategic"),
    "Levi Ackerman" -> Set("Attack on Titan", "Soldier", "Short", "Strong", "Silent", "Human"),
    "Gon" -> Set("Hunter", "Child", "Green Outfit", "Energetic", "Strong", "Friendly"),
    "Killua" -> Set("White Hair", "Assassin", "Child", "Fast", "Smart", "Lightning"),
    "Edward Elric" -> Set("Alchemy", "Metal Arm", "Brother", "Smart", "Short", "Fullmetal"),
    "Eren Yeager" -> Set("Attack on Titan", "Titan Shifter", "Angry", "Revenge", "Dark", "Strong"),
    "Tanjiro" -> Set("Demon Slayer", "Kind", "Strong", "Sister", "Water Style", "Scar"),
    "Nezuko" -> Set("Demon", "Kind", "Female", "Bamboo Muzzle", "Demon Slayer", "Cute"),
    "Inuyasha" -> Set("Half-Demon", "White Hair", "Sword", "Japan", "Feudal Era", "Romantic")
  )

  // === Arab Actors Dataset ===
  val arabActors = Map(
    "Ahmed Helmy" -> Set("Egyptian", "Comedy", "Male", "Zarf Tarek", "Popular", "Modern" ),
    "Mohamed Ramadan" -> Set("Egyptian", "Action", "Male", "Singer", "El Ostoura", "Popular" ),
    "Adel Imam" -> Set("Egyptian", "Comedy", "Male", "Legend", "Theater", "Movies", "El Zaeem"),
    "Ahmed Ezz" -> Set("Egyptian", "Action", "Male", "Stylish", "Modern", "Cinema", "Welad Rizk"),
    "Karim Abdel Aziz" -> Set("Egyptian", "Drama", "Male", "Funny", "Romantic", "Cinema", "The Blue Elephant"),
    "Tim Hassan" -> Set("Syrian", "Drama", "Male", "Series", "Actor", "Romantic", "Al Hayba"),
    "Kosai Khauli" -> Set("Syrian", "Drama", "Male", "TV", "Modern", "Handsome", "2020"),
    "Tamer Hosny" -> Set("Egyptian", "Romantic", "Male", "Singer", "Omar & Salma", "Modern"),
    "Youssef El Sherif" -> Set("Egyptian", "Sci-Fi", "Male", "Actor", "Action", "Unique Roles", "The End"),
    "Mohamed Henedy" -> Set("Egyptian", "Comedy", "Male", "Short", "Voice Acting", "Theater", "Sa'eedi in the American University"),
    "Hend Sabry" -> Set("Tunisian", "Drama", "Female", "Movies", "TV", "Serious Roles", "Ayza Atgawez"),
    "Menna Shalaby" -> Set("Egyptian", "Drama", "Female", "TV", "Cinema", "Awards", "Fi Kol Osbou3 Youm Gom3a"),
    "Donia Samir Ghanem" -> Set("Egyptian", "Comedy", "Female", "Singer", "TV", "Popular", "Lahfa"),
    "Yasmine Sabri" -> Set("Egyptian", "Drama", "Female", "Fashion", "Cinema", "Social Media", "Hekayet Banat"),
    "Amr Youssef" -> Set("Egyptian", "Action", "Male", "Series", "Drama", "Modern", "Tayea")
  )

  // === Football Players Dataset ===
  val footballPlayers = Map(
    "Cristiano Ronaldo" -> Set("Portuguese", "Ballon d'Or", "Top Scorer", "AlNassr", "Male", "Right Footed"),
    "Lionel Messi" -> Set("Argentinian", "Ballon d'Or", "Miami", "Playmaker", "Male", "Left Footed"),
    "Mohamed Salah" -> Set("Egyptian", "Left Footed", "Liverpool", "Fast", "Male", "Right Wing"),
    "Mbappé" -> Set("French", "Fast", "PSG", "Striker", "Young", "Male"),
    "Neymar" -> Set("Brazilian", "Dribbler", "PSG", "Left Wing", "Male", "Skilled"),
    "Karim Benzema" -> Set("French", "Al Ittihad", "Ballon d'Or", "Striker", "Experienced", "Male"),
    "Luka Modric" -> Set("Croatian", "Midfielder", "Real Madrid", "Ballon d'Or", "Male", "Playmaker"),
    "Erling Haaland" -> Set("Norwegian", "Manchester City", "Striker", "Tall", "Young", "Goal Scorer"),
    "Robert Lewandowski" -> Set("Polish", "Barcelona", "Striker", "Goal Scorer", "Male", "Experienced"),
    "Zlatan Ibrahimovic" -> Set("Swedish", "Tall", "Veteran", "Striker", "Charismatic", "Male"),
    "Harry Kane" -> Set("English", "Striker", "Bayern Munich", "Captain", "Right Footed", "Male"),
    "Sadio Mané" -> Set("Senegalese", "Fast", "Left Wing", "Al Nassr", "Muslim", "Male"),
    "Kevin De Bruyne" -> Set("Belgian", "Playmaker", "Manchester City", "Midfielder", "Male", "Visionary"),
    "Vinicius Jr." -> Set("Brazilian", "Left Wing", "Fast", "Real Madrid", "Young", "Skilled"),
    "Riyad Mahrez" -> Set("Algerian", "Left Footed", "Right Wing", "Fast", "Al Ahli", "Male")
  )
  // === Instructors Dataset ===
  val instructors = Map(
    "Nourhan" -> Set("in the room", "Teaching Assistant", "Scala", "Advanced Programming", "Supportive", "Technical"),
    "Basma" -> Set("in the room", "Professor", "Scala", "Advanced Programming", "Experienced", "Analytical")
  )

  val scientists = Map(
    "Albert Einstein" -> Set("German", "Physics", "Theory of Relativity", "Nobel Prize", "Genius", "1905"),
    "Isaac Newton" -> Set("British", "Mathematics", "Gravity", "Laws of Motion", "Apple", "1687"),
    "Marie Curie" -> Set("Polish", "Chemistry", "Radioactivity", "Nobel Prize", "Female Scientist", "1898"),
    "Nikola Tesla" -> Set("Serbian", "Engineering", "AC Current", "Inventor", "Genius", "1891"),
    "Stephen Hawking" -> Set("British", "Cosmology", "Black Holes", "Wheelchair", "Bestseller Author", "1988"),
    "Galileo Galilei" -> Set("Italian", "Astronomy", "Telescope", "Earth Rotation", "Trial", "1609"),
    "Charles Darwin" -> Set("British", "Biology", "Evolution", "Natural Selection", "Voyage of Beagle", "1859"),
    "Alan Turing" -> Set("British", "Computer Science", "Turing Machine", "WWII Codebreaker", "AI Pioneer", "1936"),
    "Rosalind Franklin" -> Set("British", "Chemistry", "DNA Structure", "X-ray Crystallography", "Female Scientist", "1952"),
    "Richard Feynman" -> Set("American", "Physics", "Quantum Electrodynamics", "Lectures", "Famous Explainer", "1965"),
    "Niels Bohr" -> Set("Danish", "Physics", "Atomic Structure", "Nobel Prize", "Complementarity", "1913"),
    "Gregor Mendel" -> Set("Austrian", "Biology", "Genetics", "Pea Plants", "Inheritance Laws", "1866"),
    "Ada Lovelace" -> Set("British", "Mathematics", "First Programmer", "Analytical Engine", "Visionary", "1842"),
    "Carl Sagan" -> Set("American", "Astronomy", "Cosmos Series", "Public Educator", "SETI", "1980"),
    "James Clerk Maxwell" -> Set("Scottish", "Physics", "Electromagnetism", "Color Photography", "Equations", "1865"),
    "Jane Goodall" -> Set("British", "Primatology", "Chimpanzees", "Conservation", "Field Research", "1960"),
    "Dmitri Mendeleev" -> Set("Russian", "Chemistry", "Periodic Table", "Prediction", "Elements", "1869"),
    "Rachel Carson" -> Set("American", "Biology", "Environmentalism", "Silent Spring", "Activism", "1962"),
    "Barbara McClintock" -> Set("American", "Genetics", "Transposons", "Nobel Prize", "Cytogenetics", "1951"),
    "Chien-Shiung Wu" -> Set("Chinese-American", "Physics", "Beta Decay", "Manhattan Project", "Nobel Overlooked", "1957")
  )


}