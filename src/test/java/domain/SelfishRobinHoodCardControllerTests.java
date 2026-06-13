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

    @Test
    void executeCardAction_TargetHasOneMoreCard_StealsOneCard() {
        Game game = Game.createGame(2);
        GameController gc = new GameController(game);
        Player initiator = game.getAlivePlayers().get(0);
        Player target = game.getAlivePlayers().get(1);

        for (int i = 0; i < 3; i++) initiator.addCard(Card.createCard(CardType.TEST_TYPE));
        for (int i = 0; i < 4; i++) target.addCard(Card.createCard(CardType.TEST_TYPE));

        SelfishRobinHoodCardController controller = new SelfishRobinHoodCardController();
        controller.executeCardAction(gc, initiator, Optional.empty());

        assertEquals(4, initiator.getHandSize());
        assertEquals(3, target.getHandSize());
    }

    @Test
    void executeCardAction_TargetHasManyMoreCards_StealsOneCard() {
        Game game = Game.createGame(2);
        GameController gc = new GameController(game);
        Player initiator = game.getAlivePlayers().get(0);
        Player target = game.getAlivePlayers().get(1);

        for (int i = 0; i < 3; i++) initiator.addCard(Card.createCard(CardType.TEST_TYPE));
        for (int i = 0; i < 7; i++) target.addCard(Card.createCard(CardType.TEST_TYPE));

        SelfishRobinHoodCardController controller = new SelfishRobinHoodCardController();
        controller.executeCardAction(gc, initiator, Optional.empty());

        assertEquals(4, initiator.getHandSize());
        assertEquals(6, target.getHandSize());
    }

    @Test
    void executeCardAction_MultiPlayerInitiatorRichest_NoSteals() {
        Game game = Game.createGame(5);
        GameController gc = new GameController(game);
        Player initiator = game.getAlivePlayers().get(0);

        for (int i = 0; i < 5; i++) initiator.addCard(Card.createCard(CardType.TEST_TYPE));

        for (int p = 1; p < 5; p++) {
            for (int i = 0; i < 2; i++) {
                game.getAlivePlayers().get(p).addCard(Card.createCard(CardType.TEST_TYPE));
            }
        }

        SelfishRobinHoodCardController controller = new SelfishRobinHoodCardController();
        controller.executeCardAction(gc, initiator, Optional.empty());

        assertEquals(5, initiator.getHandSize());
        assertEquals(2, game.getAlivePlayers().get(1).getHandSize());
        assertEquals(2, game.getAlivePlayers().get(2).getHandSize());
        assertEquals(2, game.getAlivePlayers().get(3).getHandSize());
        assertEquals(2, game.getAlivePlayers().get(4).getHandSize());
    }

    @Test
    void executeCardAction_MultiPlayerInitiatorPoorest_MaximumSteals() {
        Game game = Game.createGame(5);
        GameController gc = new GameController(game);
        Player initiator = game.getAlivePlayers().get(0);

        initiator.addCard(Card.createCard(CardType.TEST_TYPE));

        for (int p = 1; p < 5; p++) {
            for (int i = 0; i < 5; i++) game.getAlivePlayers().get(p).addCard(Card.createCard(CardType.TEST_TYPE));
        }

        SelfishRobinHoodCardController controller = new SelfishRobinHoodCardController();
        controller.executeCardAction(gc, initiator, Optional.empty());

        assertEquals(5, initiator.getHandSize());
        assertEquals(4, game.getAlivePlayers().get(1).getHandSize());
        assertEquals(4, game.getAlivePlayers().get(2).getHandSize());
        assertEquals(4, game.getAlivePlayers().get(3).getHandSize());
        assertEquals(4, game.getAlivePlayers().get(4).getHandSize());
    }

    @Test
    void executeCardAction_MultiPlayerMixedWealth_PartialSteals() {
        Game game = Game.createGame(5);
        GameController gc = new GameController(game);
        Player initiator = game.getAlivePlayers().get(0);
        Player p2_poorer = game.getAlivePlayers().get(1);
        Player p3_equal = game.getAlivePlayers().get(2);
        Player p4_richer = game.getAlivePlayers().get(3);
        Player p5_muchRicher = game.getAlivePlayers().get(4);

        for (int i = 0; i < 3; i++) initiator.addCard(Card.createCard(CardType.TEST_TYPE));
        for (int i = 0; i < 1; i++) p2_poorer.addCard(Card.createCard(CardType.TEST_TYPE));
        for (int i = 0; i < 3; i++) p3_equal.addCard(Card.createCard(CardType.TEST_TYPE));
        for (int i = 0; i < 4; i++) p4_richer.addCard(Card.createCard(CardType.TEST_TYPE));
        for (int i = 0; i < 7; i++) p5_muchRicher.addCard(Card.createCard(CardType.TEST_TYPE)); // 7

        SelfishRobinHoodCardController controller = new SelfishRobinHoodCardController();
        controller.executeCardAction(gc, initiator, Optional.empty());

        assertEquals(5, initiator.getHandSize());

        assertEquals(1, p2_poorer.getHandSize());
        assertEquals(3, p3_equal.getHandSize());
        assertEquals(3, p4_richer.getHandSize());
        assertEquals(6, p5_muchRicher.getHandSize());
    }
}
