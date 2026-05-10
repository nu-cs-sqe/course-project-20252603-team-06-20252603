import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {
    @Test
    public void constructor_ZeroPlayers_IllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Game(0);
        });
    }

    @Test
    public void constructor_OnePlayer_IllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Game(1);
        });
    }

    @Test
    public void constructor_SixPlayers_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Game(6);
        });
    }

    @Test
    public void constructor_TwoPlayers_CreatesTwoPlayersWithNullCurrentPlayer() {
        Game game = new Game(2);
        assertEquals(2, game.getPlayerCount());
        assertNull(game.getCurrentPlayer());
    }

}
