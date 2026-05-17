# BVA Analysis for Player Class (each public method)

You are encouraged to document your intermediate analysis results for Steps 1-3.
However, you are only required to document Step 4.

### Method under test: constructor
- **TC0: Constructor_nullName_IllegalArgumentException** ( :white_check_mark: )
    - **State of the system**: null
    - **Expected output**: IllegalArgumentException

- **TC1: Constructor_EmptyName_IllegalArgumentException** ( :white_check_mark: )
    - **State of the system**: ""
    - **Expected output**: IllegalArgumentException

- **TC2: Constructor_ValidName_success** ( :white_check_mark: )
    - **State of the system**: "lily"
    - **Expected output**: N/A, player successfully created


### Method under test: hasDefuse()
- **TC3: hasDefuse_EmptyCards_False** ( :white_check_mark: )
    - **State of the system**: []
    - **Expected output**: False

- **TC4: hasDefuse_OneCardwithDefuse_True** ( :white_check_mark: )
    - **State of the system**: [Defuse]
    - **Expected output**: True
  
- **TC5: hasDefuse_TwoCardswithoutDefuse_False** ( :white_check_mark: )
    - **State of the system**: [Other, Other]
    - **Expected output**: False

- **TC6: hasDefuse_duplicateDefuse_True** ( :white_check_mark: )
  - **State of the system**: [Defuse, Defuse]
  - **Expected output**: True


### Method under test: addCard()
- **TC7: addCard_noCards_success** ( :white_check_mark: )
    - **State of the system**: [], Card
    - **Expected output**: [Card]

- **TC8: addCard_1cardAddNewCard_success** ( :white_check_mark: )
    - **State of the system**: [Card], Card1
    - **Expected output**: [Card, Card1]

- **TC9: addCard_2cardDuplicateCards_success** ( :white_check_mark: )
    - **State of the system**: [Card, Card1], Card
    - **Expected output**: [Card, Card1, Card]

- **TC10: addCard_AddNullCard_IllegalArgumentException** ( :white_check_mark: )
    - **State of the system**: [], null
    - **Expected output**: [], IllegalArgumentException

- ### Method under test: setLife()
- **TC11: setLife_AlivetoDead_Success** ( :white_check_mark: )
    - **State of the system**: isAlive: True, input: False
    - **Expected output**: isAlive: False

- **TC12: setLife_DeadtoAlive_IllegalStateException** ( :white_check_mark: )
    - **State of the system**: isAlive: False, input: True
    - **Expected output**: IllegalStateException

- **TC13: setLife_AlivetoAlive_SucessNoChange** ( :white_check_mark: )
  - **State of the system**: isAlive: True, input: True
  - **Expected output**: isAlive: True

- ### Method under test: removeCard()
input: card (pointer), cards (list)
output: cards (list)

- **TC18: removeCard_NormalCardOneCard_CardRemoved** ( :x: or :white_check_mark: )
    - **State of the system**: card: TEST, cards: [TEST]
    - **Expected output**: cards: []

- **TC19: removeCard_NormalCardTwoCards_CardRemoved** ( :x: or :white_check_mark: )
    - **State of the system**: card: TEST, cards: [TEST, TEST]
    - **Expected output**: cards: [TEST]

- **TC20: removeCard_nullCard_IllegalStateException** ( :x: or :white_check_mark: )
    - **State of the system**: card: null, cards: [TEST]
    - **Expected output**: IllegalStateException

- **TC21: removeCard_noCards_IllegalStateException** ( :x: or :white_check_mark: )
    - **State of the system**: card: TEST, cards: []
    - **Expected output**: IllegalStateException

- **TC22: removeCard_cardNotInCards_IllegalArgumentException** ( :x: or :white_check_mark: )
    - **State of the system**: card: TEST, cards: [DEFUSE]
    - **Expected output**: IllegalArgumentException

- ### Method under test: takeTurn()
input: card (pointer) (EXPLODING KITTEN or other), hand (list)
output: isAlive (boolean), hand (list)

- **TC14: takeTurn_NormalCardNoCardsInHand_CardAddedtoHand** ( :x: or :white_check_mark: )
    - **State of the system**: card: TEST, hand: []
    - **Expected output**: isAlive: true, hand: [TEST]

- **TC14: takeTurn_NormalCardOneCardInHand_CardAddedtoHand** ( :x: or :white_check_mark: )
    - **State of the system**: card: TEST, hand: [TEST]
    - **Expected output**: isAlive: true, hand: [TEST, TEST]

- **TC15: takeTurn_ExplodingKittenHas1Defuse_PlayerLives** ( :x: or :white_check_mark: )
    - **State of the system**: card: EXPLODING KITTEN, hand: [DEFUSE]
    - **Expected output**: isAlive: true, hand: []

- **TC16: takeTurn_ExplodingKittenHas2Defuse_PlayerLives** ( :x: or :white_check_mark: )
    - **State of the system**: card: EXPLODING KITTEN, hand: [DEFUSE, DEFUSE]
    - **Expected output**: isAlive: true, hand: [DEFUSE]

- **TC17: takeTurn_ExplodingKittenNoDefuse_PlayerDies** ( :x: or :white_check_mark: )
    - **State of the system**: card: EXPLODING KITTEN, hand: []
    - **Expected output**: isAlive: false, hand: []

- **TC20: takeTurn_nullCard_IllegalStateException** ( :x: or :white_check_mark: )
    - **State of the system**: card: null, hand: []
    - **Expected output**: IllegalStateException