package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardTypeTests {
    @Test
    public void CatCard1_ReturnsTrue(){
        CardType type = CardType.CAT_CARD_1;
        assertTrue(type.canHaveTarget());
    }
}
