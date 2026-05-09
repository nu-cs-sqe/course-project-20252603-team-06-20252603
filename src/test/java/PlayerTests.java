import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {

    @Test
    void Constructor_nullName_IllegalArgumentException() {
        String playerName = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Player(playerName);
        });

        assertEquals("player name can not be null", exception.getMessage());
    }

    @Test
    void Constructor_EmptyName_IllegalArgumentException() {
        String playerName = "";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Player(playerName);
        });

        assertEquals("player name can not be empty", exception.getMessage());
    }

    @Test
    void Constructor_ValidName_success() {
        String playerName = "lily";
        Player player = new Player(playerName);

        assertEquals("lily", player.getPlayerName());
        assertTrue(player.getCards().isEmpty());
        assertTrue(player.isAlive());
    }
}
