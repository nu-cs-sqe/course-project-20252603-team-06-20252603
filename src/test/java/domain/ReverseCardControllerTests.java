package domain;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class ReverseCardControllerTests {

    @Test
    void executeCardAction_TwoPlayers_ReversesOrderAndUpdatesIndices() {
        Game game = Game.createGame(2);
        GameController gc = new GameController(game);
        gc.setCurrentPlayerIndex(0);
        gc.setNextPlayerIndex(1);

        Player p1 = game.getAlivePlayers().get(0);
        Player p2 = game.getAlivePlayers().get(1);

        ReverseCardController controller = new ReverseCardController();
        controller.executeCardAction(gc, p1, Optional.empty());

        assertEquals(p2, game.getAlivePlayers().get(0));
        assertEquals(p1, game.getAlivePlayers().get(1));

        assertEquals(1, gc.getCurrentPlayerIndex());
        assertEquals(0, gc.getNextPlayerIndex());
    }

    @Test
    void executeCardAction_ThreePlayers_ReversesOrderAndUpdatesIndices() {
        Game game = Game.createGame(3);
        GameController gc = new GameController(game);
        gc.setCurrentPlayerIndex(1);
        gc.setNextPlayerIndex(2);

        Player p1 = game.getAlivePlayers().get(0);
        Player p2 = game.getAlivePlayers().get(1);
        Player p3 = game.getAlivePlayers().get(2);

        ReverseCardController controller = new ReverseCardController();
        controller.executeCardAction(gc, p2, Optional.empty());

        assertEquals(p3, game.getAlivePlayers().get(0));
        assertEquals(p2, game.getAlivePlayers().get(1));
        assertEquals(p1, game.getAlivePlayers().get(2));

        assertEquals(1, gc.getCurrentPlayerIndex());
        assertEquals(2, gc.getNextPlayerIndex());
    }
}