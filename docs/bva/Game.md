# BVA analysis for Game

### Method under test: `Game(int playerCount)`
- **TC1: One Below Minimum Players** ( ☑️ )
  - **State of the system**: `playerCount = 1`
  - **Expected output**: throws `IllegalArgumentException`

- **TC2: Minimum Players** ( ☑️ )
  - **State of the system**: `playerCount = 2`
  - **Expected output**: `Game` object created with 2 players in `totalPlayers` and `alivePlayers`

- **TC4: Maximum Players** ( ☑️ )
  - **State of the system**: `playerCount = 5`
  - **Expected output**: `Game` object created with 5 players in `totalPlayers` and `alivePlayers`

- **TC5: One Above Maximum Players** (  ☑️  )
  - **State of the system**: `playerCount = 6`
  - **Expected output**: throws `IllegalArgumentException`
  
### Method under test: `setup()`
- **TC6: Setup With Minimum Players** (  ☑️  )
  - **State of the system**: game created with 2 players; default deck
  - **Expected output**: each player has 8 cards (7 normal + 1 DEFUSE); deck contains 1 EXPLODING_KITTEN

- **TC7: Setup With Maximum Players** ( :x: )
  - **State of the system**: game created with 5 players; default deck
  - **Expected output**: each player has 8 cards (7 normal + 1 DEFUSE); deck contains 4 EXPLODING_KITTENs

### Method under test: `draw(Player player, Deck deck)`
- **TC8: Draw From Deck With One Card** ( :x: )
  - **State of the system**: `deck` contains exactly 1 card; `player` has 0 cards
  - **Expected output**: player receives the card; deck is now empty

- **TC9: Draw From Empty Deck** ( :x: )
  - **State of the system**: `deck` contains 0 cards; `player` has 0 cards
  - **Expected output**: throws an exception

- **TC10: Draw From Deck With Many Cards** ( :x: )
  - **State of the system**: `deck` contains more than 1 card; `player` has 0 cards
  - **Expected output**: player receives only the top card; deck size decreases by 1

### Method under test: `getTotalPlayerCount()`
- **TC11: Minimum Total Players** ( :x: )
  - **State of the system**: game created with 2 players
  - **Expected output**: `getTotalPlayerCount() == 2`

- **TC12: Maximum Total Players** ( :x: )
  - **State of the system**: game created with 5 players
  - **Expected output**: `getTotalPlayerCount() == 5`

### Method under test: `getAlivePlayerCount()`
- **TC13: All Players Alive** ( :x: )
  - **State of the system**: no players eliminated; game created with 2 players
  - **Expected output**: `getAlivePlayerCount() == 2`

- **TC14: One Player Eliminated** ( :x: )
  - **State of the system**: one player removed from `alivePlayers`; game created with 2 players
  - **Expected output**: `getAlivePlayerCount() == 1`

- **TC15: Last Player Remaining** ( :x: )
  - **State of the system**: all but one player eliminated; game created with 5 players
  - **Expected output**: `getAlivePlayerCount() == 1`

### Method under test: `getTotalPlayers()`
- **TC16: Returns Correct Players** ( :x: )
  - **State of the system**: game created with 3 players
  - **Expected output**: returned list contains exactly the 3 players created at construction

### Method under test: `getAlivePlayers()`
- **TC17: Returns All Players When None Eliminated** ( :x: )
  - **State of the system**: game created with 3 players; no eliminations
  - **Expected output**: returned list contains all 3 players

- **TC18: Returns Reduced List After Elimination** ( :x: )
  - **State of the system**: game created with 3 players; one player removed from `alivePlayers`
  - **Expected output**: returned list contains the 2 remaining players

### Method under test: `removeAlivePlayer(Player player)`
- **TC19: Remove Player From List With Many Players** ( :x: )
  - **State of the system**: game created with 5 players; no eliminations
  - **Expected output**: `alivePlayers` contains 4 players; removed player is no longer present

- **TC20: Remove Player From List With One Player** ( :x: )
  - **State of the system**: game created with 2 players; one player already eliminated; one player remaining in `alivePlayers`
  - **Expected output**: `alivePlayers` is empty

- **TC21: Remove Player From Empty List** ( :x: )
  - **State of the system**: all players already eliminated; `alivePlayers` is empty
  - **Expected output**: throws an exception

- **TC22: Remove Player Not In Alive List** ( :x: )
  - **State of the system**: game created with 2 players; target player already eliminated
  - **Expected output**: throws an exception