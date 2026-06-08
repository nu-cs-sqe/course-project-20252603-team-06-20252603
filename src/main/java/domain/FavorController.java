package domain;

import ui.UserInput;
import java.util.List;
import java.util.Optional;

public class FavorController implements CardController {
    private final UserInput userInput;

    public FavorController(UserInput userInput) {
        this.userInput = userInput;
    }

    public Optional<List<Card>> executeCardAction(Game game, Player initiator, Optional<Player> target) {
        userInput.getCardToGive(target.get().getHand());

        initiator.addCard(new Card(CardType.ATTACK));
        Card first = target.get().getHand().get(0);
        target.get().removeCard(first);

        return Optional.empty();
    }
}