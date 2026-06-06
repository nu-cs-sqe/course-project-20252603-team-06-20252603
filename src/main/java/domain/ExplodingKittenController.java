package domain;

import java.util.List;
import java.util.Optional;

public class ExplodingKittenController implements CardController {
    public Optional<List<Card>> executeCardAction(Game game, Player initiator, Optional<Player> target) {
        if (!initiator.hasDefuse()) {
            game.removeAlivePlayer(initiator);
        }
        return Optional.empty();
    }
}
