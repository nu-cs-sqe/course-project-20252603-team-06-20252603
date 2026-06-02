### Method under test: `executeCardAction()`
- **TC1: draw when deck is empty** ( :x: )
    - **State of the system**: game.deck has no cards left, initiator = player1
    - **Expected output**: IllegalStateException, "no cards left in deck"

- **TC2: draw when deck has only one card** ( :x: )
    - **State of the system**: game.deck has 1 card, initiator = player 1
    - **Expected output**: game.deck is now an empty deck, player 1 has one more card now

- **TC3: draw when deck has normal amount of cards** ( :x: )
    - **State of the system**: game.deck has 15 card, initiator = player 1
    - **Expected output**: game.deck now has 14 cards, player 1 has one more card now