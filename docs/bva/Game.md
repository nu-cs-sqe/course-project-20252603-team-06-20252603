# BVA analysis for Game

### Method under test: Constructor

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
    - 
### Method under test: createGame

- createGame_ZeroPlayers_IllegalArgumentException ( :white_check_mark: )
  - **State of the system**: playerCount = 0
  - **Expected output**: IllegalArgumentException; "Cannot initiate game with less than 2 players"

- createGame_OnePlayer_IllegalArgumentException ( :white_check_mark: )
  - **State of the system**: playerCount = 1
  - **Expected output**: IllegalArgumentException; "Cannot initiate game with less than 2 players"

- createGame_TwoPlayers_CreatesTwoPlayers ( :white_check_mark: )
  - **State of the system**: playerCount = 2
  - **Expected output**: players = [Player, Player], deck = [], currentPlayer = null

- createGame_ThreePlayers_CreatesThreePlayers ( :white_check_mark: )
  - **State of the system**: playerCount = 3
  - **Expected output**: players = [Player, Player, Player], deck = [], currentPlayer = null

- createGame_FourPlayers_CreatesFourPlayers ( :white_check_mark: )
  - **State of the system**: playerCount = 4
  - **Expected output**: players = [Player, Player, Player, Player], deck = [], currentPlayer = null

- createGame_FivePlayers_CreatesFivePlayers ( :white_check_mark: )
  - **State of the system**: playerCount = 5
  - **Expected output**: players = [Player, Player, Player, Player, Player], deck = [], currentPlayer = null

- createGame_SixPlayers_IllegalArgumentException ( :white_check_mark: )
  - **State of the system**: playerCount = 6
  - **Expected output**: IllegalArgumentException; "Cannot initiate game with more than 5 players"

### Method under test: setup()

- setup_TwoPlayers_CorrectCardDistribution_PlentyOfCards ( :white_check_mark: )
    - **State of the system**: playerCount = 2, deck initialized with 100 standard playing cards (excluding kittens and
      initial defuses)
    - **Expected output**: Each player's hand size = 8 (1 Defuse, 7 random). Remaining deck size decreases by 14. 1
      Exploding Kitten inserted into draw pile alongside spare Defuses. currentPlayer = Player 1

- setup_ThreePlayers_CorrectCardDistribution_PlentyOfCards ( :white_check_mark: )
    - **State of the system**: playerCount = 3, deck initialized with 100 standard playing cards (excluding kittens and
      initial defuses)
    - **Expected output**: Each player's hand size = 8 (1 Defuse, 7 random). Remaining deck size decreases by 21. 2
      Exploding Kittens inserted into draw pile alongside spare Defuses. currentPlayer = Player 1

- setup_FourPlayers_CorrectCardDistribution_PlentyOfCards ( :white_check_mark: )
  - **State of the system**: playerCount = 5, deck initialized with 100 standard playing cards (excluding kittens and
    initial defuses)
  - **Expected output**: Each player's hand size = 8 (1 Defuse, 7 random). Remaining deck size decreases by 28. 3
    Exploding Kittens inserted into draw pile alongside spare Defuses. currentPlayer = Player 1

- setup_FivePlayers_CorrectCardDistribution_PlentyOfCards ( :white_check_mark: )
    - **State of the system**: playerCount = 5, deck initialized with 100 standard playing cards (excluding kittens and
      initial defuses)
    - **Expected output**: Each player's hand size = 8 (1 Defuse, 7 random). Remaining deck size decreases by 35. 4
      Exploding Kittens inserted into draw pile alongside spare Defuses. currentPlayer = Player 1

- setup_TwoPlayers_CorrectCardDistribution_JustEnoughCards ( :white_check_mark: )
  - **State of the system**: playerCount = 2, deck initialized with 14 standard playing cards (excluding kittens and
    initial defuses)
  - **Expected output**: Each player's hand size = 8 (1 Defuse, 7 random). Remaining deck size decreases by 14. 1
    Exploding Kitten inserted into draw pile alongside spare Defuses. currentPlayer = Player 1

- setup_ThreePlayers_CorrectCardDistribution_JustEnoughCards ( :white_check_mark: )
  - **State of the system**: playerCount = 3, deck initialized with 21 standard playing cards (excluding kittens and
    initial defuses)
  - **Expected output**: Each player's hand size = 8 (1 Defuse, 7 random). Remaining deck size decreases by 21. 2
    Exploding Kittens inserted into draw pile alongside spare Defuses. currentPlayer = Player 1

- setup_FourPlayers_CorrectCardDistribution_JustEnoughCards ( :white_check_mark: )
  - **State of the system**: playerCount = 5, deck initialized with 28 standard playing cards (excluding kittens and
    initial defuses)
  - **Expected output**: Each player's hand size = 8 (1 Defuse, 7 random). Remaining deck size decreases by 28. 3
    Exploding Kittens inserted into draw pile alongside spare Defuses. currentPlayer = Player 1

- setup_FivePlayers_CorrectCardDistribution_JustEnoughCards ( :white_check_mark: )
  - **State of the system**: playerCount = 5, deck initialized with 35 standard playing cards (excluding kittens and
    initial defuses)
  - **Expected output**: Each player's hand size = 8 (1 Defuse, 7 random). Remaining deck size decreases by 35. 4
    Exploding Kittens inserted into draw pile alongside spare Defuses. currentPlayer = Player 1

- setup_InsufficientDeckSize_ThrowsIllegalStateException ( :white_check_mark: )
    - **State of the system**: playerCount = 5, initial deck = 34 random cards total
    - **Expected output**: IllegalStateException; "Not enough cards in deck to deal starting hands"

### Method under test: runGame()

- runGame_TwoPlayers_FirstPlayerTakesTurn ( :white_check_mark: )
    - **State of the system**: playerCount = 2, Player 1 takes a turn, then Player 2 takes a turn.
        - **Expected output**: Both players still alive.

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

