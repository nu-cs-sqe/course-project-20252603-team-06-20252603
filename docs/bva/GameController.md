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

### Method under test: `setCurrentPlayerTurnsLeft(int newCurrentPlayerTurnsLeft)`
- **TC: Invalid negative turn count is passed 
  - **State of the system**: alivePlayer list is initialized, newCurrentPlayerTurnsLeft = -1
  - **Expected output**: IllegalArgumentException

- **TC: Turn count at minimum possible boundary, 0 
  - **State of the system**: alivePlayer list is initialized, newCurrentPlayerTurnsLeft = 0
  - **Expected output**: this.currentPlayerTurnsLeft = 0

- **TC: Turn count at amount above minimum possible boundary
  - **State of the system**: alivePlayer list is initialized, newCurrentPlayerTurnsLeft = 4
  - **Expected output**: this.currentPlayerTurnsLeft = 4

### Method under test: `setNextPlayerTurnsLeft(int newNextPlayerTurnsLeft)`
- **TC: Invalid negative turn count is passed
  - **State of the system**: alivePlayer list is initialized, newNextPlayerTurnsLeft = -1
  - **Expected output**: IllegalArgumentException

- **TC: Turn count at minimum possible boundary, 0
  - **State of the system**: alivePlayer list is initialized, newNextPlayerTurnsLeft = 0
  - **Expected output**: this.NextPlayerTurnsLeft = 0

- **TC: Turn count at amount above minimum possible boundary
  - **State of the system**: alivePlayer list is initialized, newNextPlayerTurnsLeft = 4
  - **Expected output**: this.NextPlayerTurnsLeft = 4

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

- **TC: attack** ( :white-check-mark: )
  - **State of the system**: `card = ATTACK`
  - **Expected output**: attackController

- **TC: defuse** ( :white-check-mark: )
  - **State of the system**: `card = DEFUSE`
  - **Expected output**: defuseController

- **TC: see the future** ( :white-check-mark: )
  - **State of the system**: `card = SEE_THE_FUTURE`
  - **Expected output**: seeTheFutureController

- **TC: shuffle** ( :white-check-mark: )
  - **State of the system**: `card = SHUFFLE`
  - **Expected output**: shuffleController

- **TC: skip** ( :white-check-mark: )
  - **State of the system**: `card = SKIP`
  - **Expected output**: skipController

- **TC: nope** ( :white-check-mark: )
  - **State of the system**: `card = NOPE`
  - **Expected output**: nopeController

- **TC: draw from bottom** ( :white-check-mark: )
  - **State of the system**: `card = DRAW_FROM_BOTTOM`
  - **Expected output**: drawFromBottomController

- **TC: cat card 1** ( :white-check-mark: )
  - **State of the system**: `card = CAT_CARD_1`
  - **Expected output**: catCardController

- **TC: cat card 2** ( :white-check-mark: )
  - **State of the system**: `card = CAT_CARD_2`
  - **Expected output**: catCardController

- **TC: cat card 3** ( :white-check-mark: )
  - **State of the system**: `card = CAT_CARD_3`
  - **Expected output**: catCardController

- **TC: cat card 4** ( :white-check-mark: )
  - **State of the system**: `card = CAT_CARD_4`
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

- **TC: EmptyList_ThrowsException** ( ☑️ )
    - **State of the system**: `cards = []`
    - **Expected output**: `IndexOutOfBoundsException`

- **TC: SingleCatCard_ReturnsTrue** ( ☑️ )
    - **State of the system**: `cards = [CAT_CARD_1]`
    - **Expected output**: `true`

- **TC: SingleNonCatCard_ReturnsFalse** ( ☑️ )
    - **State of the system**: `cards = [SKIP]`
    - **Expected output**: `false`

- **TC: AllMatchingCatCards_ReturnsTrue** ( ☑️ )
    - **State of the system**: `cards = [CAT_CARD_1, CAT_CARD_1, CAT_CARD_1]`
    - **Expected output**: `true`

