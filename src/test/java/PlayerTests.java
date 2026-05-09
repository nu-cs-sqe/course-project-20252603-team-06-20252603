import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {

    @Test
    void Constructor_nullName_IllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Player(null);
        });

        assertEquals("player name can not be null", exception.getMessage());
    }

    @Test
    void Constructor_EmptyName_IllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Player("");
        });

        assertEquals("player name can not be empty", exception.getMessage());
    }

    @Test
    void Constructor_ValidName_success() {
        Player player = new Player("lily");

        assertEquals("lily", player.getPlayerName());
        assertTrue(player.getCards().isEmpty());
        assertTrue(player.isAlive());
    }

    @Test
    void hasDefuse_EmptyCards_False() {
        Player player = new Player("lily");

        assertFalse(player.hasDefuse());
    }
}
