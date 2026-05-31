package domain;

public class GameController {
    private Game game;
    private int currentPlayerIndex;

    GameController(Game game) {
        this.game = game;
    }

    public Game getGame() { // trivial setter so no BVA needed
        return this.game;
    }

    public void setCurrentPlayerIndex(int current_player_index) {
        if (current_player_index < 0 || current_player_index >= this.game.getAlivePlayerCount()) {
            throw new IllegalArgumentException("Invalid player index: " + current_player_index);
        }

        this.currentPlayerIndex = current_player_index;
    }

    public int getCurrentPlayerIndex() { // simple getter so no BVA needed
        return this.currentPlayerIndex;
    }
}
