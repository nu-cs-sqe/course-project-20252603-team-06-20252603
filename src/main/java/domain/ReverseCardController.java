package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReverseCardController implements CardController {

    @Override
    public Optional<List<Card>> executeCardAction(GameController gameController,
                                                  Player user,
                                                  Optional<Player> target) {
        List<Player> alivePlayers = gameController.getGame().getAlivePlayers();
        int size = alivePlayers.size();

        List<Player> reversedOrder = new ArrayList<>(alivePlayers);
        Collections.reverse(reversedOrder);
        gameController.setPlayerOrder(reversedOrder);

        int newCurrentIndex = size - 1 - gameController.getCurrentPlayerIndex();
        gameController.setCurrentPlayerIndex(newCurrentIndex);
        gameController.setNextPlayerIndex((newCurrentIndex + 1) % size);

        return Optional.empty();
    }
}