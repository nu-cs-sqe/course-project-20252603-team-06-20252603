import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeckTests {

    @Test
    void defaultConstructor(){
        Deck deck = new Deck();

        ArrayList<Card> cards = deck.getCards();

        Map<CardType, Integer> card_counts = new HashMap<>(Map.of(
                // Confirm features w/team
                CardType.ATTACK, 0,
                CardType.SKIP, 0,
                CardType.SEE_THE_FUTURE, 0,
                CardType.SHUFFLE, 0,
                CardType.NOPE, 0,
                CardType.CAT_CARD_1, 0,
                CardType.CAT_CARD_2, 0,
                CardType.CAT_CARD_3, 0,
                CardType.CAT_CARD_4, 0
            )
        );

        for(int i = 0; i < cards.size(); i++){
            Card curr = cards.get(i);
            CardType type = curr.getType();
            card_counts.put(type, card_counts.get(type) + 1);
        }

        assertEquals(3, card_counts.get(CardType.ATTACK));
        assertEquals(3, card_counts.get(CardType.SKIP));
        assertEquals(4, card_counts.get(CardType.SEE_THE_FUTURE));
        assertEquals(4, card_counts.get(CardType.SHUFFLE));
        assertEquals(4, card_counts.get(CardType.NOPE));
        assertEquals(4, card_counts.get(CardType.CAT_CARD_1));
        assertEquals(4, card_counts.get(CardType.CAT_CARD_2));
        assertEquals(4, card_counts.get(CardType.CAT_CARD_3));
        assertEquals(4, card_counts.get(CardType.CAT_CARD_4));
        assertEquals(34, cards.size());
    }

    @Test
    void emptyConstructor(){
        Deck deck = new Deck(0);
        ArrayList<Card> cards = deck.getCards();

        assertEquals(0, cards.size());
    }

    @Test
    void oneCardConstructor(){
        Deck deck = new Deck(1);
        ArrayList<Card> cards = deck.getCards();

        assertEquals(1, cards.size());
    }


    @Test
    void shuffleOnEmptyDeck(){
        Deck deck = new Deck(0);
        ArrayList<Card> original_cards = deck.getCards();
        int original_size = deck.count();

        deck.shuffle();
        ArrayList<Card> shuffled_cards = deck.getCards();
        int shuffled_size = deck.count();

        assertEquals(0, original_size);
        assertEquals(0, shuffled_size);
        assertEquals(original_cards, shuffled_cards);
    }

    @Test
    void shuffleOnDeckWithOneCard(){
        Deck deck = new Deck(1);
        ArrayList<Card> original_cards = deck.getCards();
        int original_size = deck.count();

        deck.shuffle();
        ArrayList<Card> shuffled_cards = deck.getCards();
        int shuffled_size = deck.count();

        assertEquals(1, original_size);
        assertEquals(1, shuffled_size);
        assertEquals(original_cards, shuffled_cards);
    }

    @Test
    void shuffleOnDefaultDeck(){
        Deck deck = new Deck();
        ArrayList<Card> original_cards = deck.getCards();
        int original_size = deck.count();

        deck.shuffle();
        ArrayList<Card> shuffled_cards = deck.getCards();
        int shuffled_size = deck.count();

        assertEquals(34, original_size);
        assertEquals(34, shuffled_size);
        assertEquals(original_cards, shuffled_cards);
    }

    @Test
    void insertAtIndexNegativeOne(){
        Deck deck = new Deck();
        Card mockCard = EasyMock.createMock(Card.class);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            deck.insert(mockCard, -1);
        });
    }

    @Test
    void insertAtIndexZero(){
        Deck deck = new Deck();
        int initialSize = deck.count();
        ArrayList<Card> cards = deck.getCards();

        Card mockCard = EasyMock.createMock(Card.class);
        deck.insert(mockCard, 0);

        assertEquals(initialSize + 1, deck.count());
        assertEquals(mockCard, cards.get(0));
    }

    @Test
    void insertAtIndexOne(){
        Deck deck = new Deck();
        int initialSize = deck.count();
        ArrayList<Card> cards = deck.getCards();

        Card mockCard = EasyMock.createMock(Card.class);
        deck.insert(mockCard, 1);

        assertEquals(initialSize + 1, deck.count());
        assertEquals(mockCard, cards.get(1));
    }

    @Test
    void insertAtIndexN(){
        Deck deck = new Deck();
        int initialSize = deck.count();
        ArrayList<Card> cards = deck.getCards();

        Card mockCard = EasyMock.createMock(Card.class);
        deck.insert(mockCard, initialSize);

        assertEquals(initialSize + 1, deck.count());
        assertEquals(mockCard, cards.get(initialSize));
    }

}
