package domain;

import ui.UserInput;

import java.util.List;
import java.util.Optional;

public class ExplodingKittenController implements CardController {
    private final UserInput userInput;

    public ExplodingKittenController(UserInput userInput) {
        this.userInput = userInput;
    }

    public Optional<List<Card>> executeCardAction(GameController gameController,
                                                  Player user,
                                                  Optional<Player> target) {
        if (!user.hasDefuse()) {
            user.kill();
            gameController.getGame().removeAlivePlayer(user);
            return Optional.empty();
        }

        for (Card card : user.getHand()) {
            if (card.getType() == CardType.DEFUSE) {
                user.removeCard(card);
                break;
            }
        }

        int position = userInput.getInsertPosition(gameController.getGame().getDeck().count());
        gameController.getGame().getDeck().insert(new Card(CardType.EXPLODING_KITTEN), position);

        return Optional.empty();
    }
}