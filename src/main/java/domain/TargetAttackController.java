package domain;

import java.util.List;
import java.util.Optional;

public class TargetAttackController implements CardController {
    @Override
    public Optional<List<Card>> executeCardAction(GameController gameController, Player initiator, Optional<Player> target){
        throw new IllegalArgumentException("Need to have a target");
    }
}
