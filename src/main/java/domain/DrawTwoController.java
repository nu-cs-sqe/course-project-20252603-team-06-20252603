package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DrawTwoController {
    public Optional<List<Card>> executeCardAction(Game game, Player user, Optional<Player> target){
        throw new IllegalArgumentException("deck needs at least two cards to play draw two card");
    }
}
