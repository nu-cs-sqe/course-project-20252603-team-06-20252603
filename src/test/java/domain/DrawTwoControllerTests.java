package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DrawTwoControllerTests {
    @Test
    public void executeCardAction_emptyDeckEmptyHand_IllegalArgumentException() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);

        for (int i = 0; i < 34; i++) {
            game.getDeck().takeTopCard();
        }

        assertEquals(0, game.getDeck().getCards().size());
        assertEquals(new ArrayList<Card>(), initiator.getHand());

        DrawTwoController controller = new DrawTwoController();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.executeCardAction(game, initiator, Optional.empty());
        });

        assertEquals("deck needs at least two cards to play draw two card", exception.getMessage());
    }

    @Test
    public void executeCardAction_oneCardDeckEmptyHand_IllegalArgumentException() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);

        for (int i = 0; i < 33; i++) {
            game.getDeck().takeTopCard();
        }

        assertEquals(1, game.getDeck().getCards().size());
        assertEquals(new ArrayList<Card>(), initiator.getHand());

        DrawTwoController controller = new DrawTwoController();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.executeCardAction(game, initiator, Optional.empty());
        });

        assertEquals("deck needs at least two cards to play draw two card", exception.getMessage());
    }
}
