# BVA Analysis for TargetAttackController class

### Method under test: `executeCardAction()`

- **TC1: target is empty** ( :x: )
    - **State of the system**: initiator = player1, target = Optional.empty()
    - **Expected output**: IllegalArgumentException is thrown. Game state is unchanged.

- **TC2: target is an alive player (not the initiator)** ( :x: )
    - **State of the system**: initiator = player1, target = player2 (alive), 2+ players alive
    - **Expected output**: `Optional.empty()` is returned. player2 is assigned 2 turns.

- **TC3: target is the initiator** ( :x: )
    - **State of the system**: initiator = player1, target = player1
    - **Expected output**: IllegalArgumentException is thrown. Game state is unchanged.

- **TC4: target is a dead player** ( :x: )
    - **State of the system**: initiator = player1, target = player2 (dead/eliminated)
    - **Expected output**: IllegalArgumentException is thrown. Game state is unchanged.
