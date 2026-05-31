package domain;

public class GameController {
    private Game game;

    GameController(Game game) {
        this.game = game;
    }

    public Game getGame() { // trivial setter so no BVA needed
        return this.game;
    }
}
