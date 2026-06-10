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
        return new ArrayList<>(deck.getCards());
    }

    void validateReorder(List<Card> original, List<Card> reordered) {
    }

    void applyReorder(Deck deck, List<Card> original, List<Card> reordered) {
    }
}
