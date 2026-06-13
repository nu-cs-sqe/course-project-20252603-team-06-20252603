package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TargetAttackControllerTests {
    @Test
    public void executeCardAction_EmptyTarget_ThrowsException() {
        GameController mockController = EasyMock.createMock(GameController.class);
        Player mockUser = EasyMock.createMock(Player.class);

        EasyMock.replay(mockController, mockUser);

        TargetAttackController controller = new TargetAttackController();
        assertThrows(IllegalArgumentException.class, () ->
                controller.executeCardAction(mockController, mockUser, Optional.empty()));

        EasyMock.verify(mockController, mockUser);
    }
}