- **TC: AllNonCatCards_ReturnsFalse** ( ☑️ )
    - **State of the system**: `cards = [SKIP, SKIP, SKIP]`
    - **Expected output**: `false`

- **TC: MismatchAfterMatch_ReturnsFalse** ( ☑️ )
    - **State of the system**: `cards = [CAT_CARD_1, CAT_CARD_1, SKIP]`
    - **Expected output**: `false`

- **TC: MatchAfterMismatch_ReturnsFalse** ( ☑️ )
    - **State of the system**: `cards = [SKIP, CAT_CARD_1, CAT_CARD_1]`
    - **Expected output**: `false`

- **TC: MismatchAtLast_ReturnsFalse** ( ☑️ )
    - **State of the system**: `cards = [CAT_CARD_1, CAT_CARD_1, CAT_CARD_2]`
    - **Expected output**: `false`

### Method under test: `playerHasCards(Player initiator, List<Card> cards)`
- **TC: empty card list** ( :white_check_mark: )
  - **State of the system**: `cards = [], initiator hand: [CAT_CARD_1, SKIP]`
  - **Expected output**: IllegalArgumentException, "cards list cannot be empty"

- **TC: card list has one card, in initiator's hand** ( :white_check_mark: )
  - **State of the system**: `cards = [CAT_CARD_1], initiator hand: [CAT_CARD_1, SKIP]`
  - **Expected output**: return = true

- **TC: card list has one card, NOT in initiator's hand** ( :white_check_mark: )
  - **State of the system**: `cards = [CAT_CARD_2], initiator hand: [CAT_CARD_1, SKIP]`
  - **Expected output**: return = false

- **TC: card list has one card, initiator's hand is empty** ( :white_check_mark: )
  - **State of the system**: `cards = [CAT_CARD_2], initiator hand: []`
  - **Expected output**: return = false

- **TC: card list has two same cards, initiator hand only has one of those cards** ( :white_check_mark: )
  - **State of the system**: `cards = [CAT_CARD_1, CAT_CARD_1], initiator hand: [CAT_CARD_1]`
  - **Expected output**: return = false

- **TC: card list has two same cards, initiator hand has both** ( :white_check_mark: )
  - **State of the system**: `cards = [CAT_CARD_2, CAT_CARD_2], initiator hand: [CAT_CARD_2, CAT_CARD_2]`
  - **Expected output**: return = true

- **TC: card list has two different cards, initiator hand has only one** ( :white_check_mark: )
  - **State of the system**: `cards = [CAT_CARD_2, SKIP], initiator hand: [CAT_CARD_2, SHUFFLE]`
  - **Expected output**: return = false

- **TC: card list has two different cards, initiator hand has neither** ( :white_check_mark: )
  - **State of the system**: `cards = [CAT_CARD_2, SKIP], initiator hand: [SEE_THE_FUTURE, SHUFFLE]`
  - **Expected output**: return = false

- **TC: card list has two different cards, initiator hand has both** ( :white_check_mark: )
  - **State of the system**: `cards = [SKIP, CAT_CARD_4], initiator hand: [CAT_CARD_4, SEE_THE_FUTURE, SHUFFLE, SKIP]`
  - **Expected output**: return = true

- **TC: card list has three different cards, initiator hand has only one** ( :white_check_mark: )
  - **State of the system**: `cards = [CAT_CARD_3, CAT_CARD_2, CAT_CARD_4], initiator hand: [CAT_CARD_3]`
  - **Expected output**: return = false

- **TC: card list has three same cards, initiator hand has all three** ( :white_check_mark: )
  - **State of the system**: ```cards = [CAT_CARD_4, CAT_CARD_4, CAT_CARD_4], 
 initiator hand: [CAT_CARD_4, ATTACK, CAT_CARD_4, CAT_CARD_4, SHUFFLE, SKIP]```
  - **Expected output**: return = true

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

