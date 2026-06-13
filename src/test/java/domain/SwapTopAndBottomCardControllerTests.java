package domain;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class SwapTopAndBottomCardControllerTests {
    @Test
    void executeCardAction_EmptyDeck_ThrowsException() {
        Game game = Game.createGame(2);
        GameController gc = new GameController(game);
        Player user = game.getAlivePlayers().get(0);

        while (game.getDeck().count() > 0) {
            game.getDeck().takeTopCard();
        }

        SwapTopAndBottomCardController controller = new SwapTopAndBottomCardController();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            controller.executeCardAction(gc, user, Optional.empty());
        });
        assertEquals("not enough cards to swap", exception.getMessage());
    }

    @Test
    void executeCardAction_OneCardDeck_ThrowsException() {
        Game game = Game.createGame(2);
        GameController gc = new GameController(game);
        Player user = game.getAlivePlayers().get(0);

        while (game.getDeck().count() > 1) {
            game.getDeck().takeTopCard();
        }

        SwapTopAndBottomCardController controller = new SwapTopAndBottomCardController();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            controller.executeCardAction(gc, user, Optional.empty());
        });
        assertEquals("not enough cards to swap", exception.getMessage());
    }
}
