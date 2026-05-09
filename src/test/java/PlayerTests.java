import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTests {

    @Test
    void Constructor_nullName_IllegalArgumentException() {
        String Playername = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Player(Playername);
        });

        assertEquals("player name can not be null", exception.getMessage());
    }

    @Test
    void Constructor_EmptyName_IllegalArgumentException() {
        String Playername = "";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Player(Playername);
        });

        assertEquals("player name can not be empty", exception.getMessage());
    }
}
