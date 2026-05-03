import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardTests {

    @Test
    void constructor_NullTypeNullAction_ThrowsIllegalArgumentException() {
        CardType type = null;
        Action action =  null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Card(type, action);
        });

        assertEquals("need a card type and an action", exception.getMessage());
    }
}

