# BVA analysis for Game

### Method under test: `createGame(int playerCount)`
- **TC1: One Below Minimum Players** ( ☑️ )
  - **State of the system**: `playerCount = 1`
  - **Expected output**: throws `IllegalArgumentException`

- **TC2: Minimum Players** ( ☑️ )
  - **State of the system**: `playerCount = 2`
  - **Expected output**: `Game` object created with 2 players in `totalPlayers` and `alivePlayers`

- **TC3: Maximum Players** ( ☑️ )
  - **State of the system**: `playerCount = 5`
  - **Expected output**: `Game` object created with 5 players in `totalPlayers` and `alivePlayers`

- **TC4: One Above Maximum Players** (  ☑️  )
  - **State of the system**: `playerCount = 6`
  - **Expected output**: throws `IllegalArgumentException`
  
### Method under test: `setup()`
- **TC5: Setup With Minimum Players** (  ☑️  )
  - **State of the system**: game created with 2 players; default deck
  - **Expected output**: each player has 8 cards (7 normal + 1 DEFUSE); deck contains 1 EXPLODING_KITTEN

- **TC6: Setup With Maximum Players** ( ☑️ )
  - **State of the system**: game created with 5 players; default deck
  - **Expected output**: each player has 8 cards (7 normal + 1 DEFUSE); deck contains 4 EXPLODING_KITTENs

### Method under test: `draw(Player player, Deck deck)`
- **TC7: Draw From Deck With One Card** ( ☑️ )
  - **State of the system**: `deck` contains exactly 1 card; `player` has 0 cards
  - **Expected output**: player receives the card; deck is now empty

- **TC8: Draw From Empty Deck** ( ☑️ )
  - **State of the system**: `deck` contains 0 cards; `player` has 0 cards
  - **Expected output**: throws an exception

- **TC9: Draw From Deck With Many Cards** ( ☑️  )
  - **State of the system**: `deck` contains more than 1 card; `player` has 0 cards
  - **Expected output**: player receives only the top card; deck size decreases by 1

### Method under test: `getTotalPlayerCount()`
- **TC10: Minimum Total Players** ( ☑️ )
  - **State of the system**: game created with 2 players
  - **Expected output**: `getTotalPlayerCount() == 2`

- **TC11: Maximum Total Players** ( ☑️ )
  - **State of the system**: game created with 5 players
  - **Expected output**: `getTotalPlayerCount() == 5`

### Method under test: `getAlivePlayerCount()`
- **TC12: All Players Alive** (  ☑️ )
  - **State of the system**: no players eliminated; game created with 2 players
  - **Expected output**: `getAlivePlayerCount() == 2`

- **TC13: One Player Eliminated** (  ☑️  )
  - **State of the system**: one player removed from `alivePlayers`; game created with 2 players
  - **Expected output**: `getAlivePlayerCount() == 1`

- **TC14: Last Player Remaining** ( ☑️ )
  - **State of the system**: all but one player eliminated; game created with 5 players
  - **Expected output**: `getAlivePlayerCount() == 1`

### Method under test: `getTotalPlayers()`
- **TC15: Returns Correct Players** ( ☑️  )
  - **State of the system**: game created with 2 players
  - **Expected output**: returned list contains exactly the 2 players created at construction

### Method under test: `getAlivePlayers()`
- **TC16: Returns All Players When None Eliminated** ( ☑️ )
  - **State of the system**: game created with 5 players; no eliminations
  - **Expected output**: returned list contains all 5 players

- **TC17: Returns Reduced List After Elimination** ( ☑️ )
  - **State of the system**: game created with 5 players; one player removed from `alivePlayers`
  - **Expected output**: returned list contains the 4 remaining players
       
- **TC19: Remove Player From List With One Player** ( ☑️ )
  - **State of the system**: game created with 2 players; one player already eliminated; one player remaining in `alivePlayers`
  - **Expected output**: `alivePlayers` is empty

- **TC20: Remove Player From Empty List** ( ☑️ )
  - **State of the system**: all players already eliminated; `alivePlayers` is empty
  - **Expected output**: throws an exception

- **TC21: Remove Player Not In Alive List** (  ☑️ )
  - **State of the system**: game created with 2 players; target player already eliminated
  - **Expected output**: throws an exception
  - 
### Method under test: `addAlivePlayer(Player player)`
- **TC22: Add Player Back To List With No Alive Players** ( ☑️ )
  - **State of the system**: game created with 2 players; both players eliminated; `alivePlayers` is empty
  - **Expected output**: `alivePlayers` contains 1 player

- **TC23: Add Player Back To List With Some Alive Players** ( ☑️ )
  - **State of the system**: game created with 5 players; one player eliminated; 4 players in `alivePlayers`
  - **Expected output**: `alivePlayers` contains 5 players

- **TC24: Add Player Not In Game** ( ☑️ )
  - **State of the system**: game created with 2 players; player being added was never in `totalPlayers`
  - **Expected output**: throws `IllegalArgumentException`

- **TC25: Add Player Already Alive** ( ☑️ )
  - **State of the system**: game created with 2 players; no eliminations; target player is already in `alivePlayers`
  - **Expected output**: throws `IllegalArgumentException`