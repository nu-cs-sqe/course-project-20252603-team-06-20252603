package domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {

    @Test
    void createPlayer_EmptyName_IllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Player.createPlayer("");
        });

        assertEquals("player name cannot be empty", exception.getMessage());
    }

    @Test
    void createPlayer_ValidName_success() {
        Player player = Player.createPlayer("lily");

        assertEquals("lily", player.getPlayerName());
        assertEquals(0, player.getHandSize());
        assertTrue(player.isAlive());
    }

    @Test
    void hasDefuse_EmptyHand_False() {
        Player player = Player.createPlayer("lily");

        assertFalse(player.hasDefuse());
    }

    @Test
    void hasDefuse_OneCardwithDefuse_True() {
        Player player = Player.createPlayer("lily");

        Card card = Card.createCard(CardType.DEFUSE);

        player.addCard(card);

        assertTrue(player.hasDefuse());
    }

    @Test
    void hasDefuse_TwoCardswithoutDefuse_False() {
        Player player = Player.createPlayer("lily");

        Card card1 = Card.createCard(CardType.TEST_TYPE);
        Card card2 = Card.createCard(CardType.TEST_TYPE);

        player.addCard(card1);
        player.addCard(card2);

        assertFalse(player.hasDefuse());
    }

    @Test
    void hasDefuse_duplicateDefuse_True() {
        Player player = Player.createPlayer("lily");

        Card card1 = Card.createCard(CardType.DEFUSE);
        Card card2 = Card.createCard(CardType.DEFUSE);

        player.addCard(card1);
        player.addCard(card2);

        assertTrue(player.hasDefuse());
    }

    @Test
    void addCard_noCards_success() {
        Player player = Player.createPlayer("lily");

        Card card = Card.createCard(CardType.TEST_TYPE);

        player.addCard(card);
        assertEquals(1, player.getHandSize());
        assertTrue(player.hasCard(CardType.TEST_TYPE));
    }

    @Test
    void addCard_onecardAddNewCard_success() {
        Player player = Player.createPlayer("lily");

        Card card1 = Card.createCard(CardType.TEST_TYPE);
        Card card2 = Card.createCard(CardType.DEFUSE);

        player.addCard(card1);
        player.addCard(card2);

        assertEquals(2, player.getHandSize());
        assertTrue(player.hasCard(CardType.TEST_TYPE));
        assertTrue(player.hasCard(CardType.DEFUSE));
    }

    @Test
    void addCard_twocardsDuplicateCards_success() {
        Player player = Player.createPlayer("lily");

        Card card1 = Card.createCard(CardType.TEST_TYPE);
        Card card2 = Card.createCard(CardType.DEFUSE);

        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card1);

        assertEquals(3, player.getHandSize());
        assertTrue(player.hasCard(CardType.TEST_TYPE));
        assertTrue(player.hasCard(CardType.DEFUSE));
    }

    @Test
    void kill_Success() {
        Player player = Player.createPlayer("lily");

        player.kill();
        assertFalse(player.isAlive());
    }

    @Test
    void kill_IllegalStateException() {
        Player player = Player.createPlayer("lily");

        player.kill();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            player.kill();
        });

        assertEquals("cannot kill dead player", exception.getMessage());
    }

    @Test
    void revive_Success() {
        Player player = Player.createPlayer("lily");
        player.kill();

        player.revive();
        assertTrue(player.isAlive());
    }

    @Test
    void revive_IllegalStateException() {
        Player player = Player.createPlayer("lily");

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            player.revive();
        });

        assertEquals("cannot revive alive player", exception.getMessage());
    }

    @Test
    void removeCard_NormalCardOneCard_CardRemoved() {
        Player player = Player.createPlayer("lily");

        Card card = Card.createCard(CardType.TEST_TYPE);

        player.addCard(card);
        player.removeCard(card);

        assertEquals(0, player.getHandSize());
    }

    @Test
    void removeCard_NormalCardTwoCards_CardRemoved() {
        Player player = Player.createPlayer("lily");

        Card card1 = Card.createCard(CardType.TEST_TYPE);
        Card card2 = Card.createCard(CardType.TEST_TYPE);

        player.addCard(card1);
        player.addCard(card2);
        player.removeCard(card1);

        assertEquals(1, player.getHandSize());
        assertTrue(player.hasCard(CardType.TEST_TYPE));
    }

    @Test
    void removeCard_noCards_IllegalStateException() {
        Player player = Player.createPlayer("lily");

        Card card = Card.createCard(CardType.TEST_TYPE);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            player.removeCard(card);
        });

        assertEquals("cannot remove from empty hand", exception.getMessage());
    }

    @Test
    void removeCard_cardNotInHand_IllegalArgumentException() {
        Player player = Player.createPlayer("lily");

        Card testCard = Card.createCard(CardType.TEST_TYPE);
        Card defuseCard = Card.createCard(CardType.DEFUSE);

        player.addCard(testCard);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            player.removeCard(defuseCard);
        });

        assertEquals("card to remove not in hand", exception.getMessage());
    }

    @Test
    void takeTurn_NormalCardNoCardsInHand_CardAddedtoHand() {
        Player player = Player.createPlayer("lily");

        Card card = Card.createCard(CardType.TEST_TYPE);
        player.takeTurn(card);

        assertEquals(1, player.getHandSize());
        assertTrue(player.hasCard(CardType.TEST_TYPE));
        assertTrue(player.isAlive());
    }

    @Test
    void takeTurn_NormalCardOneCardInHand_CardAddedtoHand() {
        Player player = Player.createPlayer("lily");

        Card card1 = Card.createCard(CardType.TEST_TYPE);
        Card card2 = Card.createCard(CardType.TEST_TYPE);

        player.addCard(card1);

        player.takeTurn(card2);

        assertEquals(2, player.getHandSize());
        assertTrue(player.hasCard(CardType.TEST_TYPE));
        assertTrue(player.isAlive());
    }

    @Test
    void getHand_EmptyHand_EmptyList() {
        Player player = Player.createPlayer("lily");

        List<Card> expected = new ArrayList<Card>();
        assertEquals(expected, player.getHand());
    }

    @Test
    void getHand_OneCard_ListWithOneCard() {
        Player player = Player.createPlayer("lily");

        List<Card> expected = new ArrayList<Card>();
        Card card = Card.createCard(CardType.TEST_TYPE);

        player.addCard(card);
        expected.add(card);

        assertEquals(expected, player.getHand());
    }
    @Test
    void getHand_TwoCardsSameType_ListWithTwoCadsSameType() {
        Player player = Player.createPlayer("lily");

        List<Card> expected = new ArrayList<Card>();
        Card card1 = Card.createCard(CardType.TEST_TYPE);
        Card card2 = Card.createCard(CardType.TEST_TYPE);


        player.addCard(card1);
        player.addCard(card2);

        expected.add(card1);
        expected.add(card2);

        assertEquals(expected, player.getHand());
    }

    @Test
    void getHand_SevenCardsManyTypes_ListWithSevenCardsSameOrder() {
        Player player = Player.createPlayer("lily");

        List<Card> expected = new ArrayList<Card>();
        Card seeTheFuture = Card.createCard(CardType.SEE_THE_FUTURE);
        Card defuse = Card.createCard(CardType.DEFUSE);
        Card shuffle1 = Card.createCard(CardType.SHUFFLE);
        Card nope1 = Card.createCard(CardType.NOPE);
        Card nope2 = Card.createCard(CardType.NOPE);
        Card skip = Card.createCard(CardType.SKIP);
        Card shuffle2 = Card.createCard(CardType.SHUFFLE);

        player.addCard(seeTheFuture);
        player.addCard(defuse);
        player.addCard(shuffle1);
        player.addCard(nope1);
        player.addCard(nope2);
        player.addCard(skip);
        player.addCard(shuffle2);

        expected.add(seeTheFuture);
        expected.add(defuse);
        expected.add(shuffle1);
        expected.add(nope1);
        expected.add(nope2);
        expected.add(skip);
        expected.add(shuffle2);

        assertEquals(expected, player.getHand());
    }


    @Test
    void getHandSize_EmptyHand_Zero() {
        Player player = Player.createPlayer("lily");

        assertEquals(0, player.getHandSize());
    }

    @Test
    void getHandSize_OneCard_One() {
        Player player = Player.createPlayer("lily");

        Card card = Card.createCard(CardType.TEST_TYPE);

        player.addCard(card);
        assertEquals(1, player.getHandSize());
    }

    @Test
    void getHandSize_TwoCards_Two() {
        Player player = Player.createPlayer("lily");

        Card card1 = Card.createCard(CardType.TEST_TYPE);
        Card card2 = Card.createCard(CardType.TEST_TYPE);

        player.addCard(card1);
        player.addCard(card2);
        assertEquals(2, player.getHandSize());
    }

    @Test
    void getHandSize_DuplicateCards_Two() {
        Player player = Player.createPlayer("lily");

        Card card = Card.createCard(CardType.TEST_TYPE);

        player.addCard(card);
        player.addCard(card);
        assertEquals(2, player.getHandSize());
    }

    @Test
    void hasCard_EmptyHand_False() {
        Player player = Player.createPlayer("lily");

        assertFalse(player.hasCard(CardType.DEFUSE));
    }

    @Test
    void hasCard_OneCardMatch_True() {
        Player player = Player.createPlayer("lily");

        Card card = Card.createCard(CardType.DEFUSE);

        player.addCard(card);
        assertTrue(player.hasCard(CardType.DEFUSE));
    }

    @Test
    void hasCard_OneCardNoMatch_False() {
        Player player = Player.createPlayer("lily");

        Card card = Card.createCard(CardType.TEST_TYPE);

        player.addCard(card);
        assertFalse(player.hasCard(CardType.DEFUSE));
    }

    @Test
    void hasCard_TwoCardsOneMatch_True() {
        Player player = Player.createPlayer("lily");

        Card card1 = Card.createCard(CardType.TEST_TYPE);
        Card card2 = Card.createCard(CardType.DEFUSE);

        player.addCard(card1);
        player.addCard(card2);
        assertTrue(player.hasCard(CardType.DEFUSE));
    }

    @Test
    void hasCard_DuplicateCardsMatch_True() {
        Player player = Player.createPlayer("lily");

        Card card1 = Card.createCard(CardType.DEFUSE);
        Card card2 = Card.createCard(CardType.DEFUSE);

        player.addCard(card1);
        player.addCard(card2);
        assertTrue(player.hasCard(CardType.DEFUSE));
    }

    @Test
    void hasCard_DuplicateCardsNoMatch_False() {
        Player player = Player.createPlayer("lily");

        Card card1 = Card.createCard(CardType.TEST_TYPE);
        Card card2 = Card.createCard(CardType.TEST_TYPE);

        player.addCard(card1);
        player.addCard(card2);
        assertFalse(player.hasCard(CardType.DEFUSE));
    }
}