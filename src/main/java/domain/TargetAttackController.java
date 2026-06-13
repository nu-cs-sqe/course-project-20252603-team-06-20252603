package domain;

import java.util.List;
import java.util.Optional;

public class TargetAttackController implements CardController {
    @Override
    public Optional<List<Card>> executeCardAction(
            GameController gameController,
            Player initiator,
            Optional<Player> target) {
        if (target.isEmpty()){
            throw new IllegalArgumentException("Need to have a target");
        }

        if (!target.get().isAlive()) {
            throw new IllegalArgumentException("Target must be alive");
        }

        if (target.get() == initiator){
            throw new IllegalArgumentException("Target and initiator must be different players");
        }

        gameController.setNextPlayerTurnsLeft(2);
        return Optional.empty();
    }
}
