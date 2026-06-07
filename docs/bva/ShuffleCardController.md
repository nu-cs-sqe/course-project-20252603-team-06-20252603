# Shuffle Card Controller
### Method under test: `executeCardAction()`
- **TC1: Empty Deck* ( :x: or :white_check_mark: )
    - **State of the system**: Game.deck is empty
    - **Expected output**: Game.deck is empty
    - 
- **TC2: Deck Has One Card** ( :x: or :white_check_mark: )
    - **State of the system**: Game.deck contains exactly one card
    - **Expected output**: Game.deck still contains the same 1 card

- **TC3: Deck Has Multiple Cards** ( :x: or :white_check_mark: )
    - **State of the system**: Game.deck contains 34 cards (post-constructor)
    - **Expected output**: Game.deck still contains the same 34 cards with no duplicates and no cards lost