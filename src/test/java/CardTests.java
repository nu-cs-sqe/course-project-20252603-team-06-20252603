import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTests {

    @Test
    void constructor_NullTypeNullAction_ThrowsIllegalArgumentException() {
        CardType type = null;
        Action action =  null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Card(type, action);
        });

        assertEquals("need a card type and an action!", exception.getMessage());
    }

    @Test
    void constructor_NullTypeAndAction_ThrowsIllegalArgumentException() {
        CardType type = null;
        Action action =  new Action() {};;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Card(type, action);
        });

        assertEquals("need a card type!", exception.getMessage());
    }

    @Test
    void constructor_ValidTypeNullAction_CreatesCard() {
        CardType type = CardType.TEST_TYPE;
        Action action = null;

        Card card = new Card(type, action);

        assertEquals(CardType.TEST_TYPE, card.getType());
        assertNull(card.getAction());
    }

    @Test
    void constructor_ValidTypeAndAction_CreatesCard() {
        CardType type = CardType.TEST_TYPE;
        Action action = new Action() {};

        Card card = new Card(type, action);

        assertEquals(CardType.TEST_TYPE, card.getType());
        assertNotNull(card.getAction());
        assertEquals(action, card.getAction());
    }

    //moving onto play tests

}

