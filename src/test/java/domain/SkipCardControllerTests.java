import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import domain.Card;
import domain.Game;
import domain.Player;
import domain.SkipCardController;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SkipCardControllerTests {

    @Test
    public void executeCardAction_NoTarget_ReturnsEmpty() {
        Game game = EasyMock.createMock(Game.class);
        Player user = EasyMock.createMock(Player.class);
        SkipCardController controller = new SkipCardController();

        EasyMock.replay(game, user);

        Optional<List<Card>> result = controller.executeCardAction(game, user, Optional.empty());

        assertTrue(result.isEmpty());
        EasyMock.verify(game, user);
    }
}