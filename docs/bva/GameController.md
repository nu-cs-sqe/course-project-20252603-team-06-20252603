### Method under test: `GameController(Game game)`
- **TC1: a valid Game is input into the controller** ( ☑️ )
    - **State of the system**: an initialized game object with more than 0 alive players is passed into the constructor
    - **Expected output**: GameController is successfully initialized with the game input as this.game `

### Method under test: `setCurrentPlayerIndex(int current_player_index)`
- **TC2: Invalid index is passed in (just under the minimum boundary, so -1) ( ☑️ )
  - **State of the system**: alivePlayer list is initialized
  - **Expected output**: IllegalArgumentException

- **TC3: Invalid index is passed in (just above the maximum boundary, so alivePlayers.size()) (☑️)
  - **State of the system**: alivePlayer list is initialized
  - **Expected output**: IllegalArgumentException

- **TC4: index at minimum possible boundary, 0 ( ☑️ )
  - **State of the system**: alivePlayer list is initialized
  - **Expected output**: this.currentPlayerIndex is updated to current_player_index

- **TC5: index at maximum possible boundary, alivePlayers.size() - 1 (☑️ )
  - **State of the system**: alivePlayer list is initialized
  - **Expected output**: this.currentPlayerIndex is updated to current_player_index

### Method under test: `setNextPlayerIndex(int newNextPlayerIndex)`
- **TC6: set next player index to invalid negative index** ( :white-check-mark: )
  - **State of the system**: `game.alivePlayers.size() = 5`, `this.nextPlayerIndex = 0`, `newNextPlayerIndex = -1`
  - **Expected output**: `IllegalArgumentException` ("invalid next index")

- **TC7: set next player index to lower bound of list** ( :white-check-mark: )
  - **State of the system**: `game.alivePlayers.size() = 5`, `this.nextPlayerIndex = 3`, `newNextPlayerIndex = 0`
  - **Expected output**: `this.nextPlayerIndex` is updated to `0`

- **TC8: set next player index to middle of list** ( :white-check-mark: )
  - **State of the system**: `game.alivePlayers.size() = 5`, `this.nextPlayerIndex = 0`, `newNextPlayerIndex = 2`
  - **Expected output**: `this.nextPlayerIndex` is updated to `2`

- **TC9: set next player index to upper bound of list** ( :white-check-mark: )
  - **State of the system**: `game.alivePlayers.size() = 5`, `this.nextPlayerIndex = 2`, `newNextPlayerIndex = 4`
  - **Expected output**: `this.nextPlayerIndex` is updated to `4`

- **TC10: set next player index to illegal index above upper bound of list** ( :white-check-mark: )
  - **State of the system**: `game.alivePlayers.size() = 5`, `this.nextPlayerIndex = 4`, `newNextPlayerIndex = 5`
  - **Expected output**: `IllegalArgumentException` ("invalid next index")

### Method under test: `setPlayerOrder(List<Player> playerOrder)`
- **TC11: set order to an illegally empty list** ( :white-check-mark: )
  - **State of the system**: `game.alivePlayers.size() = 5`, `playerOrder.size() = 0`
  - **Expected output**: `IllegalArgumentException` ("list size doesn’t match alivePlayer")

- **TC12: set order to a list matching size of alivePlayers (Lower Bound Size 2)** ( x )
  - **State of the system**: `game.alivePlayers.size() = 2`, `playerOrder.size() = 2`
  - **Expected output**: `game.alivePlayers` is updated to match the exact order of `playerOrder`

- **TC13: set order to a list shorter than alivePlayers** ( x )
  - **State of the system**: `game.alivePlayers.size() = 4`, `playerOrder.size() = 3`
  - **Expected output**: `IllegalArgumentException` ("list size doesn’t match alivePlayer")

- **TC14: set order to a list matching size of alivePlayers (Upper Bound Size 5)** ( x )
  - **State of the system**: `game.alivePlayers.size() = 5`, `playerOrder.size() = 5`
  - **Expected output**: `game.alivePlayers` is updated to match the exact order of `playerOrder`

- **TC15: set order to a list longer than alivePlayers** ( x )
  - **State of the system**: `game.alivePlayers.size() = 2`, `playerOrder.size() = 4`
  - **Expected output**: `IllegalArgumentException` ("list size doesn’t match alivePlayer")

### Method under test: `getControllerType(Card card)`
- **TC: invalid test type** ( :white-check-mark: )
  - **State of the system**: `card = TEST_TYPE`
  - **Expected output**: IllegalArgumentException, "invalid test type"

- **TC: attack**
  - **State of the system**: `card = ATTACK`
  - **Expected output**: attackController

- **TC: defuse**
  - **State of the system**: `card = DEFUSE`
  - **Expected output**: defuseController

- **TC: see the future**
  - **State of the system**: `card = SEE_THE_FUTURE`
  - **Expected output**: seeTheFutureController

- **TC: shuffle**
  - **State of the system**: `card = SHUFFLE`
  - **Expected output**: shuffleController

- **TC: skip**
  - **State of the system**: `card = SKIP`
  - **Expected output**: skipController

- **TC: nope**
  - **State of the system**: `card = NOPE`
  - **Expected output**: nopeController

- **TC: draw from bottom**
  - **State of the system**: `card = DRAW_FROM_BOTTOM`
  - **Expected output**: drawFromBottomController

- **TC: cat cards**
  - **State of the system**: `card = CAT_CARD_1`
  - **Expected output**: catCardController

### Method under test: `isTargetValid()`
- **TC: CatCard_ValidTarget_ReturnsTrue** ( ☑️ )
    - **State of the system**: `CardType = CAT_CARD_1`, `target = Optional.of(player2)`, `initiator = player1`
    - **Expected output**: `true`

- **TC: NonCatCard_ValidTarget_ReturnsFalse** ( ☑️ )
    - **State of the system**: `CardType = SKIP`, `target = Optional.of(player2)`, `initiator = player1`
    - **Expected output**: `false`

- **TC: CatCard_TargetIsSelf_ReturnsFalse** ( ☑️ )
    - **State of the system**: `CardType = CAT_CARD_1`, `target = Optional.of(player1)`, `initiator = player1`
    - **Expected output**: `false`

### Method under test: `cardsAllMatchingCatCards()`

- **TC: EmptyList_ThrowsException** ( :x: )
    - **State of the system**: `cards = []`
    - **Expected output**: `IndexOutOfBoundsException`

- **TC: SingleCatCard_ReturnsTrue** ( :x: )
    - **State of the system**: `cards = [CAT_CARD_1]`
    - **Expected output**: `true`

- **TC: SingleNonCatCard_ReturnsFalse** ( :x: )
    - **State of the system**: `cards = [SKIP]`
    - **Expected output**: `false`

- **TC: AllMatchingCatCards_ReturnsTrue** ( :x: )
    - **State of the system**: `cards = [CAT_CARD_1, CAT_CARD_1, CAT_CARD_1]`
    - **Expected output**: `true`

- **TC: AllNonCatCards_ReturnsFalse** ( :x: )
    - **State of the system**: `cards = [SKIP, SKIP, SKIP]`
    - **Expected output**: `false`

- **TC: MismatchAfterMatch_ReturnsFalse** ( :x: )
    - **State of the system**: `cards = [CAT_CARD_1, CAT_CARD_1, SKIP]`
    - **Expected output**: `false`

- **TC: MatchAfterMismatch_ReturnsFalse** ( :x: )
    - **State of the system**: `cards = [SKIP, CAT_CARD_1, CAT_CARD_1]`
    - **Expected output**: `false`

- **TC: MismatchAtLast_ReturnsFalse** ( :x: )
    - **State of the system**: `cards = [CAT_CARD_1, CAT_CARD_1, CAT_CARD_2]`
    - **Expected output**: `false`

### Method under test: `isValidMove(List<Card> cards, Player initiator, Optional<Player> target)`
- **TC: empty card list** ( :white-check-mark: )
  - **State of the system**: `cards = []`, initiator = player1
  - **Expected output**: return = false

- **TC: valid single card** ( :white-check-mark: )
  - **State of the system**: `cards = [SKIP]`, initiator = player1
  - **Expected output**: return = true

- **TC: invalid single card (cat)** ( :white-check-mark: )
  - **State of the system**: `cards = [CAT_CARD_1]`, initiator = player1
  - **Expected output**: return = false

- **TC: valid pair of cat cards** ( :white-check-mark: )
  - **State of the system**: `cards = [CAT_CARD_1, CAT_CARD_1], initiator = player1, target = player2`
  - **Expected output**: return = true

- **TC: valid pair of cat cards, but without target** ( :white-check-mark: )
  - **State of the system**: `cards = [CAT_CARD_1, CAT_CARD_1],  initiator = player1`
  - **Expected output**: return = true

- **TC: valid pair of cat cards, but targeting self** ( :white-check-mark: )
  - **State of the system**: `cards = [CAT_CARD_1, CAT_CARD_1],  initiator = player1,target = player1`
  - **Expected output**: return = true

- **TC: invalid pair of cat cards** ( :white-check-mark: )
  - **State of the system**: `cards = [CAT_CARD_2, CAT_CARD_1]`, initiator = player1
  - **Expected output**: return = false

- **TC: invalid pair of non-cat cards** ( :white-check-mark: )
  - **State of the system**: `cards = [SKIP, SKIP]`, initiator = player1
  - **Expected output**: return = false

- **TC: valid triple of cat cards** ( :white-check-mark: )
  - **State of the system**: `cards = [CAT_CARD_1, CAT_CARD_1, CAT_CARD_1], initiator = player1, target = player2`
  - **Expected output**: return = true

- **TC: valid triple of cat cards but no target** ( :white-check-mark: )
  - **State of the system**: `cards = [CAT_CARD_1, CAT_CARD_3, CAT_CARD_2], initiator = player1`
  - **Expected output**: return = false

- **TC: invalid triple of cat cards** ( :white-check-mark: )
  - **State of the system**: `cards = [CAT_CARD_1, CAT_CARD_3, CAT_CARD_2], initiator = player1, target = player2`
  - **Expected output**: return = false

- **TC: invalid triple of non-cat cards** ( :white-check-mark: )
  - **State of the system**: `cards = [SKIP, SKIP, SKIP], initiator = player1`
  - **Expected output**: return = false

- **TC: four cards (too many cards)** ( :white-check-mark: )
  - **State of the system**: `cards = [CAT_CARD_1, CAT_CARD_3, CAT_CARD_2, shuffle], initiator = player1`


### Method under test: `advanceTurn()`
- **TC: Standard Turn Advance** (:white-check-mark:)
  - **State of the system**: `alivePlayers` size = 4. `current` points to index 1, `next` points to index 2.
  - **Expected output**: `current` becomes index 2, `next` becomes index 3.

- **TC: Next Player Hits Upper Boundary** (:white-check-mark:)
  - **State of the system**: `alivePlayers` size = 4. `current` points to index 2, `next` points to index 3.
  - **Expected output**: `current` becomes index 3. `next` wraps around and becomes index 0.

- **TC: Current Player Hits Upper Boundary** ( :white-check-mark:)
  - **State of the system**: `alivePlayers` size = 4. `current` points to index 3, `next` points to index 0.
  - **Expected output**: `current` becomes index 0. `next` becomes index 1.

- **TC: Minimum Players Toggle** ( :white-check-mark: )
  - **State of the system**: `alivePlayers` size = 2. `current` points to index 0, `next` points to index 1.
  - **Expected output**: `current` becomes index 1. `next` wraps around to index 0.

- **TC: Absolute Maximum Boundary** ( :white-check-mark: )
  - **State of the system**: `alivePlayers` size = `MAX_PLAYERS`. `current` = `MAX_PLAYERS - 1`, `next` = 0.
  - **Expected output**: `current` becomes index 0, `next` becomes index 1.