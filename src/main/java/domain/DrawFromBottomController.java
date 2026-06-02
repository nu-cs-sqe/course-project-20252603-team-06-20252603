package domain;

import java.util.List;
import java.util.Optional;

public class DrawFromBottomController implements CardController {
    public Optional<List<Card>> executeCardAction(Game game, Player initiator, Optional<Player> target) {
        throw new IllegalArgumentException("no cards left in deck");
    }
}
