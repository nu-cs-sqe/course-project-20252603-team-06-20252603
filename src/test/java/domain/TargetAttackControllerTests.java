package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TargetAttackControllerTests {
    @Test
    public void executeCardAction_EmptyTarget_ThrowsException() {
        Game game = EasyMock.createMock(Game.class);
        Player initiator = EasyMock.createMock(Player.class);

        EasyMock.replay(game, initiator);

        TargetAttackController controller = new TargetAttackController();
        assertThrows(IllegalArgumentException.class, () ->
                controller.executeCardAction(game, initiator, Optional.empty()));

        EasyMock.verify(game, initiator);
    }
}
