import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {

    @Test
    void Constructor_EmptyName_IllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Player("");
        });

        assertEquals("player name cannot be empty", exception.getMessage());
    }

    @Test
    void Constructor_ValidName_success() {
        Player player = new Player("lily");

        assertEquals("lily", player.getPlayerName());
        assertTrue(player.getHand().isEmpty());
        assertTrue(player.isAlive());
    }

    @Test
    void hasDefuse_EmptyHand_False() {
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

    @Test
    void hasDefuse_duplicateDefuse_True() {
        Player player = new Player("lily");

        Card card1 = EasyMock.createMock(Card.class);
        EasyMock.expect(card1.getType()).andStubReturn(CardType.DEFUSE);
        EasyMock.replay(card1);

        Card card2 = EasyMock.createMock(Card.class);
        EasyMock.expect(card2.getType()).andStubReturn(CardType.DEFUSE);
        EasyMock.replay(card2);

        player.addCard(card1);
        player.addCard(card2);

        assertTrue(player.hasDefuse());
    }

    @Test
    void addCard_noCards_success() {
        Player player = new Player("lily");

        Card card = EasyMock.createMock(Card.class);
        EasyMock.replay(card);

        player.addCard(card);
        assertEquals(1, player.getHand().size());
        assertTrue(player.getHand().contains(card));
    }

    @Test
    void addCard_1cardAddNewCard_success() {
        Player player = new Player("lily");

        Card card1 = EasyMock.createMock(Card.class);
        EasyMock.replay(card1);

        Card card2 = EasyMock.createMock(Card.class);
        EasyMock.replay(card2);

        player.addCard(card1);
        player.addCard(card2);

        assertEquals(2, player.getHand().size());
        assertTrue(player.getHand().contains(card1));
        assertTrue(player.getHand().contains(card2));
    }

    @Test
    void addCard_2cardDuplicateCards_success() {
        Player player = new Player("lily");

        Card card1 = EasyMock.createMock(Card.class);
        EasyMock.replay(card1);

        Card card2 = EasyMock.createMock(Card.class);
        EasyMock.replay(card2);

        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card1);

        List<Card> hand = player.getHand();
        assertEquals(3, hand.size());
        assertEquals(card1, hand.get(0));
        assertEquals(card2, hand.get(1));
        assertEquals(card1, hand.get(2));
    }

    @Test
    void kill_Success() {
        Player player = new Player("lily");

        player.kill();
        assertFalse(player.isAlive());
    }

    @Test
    void kill_IllegalStateException() {
        Player player = new Player("lily");

        player.kill();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            player.kill();
        });

        assertEquals("cannot kill dead player", exception.getMessage());
    }

    @Test
    void revive_Success() {
        Player player = new Player("lily");
        player.kill();

        player.revive();
        assertTrue(player.isAlive());
    }

    @Test
    void revive_IllegalStateException() {
        Player player = new Player("lily");

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            player.revive();
        });

        assertEquals("cannot revive alive player", exception.getMessage());
    }

    @Test
    void removeCard_NormalCardOneCard_CardRemoved() {
        Player player = new Player("lily");

        Card card = EasyMock.createMock(Card.class);
        EasyMock.expect(card.getType()).andStubReturn(CardType.TEST_TYPE);
        EasyMock.replay(card);

        player.addCard(card);
        player.removeCard(card);

        assertEquals(0, player.getHand().size());
    }

    @Test
    void removeCard_NormalCardTwoCards_CardRemoved() {
        Player player = new Player("lily");

        Card card1 = EasyMock.createMock(Card.class);
        EasyMock.expect(card1.getType()).andStubReturn(CardType.TEST_TYPE);

        Card card2 = EasyMock.createMock(Card.class);
        EasyMock.expect(card2.getType()).andStubReturn(CardType.TEST_TYPE);

        EasyMock.replay(card1, card2);

        player.addCard(card1);
        player.addCard(card2);
        player.removeCard(card1);

        assertEquals(1, player.getHand().size());
        assertTrue(player.getHand().contains(card2));
    }

    @Test
    void removeCard_noCards_IllegalStateException() {
        Player player = new Player("lily");

        Card card = EasyMock.createMock(Card.class);
        EasyMock.expect(card.getType()).andStubReturn(CardType.TEST_TYPE);
        EasyMock.replay(card);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            player.removeCard(card);
        });

        assertEquals("cannot remove from empty hand", exception.getMessage());
    }

    @Test
    void removeCard_cardNotInHand_IllegalArgumentException() {
        Player player = new Player("lily");
        Card testCard = EasyMock.createMock(Card.class);
        EasyMock.expect(testCard.getType()).andStubReturn(CardType.TEST_TYPE);

        Card defuseCard = EasyMock.createMock(Card.class);
        EasyMock.expect(defuseCard.getType()).andStubReturn(CardType.DEFUSE);
        EasyMock.replay(testCard, defuseCard);

        player.addCard(testCard);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            player.removeCard(defuseCard);
        });

        assertEquals("card to remove not in hand", exception.getMessage());
    }

    @Test
    void takeTurn_NormalCardNoCardsInHand_CardAddedtoHand() {
        Player player = new Player("lily");

        Card card = EasyMock.createMock(Card.class);
        EasyMock.expect(card.getType()).andStubReturn(CardType.TEST_TYPE);
        EasyMock.replay(card);

        player.takeTurn(card);

        assertEquals(1, player.getHand().size());
        assertTrue(player.getHand().contains(card));
        assertTrue(player.isAlive());
    }

    @Test
    void takeTurn_NormalCardOneCardInHand_CardAddedtoHand() {
        Player player = new Player("lily");

        Card card1 = EasyMock.createMock(Card.class);
        EasyMock.expect(card1.getType()).andStubReturn(CardType.TEST_TYPE);

        Card card2 = EasyMock.createMock(Card.class);
        EasyMock.expect(card2.getType()).andStubReturn(CardType.TEST_TYPE);

        EasyMock.replay(card1, card2);

        player.addCard(card1);

        player.takeTurn(card2);

        assertEquals(2, player.getHand().size());
        assertTrue(player.getHand().contains(card1));
        assertTrue(player.getHand().contains(card2));
        assertTrue(player.isAlive());
    }
}