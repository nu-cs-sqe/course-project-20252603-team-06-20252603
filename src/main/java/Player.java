import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String playerName;
    private final List<Card> hand;
    private boolean isAlive;

    public Player(String playerName) {
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
        hand.add(card);
    }

    public void kill() {
        if (!isAlive) {
            throw new IllegalStateException("cannot kill dead player");
        }
        isAlive = false;
    }

    public boolean hasDefuse() {
        for (Card card : hand) {
            if (card.getType() == CardType.DEFUSE) {
                return true;
            }
        }
        return false;
    }

    public void removeCard(Card card) {
        if (hand.isEmpty()) {
            throw new IllegalStateException("cannot remove from empty hand");
        }
        if (!hand.contains(card)) {
            throw new IllegalArgumentException("card to remove not in hand");
        }

        hand.remove(card);
    }

    public void takeTurn(Card card) {
        hand.add(card);
    }
}
