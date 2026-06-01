package domain;

import java.util.List;
import java.util.Optional;

public interface CardController {
   Optional<List<Player>> executeCardAction(Game game, Player initiator, Optional<Player> target);
}