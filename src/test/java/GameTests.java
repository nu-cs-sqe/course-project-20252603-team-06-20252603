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

    @Test
    public void constructor_ThreePlayers_CreatesThreePlayers() {
        assertEquals(3, new Game(3).getPlayerCount());
    }

    @Test
    public void constructor_FourPlayers_CreatesFourPlayers() {
        assertEquals(4, new Game(4).getPlayerCount());
    }

    @Test
    public void constructor_FivePlayers_CreatesFivePlayers() {
        assertEquals(5, new Game(5).getPlayerCount());
    }
}
