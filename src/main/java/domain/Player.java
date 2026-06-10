package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String playerName;
    private final List<Card> hand;
    private boolean isAlive;

    Player(String playerName) {
        this.playerName = playerName;
        this.hand = new ArrayList<>();
        this.isAlive = true;
    }

    public static Player createPlayer(String playerName) {
        if (playerName.isEmpty()) {
            throw new IllegalArgumentException("player name cannot be empty");
        }

        return new Player(playerName);
    }

    public String getPlayerName() { return playerName; }
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

    public void revive() {
        if (isAlive) {
            throw new IllegalStateException("cannot revive alive player");
        }
        isAlive = true;
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

    public ArrayList<Card> getHand() {
        return new ArrayList<Card>(this.hand);
    }

    public int getHandSize() {
        return hand.size();
    }

    public boolean hasCard(CardType type) {
        for (Card card : hand) {
            if (card.getType() == type) {
                return true;
            }
        }
        return false;
    }
}
