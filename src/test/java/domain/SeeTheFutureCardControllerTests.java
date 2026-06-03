package domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeeTheFutureCardControllerTests {
    @Test
    public void executeCardAction_FourCardsInDeck_ReturnsListOfTopThree() {
        Game game = new Game(2);
        game.setup();

        while (game.getDeck().count() > 4) {
            game.getDeck().takeTopCard();
        }

        assertEquals(4, game.getDeck().count());

        int initialDeckSize = game.getDeck().count();
        Player initiator = game.getAlivePlayers().get(0);
        SeeTheFutureCardController controller = new SeeTheFutureCardController();

        Optional<List<Card>> result = controller.executeCardAction(game, initiator, Optional.empty());

        assertTrue(result.isPresent(), "Controller should return an Optional containing the cards");

        List<Card> topThree = result.get();
        assertEquals(3, topThree.size(), "Should return exactly 3 cards");

        assertEquals(initialDeckSize, game.getDeck().count(), "Original deck size must remain unchanged");

        assertEquals(game.getDeck().getCards().get(0).getType(), topThree.get(0).getType());
        assertEquals(game.getDeck().getCards().get(1).getType(), topThree.get(1).getType());
        assertEquals(game.getDeck().getCards().get(2).getType(), topThree.get(2).getType());
    }
}