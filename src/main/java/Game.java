import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Game {
    private Deck deck;
    private List<Player> players;
    private int currentPlayer;
    private int playersAlive;

    private static final int MIN_PLAYERS    = 2;
    private static final int MAX_PLAYERS    = 5;
    private static final int TOTAL_DEFUSES  = 5;
    private static final int CARDS_PER_PLAYER = 7; // + 1 defuse = 8 total hand size

    private static final int INDEX_FOR_NO_CURRENT_PLAYER_YET = -1;

    public Game(int playerCount) {
        this.playersAlive = playerCount;
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
        this.currentPlayer = INDEX_FOR_NO_CURRENT_PLAYER_YET;
    }

    // Package-private: only visible within the same package, used to inject mocks in tests
    Game(Deck deck, List<Player> players) {
        this.deck = deck;
        this.players = new ArrayList<>(players);
        this.currentPlayer = INDEX_FOR_NO_CURRENT_PLAYER_YET;
        this.playersAlive = players.size();
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

    public void runGame(Shuffler shuffler) {
        currentPlayer = 0;

        shuffler.shuffle(this.players);

        while (playersAlive > 1) {

            Player activePlayer = this.players.get(currentPlayer);
            if (activePlayer.isAlive()) {
                activePlayer.takeTurn();
            }

            currentPlayer += 1;
            if (currentPlayer == players.size()){
                currentPlayer = 0;
                break; // will remove once we move past one-turn functionality
            }
        }
    }

    public int getPlayerCount() {
        return players.size();
    }

    public Player getCurrentPlayer() {
        if (currentPlayer == INDEX_FOR_NO_CURRENT_PLAYER_YET) {
            return null;
        }
        return this.players.get(currentPlayer);
    }

    public static class Shuffler {
        public void shuffle(List<?> list) {
            Collections.shuffle(list);
        }
    }
}


