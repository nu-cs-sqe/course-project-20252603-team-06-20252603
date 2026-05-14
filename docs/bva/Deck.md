# BVA Analysis for Deck Class

### Method under test: constructor
- **TC1: Default Constructor** ( :x: or :white_check_mark: )
    - **State of the system**: 
    - **Expected output**: A list of 34 total cards with the following types
      * Attack: 3
      * Skip: 3
      * See the Future: 4
      * Shuffle: 4
      * Nope: 4
      * Cat Cards: 4 of each × 4 types = 16

### Method under test: shuffle()
- **TC2: Empty Deck** ( :x: or :white_check_mark: )
  - **State of the system**: Deck.cards is empty
  - **Expected Output**: 

- **TC3: Single Card** ( :x: or :white_check_mark: )
    - **State of the system**: Deck.cards contains exactly 1 card
    - **Expected Output**: Deck.cards still contains the same 1 card, no exception thrown

- **TC4: Two Cards** ( :x: or :white_check_mark: )
    - **State of the system**: Deck.cards contains exactly 2 cards
    - **Expected Output**: Deck.cards still contains the same 2 cards with no duplicates

- **TC5: Standard Deck** ( :x: or :white_check_mark: )
    - **State of the system**: Deck.cards contains 34 cards (post-constructor)
    - **Expected Output**: Deck.cards still contains the same 34 cards with no duplicates and no cards lost

- **TC6: Full Game Deck** ( :x: or :white_check_mark: )
    - **State of the system**: Deck.cards contains 34 cards plus Exploding Kittens inserted
    - **Expected Output**: Deck.cards still contains the same cards with no duplicates and no cards lost

### Method under test: insert(card, location)
- **TC7: Insert at index 0 ** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has N cards; valid card object provided; location = 0
    - **Expected output**: Card is inserted at the front of the deck; deck size becomes N+1

- **TC8: Insert at index 1 (just above minimum boundary)** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has N cards; valid card object provided; location = 1
    - **Expected output**: Card is inserted at index 1; deck size becomes N+1

- **TC9: Insert at index N (last valid index / maximum boundary)** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has N cards; valid card object provided; location = N (end of list)
    - **Expected output**: Card is appended at index N+1; deck size becomes N+1

- **TC10: Insert at index N-1 (just below maximum boundary)** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has N cards; valid card object provided; location = N-1
    - **Expected output**: Card is inserted at index N-1; deck size becomes N+1

- **TC11: Insert at index -1 (just below minimum boundary)** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has N cards; valid card object provided; location = -1
    - **Expected output**: Raises an `IndexError`; deck remains unchanged

- **TC12: Insert into an empty deck at index 0** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has 0 cards; valid card object provided; location = 0
    - **Expected output**: Card is inserted as the only element; deck size becomes 1

- **TC13: Insert into an empty deck at index 1 (out of bounds for empty deck)** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has 0 cards; valid card object provided; location = 1
    - **Expected output**: Raises an `IndexError`; deck remains empty

- **TC14: Insert a null/None card at a valid location** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has N cards; card = null/None; location = 0
    - **Expected output**: Raises a `ValueError`; deck remains unchanged

### Method under test: insert(card, location)
- **TC15: Discard existing card** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has N cards; card is present in the deck
    - **Expected output**: Card is removed from the deck; deck size becomes N-1

- **TC16: Discard the only card in the deck** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has exactly 1 card; card is that card
    - **Expected output**: Card is removed; deck size becomes 0

- **TC17: Discard from an empty deck** (:x: or :white_check_mark: )
    - **State of the system**: Deck has 0 cards; any card provided
    - **Expected output**: Raises a `ValueError'; deck remains empty

- **TC18: Discard the first card in the deck** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has N cards; card is at index 0
    - **Expected output**: Card is removed from the front; deck size becomes N-1

- **TC19: Discard the last card in the deck (maximum index boundary)** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has N cards; card is at index N-1
    - **Expected output**: Card is removed from the back; deck size becomes N-1

- **TC20: Discard a card not present in the deck** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has N cards; provided card does not exist in the deck
    - **Expected output**: Raises a `ValueError`; deck remains unchanged

- **TC21: Discard a null/None card** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has N cards; card = null/None
    - **Expected output**: Raises a `ValueError`; deck remains unchanged

### Method under test: peek()
- **TC22: Peek at a deck with multiple cards** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has N cards
    - **Expected output**: Returns a list of all N cards; deck remains unchanged

- **TC23: Peek at a deck with exactly one card** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has exactly 1 card
    - **Expected output**: Returns a list containing that 1 card; deck remains unchanged

- **TC24: Peek at an empty deck** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has 0 cards
    - **Expected output**: Returns an empty list; deck remains unchanged

- **TC25: Deck has 2 cards** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has N cards
    - **Expected output**: Deck.cards is identical before and after the call; size remains 2

### Method under test: count()
- **TC26: Count an empty deck** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has 0 cards
    - **Expected output**: Returns 0

- **TC27: Count a deck with exactly one card** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has exactly 1 card
    - **Expected output**: Returns 1

- **TC28: Count a deck with exactly two cards** ( :x: or :white_check_mark: )
    - **State of the system**: Deck has exactly 2 cards
    - **Expected output**: Returns 2