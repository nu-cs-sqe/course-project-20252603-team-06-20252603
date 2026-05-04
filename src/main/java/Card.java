public class Card {
    private final CardType type;
    private final Action action;

    public Card(CardType type, Action action) {
        if (type == null) {
            if (action == null) {
                throw new IllegalArgumentException("need a card type and an action!");
            }
            throw new IllegalArgumentException("need a card type!");
        }
        this.type = type;
        this.action = action;
    }

    public CardType getType() {
        return type;
    }

    public Action getAction() {
        return action;
    }
}
