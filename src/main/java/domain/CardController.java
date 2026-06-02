package domain;

import java.util.List;
import java.util.Optional;

public interface CardController {
   Optional<List<Card>> executeCardAction(Game game, Player initiator, Optional<Player> target);
}