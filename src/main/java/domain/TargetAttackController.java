package domain;

import java.util.List;
import java.util.Optional;

public class TargetAttackController {
    public Optional<List<Card>> executeCardAction(Game game, Player initiator, Optional<Player> target){
        throw new IllegalArgumentException("Need to have a target");
    }
}
