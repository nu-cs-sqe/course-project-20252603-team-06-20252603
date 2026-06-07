package domain;

import java.util.List;
import java.util.Optional;

public class ExplodingKittenController implements CardController {

    public Optional<List<Card>> executeCardAction(Game game, Player user, Optional<Player> target) {
        game.removeAlivePlayer(user);
        return Optional.empty();
    }
}

