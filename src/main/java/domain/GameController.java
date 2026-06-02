package domain;

import java.util.ArrayList;
import java.util.Optional;

public class GameController {
    private Game game;
    private int currentPlayerIndex;

    GameController(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        if (currentPlayerIndex < 0 || currentPlayerIndex >= this.game.getAlivePlayerCount()) {
            throw new IllegalArgumentException("Invalid player index: " + currentPlayerIndex);
        }

        this.currentPlayerIndex = currentPlayerIndex;
    }

    public int getCurrentPlayerIndex() { // simple getter so no BVA needed
        return this.currentPlayerIndex;
    }

    public boolean isValidMove(ArrayList<Card> cards, Player initiator, Optional<Player> target) {
        if (cards.isEmpty()) {
            return false;
        }
        return true;
    }
}
