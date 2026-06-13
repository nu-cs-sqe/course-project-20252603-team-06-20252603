package domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelfishRobinHoodCardControllerTests {
    @Test
    void executeCardAction_TargetHasFewerCards_NoSteal() {
        Game game = Game.createGame(2);
        GameController gc = new GameController(game);
        Player initiator = game.getAlivePlayers().get(0);
        Player target = game.getAlivePlayers().get(1);

        for (int i = 0; i < 3; i++) initiator.addCard(Card.createCard(CardType.TEST_TYPE));
        for (int i = 0; i < 2; i++) target.addCard(Card.createCard(CardType.TEST_TYPE));

        SelfishRobinHoodCardController controller = new SelfishRobinHoodCardController();
        controller.executeCardAction(gc, initiator, Optional.empty());

        assertEquals(3, initiator.getHandSize());
        assertEquals(2, target.getHandSize());
    }

    @Test
    void executeCardAction_TargetHasEqualCards_NoSteal() {
        Game game = Game.createGame(2);
        GameController gc = new GameController(game);
        Player initiator = game.getAlivePlayers().get(0);
        Player target = game.getAlivePlayers().get(1);

        for (int i = 0; i < 3; i++) initiator.addCard(Card.createCard(CardType.TEST_TYPE));
        for (int i = 0; i < 3; i++) target.addCard(Card.createCard(CardType.TEST_TYPE));

        SelfishRobinHoodCardController controller = new SelfishRobinHoodCardController();
        controller.executeCardAction(gc, initiator, Optional.empty());

        assertEquals(3, initiator.getHandSize());
        assertEquals(3, target.getHandSize());
    }
}
