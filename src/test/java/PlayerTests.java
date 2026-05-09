import org.easymock.EasyMock;
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

    @Test
    void hasDefuse_OneCardwithDefuse_True() {
        Player player = new Player("lily");

        Card card = EasyMock.createMock(Card.class);
        EasyMock.expect(card.getType()).andStubReturn(CardType.DEFUSE);
        EasyMock.replay(card);

        player.addCard(card);

        assertTrue(player.hasDefuse());
    }

    @Test
    void hasDefuse_TwoCardswithoutDefuse_False() {
        Player player = new Player("lily");

        Card card1 = EasyMock.createMock(Card.class);
        EasyMock.expect(card1.getType()).andStubReturn(CardType.TEST_TYPE);
        EasyMock.replay(card1);

        Card card2 = EasyMock.createMock(Card.class);
        EasyMock.expect(card2.getType()).andStubReturn(CardType.TEST_TYPE);
        EasyMock.replay(card2);

        player.addCard(card1);
        player.addCard(card2);

        assertFalse(player.hasDefuse());
    }
}
