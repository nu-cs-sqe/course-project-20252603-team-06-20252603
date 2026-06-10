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
        return Optional.empty();
    }

    List<Card> getTopCards(Deck deck) {
        List<Card> cards = deck.getCards();
        int count = Math.min(MAX_PEEK, cards.size());
        return new ArrayList<>(cards.subList(0, count));
    }

    void validateReorder(List<Card> original, List<Card> reordered) {
        throw new IllegalArgumentException("Reordered list must have the same number of cards");
    }

    void applyReorder(Deck deck, List<Card> original, List<Card> reordered) {
    }
}
