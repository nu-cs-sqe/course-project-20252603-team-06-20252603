# BVA analysis for Game

### Method under test: Constructor

- constructor_ZeroPlayers_IllegalArgumentException ( :white_check_mark: )
    - **State of the system**: playerCount = 0
    - **Expected output**: IllegalArgumentException; "Cannot initiate game with less than 2 players"

- constructor_OnePlayer_IllegalArgumentException ( :white_check_mark: )
  - **State of the system**: playerCount = 1
  - **Expected output**: IllegalArgumentException; "Cannot initiate game with less than 2 players"

- constructor_TwoPlayers_CreatesTwoPlayers ( :white_check_mark: )
  - **State of the system**: playerCount = 2
  - **Expected output**: players = [Player, Player], deck = [], currentPlayer = null

- constructor_ThreePlayers_CreatesThreePlayers ( :white_check_mark: )
  - **State of the system**: playerCount = 3
  - **Expected output**: players = [Player, Player, Player], deck = [], currentPlayer = null

- constructor_FourPlayers_CreatesFourPlayers ( :white_check_mark: )
  - **State of the system**: playerCount = 4
  - **Expected output**: players = [Player, Player, Player, Player], deck = [], currentPlayer = null

- constructor_FivePlayers_CreatesFivePlayers ( :white_check_mark: )
  - **State of the system**: playerCount = 5
  - **Expected output**: players = [Player, Player, Player, Player, Player], deck = [], currentPlayer = null

- constructor_SixPlayers_IllegalArgumentException ( :white_check_mark: )
  - **State of the system**: playerCount = 6
  - **Expected output**: IllegalArgumentException; "Cannot initiate game with more than 5 players"

### Method under test: setup()

- setup_TwoPlayers_CorrectCardDistribution ( :white_check_mark: )
  - **State of the system**: playerCount = 2, deck initialized with standard playing cards (excluding kittens and initial defuses)
  - **Expected output**: Each player's hand size = 8 (1 Defuse, 7 random). Remaining deck size decreases by 14. 1 Exploding Kitten inserted into draw pile alongside spare Defuses. currentPlayer = Player 1

- setup_ThreePlayers_CorrectCardDistribution ( :white_check_mark: )
  - **State of the system**: playerCount = 3, deck initialized with standard playing cards (excluding kittens and initial defuses)
  - **Expected output**: Each player's hand size = 8 (1 Defuse, 7 random). Remaining deck size decreases by 21. 2 Exploding Kittens inserted into draw pile alongside spare Defuses. currentPlayer = Player 1

- setup_DeckIsShuffled_OrderIsRandomized 
  - **State of the system**: playerCount = 4, deck initialized
  - **Expected output**: Draw pile and player hands are in a randomized order compared to sequential generation

- setup_FivePlayers_CorrectCardDistribution ( :white_check_mark: )
  - **State of the system**: playerCount = 5, deck initialized with standard playing cards (excluding kittens and initial defuses)
  - **Expected output**: Each player's hand size = 8 (1 Defuse, 7 random). Remaining deck size decreases by 35. 4 Exploding Kittens inserted into draw pile alongside spare Defuses. currentPlayer = Player 1

- setup_InsufficientDeckSize_ThrowsIllegalStateException
  - **State of the system**: playerCount = 5, initial deck = 40 random cards total
  - **Expected output**: IllegalStateException; "Not enough cards in deck to deal starting hands"

### Method under test: runGame()

- runGame_TwoPlayers_OneDiesImmediately_TerminatesAndDeclaresWinner
  - **State of the system**: playerCount = 2, Player 1 draws an Exploding Kitten without a Defuse
  - **Expected output**: Player 1 status = DEAD. Game loop terminates. Player 2 declared winner

- runGame_ThreePlayers_OneDies_LoopContinues
  - **State of the system**: playerCount = 3, Player 1 draws an Exploding Kitten without a Defuse
  - **Expected output**: Player 1 status = DEAD. Game loop continues. Turn advances to Player 2

- runGame_ValidCardPlayed_DelegatesToCorrectController
  - **State of the system**: playerCount = 4, currentPlayer = Player 1, Player 1 plays an Attack card
  - **Expected output**: AttackController is instantiated/called. Player 1's turn ends without drawing a card

- runGame_FivePlayers_OneDies_LoopContinues
  - **State of the system**: playerCount = 5, Player 1 draws an Exploding Kitten without a Defuse
  - **Expected output**: Player 1 status = DEAD. Game loop continues. Turn advances to Player 2

