package domain;

import java.util.List;
import java.util.Optional;

public class BountyCardController implements CardController {
    public Optional<List<Card>> executeCardAction(Game game, Player user, Optional<Player> target) {
        return Optional.empty();
    }
}