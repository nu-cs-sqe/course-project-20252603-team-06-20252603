import java.util.ArrayList;
import java.util.List;

public class Game {
    private Deck deck;
    private List<Player> players;
    private Player currentPlayer;

    private static final int MIN_PLAYERS    = 2;
    private static final int MAX_PLAYERS    = 5;
    private static final int TOTAL_DEFUSES  = 5;
    private static final int CARDS_PER_PLAYER = 7; // + 1 defuse = 8 total hand size

    public Game(int playerCount) {
        if (playerCount < MIN_PLAYERS) {
            throw new IllegalArgumentException("Cannot initiate game with less than 2 players");
        }
        if (playerCount > MAX_PLAYERS) {
            throw new IllegalArgumentException("Cannot initiate game with more than 5 players");
        }
        this.players = new ArrayList<>();
        for (int i = 1; i <= playerCount; i++) {
            players.add(new Player("Player " + i));
        }
        this.deck = new Deck();
        this.currentPlayer = null;
    }

    // Package-private: only visible within the same package, used to inject mocks in tests
    Game(Deck deck, List<Player> players) {
        this.deck = deck;
        this.players = new ArrayList<>(players);
        this.currentPlayer = null;
    }

    public void setup() {
        int cardsNeeded = players.size() * CARDS_PER_PLAYER;
        if (deck.count() < cardsNeeded) {
            throw new IllegalStateException("Not enough cards in deck to deal starting hands");
        }
        for (Player player : players) {
            player.addCard(deck.drawDefuse());
            for (int i = 0; i < CARDS_PER_PLAYER; i++) {
                player.addCard(deck.draw());
            }
        }
    }

    public int getPlayerCount() {
        return players.size();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
