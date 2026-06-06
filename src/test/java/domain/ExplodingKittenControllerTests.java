package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ExplodingKittenControllerTests {

    @Test
    void executeCardAction_noDefuseEmptyHand_playerKilled() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);

        ExplodingKittenController controller = new ExplodingKittenController();
        controller.executeCardAction(game, initiator, Optional.empty());

        assertFalse(initiator.isAlive());
        assertEquals(new ArrayList<>(), initiator.getHand());
    }
}
