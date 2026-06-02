package domain;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DrawFromBottomControllerTests {

    @Test
    public void executeCardAction_DrawFromEmptyDeck_IllegalStateException() {
        Game game = new Game(2);
        Player player1 = game.getTotalPlayers().get(0);


        for (int i = 0; i < 34; i++) {
            game.getDeck().takeTopCard();
        }

        assertEquals(0, game.getDeck().getCards().size());

        DrawFromBottomController controller = new DrawFromBottomController();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.executeCardAction(game, player1, Optional.empty());
        });

        assertEquals("no cards left in deck", exception.getMessage());
    }

}
