package domain;

public class GameController {
    private Game game;
    private Player currentPlayer;

    GameController(Game game) {
        this.game = game;
    }

    public Game getGame() { // trivial setter so no BVA needed
        return this.game;
    }

    public void  setCurrentPlayerIndex(int current_player_index) {
        throw new IllegalArgumentException("Invalid player index: " + current_player_index);
    }
}
