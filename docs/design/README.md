# DECK 
Purpose: handles the collection and manipulation of the cards

Properties: 
- cards: List<Card>

Methods:
- draw(): Card
- drawDefuse(): Card - Used for setup purposes at the beginning of the Game (each player gets a Defuse card).
- shuffle(): void
- insert(): void
- discard(): Card
- peek(): List<Card>
- count(): int

# CARD
Purpose: Base bottom line representation of a card in Exploding Wildkittens

Properties:
- CardType: enum
- Action: type

Methods: 
- Constructor()

# Player 
Purpose: Represents each player in the game + their current state

Properties:
- name: String
- cards: List<Card>
- isAlive: boolean

Methods:
- hasDefuse(): boolean
- takeTurn(): void
- addCard(card: Card): void
- setLife(): void

# Game 
Purpose: The main class in the game, which represents the game itself

Properties:
- deck: Deck
- players: List<Player>
- currentPlayer: Player

Methods:
- setup(): void
- startGame(): void
- endGame(): void
- isEndOfGame(): boolean
- pickFirstPlayer(): Player

Implementation notes:
- Create a controller class for each type of card, in which card logic will live. Card logic will NOT be in the Game or Card classes
- Game will have access to all controllers and all of the cards. Once a card is played by a player, game delegates the card's execution to its respective controller