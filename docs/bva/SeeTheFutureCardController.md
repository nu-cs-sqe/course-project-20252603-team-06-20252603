# BVA Analysis for SeeTheFutureCardController class

### Method under test: executeCardAction

- **TC1: Deck With Four cards (one more than the boundary) ( :white_check_mark: )
    - **State of the system**: Deck contains 4 cards
    - **Expected output**: A new list containing exactly the top 3 cards from the deck is returned. The original Deck remains completely unchanged (size and order).

- **TC2: Deck With Exactly Three Cards ( x )
    - **State of the system**: Deck contains exactly 3 cards
    - **Expected output**: A new list containing all 3 cards is returned. The original Deck remains completely unchanged.
  
- **TC3: Deck With Two Cards ( x )
    - **State of the system**: Deck contains exactly 2 cards
    - **Expected output**: A new list containing all 2 cards is returned. The original Deck remains completely unchanged.

- **TC4: Deck With One Card ( x )
    - **State of the system**: Deck contains exactly 1 cards
    - **Expected output**: A new list containing the only card is returned. The original Deck remains completely unchanged.

- **TC5: Deck With No Cards ( x )
    - **State of the system**: Deck contains no cards at all
    - **Expected output**: An empty new list is returned (should i error here? thoughts?)
