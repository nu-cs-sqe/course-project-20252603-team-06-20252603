package domain;

import ui.UserInput;

import java.util.List;
import java.util.Optional;

public class ExplodingKittenController implements CardController {
    private final UserInput userInput;

    public ExplodingKittenController(UserInput userInput) {
        this.userInput = userInput;
    }

    public Optional<List<Card>> executeCardAction(Game game, Player user, Optional<Player> target) {
        if (!user.hasDefuse()) {
            game.removeAlivePlayer(user);
            return Optional.empty();
        }

        user.removeCard(user.getHand().get(0));

        int position = userInput.getInsertPosition(game.getDeck().count());
        game.getDeck().insert(new Card(CardType.EXPLODING_KITTEN), position);

        return Optional.empty();
    }
}

