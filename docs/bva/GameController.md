### Method under test: `GameController(Game game)`
- **TC1: a valid Game is input into the controller** (  )
    - **State of the system**: an initialized game object with more than 0 alive players is passed into the constructor
    - **Expected output**: GameController is successfully initialized with the game input as this.game `

## caveat: do we need to check the case where we pass something null into GameController? check w/ prof

### Method under test: `setCurrentPlayerIndex(int current_player_index)`
- **TC5: Invalid index is passed in (just under the minimum boundary, so -1) ( x )
  - **State of the system**: alivePlayer list is initialized
  - **Expected output**: IllegalArgumentException

- **TC6: Invalid index is passed in (just above the maximum boundary, so alivePlayers.size()) (x)
  - **State of the system**: alivePlayer list is initialized
  - **Expected output**: IllegalArgumentException

- **TC7: index at minimum possible boundary, 0 ( x )
  - **State of the system**: alivePlayer list is initialized
  - **Expected output**: this.currentPlayerIndex is updated to current_player_index, and this.currentPlayer is updated to alivePlayers[current_player_index] (aka the first player in the list)

- **TC8: index at maximum possible boundary, alivePlayers.size() - 1 ( x )
  - **State of the system**: alivePlayer list is initialized
  - **Expected output**: this.currentPlayerIndex is updated to current_player_index, and this.currentPlayer is updated to alivePlayers[current_player_index] (aka the last player in the list)

