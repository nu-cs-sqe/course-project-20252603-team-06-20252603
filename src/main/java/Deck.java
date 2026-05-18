import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Deck {
    private final ArrayList<Card> cards;
    private static final Map<CardType, Integer> card_counts = Map.of(
            // Confirm features w/team
            CardType.ATTACK, 3,
            CardType.SKIP, 3,
            CardType.SEE_THE_FUTURE, 4,
            CardType.SHUFFLE, 4,
            CardType.NOPE, 4,
            CardType.CAT_CARD_1, 4,
            CardType.CAT_CARD_2, 4,
            CardType.CAT_CARD_3, 4,
            CardType.CAT_CARD_4, 4
    );

    public Deck(){
        this.cards = new ArrayList<>();

        for (Map.Entry<CardType, Integer> entry : card_counts.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                cards.add(new Card(entry.getKey(),new NoAction()));
            }
        }
    }

    public Deck(int num_cards){
        this.cards = new ArrayList<>();

        for (int i = 0; i < num_cards; i++){
            cards.add(new Card(CardType.CAT_CARD_1, new NoAction()));
        }
    }

    public void shuffle(){
        Collections.shuffle(this.cards);
    }

    public void insert(Card card, int location){
        if (card == null){
            throw new IllegalArgumentException("Card cannot be null");
        }
        this.cards.add(location, card);
    }

    public void discard(Card card){
        if (card == null){
            throw new IllegalArgumentException("Card cannot be null");
        }
        this.cards.remove(card);
    }

    ArrayList<Card> getCards(){ // change name
        return new ArrayList<>(cards);
    }

    public int count(){
        return this.cards.size();
    }
}
