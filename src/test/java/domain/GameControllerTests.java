package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class GameControllerTests {
    @Test
    void constructorWithValidGame() {
        Game myGame = new Game(4);
        GameController controller = new GameController(myGame);

        assertSame(myGame, controller.getGame());
    }
}
