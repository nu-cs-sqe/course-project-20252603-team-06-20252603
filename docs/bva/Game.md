# BVA analysis for Game

### Method under test: Constructor

- constructor_ZeroPlayers_IllegalArgumentException
    - **State of the system**: playerCount = 0
    - **Expected output**: IllegalArgumentException; "Cannot initiate game with less than 2 players"

- constructor_OnePlayer_IllegalArgumentException
  - **State of the system**: playerCount = 1
  - **Expected output**: IllegalArgumentException; "Cannot initiate game with less than 2 players"

- constructor_TwoPlayers_CreatesTwoPlayers
  - **State of the system**: playerCount = 2
  - **Expected output**: players = [Player, Player], deck = [], currentPlayer = null

- constructor_FivePlayers_CreatesFivePlayers
  - **State of the system**: playerCount = 5
  - **Expected output**: players = [Player, Player, Player, Player, Player], deck = [], currentPlayer = null

- constructor_SixPlayers_CreatesSixPlayers
  - **State of the system**: playerCount = 6
  - **Expected output**: IllegalArgumentException; "Cannot initiate game with more than 6 players"

### Method under test: setup()


