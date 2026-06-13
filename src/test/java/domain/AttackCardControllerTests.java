package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttackCardControllerTests {

    @Test
    void executeCardAction_OneTurnLeft_EndsCurrentAndGivesNextThree() {
        GameController mockController = EasyMock.createMock(GameController.class);
        Player mockUser = EasyMock.createMock(Player.class);

        mockController.setNextPlayerTurnsLeft(3);
        EasyMock.expectLastCall();
        mockController.setCurrentPlayerTurnsLeft(0);
        EasyMock.expectLastCall();

        EasyMock.replay(mockController, mockUser);

        AttackCardController controller = new AttackCardController();
        Optional<List<Card>> result =
                controller.executeCardAction(mockController, mockUser, Optional.empty());

        assertTrue(result.isEmpty());
        EasyMock.verify(mockController, mockUser);
    }
}
