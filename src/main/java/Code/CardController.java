package Code;

import domain.Game;
import domain.Player;

import java.util.Optional;

public interface CardController {
   void executeCardAction(Game game, Player initiator, Optional<Player> target);
}