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
    }
}
