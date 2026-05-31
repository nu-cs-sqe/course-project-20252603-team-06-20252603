package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GameControllerTests {
    @Test
    void constructor_Valid_Game_MakesGameController() {
        Game myGame = new Game(4);
        GameController controller = new GameController(myGame);

        assertSame(myGame, controller.getGame());
    }

    @Test
    void setCurrentPlayerIndex_InvalidIndex_ThrowsIllegalArgumentException() {
        Game game = new Game(3);
        GameController controller = new GameController(game);
        int invalidIndex = -1;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.setCurrentPlayerIndex(invalidIndex);
        });

        String expectedMessage = "Invalid player index: " + invalidIndex;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
