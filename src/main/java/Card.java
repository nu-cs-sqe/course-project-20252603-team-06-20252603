public class Card {
    public Card(CardType type, Action action) {
        if (type == null) {
            if (action == null) {
                throw new IllegalArgumentException("need a card type and an action!");
            }
            throw new IllegalArgumentException("need a card type!");
        }
    }
}
