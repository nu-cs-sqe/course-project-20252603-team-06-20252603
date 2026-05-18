import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
        ArrayList<Card> initialCards = deck.getCards();
        int initialSize = deck.count();

        deck.shuffle();
        ArrayList<Card> shuffled_cards = deck.getCards();
        int shuffled_size = deck.count();

        assertEquals(0, initialSize);
        assertEquals(0, shuffled_size);
        assertEquals(initialCards, shuffled_cards);
    }

    @Test
    void shuffleOnDeckWithOneCard(){
        Deck deck = new Deck(1);
        ArrayList<Card> initialCards = deck.getCards();
        int initialSize = deck.count();

        deck.shuffle();
        ArrayList<Card> shuffled_cards = deck.getCards();
        int shuffled_size = deck.count();

        assertEquals(1, initialSize);
        assertEquals(1, shuffled_size);
        assertEquals(initialCards, shuffled_cards);
    }

    @Test
    void shuffleOnDefaultDeck(){
        Deck deck = new Deck();
        ArrayList<Card> initialCards = deck.getCards();
        int initialSize = deck.count();

        deck.shuffle();
        ArrayList<Card> shuffled_cards = deck.getCards();
        int shuffled_size = deck.count();

        assertEquals(34, initialSize);
        assertEquals(34, shuffled_size);
        assertTrue(shuffled_cards.containsAll(initialCards));
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

        Card mockCard = EasyMock.createMock(Card.class);
        deck.insert(mockCard, 0);
        ArrayList<Card> cards = deck.getCards();

        assertEquals(initialSize + 1, deck.count());
        assertSame(mockCard, cards.get(0));
    }

    @Test
    void insertAtIndexOne(){
        Deck deck = new Deck();
        int initialSize = deck.count();

        Card mockCard = EasyMock.createMock(Card.class);
        deck.insert(mockCard, 1);
        ArrayList<Card> cards = deck.getCards();

        assertEquals(initialSize + 1, deck.count());
        assertSame(mockCard, cards.get(1));
    }

    @Test
    void insertAtIndexN(){
        Deck deck = new Deck();
        int initialSize = deck.count();

        Card mockCard = EasyMock.createMock(Card.class);
        deck.insert(mockCard, initialSize);
        ArrayList<Card> cards = deck.getCards();

        assertEquals(initialSize + 1, deck.count());
        assertSame(mockCard, cards.get(initialSize));
    }

    @Test
    void insertAtIndexNMinusOne(){
        Deck deck = new Deck();
        int initialSize = deck.count();

        Card mockCard = EasyMock.createMock(Card.class);
        deck.insert(mockCard, initialSize - 1);
        ArrayList<Card> cards = deck.getCards();

        assertEquals(initialSize + 1, deck.count());
        assertSame(mockCard, cards.get(initialSize - 1));
    }

    @Test
    void insertIntoEmptyDeck(){
        Deck deck = new Deck(0);

        Card mockCard = EasyMock.createMock(Card.class);
        deck.insert(mockCard, 0);
        ArrayList<Card> cards = deck.getCards();

        assertEquals(1, deck.count());
        assertSame(mockCard, cards.get(0));
    }

    @Test
    void insertNullCard(){
        Deck deck = new Deck();
        ArrayList<Card> initialCards = deck.getCards();
        int initialSize = deck.count();

        assertThrows(IllegalArgumentException.class, () -> {
            deck.insert(null, 0);
        });

        ArrayList<Card> after_null_insert_cards = deck.getCards();
        int after_null_insert_num_cards = deck.count();

        assertEquals(initialSize, after_null_insert_num_cards);
        assertEquals(initialCards, after_null_insert_cards);
    }

    @Test
    void discardExistingCard() {
        Deck deck = new Deck();
        int initialSize = deck.count();

        Card cardToDiscard = deck.getCards().get(0);
        deck.discard(cardToDiscard);

        ArrayList<Card> cards = deck.getCards();
        assertEquals(initialSize - 1, deck.count());
        assertFalse(cards.contains(cardToDiscard));
    }

    @Test
    void discardOnlyCard() {
        Deck deck = new Deck(1);
        int initialSize = deck.count();

        Card cardToDiscard = deck.getCards().get(0);
        deck.discard(cardToDiscard);

        ArrayList<Card> cards = deck.getCards();
        assertEquals(initialSize - 1, deck.count());
        assertFalse(cards.contains(cardToDiscard));
    }

    @Test
    void discardFromEmptyDeck() {
        Deck deck = new Deck(0);
        Card mockCardToDiscard = EasyMock.createMock(Card.class);

        assertThrows(IllegalArgumentException.class, () -> {
            deck.discard(mockCardToDiscard);
        });
    }

    @Test
    void discardLastCard() {
        Deck deck = new Deck();
        int initialSize = deck.count();

        Card cardToDiscard = deck.getCards().get(initialSize - 1);
        deck.discard(cardToDiscard);

        ArrayList<Card> cards = deck.getCards();
        assertEquals(initialSize - 1, deck.count());
        assertFalse(cards.contains(cardToDiscard));
    }

    @Test
    void discardNullCard(){
        Deck deck = new Deck();
        ArrayList<Card> initialCards = deck.getCards();
        int initialSize = deck.count();

        assertThrows(IllegalArgumentException.class, () -> {
            deck.discard(null);
        });

        assertEquals(initialSize, deck.count());
        assertEquals(initialCards, deck.getCards());
    }

    @Test
    void getCardsOnMultipleCardDeck(){
        Deck deck = new Deck();
        int initialSize = deck.count();

        ArrayList<Card> cards = deck.getCards();

        assertEquals(initialSize, cards.size());
        assertEquals(initialSize, deck.count());
    }

    @Test
    void getCardsOnOneCardDeck(){
        Deck deck = new Deck(1);

        ArrayList<Card> cards = deck.getCards();

        assertEquals(1, cards.size());
        assertEquals(1, deck.count());
    }

    @Test
    void getCardsOnEmptyDeck(){
        Deck deck = new Deck(0);

        ArrayList<Card> cards = deck.getCards();

        assertEquals(0, cards.size());
        assertEquals(0, deck.count());
    }

    @Test
    void countDeckWithOneCard(){
        Deck deck = new Deck(1);
        assertEquals(1, deck.count());
    }

    @Test
    void countEmptyDeck(){
        Deck deck = new Deck(0);
        assertEquals(0, deck.count());
    }
}
