package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BountyCardControllerTests {

    @Test
    public void executeCardAction_OneOtherPlayerZeroCards_ReturnsEmpty() {
        Game game = EasyMock.createMock(Game.class);
        Player user = EasyMock.createMock(Player.class);
        Player other = EasyMock.createMock(Player.class);

        EasyMock.replay(game, user, other);

        BountyCardController controller = new BountyCardController();
        Optional<List<Card>> result = controller.executeCardAction(game, user, Optional.empty());

        assertTrue(result.isEmpty());
        EasyMock.verify(game, user, other);
    }
}