import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String playerName;
    private final List<Card> hand;
    private boolean isAlive;

    public Player(String playerName) {
        if (playerName == null) {
            throw new IllegalArgumentException("player name cannot be null");
        }
        if (playerName.isEmpty()) {
            throw new IllegalArgumentException("player name cannot be empty");
        }
        this.playerName = playerName;
        this.hand = new ArrayList<>();
        this.isAlive = true;
    }

    public String getPlayerName() { return playerName; }
    public List<Card> getHand() { return hand; }
    public boolean isAlive() { return isAlive; }

    public void addCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("card cannot be null");
        }

        hand.add(card);
    }

    public boolean hasDefuse() {
        for (Card card : hand) {
            if (card.getType() == CardType.DEFUSE) {
                return true;
            }
        }
        return false;
    }

    public void setLife(boolean alive) {
        if (alive && !isAlive) {
            throw new IllegalStateException("cannot resurrect a dead player");
        }

        this.isAlive = alive;
    }

    public void removeCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("card cannot be null");
        }
        if (this.hand.isEmpty()) {
            throw new IllegalStateException("cannot remove from empty hand");
        }
        if (!hand.contains(card)) {
            throw new IllegalArgumentException("card to remove not in hand");
        }

        this.hand.remove(card);
    }

    public void takeTurn(Card card) {
        this.hand.add(card);
    }
}
