# BVA Analysis for SelfishRobinHoodCardController class

### Method under test: `executeCardAction()`
- **TC1: Target has fewer cards than initiator (Lower Bound Invalid)** ( :white_check_mark: )
    - **State of the system**: Initiator has 3 cards. Target has 2 cards.
    - **Expected output**: No steal occurs. Hand sizes remain exactly the same.

- **TC2: Target has exactly the same number of cards (Minimum Invalid Boundary)** ( :white_check_mark: )
    - **State of the system**: Initiator has 3 cards. Target has 3 cards.
    - **Expected output**: No steal occurs. Hand sizes remain exactly the same.

- **TC3: Target has exactly one more card (Minimum Valid Boundary)** ( x )
    - **State of the system**: Initiator has 3 cards. Target has 4 cards.
    - **Expected output**: Initiator steals 1 random card. Initiator hand = 4, Target hand = 3.

- **TC4: Target has many more cards (Nominal Valid)** ( x )
    - **State of the system**: Initiator has 3 cards. Target has 7 cards.
    - **Expected output**: Initiator steals 1 random card. Initiator hand = 4, Target hand = 6.

- **TC5: AOE Multi-Player - Initiator is the richest (No Steals)** ( x )
    - **State of the system**: 5-player game. Initiator has 5 cards. All other 4 players have 2 cards.
    - **Expected output**: Loop completes with no state changes. Initiator finishes with 5 cards.

- **TC6: AOE Multi-Player - Initiator is the poorest (Maximum Steals)** ( x )
    - **State of the system**: 5-player game. Initiator has 1 card. All other 4 players have 5 cards.
    - **Expected output**: Initiator steals exactly 1 card from all 4 players. Initiator finishes with 5 cards. Every other player finishes with 4 cards.

- **TC7: AOE Multi-Player - Mixed hand sizes (Nominal Board State)** ( x )
    - **State of the system**: 5-player game. Initiator has 3 cards.
        - Player 2 has 1 card (Poorer)
        - Player 3 has 3 cards (Equal)
        - Player 4 has 4 cards (Richer)
        - Player 5 has 7 cards (Much Richer)
    - **Expected output**: Initiator steals exactly 1 card from Player 4 and Player 5 ONLY.
        - Initiator finishes with 5 cards (3 + 2 stolen).
        - Player 2 finishes with 1 card.
        - Player 3 finishes with 3 cards.
        - Player 4 finishes with 3 cards.
        - Player 5 finishes with 6 cards.