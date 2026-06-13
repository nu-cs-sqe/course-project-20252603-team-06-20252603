package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void executeCardAction_AliveTarget_ReturnEmptyAndAssignsTwoTurns() {
        GameController mockController = EasyMock.createMock(GameController.class);
        Player mockUser = EasyMock.createMock(Player.class);
        Player mockTarget = EasyMock.createMock(Player.class);

        EasyMock.expect(mockTarget.isAlive()).andReturn(true);
        mockController.setNextPlayerTurnsLeft(2);
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockController, mockUser, mockTarget);

        TargetAttackController controller = new TargetAttackController();
        Optional<List<Card>> result = controller.executeCardAction(
                mockController, mockUser, Optional.of(mockTarget));

        assertTrue(result.isEmpty());
        EasyMock.verify(mockController, mockUser, mockTarget);
    }

    @Test
    public void executeCardAction_TargetIsInitiator_ThrowsException() {
        GameController mockController = EasyMock.createMock(GameController.class);
        Player mockUser = EasyMock.createMock(Player.class);

        EasyMock.expect(mockUser.isAlive()).andReturn(true);

        EasyMock.replay(mockController, mockUser);

        TargetAttackController controller = new TargetAttackController();
        assertThrows(IllegalArgumentException.class, () ->
                controller.executeCardAction(mockController, mockUser, Optional.of(mockUser)));

        EasyMock.verify(mockController, mockUser);
    }

    @Test
    public void executeCardAction_DeadTarget_ThrowsException() {
        GameController mockController = EasyMock.createMock(GameController.class);
        Player mockUser = EasyMock.createMock(Player.class);
        Player mockTarget = EasyMock.createMock(Player.class);

        EasyMock.expect(mockTarget.isAlive()).andReturn(false);

        EasyMock.replay(mockController, mockUser, mockTarget);

        TargetAttackController controller = new TargetAttackController();
        assertThrows(IllegalArgumentException.class, () ->
                controller.executeCardAction(mockController, mockUser, Optional.of(mockTarget)));

        EasyMock.verify(mockController, mockUser, mockTarget);
    }
}
