import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String playerName;
    private final List<Card> cards;
    private boolean isAlive;

    public Player(String playerName) {
        if (playerName == null) {
            throw new IllegalArgumentException("player name can not be null");
        }
        if (playerName.isEmpty()) {
            throw new IllegalArgumentException("player name can not be empty");
        }
        this.playerName = playerName;
        this.cards = new ArrayList<>();
        this.isAlive = true;
    }

    public String getPlayerName() { return playerName; }
    public List<Card> getCards() { return cards; }
    public boolean isAlive() { return isAlive; }
}
