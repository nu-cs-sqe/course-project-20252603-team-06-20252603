# BVA Analysis for SkipCardController

### Method under test: executeCardAction()

- **TC1: skip with no target** ( :x: )
  - **State of the system**: initiator = player 1, target = Optional.empty()
  - **Expected output**: Optional.empty() is returned, game state is unchanged