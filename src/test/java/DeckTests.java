import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTests {

    @Test
    void constructorTest(){
        Deck deck = new Deck();

        ArrayList<Card> cards = deck.peek();

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

}
