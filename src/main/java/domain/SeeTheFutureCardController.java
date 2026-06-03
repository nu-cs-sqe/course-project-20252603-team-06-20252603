package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeeTheFutureCardController implements CardController {
    public Optional<List<Card>> executeCardAction(Game game, Player initiator, Optional<Player> target) {
        List<Card> cardsToReturn = new ArrayList<>();
        List<Card> cards = game.getDeck().getCards();
        cardsToReturn.add(cards.get(0));
        cardsToReturn.add(cards.get(1));
        cardsToReturn.add(cards.get(2));
        return Optional.of(cardsToReturn);
    }
}