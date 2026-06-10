### Method under test: `getTopCards()`
- **TC1: deck has 0 cards** ( :white-check-mark: )
    - **State of the system**: deck is empty
    - **Expected output**: empty list returned

- **TC2: deck has 1 card** ( :white-check-mark: )
    - **State of the system**: deck has 1 card
    - **Expected output**: list with that 1 card returned

- **TC3: deck has 2 cards** ( :white-check-mark: )
    - **State of the system**: deck has 2 cards
    - **Expected output**: list with those 2 cards returned

- **TC4: deck has exactly 3 cards (at MAX_PEEK boundary)** ( :white-check-mark: )
    - **State of the system**: deck has 3 cards
    - **Expected output**: list with all 3 cards returned

- **TC5: deck has more than 3 cards** ( :x: )
    - **State of the system**: deck has 4 cards
    - **Expected output**: list with only the top 3 cards returned

---

### Method under test: `validateReorder()`
- **TC1: reordered list has fewer cards than original** ( :x: )
    - **State of the system**: original has 3 cards, reordered has 2 cards
    - **Expected output**: IllegalArgumentException thrown

- **TC2: reordered list has more cards than original** ( :x: )
    - **State of the system**: original has 3 cards, reordered has 4 cards
    - **Expected output**: IllegalArgumentException thrown

- **TC3: reordered list has same count but contains a card not in original** ( :x: )
    - **State of the system**: original has 3 cards, reordered has 3 cards but one is different
    - **Expected output**: IllegalArgumentException thrown

- **TC4: reordered list is a valid rearrangement** ( :x: )
    - **State of the system**: original has 3 cards, reordered has the same 3 cards in a different order
    - **Expected output**: no exception thrown

- **TC5: reordered list is identical to original** ( :x: )
    - **State of the system**: original has 3 cards, reordered has the same 3 cards in the same order
    - **Expected output**: no exception thrown

---

### Method under test: `applyReorder()`
- **TC1: reorder 1 card** ( :x: )
    - **State of the system**: deck has 4 cards, original and reordered both have 1 card
    - **Expected output**: top card of deck replaced in the new order, rest of deck unchanged

- **TC2: reorder 2 cards** ( :x: )
    - **State of the system**: deck has 4 cards, original and reordered both have 2 cards in different order
    - **Expected output**: top 2 cards of deck replaced in new order, rest of deck unchanged

- **TC3: reorder 3 cards** ( :x: )
    - **State of the system**: deck has 4 cards, original and reordered both have 3 cards in different order
    - **Expected output**: top 3 cards of deck replaced in new order, rest of deck unchanged

---

### Method under test: `executeCardAction()`
- **TC1: deck has 0 cards** ( :x: )
    - **State of the system**: deck is empty, reorderFunction returns empty list
    - **Expected output**: Optional.empty() returned, deck unchanged

- **TC2: deck has 1 card** ( :x: )
    - **State of the system**: deck has 1 card, reorderFunction returns the same card
    - **Expected output**: Optional.empty() returned, deck unchanged

- **TC3: deck has 2 cards** ( :x: )
    - **State of the system**: deck has 2 cards, reorderFunction returns them in reverse order
    - **Expected output**: Optional.empty() returned, top 2 cards swapped

- **TC4: deck has exactly 3 cards** ( :x: )
    - **State of the system**: deck has 3 cards, reorderFunction returns them in reverse order
    - **Expected output**: Optional.empty() returned, all 3 cards reordered

- **TC5: deck has more than 3 cards** ( :x: )
    - **State of the system**: deck has 5 cards, reorderFunction returns top 3 in reverse order
    - **Expected output**: Optional.empty() returned, top 3 cards reordered, remaining cards unchanged

- **TC6: reorderFunction returns invalid reorder** ( :x: )
    - **State of the system**: deck has 3 cards, reorderFunction returns a list with a card not in the original top 3
    - **Expected output**: IllegalArgumentException thrown
