package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class AlterTheFutureCardController implements CardController {
    private static final int MAX_PEEK = 3;
    private final Function<List<Card>, List<Card>> reorderFunction;

    public AlterTheFutureCardController(Function<List<Card>, List<Card>> reorderFunction) {
        this.reorderFunction = reorderFunction;
    }

    public Optional<List<Card>> executeCardAction(Game game, Player user, Optional<Player> target) {
        Deck deck = game.getDeck();
        List<Card> top = getTopCards(deck);
        List<Card> reordered = reorderFunction.apply(top);
        validateReorder(top, reordered);
        applyReorder(deck, top, reordered);
        return Optional.empty();
    }

    List<Card> getTopCards(Deck deck) {
        List<Card> cards = deck.getCards();
        int count = Math.min(MAX_PEEK, cards.size());
        return new ArrayList<>(cards.subList(0, count));
    }

    void validateReorder(List<Card> original, List<Card> reordered) {
        if (reordered.size() != original.size()) {
            throw new IllegalArgumentException("Reordered list must have the same number of cards");
        }
        List<Card> copy = new ArrayList<>(original);
        for (Card card : reordered) {
            if (!copy.remove(card)) {
                throw new IllegalArgumentException(
                        "Reordered list contains cards not in the original");
            }
        }
    }

    void applyReorder(Deck deck, List<Card> original, List<Card> reordered) {
        for (Card card : original) {
            deck.discard(card);
        }
        for (int i = 0; i < reordered.size(); i++) {
            deck.insert(reordered.get(i), i);
        }
    }
}
