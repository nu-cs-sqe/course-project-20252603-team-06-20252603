package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class GameControllerTests {
    @Test
    void constructor_Valid_Game_MakesGameController() {
        Game myGame = new Game(4);
        GameController controller = new GameController(myGame);

        assertSame(myGame, controller.getGame());
    }

    @Test
    void setCurrentPlayerIndex_InvalidIndexBelowMinimum_ThrowsIllegalArgumentException() {
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

    @Test
    void setCurrentPlayerIndex_InvalidIndexAboveMaximum_ThrowsIllegalArgumentException() {
        Game game = new Game(3);
        GameController controller = new GameController(game);
        int invalidIndex = 3;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.setCurrentPlayerIndex(invalidIndex);
        });

        String expectedMessage = "Invalid player index: " + invalidIndex;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setCurrentPlayerIndex_ValidIndexAtMinimum_SetsCurrentPlayerIndexAsInput() {
        Game game = new Game(3);
        GameController controller = new GameController(game);
        int validMinIndex = 0;

        controller.setCurrentPlayerIndex(validMinIndex);

        assertEquals(validMinIndex, controller.getCurrentPlayerIndex());
    }

    @Test
    void setCurrentPlayerIndex_ValidIndexAtMaximum_SetsCurrentPlayerIndexAsInput() {
        Game game = new Game(3);
        GameController controller = new GameController(game);
        int validMaxIndex = controller.getGame().getAlivePlayerCount() - 1;

        controller.setCurrentPlayerIndex(validMaxIndex);

        assertEquals(validMaxIndex, controller.getCurrentPlayerIndex());
    }

    @Test
    void isValidMove_EmptyCards_ReturnsFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player1 = game.getAlivePlayers().get(0);

        ArrayList<Card> cards = new ArrayList<Card>();

        assertFalse(controller.isValidMove(cards, player1, Optional.empty()));
    }

}
