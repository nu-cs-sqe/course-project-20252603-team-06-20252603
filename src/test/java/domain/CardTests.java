package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTests {

    @Test
    void constructor_NullTypeThrowsIllegalArgumentException() {
        CardType type = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Card.createCard(type);
        });

        assertEquals("need a card type!", exception.getMessage());
    }


    @Test
    void constructor_ValidType_CreatesCard() {
        CardType type = CardType.TEST_TYPE;

        Card card = Card.createCard(type);

        assertEquals(CardType.TEST_TYPE, card.getType());
    }
}