### Method under test: `takeTurn()`
- **TC: input is empty, invalid choice** ( :white-check-mark: )
  - **State of the System**: `currentPlayer`'s hand: `[SKIP, CAT_CARD_1]`, `userChoice` = "", `currentPlayerTurnsLeft` = 1
  - **Expected output**: function to output invalid choice is called

- **TC: input is not d or a choice of cards, invalid choice** ( :white-check-mark: )
  - **State of the System**: `currentPlayer`'s hand: `[SKIP, CAT_CARD_1]`, `userChoice` = "skip", `currentPlayerTurnsLeft` = 1
  - **Expected output**: function to output invalid choice is called

- **TC: input is d, draw card and reduce currentPlayerTurnsLeft**
  - **State of the System**: `currentPlayer`'s hand: `[SKIP, CAT_CARD_1]`, `userChoice` = "d", `currentPlayerTurnsLeft` = 1
  - **Expected output**: `currentPlayer`'s hand size: 3, `currentPlayerTurnsLeft` = 0

- **TC: input is just a cat card, invalid move**
  - **State of the System**: `currentPlayer`'s hand: `[SKIP, CAT_CARD_1]`, `userChoice` = "1", `currentPlayerTurnsLeft` = 1
  - **Expected output**: function to output invalid move is called

- **TC: input is cat card and a non-cat card, invalid move**
  - **State of the System**: `currentPlayer`'s hand: `[SKIP, CAT_CARD_1]`, `userChoice` = "0,1", `currentPlayerTurnsLeft` = 1
  - **Expected output**: function to output invalid move is called

- **TC: input is a non-cat card that doesn't alter game state, play card and currentPlayerTurnsLeft isn't reduced, card removed from hand**
  - **State of the System**: `currentPlayer`'s hand: `[SEE_THE_FUTURE, CAT_CARD_1]`, `userChoice` = "0", `currentPlayerTurnsLeft` = 1
  - **Expected output**: `currentPlayer`'s hand: `[CAT_CARD_1]`, `currentPlayerTurnsLeft` = 1

- **TC: input is a non-cat card that alters game state, play card and currentPlayerTurnsLeft isn't reduced, card removed from hand, verify game state changed**
  - **State of the System**: `currentPlayer`'s hand: `[CAT_CARD_1, DRAW_FROM_BOTTOM, SKIP]`, `userChoice` = "1", `currentPlayerTurnsLeft` = 1
  - **Expected output**: `currentPlayer`'s hand size: 3, `currentPlayerTurnsLeft` = 1, `deck` size reduced by 1 (the bottom card is now in the player's hand)

- **TC: input is a non-cat card that immediately ends turn, play card and currentPlayerTurnsLeft is reduced, card removed from hand**
  - **State of the System**: `currentPlayer`'s hand: `[CAT_CARD_1, DRAW_FROM_BOTTOM, SKIP]`, `userChoice` = "2", `currentPlayerTurnsLeft` = 2
  - **Expected output**: `currentPlayer`'s hand size: 3, `currentPlayerTurnsLeft` = 1

- **TC: input is a pair of cat cards, play cards, currentPlayerTurnsLeft is not reduced, cards removed from hand, game state changed**
  - **State of the System**: `currentPlayer`'s hand: `[CAT_CARD_1, DRAW_FROM_BOTTOM, CAT_CARD_1, SKIP]`, `userChoice` = "0,2", `currentPlayerTurnsLeft` = 2
  - **Expected output**: `currentPlayer`'s hand size: 3, `currentPlayerTurnsLeft` = 1

- **TC: input is a triple of cat cards, play cards, currentPlayerTurnsLeft is not reduced, cards removed from hand, game state changed**
  - **State of the System**: `currentPlayer`'s hand: `[CAT_CARD_3, DRAW_FROM_BOTTOM, SKIP, CAT_CARD_3, CAT_CARD_3]`, `userChoice` = "0,3,4", `currentPlayerTurnsLeft` = 2
  - **Expected output**: `currentPlayer`'s hand size: 3, `currentPlayerTurnsLeft` = 1



