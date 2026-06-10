package domain;

import org.junit.jupiter.api.Test;

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
}
