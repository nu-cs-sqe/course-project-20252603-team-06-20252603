package domain;

public class Card {
    private final CardType type;

    Card(CardType type) {
        this.type = type;
    }

    public static Card createCard(CardType type) {
        if (type == null) {
            throw new IllegalArgumentException("need a card type!");
        }
        return new Card(type);
    }

    public CardType getType() {
        return type;
    }
}
