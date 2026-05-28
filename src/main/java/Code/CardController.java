package Code;

import java.util.Optional;

public interface CardController {
   void executeCardAction(Game game, Player initiator, Optional<Player> target);
}