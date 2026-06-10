package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DrawTwoController {
    public Optional<List<Card>> executeCardAction(Game game, Player user, Optional<Player> target){
        if (game.getDeck().count() < 2) {
            String err_msg = "deck needs at least two cards to play draw two card";
            throw new IllegalArgumentException(err_msg);
        }

        Card first = game.getDeck().takeTopCard();
        Card second = game.getDeck().takeTopCard();

        user.addCard(first);
        user.addCard(second);

        return Optional.empty();
    }
}
