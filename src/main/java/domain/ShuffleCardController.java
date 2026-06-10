package domain;

import java.util.List;
import java.util.Optional;

public class ShuffleCardController implements CardController {
    public Optional<List<Card>> executeCardAction(Game game, Player user, Optional<Player> target){
        Deck deck = game.getDeck();
        deck.shuffle();
        deck.count();

        return Optional.empty();
    }
}
