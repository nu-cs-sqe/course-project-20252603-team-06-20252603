package Code;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {

    @Test
    void Constructor_nullName_IllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Player(null);
        });

        assertEquals("player name cannot be null", exception.getMessage());
    }

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

        Card card = new Card(CardType.DEFUSE);

        player.addCard(card);

        assertTrue(player.hasDefuse());
    }

    @Test
    void hasDefuse_TwoCardswithoutDefuse_False() {
        Player player = new Player("lily");

        Card card1 = new Card(CardType.TEST_TYPE);

        Card card2 = new Card(CardType.TEST_TYPE);

        player.addCard(card1);
        player.addCard(card2);

        assertFalse(player.hasDefuse());
    }

    @Test
    void hasDefuse_duplicateDefuse_True() {
        Player player = new Player("lily");

        Card card1 = new Card(CardType.DEFUSE);

        Card card2 = new Card(CardType.DEFUSE);

        player.addCard(card1);
        player.addCard(card2);

        assertTrue(player.hasDefuse());
    }

    @Test
    void addCard_noCards_success() {
        Player player = new Player("lily");

        Card card = new Card(CardType.TEST_TYPE);

        player.addCard(card);
        assertEquals(1, player.getCards().size());
        assertTrue(player.getCards().contains(card));
    }

    @Test
    void addCard_1cardAddNewCard_success() {
        Player player = new Player("lily");

        Card card1 = new Card(CardType.TEST_TYPE);

        Card card2 = new Card(CardType.TEST_TYPE);

        player.addCard(card1);
        player.addCard(card2);

        assertEquals(2, player.getCards().size());
        assertTrue(player.getCards().contains(card1));
        assertTrue(player.getCards().contains(card2));
    }

    @Test
    void addCard_2cardDuplicateCards_success() {
        Player player = new Player("lily");

        Card card1 = new Card(CardType.TEST_TYPE);

        Card card2 = new Card(CardType.TEST_TYPE);

        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card1);

        List<Card> cards = player.getCards();
        assertEquals(3, cards.size());
        assertEquals(card1, cards.get(0));
        assertEquals(card2, cards.get(1));
        assertEquals(card1, cards.get(2));
    }

    @Test
    void addCard_AddNullCard_IllegalArgumentException() {
        Player player = new Player("lily");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            player.addCard(null);
        });

        assertEquals("card cannot be null", exception.getMessage());

        assertTrue(player.getCards().isEmpty());
    }
}
