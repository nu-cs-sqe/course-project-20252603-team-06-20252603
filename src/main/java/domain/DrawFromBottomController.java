package domain;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class DrawFromBottomController implements CardController {
    public Optional<List<Card>> executeCardAction(Game game, Player initiator, Optional<Player> target) {
        if (game.getDeck().getCards().isEmpty()) {
            throw new IllegalArgumentException("no cards left in deck");
        }

        initiator.addCard(game.getDeck().takeTopCard());


        return Optional.empty();
    }
}
