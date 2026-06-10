package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AlterTheFutureCardControllerTests {

    @Test
    public void getTopCards_EmptyDeck_ReturnsEmptyList() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Deck deck = new Deck(0);

        List<Card> result = controller.getTopCards(deck);

        assertTrue(result.isEmpty());
    }

    @Test
    public void getTopCards_DeckHasOneCard_ReturnsListWithOneCard() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Deck deck = new Deck(1);
        Card expectedCard = deck.getCards().get(0);

        List<Card> result = controller.getTopCards(deck);

        assertEquals(1, result.size());
        assertEquals(expectedCard, result.get(0));
    }

    @Test
    public void getTopCards_DeckHasTwoCards_ReturnsListWithTwoCards() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Deck deck = new Deck(2);
        Card expectedFirst = deck.getCards().get(0);
        Card expectedSecond = deck.getCards().get(1);

        List<Card> result = controller.getTopCards(deck);

        assertEquals(2, result.size());
        assertEquals(expectedFirst, result.get(0));
        assertEquals(expectedSecond, result.get(1));
    }

    @Test
    public void getTopCards_DeckHasThreeCards_ReturnsListWithThreeCards() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Deck deck = new Deck(3);
        Card expectedFirst = deck.getCards().get(0);
        Card expectedSecond = deck.getCards().get(1);
        Card expectedThird = deck.getCards().get(2);

        List<Card> result = controller.getTopCards(deck);

        assertEquals(3, result.size());
        assertEquals(expectedFirst, result.get(0));
        assertEquals(expectedSecond, result.get(1));
        assertEquals(expectedThird, result.get(2));
    }

    @Test
    public void getTopCards_DeckHasMoreThanThreeCards_ReturnsOnlyTopThree() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Deck deck = new Deck(4);
        Card expectedFirst = deck.getCards().get(0);
        Card expectedSecond = deck.getCards().get(1);
        Card expectedThird = deck.getCards().get(2);

        List<Card> result = controller.getTopCards(deck);

        assertEquals(3, result.size());
        assertEquals(expectedFirst, result.get(0));
        assertEquals(expectedSecond, result.get(1));
        assertEquals(expectedThird, result.get(2));
    }

    @Test
    public void validateReorder_ReorderedHasFewerCards_ThrowsIllegalArgumentException() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Card card1 = new Card(CardType.CAT_CARD_1);
        Card card2 = new Card(CardType.CAT_CARD_2);
        Card card3 = new Card(CardType.CAT_CARD_3);

        List<Card> original = new ArrayList<>(List.of(card1, card2, card3));
        List<Card> reordered = new ArrayList<>(List.of(card1, card2));

        assertThrows(IllegalArgumentException.class, () ->
            controller.validateReorder(original, reordered)
        );
    }

    @Test
    public void validateReorder_ReorderedHasMoreCards_ThrowsIllegalArgumentException() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Card card1 = new Card(CardType.CAT_CARD_1);
        Card card2 = new Card(CardType.CAT_CARD_2);
        Card card3 = new Card(CardType.CAT_CARD_3);
        Card card4 = new Card(CardType.CAT_CARD_4);

        List<Card> original = new ArrayList<>(List.of(card1, card2, card3));
        List<Card> reordered = new ArrayList<>(List.of(card1, card2, card3, card4));

        assertThrows(IllegalArgumentException.class, () ->
            controller.validateReorder(original, reordered)
        );
    }

    @Test
    public void validateReorder_ReorderedHasSameCountButDifferentCard_ThrowsIllegalArgumentException() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Card card1 = new Card(CardType.CAT_CARD_1);
        Card card2 = new Card(CardType.CAT_CARD_2);
        Card card3 = new Card(CardType.CAT_CARD_3);
        Card foreignCard = new Card(CardType.CAT_CARD_4);

        List<Card> original = new ArrayList<>(List.of(card1, card2, card3));
        List<Card> reordered = new ArrayList<>(List.of(card1, card2, foreignCard));

        Exception e = assertThrows(IllegalArgumentException.class, () ->
            controller.validateReorder(original, reordered)
        );
        assertEquals("Reordered list contains cards not in the original", e.getMessage());
    }

    @Test
    public void validateReorder_ValidRearrangement_NoExceptionThrown() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Card card1 = new Card(CardType.CAT_CARD_1);
        Card card2 = new Card(CardType.CAT_CARD_2);
        Card card3 = new Card(CardType.CAT_CARD_3);

        List<Card> original = new ArrayList<>(List.of(card1, card2, card3));
        List<Card> reordered = new ArrayList<>(List.of(card3, card1, card2));

        assertDoesNotThrow(() -> controller.validateReorder(original, reordered));
    }
}
