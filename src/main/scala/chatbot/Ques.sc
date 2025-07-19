object Questions {
  // === Questions Maps for Each Category ===

  // Questions for Foreign Actors (grouped)
  val foreignActorQuestions = List(
    // === Nationality ===
    List(
      "Is he American?" -> "American",
      "Is he British?" -> "British",
      "Is he Canadian?" -> "Canadian",
      "Is he French?" -> "French",
      "Is he Australian?" -> "Australian",
      "Is he Egyptian?" -> "Egyptian"
    ),

    // === Films ===
    List(
      "Did he act in Titanic?" -> "Titanic",
      "Did he act in Mr. Robot?" -> "Mr. Robot",
      "Did he act in Mr. Bean?" -> "Mr. Bean",
      "Did he act in Leon?" -> "Leon",
      "Did he act in Men in Black?" -> "Men in Black",
      "Did he act in Forrest Gump?" -> "Forrest Gump",
      "Did he act in Pirates?" -> "Pirates",
      "Did he act in Fight Club?" -> "Fight Club",
      "Did he act in John Wick?" -> "John Wick",
      "Did he act in Iron Man?" -> "Iron Man",
      "Did he act in Thor?" -> "Thor",
      "Did he act in Deadpool?" -> "Deadpool",
      "Did he act in The Martian?" -> "The Martian",
      "Did he act in Harry Potter?" -> "Harry Potter",
      "Did he act in Batman?" -> "Batman"
    ),

    // === Personality/Traits ===
    List(
      "Is he famous?" -> "Famous",
      "Is he friendly?" -> "Friendly",
      "Is he serious in roles?" -> "Serious",
      "Is he cool?" -> "Cool",
      "Is he dark?" -> "Dark",
      "Is he funny?" -> "Funny",
      "Is he realistic?" -> "Realistic",
      "Is he stylish?" -> "Stylish",
      "Is he young?" -> "Young",
      "Is he unique?" -> "Unique",
      "Is he popular?" -> "Popular"
    ),

    // === Genre ===
    List(
      "Did he act in action movies?" -> "Action",
      "Did he act in drama movies?" -> "Drama",
      "Did he act in fantasy movies?" -> "Fantasy",
      "Did he act in sci-fi movies?" -> "Sci-Fi",
      "Is he known for comedy?" -> "Comedy"
    ),

    // === Industry Identity ===
    List(
      "Is he in Marvel movies?" -> "Marvel",
      "Is he a TV Star?" -> "TV Star",
      "Is he a Classic Actor?" -> "Classic Actor",
      "Is he associated with Hollywood?" -> "Hollywood"
    ),

    // === Appearance and Demographics ===
    List(
      "Is he a male actor?" -> "Male",
      "Is he strong?" -> "Strong",
      "Is he blonde?" -> "Blonde"
    )
  )

  // Questions for Singers (grouped)
  val singerQuestions = List(
    // === Nationality ===
    List(
      "Is the singer American?" -> "American",
      "Is the singer British?" -> "British",
      "Is the singer Egyptian?" -> "Egyptian",
      "Is the singer Lebanese?" -> "Lebanese",
      "Is the singer Canadian?" -> "Canadian",
      "Is the singer Syrian?" -> "Syrian",
      "Is the singer Colombian?" -> "Colombian",
      "Is the singer Barbadian?" -> "Barbadian"
    ),

    // === Famous Songs ===
    List(
      "Did the singer perform 'Hello'?" -> "Hello",
      "Did the singer perform 'Tamally Maak'?" -> "Tamally Maak",
      "Did the singer perform 'Blinding Lights'?" -> "Blinding Lights",
      "Did the singer perform 'Ah W Noss'?" -> "Ah W Noss",
      "Did the singer perform 'Thriller'?" -> "Thriller",
      "Did the singer perform 'Ayshalak'?" -> "Ayshalak",
      "Did the singer perform '180 Daraga'?" -> "180 Daraga",
      "Did the singer perform 'Waka Waka'?" -> "Waka Waka",
      "Did the singer perform 'Diamonds'?" -> "Diamonds",
      "Did the singer perform 'Shape of You'?" -> "Shape of You",
      "Did the singer perform 'Omri Kello'?" -> "Omri Kello",
      "Did the singer perform 'Wrecking Ball'?" -> "Wrecking Ball",
      "Did the singer perform 'Mabrook'?" -> "Mabrook",
      "Did the singer perform 'Halo'?" -> "Halo",
      "Did the singer perform 'Aktar'?" -> "Aktar"
    ),

    // === Language & Style ===
    List(
      "Does the singer sing in English?" -> "English",
      "Does the singer sing in Arabic?" -> "Arabic"
    ),

    // === Gender ===
    List(
      "Is the singer male?" -> "Male",
      "Is the singer female?" -> "Female"
    ),

    // === Personality/Traits ===
    List(
      "Is the singer famous?" -> "Famous",
      "Is the singer emotional?" -> "Emotional",
      "Is the singer romantic?" -> "Romantic",
      "Is the singer modern?" -> "Modern",
      "Is the singer bold?" -> "Bold",
      "Is the singer a legend?" -> "Legend"
    ),

    // === Genre/Identity ===
    List(
      "Does the singer perform Pop music?" -> "Pop",
      "Does the singer perform R&B music?" -> "R&B",
      "Does the singer perform Dance music?" -> "Dance",
      "Does the singer perform Classic music?" -> "Classic",
      "Does the singer play guitar?" -> "Guitar",
      "Does the singer have a powerful voice?" -> "Powerful Voice",
      "Does the singer have a strong voice?" -> "Strong Voice"
    )
  )

  // === Questions for Cartoon Characters ===
  val cartoonQuestions = List(
    // === Species / Type ===
    List(
      "Is the character an animal?" -> "Animal",
      "Is the character human?" -> "Human",
      "Is the character a starfish?" -> "Starfish",
      "Is the character a mouse?" -> "Mouse",
      "Is the character a dog?" -> "Dog",
      "Is the character a cat?" -> "Cat",
      "Is the character a rabbit?" -> "Rabbit",
      "Is the character a boy?" -> "Boy"
    ),

    // === Appearance / Color ===
    List(
      "Is the character yellow?" -> "Yellow",
      "Is the character pink?" -> "Pink",
      "Is the character brown?" -> "Brown",
      "Is the character grey?" -> "Grey",
      "Is the character black?" -> "Black",
      "Does the character have blond hair?" -> "Blond Hair",
      "Does the character have green hair?" -> "Green Hair",
      "Does the character have red hair?" -> "Red Hair"
    ),

    // === Powers / Abilities ===
    List(
      "Does the character have powers?" -> "Has Powers",
      "Does the character use magic?" -> "Magic",
      "Is the character smart?" -> "Smart",
      "Is the character an inventor?" -> "Inventor"
    ),

    // === Behavior / Personality ===
    List(
      "Is the character funny?" -> "Funny",
      "Is the character lazy?" -> "Lazy",
      "Is the character silent?" -> "Silent",
      "Is the character mischievous?" -> "Mischievous",
      "Is the character dumb?" -> "Dumb",
      "Is the character iconic?" -> "Iconic"
    ),

    // === Items / Props ===
    List(
      "Does the character have a skateboard?" -> "Skateboard",
      "Does the character have a cap?" -> "Cap",
      "Does the character have a carrot?" -> "Carrot",
      "Does the character have a hat?" -> "Hat",
      "Does the character have a backpack?" -> "Backpack",
      "Does the character wear red shorts?" -> "Red Shorts",
      "Does the character have fairy parents?" -> "Fairy Parents",
      "Does the character have a pink shirt?" -> "Pink Shirt"
    ),

    // === Environment / Setting ===
    List(
      "Is the character underwater?" -> "Underwater",
      "Is the character from Frozen?" -> "Frozen",
      "Is the character from a child show?" -> "Child Show",
      "Is the character in the ice world?" -> "Ice",
      "Is the character from summer scenes?" -> "Summer"
    ),

    // === Role / Identity ===
    List(
      "Is the character a queen?" -> "Queen",
      "Is the character an explorer?" -> "Explorer",
      "Is the character a cartoon dad?" -> "Cartoon Dad",
      "Is the character a best friend?" -> "Best Friend",
      "Is the character part of a classic cartoon?" -> "Cartoon Classic",
      "Is the character from classic era?" -> "Classic",
      "Is the character solving mysteries?" -> "Solves Mysteries",
      "Is the character known for chasing Jerry?" -> "Chases Jerry",
      "Is the character known for escaping Tom?" -> "Escapes Tom",
      "Does the character make wishes?" -> "Wishes",
      "Is the character known for a silly voice?" -> "Silly Voice",
      "Is the character known for a triangle head?" -> "Triangle Head",
      "Does the character have a straight face?" -> "Straight Face",
      "Is the character human-like?" -> "Human-Like",
      "Is the character square-shaped?" -> "Square"
    )
  )

  // === Questions for Anime Characters ===
  val animeQuestions = List(
    // === Universe / Origin ===
    List(
      "Is the character from Naruto?" -> "Naruto Universe",
      "Is the character from One Piece?" -> "One Piece",
      "Is the character from Bleach?" -> "Bleach",
      "Is the character from Death Note?" -> "Death Note",
      "Is the character from Dragon Ball?" -> "Dragon Ball",
      "Is the character from Attack on Titan?" -> "Attack on Titan",
      "Is the character from Demon Slayer?" -> "Demon Slayer",
      "Is the character from Japan?" -> "Japan",
      "Is the character from the Feudal Era?" -> "Feudal Era"
    ),

    // === Powers & Abilities ===
    List(
      "Does the character have powers?" -> "Has Powers",
      "Can the character transform?" -> "Transformation",
      "Is the character a Titan Shifter?" -> "Titan Shifter",
      "Does the character use a notebook?" -> "Notebook",
      "Does the character practice alchemy?" -> "Alchemy",
      "Is the character strong?" -> "Strong",
      "Is the character smart?" -> "Smart",
      "Is the character fast?" -> "Fast",
      "Does the character use water style?" -> "Water Style",
      "Can the character use lightning?" -> "Lightning",
      "Does the character have a scar?" -> "Scar",
      "Does the character wield a sword?" -> "Sword",
      "Does the character use three swords?" -> "Three Swords",
      "Does the character have a metal arm?" -> "Metal Arm"
    ),

    // === Personality / Traits ===
    List(
      "Is the character funny?" -> "Funny",
      "Is the character silent?" -> "Silent",
      "Is the character friendly?" -> "Friendly",
      "Is the character angry?" -> "Angry",
      "Is the character kind?" -> "Kind",
      "Is the character dark?" -> "Dark",
      "Is the character strategic?" -> "Strategic",
      "Is the character romantic?" -> "Romantic",
      "Is the character energetic?" -> "Energetic",
      "Is the character cute?" -> "Cute",
      "Is the character vengeful?" -> "Revenge"
    ),

    // === Role / Identity ===
    List(
      "Is the character a ninja?" -> "Ninja",
      "Is the character a pirate?" -> "Pirate",
      "Is the character a fighter?" -> "Fighter",
      "Is the character a hunter?" -> "Hunter",
      "Is the character an assassin?" -> "Assassin",
      "Is the character a soldier?" -> "Soldier",
      "Is the character a demon?" -> "Demon",
      "Is the character a half-demon?" -> "Half-Demon",
      "Is the character a Shinigami?" -> "Shinigami",
      "Is the character a Saiyan?" -> "Saiyan",
      "Is the character a brother?" -> "Brother",
      "Is the character a sister?" -> "Sister",
      "Is the character a teen?" -> "Teen"
    ),

    // === Appearance & Props ===
    List(
      "Does the character have blond hair?" -> "Blond Hair",
      "Does the character have dark hair?" -> "Dark Hair",
      "Does the character have green hair?" -> "Green Hair",
      "Does the character have orange hair?" -> "Orange Hair",
      "Does the character have white hair?" -> "White Hair",
      "Does the character wear a headband?" -> "Headband",
      "Does the character wear a straw hat?" -> "Straw Hat",
      "Does the character wear a bamboo muzzle?" -> "Bamboo Muzzle",
      "Does the character have a green outfit?" -> "Green Outfit"
    ),

    // === Signature Skills ===
    List(
      "Does the character use Kamehameha?" -> "Kamehameha",
      "Does the character use a strategic notebook?" -> "Notebook",
      "Is the character known as Fullmetal?" -> "Fullmetal"
    )
  )


  // === Questions for Arab Actors ===
  val arabActorQuestions = List(
    // === Nationality ===
    List(
      "Is the actor Egyptian?" -> "Egyptian",
      "Is the actor Syrian?" -> "Syrian",
      "Is the actor Tunisian?" -> "Tunisian"
    ),

    // === Gender ===
    List(
      "Is the actor male?" -> "Male",
      "Is the actor female?" -> "Female"
    ),

    // === Field / Role ===
    List(
      "Is the actor a comedian?" -> "Comedy",
      "Is the actor known for action roles?" -> "Action",
      "Is the actor known for drama roles?" -> "Drama",
      "Is the actor romantic?" -> "Romantic",
      "Is the actor known for sci-fi?" -> "Sci-Fi"
    ),

    // === Career Identity ===
    List(
      "Is the actor a singer?" -> "Singer",
      "Is the actor also an actor?" -> "Actor Also",
      "Is the actor known for voice acting?" -> "Voice Acting",
      "Is the actor involved in theater?" -> "Theater",
      "Is the actor known for cinema roles?" -> "Cinema",
      "Is the actor famous for TV series?" -> "TV",
      "Is the actor active in modern media?" -> "Modern",
      "Is the actor active on social media?" -> "Social Media",
      "Is the actor known for awards?" -> "Awards"
    ),

    // === Popularity & Personality ===
    List(
      "Is the actor popular?" -> "Popular",
      "Is the actor stylish?" -> "Stylish",
      "Is the actor controversial?" -> "Controversial",
      "Is the actor handsome?" -> "Handsome",
      "Is the actor short?" -> "Short",
      "Does the actor play unique roles?" -> "Unique Roles",
      "Is the actor serious in roles?" -> "Serious Roles",
      "Is the actor funny?" -> "Funny",
      "Is the actor a legend?" -> "Legend"
    ),

    // === Famous Works ===
    List(
      "Did the actor star in 'Zarf Tarek'?" -> "Zarf Tarek",
      "Did the actor star in 'El Ostoura'?" -> "El Ostoura",
      "Did the actor star in 'El Zaeem'?" -> "El Zaeem",
      "Did the actor star in 'Welad Rizk'?" -> "Welad Rizk",
      "Did the actor star in 'The Blue Elephant'?" -> "The Blue Elephant",
      "Did the actor star in 'Al Hayba'?" -> "Al Hayba",
      "Did the actor star in '2020'?" -> "2020",
      "Did the actor star in 'Omar & Salma'?" -> "Omar & Salma",
      "Did the actor star in 'The End'?" -> "The End",
      "Did the actor star in 'Sa'eedi in the American University'?" -> "Sa'eedi in the American University",
      "Did the actor star in 'Ayza Atgawez'?" -> "Ayza Atgawez",
      "Did the actor star in 'Fi Kol Osbou3 Youm Gom3a'?" -> "Fi Kol Osbou3 Youm Gom3a",
      "Did the actor star in 'Lahfa'?" -> "Lahfa",
      "Did the actor star in 'Hekayet Banat'?" -> "Hekayet Banat",
      "Did the actor star in 'Tayea'?" -> "Tayea"
    )
  )


  // === Questions for Football Players ===
  val footballQuestions = List(
    // === Nationality ===
    List(
      "Is the player Portuguese?" -> "Portuguese",
      "Is the player Argentinian?" -> "Argentinian",
      "Is the player Egyptian?" -> "Egyptian",
      "Is the player French?" -> "French",
      "Is the player Brazilian?" -> "Brazilian",
      "Is the player Croatian?" -> "Croatian",
      "Is the player Norwegian?" -> "Norwegian",
      "Is the player Polish?" -> "Polish",
      "Is the player Swedish?" -> "Swedish",
      "Is the player English?" -> "English",
      "Is the player Senegalese?" -> "Senegalese",
      "Is the player Belgian?" -> "Belgian",
      "Is the player Algerian?" -> "Algerian"
    ),

    // === Club / League ===
    List(
      "Does the player play for AlNassr?" -> "AlNassr",
      "Does the player play for Miami?" -> "Miami",
      "Does the player play for Liverpool?" -> "Liverpool",
      "Does the player play for PSG?" -> "PSG",
      "Does the player play for Al Ittihad?" -> "Al Ittihad",
      "Does the player play for Real Madrid?" -> "Real Madrid",
      "Does the player play for Manchester City?" -> "Manchester City",
      "Does the player play for Barcelona?" -> "Barcelona",
      "Does the player play for Bayern Munich?" -> "Bayern Munich",
      "Does the player play for Al Ahli?" -> "Al Ahli"
    ),

    // === Position / Style ===
    List(
      "Is the player a striker?" -> "Striker",
      "Is the player a playmaker?" -> "Playmaker",
      "Is the player a winger?" -> "Right Wing",
      "Is the player a left winger?" -> "Left Wing",
      "Is the player a midfielder?" -> "Midfielder",
      "Is the player fast?" -> "Fast",
      "Is the player tall?" -> "Tall",
      "Is the player experienced?" -> "Experienced",
      "Is the player young?" -> "Young",
      "Is the player a captain?" -> "Captain",
      "Is the player a top scorer?" -> "Top Scorer",
      "Is the player a goal scorer?" -> "Goal Scorer",
      "Is the player left footed?" -> "Left Footed",
      "Is the player right footed?" -> "Right Footed"
    ),

    // === Traits / Awards ===
    List(
      "Has the player won the Ballon d'Or?" -> "Ballon d'Or",
      "Is the player charismatic?" -> "Charismatic",
      "Is the player skilled?" -> "Skilled",
      "Is the player visionary?" -> "Visionary",
      "Is the player Muslim?" -> "Muslim",
      "Is the player male?" -> "Male",
      "Is the player a veteran?" -> "Veteran"
    )
  )
  // === Questions for Instructors ===
  val instructorQuestions = List(
    // === Presence & Role ===
    List(
      "Is the instructor in the room?" -> "in the room",
      "Is the instructor a Teaching Assistant?" -> "Teaching Assistant",
      "Is the instructor a Professor?" -> "Professor"
    ),

    // === Subjects Taught ===
    List(
      "Does the instructor teach Scala?" -> "Scala",
      "Does the instructor teach Advanced Programming?" -> "Advanced Programming"
    ),

    // === Personality / Skills ===
    List(
      "Is the instructor supportive?" -> "Supportive",
      "Is the instructor technical?" -> "Technical",
      "Is the instructor experienced?" -> "Experienced",
      "Is the instructor analytical?" -> "Analytical"
    )
  )
  val scientistQuestions = List(
    // === Nationality ===
    List(
      "Is the scientist German?" -> "German",
      "Is the scientist British?" -> "British",
      "Is the scientist Polish?" -> "Polish",
      "Is the scientist Serbian?" -> "Serbian",
      "Is the scientist Italian?" -> "Italian",
      "Is the scientist American?" -> "American",
      "Is the scientist Danish?" -> "Danish",
      "Is the scientist Austrian?" -> "Austrian",
      "Is the scientist Scottish?" -> "Scottish",
      "Is the scientist Russian?" -> "Russian",
      "Is the scientist Chinese-American?" -> "Chinese-American"
    ),

    // === Field of Study ===
    List(
      "Did the scientist work in Physics?" -> "Physics",
      "Did the scientist work in Mathematics?" -> "Mathematics",
      "Did the scientist work in Chemistry?" -> "Chemistry",
      "Did the scientist work in Biology?" -> "Biology",
      "Did the scientist work in Engineering?" -> "Engineering",
      "Did the scientist work in Cosmology?" -> "Cosmology",
      "Did the scientist work in Computer Science?" -> "Computer Science",
      "Did the scientist work in Primatology?" -> "Primatology",
      "Did the scientist work in Astronomy?" -> "Astronomy",
      "Did the scientist work in Genetics?" -> "Genetics"
    ),

    // === Achievements ===
    List(
      "Did the scientist win a Nobel Prize?" -> "Nobel Prize",
      "Did the scientist propose the Theory of Relativity?" -> "Theory of Relativity",
      "Did the scientist create the Periodic Table?" -> "Periodic Table",
      "Did the scientist propose Evolution?" -> "Evolution",
      "Did the scientist decode DNA structure?" -> "DNA Structure",
      "Did the scientist discover Transposons?" -> "Transposons",
      "Did the scientist invent the Analytical Engine?" -> "Analytical Engine",
      "Did the scientist explore Beta Decay?" -> "Beta Decay"
    ),

    // === Traits & Recognition ===
    List(
      "Was the scientist a genius?" -> "Genius",
      "Was the scientist a visionary?" -> "Visionary",
      "Was the scientist a public educator?" -> "Public Educator",
      "Was the scientist female?" -> "Female Scientist",
      "Was the scientist involved in a famous trial?" -> "Trial",
      "Was the scientist overlooked for a Nobel Prize?" -> "Nobel Overlooked",
      "Was the scientist a bestseller author?" -> "Bestseller Author",
      "Was the scientist known for activism?" -> "Activism"
    ),

    // === Concepts & Keywords ===
    List(
      "Is the scientist associated with Gravity?" -> "Gravity",
      "Is the scientist associated with Black Holes?" -> "Black Holes",
      "Is the scientist associated with Electromagnetism?" -> "Electromagnetism",
      "Is the scientist associated with AC Current?" -> "AC Current",
      "Is the scientist associated with Natural Selection?" -> "Natural Selection",
      "Is the scientist associated with AI?" -> "AI Pioneer",
      "Is the scientist known for Color Photography?" -> "Color Photography",
      "Is the scientist associated with Environmentalism?" -> "Environmentalism"
    )
  )
}