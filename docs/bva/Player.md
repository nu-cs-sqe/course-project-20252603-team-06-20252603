# BVA Analysis for Player Class (each public method)

You are encouraged to document your intermediate analysis results for Steps 1-3.
However, you are only required to document Step 4.

### Method under test: constructor
- **TC1: Constructor_EmptyName_IllegalArgumentException** ( :white_check_mark: )
    - **State of the system**: ""
    - **Expected output**: IllegalArgumentException

- **TC2: Constructor_ValidName_success** ( :white_check_mark: )
    - **State of the system**: "lily"
    - **Expected output**: N/A, player successfully created


### Method under test: hasDefuse()
- **TC3: hasDefuse_EmptyHand_False** ( :white_check_mark: )
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

- **TC8: addCard_onecardAddNewCard_success** ( :white_check_mark: )
    - **State of the system**: [Card], Card1
    - **Expected output**: [Card, Card1]

- **TC9: addCard_twocardsDuplicateCards_success** ( :white_check_mark: )
    - **State of the system**: [Card, Card1], Card
    - **Expected output**: [Card, Card1, Card]

- ### Method under test: kill()
- **TC10: kill_Success** ( :white_check_mark: )
    - **State of the system**: isAlive: True
    - **Expected output**: isAlive: False

- **TC10: kill_IllegalStateException** ( :white_check_mark: )
    - **State of the system**: isAlive: False
    - **Expected output**: IllegalStateException

- ### Method under test: revive()
- **TC10: revive_Success** ( :white_check_mark: )
    - **State of the system**: isAlive: False
    - **Expected output**: isAlive: True

- **TC10: revive_IllegalStateException** ( :white_check_mark: )
    - **State of the system**: isAlive: True
    - **Expected output**: IllegalStateException

- ### Method under test: removeCard()
input: card (pointer), cards (list)
output: cards (list)

- **TC13: removeCard_NormalCardOneCard_CardRemoved** ( :white_check_mark: )
    - **State of the system**: card: TEST, cards: [TEST]
    - **Expected output**: cards: []

- **TC14: removeCard_NormalCardTwoCards_CardRemoved** ( :white_check_mark: )
    - **State of the system**: card: TEST, cards: [TEST, TEST]
    - **Expected output**: cards: [TEST]

- **TC15: removeCard_noCards_IllegalStateException** ( :white_check_mark: )
    - **State of the system**: card: TEST, cards: []
    - **Expected output**: IllegalStateException

- **TC16: removeCard_cardNotInHand_IllegalArgumentException** ( :white_check_mark: )
    - **State of the system**: card: TEST, cards: [DEFUSE]
    - **Expected output**: IllegalArgumentException

- ### Method under test: takeTurn()
input: card (pointer) (EXPLODING KITTEN or other), hand (list)
output: isAlive (boolean), hand (list)

- **TC17: takeTurn_NormalCardNoCardsInHand_CardAddedtoHand** ( :white_check_mark: )
    - **State of the system**: card: TEST, hand: []
    - **Expected output**: isAlive: true, hand: [TEST]

- **TC18: takeTurn_NormalCardOneCardInHand_CardAddedtoHand** ( :white_check_mark: )
    - **State of the system**: card: TEST, hand: [TEST]
    - **Expected output**: isAlive: true, hand: [TEST, TEST]

***TODO***
- **TC19: takeTurn_ExplodingKittenHas1Defuse_PlayerLives** ( :x: or :white_check_mark: )
    - **State of the system**: card: EXPLODING KITTEN, hand: [DEFUSE]
    - **Expected output**: isAlive: true, hand: []

- **TC20: takeTurn_ExplodingKittenHas2Defuse_PlayerLives** ( :x: or :white_check_mark: )
    - **State of the system**: card: EXPLODING KITTEN, hand: [DEFUSE, DEFUSE]
    - **Expected output**: isAlive: true, hand: [DEFUSE]

- **TC21: takeTurn_ExplodingKittenNoDefuse_PlayerDies** ( :x: or :white_check_mark: )
    - **State of the system**: card: EXPLODING KITTEN, hand: []
    - **Expected output**: isAlive: false, hand: []

- ### Method under test: getHand()
- **TC10: getHand_EmptyHand_EmptyList** ( :white_check_mark: )
  - **State of the system**: hand: []
  - **Expected output**: []

- **TC10: getHand_OneCard_ListWithOneCard** ( :white_check_mark: )
  - **State of the system**: hand: [TEST]
  - **Expected output**: [TEST]

- **TC10: getHand_TwoCardsSameType_ListWithTwoCadsSameType** ( :white_check_mark: )
  - **State of the system**: hand: [TEST, TEST]
  - **Expected output**: [TEST, TEST]

- **TC10: getHand_SevenCardsManyTypes_Two** ( :x: )
  - **State of the system**: hand: [SEE_THE_FUTURE, DEFUSE, SHUFFLE, NOPE, NOPE, SKIP, SHUFFLE]
  - **Expected output**: [SEE_THE_FUTURE, DEFUSE, SHUFFLE, NOPE, NOPE, SKIP, SHUFFLE]

- ### Method under test: getHandSize()
- **TC10: getHandSize_EmptyHand_Zero** ( :white_check_mark: )
    - **State of the system**: hand: []
    - **Expected output**: 0

- **TC10: getHandSize_OneCard_One** ( :white_check_mark: )
    - **State of the system**: hand: [TEST]
    - **Expected output**: 1

- **TC10: getHandSize_TwoCards_Two** ( :white_check_mark: )
    - **State of the system**: hand: [TEST, TEST2]
    - **Expected output**: 2

- **TC10: getHandSize_DuplicateCards_Two** ( :white_check_mark: )
    - **State of the system**: hand: [TEST, TEST]
    - **Expected output**: 2

- ### Method under test: hasCard()
- **TC10: hasCard_EmptyHand_False** ( :white_check_mark: )
    - **State of the system**: hand: [], card: TEST
    - **Expected output**: False

- **TC10: hasCard_OneCardMatch_True** ( :white_check_mark: )
    - **State of the system**: hand: [DEFUSE], card: DEFUSE
    - **Expected output**: True

- **TC10: hasCard_OneCardNoMatch_False** ( :white_check_mark: )
    - **State of the system**: hand: [TEST], card: DEFUSE
    - **Expected output**: False

- **TC10: hasCard_TwoCardsOneMatch_True** ( :white_check_mark: )
    - **State of the system**: hand: [TEST, DEFUSE], card: DEFUSE
    - **Expected output**: True

- **TC10: hasCard_DuplicateCardsMatch_True** ( :white_check_mark: )
    - **State of the system**: hand: [DEFUSE, DEFUSE], card: DEFUSE
    - **Expected output**: True

- **TC10: hasCard_DuplicateCardsNoMatch_False** ( :white_check_mark: )
    - **State of the system**: hand: [TEST, TEST], card: DEFUSE
    - **Expected output**: False