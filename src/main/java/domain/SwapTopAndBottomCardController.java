package domain;

import java.util.List;
import java.util.Optional;

public class SwapTopAndBottomCardController implements CardController {

    @Override
    public Optional<List<Card>> executeCardAction(GameController gameController,
                                                  Player user,
                                                  Optional<Player> target) {
        throw new IllegalStateException("not enough cards to swap");
    }
}