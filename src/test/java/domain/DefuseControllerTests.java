package domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DefuseControllerTests {
    @Test
    public void executeCardAction_defusePlayed_throwIllegalStateException() {
        Game game = new Game(2);
        Player user = game.getTotalPlayers().get(0);

        DefuseController controller = new DefuseController();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            controller.executeCardAction(game, user, Optional.empty());
        });

        assertEquals("defuse cards can not be played directly", exception.getMessage());
    }
}
