package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ExplodingKittenControllerTests {

    @Test
    void executeCardAction_noDefuseEmptyHand_playerKilled() {
        Game game = new Game(2);
        Player user = game.getTotalPlayers().get(0);

        ExplodingKittenController controller = new ExplodingKittenController();
        controller.executeCardAction(game, user, Optional.empty());

        assertFalse(user.isAlive());
        assertEquals(new ArrayList<>(), user.getHand());
    }
}

