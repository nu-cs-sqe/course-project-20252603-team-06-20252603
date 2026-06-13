package domain;

import java.util.List;
import java.util.Optional;

public class SelfishRobinHoodCardController implements CardController {

    @Override
    public Optional<List<Card>> executeCardAction(GameController gameController,
                                                  Player initiator,
                                                  Optional<Player> target) {
        return Optional.empty();
    }
}
