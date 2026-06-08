package domain;

import ui.UserInput;
import java.util.List;
import java.util.Optional;

public class FavorController implements CardController {
    private final UserInput userInput;

    public FavorController(UserInput userInput) {
        this.userInput = userInput;
    }

    public Optional<List<Card>> executeCardAction(Game game, Player user, Optional<Player> target) {
        Player t = target.get();
        Card card = userInput.getCardToGive(t.getHand());

        t.removeCard(card);
        user.addCard(card);

        return Optional.empty();
    }
}