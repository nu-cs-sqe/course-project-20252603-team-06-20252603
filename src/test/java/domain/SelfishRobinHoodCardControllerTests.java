package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelfishRobinHoodCardControllerTests {
    @Test
    void executeCardAction_TargetHasFewerCards_NoSteal() {
        GameController mockGc = EasyMock.createMock(GameController.class);
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockInitiator = EasyMock.createMock(Player.class);
        Player mockTarget = EasyMock.createMock(Player.class);

        EasyMock.expect(mockGc.getGame()).andReturn(mockGame).anyTimes();
        EasyMock.expect(mockGame.getAlivePlayers()).andReturn(List.of(mockInitiator, mockTarget)).anyTimes();

        EasyMock.expect(mockInitiator.getHandSize()).andReturn(3).anyTimes();
        EasyMock.expect(mockTarget.getHandSize()).andReturn(2).anyTimes();

        EasyMock.replay(mockGc, mockGame, mockInitiator, mockTarget);

        SelfishRobinHoodCardController controller = new SelfishRobinHoodCardController();
        controller.executeCardAction(mockGc, mockInitiator, Optional.empty());

        EasyMock.verify(mockGc, mockGame, mockInitiator, mockTarget);
    }

    @Test
    void executeCardAction_TargetHasEqualCards_NoSteal() {
        GameController mockGc = EasyMock.createMock(GameController.class);
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockInitiator = EasyMock.createMock(Player.class);
        Player mockTarget = EasyMock.createMock(Player.class);

        EasyMock.expect(mockGc.getGame()).andReturn(mockGame).anyTimes();
        EasyMock.expect(mockGame.getAlivePlayers()).andReturn(List.of(mockInitiator, mockTarget)).anyTimes();

        EasyMock.expect(mockInitiator.getHandSize()).andReturn(3).anyTimes();
        EasyMock.expect(mockTarget.getHandSize()).andReturn(3).anyTimes();

        EasyMock.replay(mockGc, mockGame, mockInitiator, mockTarget);

        SelfishRobinHoodCardController controller = new SelfishRobinHoodCardController();
        controller.executeCardAction(mockGc, mockInitiator, Optional.empty());

        EasyMock.verify(mockGc, mockGame, mockInitiator, mockTarget);
    }

    @Test
    void executeCardAction_TargetHasOneMoreCard_StealsOneCard() {
        GameController mockGc = EasyMock.createMock(GameController.class);
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockInitiator = EasyMock.createMock(Player.class);
        Player mockTarget = EasyMock.createMock(Player.class);
        Card mockStolenCard = EasyMock.createMock(Card.class);

        EasyMock.expect(mockGc.getGame()).andReturn(mockGame).anyTimes();
        EasyMock.expect(mockGame.getAlivePlayers()).andReturn(List.of(mockInitiator, mockTarget)).anyTimes();

        EasyMock.expect(mockInitiator.getHandSize()).andReturn(3).anyTimes();
        EasyMock.expect(mockTarget.getHandSize()).andReturn(4).anyTimes();

        EasyMock.expect(mockTarget.getHand()).andReturn(new ArrayList<>(List.of(mockStolenCard))).anyTimes();

        mockTarget.removeCard(mockStolenCard);
        EasyMock.expectLastCall().once();

        mockInitiator.addCard(mockStolenCard);
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockGc, mockGame, mockInitiator, mockTarget, mockStolenCard);

        SelfishRobinHoodCardController controller = new SelfishRobinHoodCardController();
        controller.executeCardAction(mockGc, mockInitiator, Optional.empty());

        EasyMock.verify(mockGc, mockGame, mockInitiator, mockTarget, mockStolenCard);
    }

    @Test
    void executeCardAction_TargetHasManyMoreCards_StealsOneCard() {
        GameController mockGc = EasyMock.createMock(GameController.class);
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockInitiator = EasyMock.createMock(Player.class);
        Player mockTarget = EasyMock.createMock(Player.class);
        Card mockStolenCard = EasyMock.createMock(Card.class);

        EasyMock.expect(mockGc.getGame()).andReturn(mockGame).anyTimes();
        EasyMock.expect(mockGame.getAlivePlayers()).andReturn(List.of(mockInitiator, mockTarget)).anyTimes();

        EasyMock.expect(mockInitiator.getHandSize()).andReturn(3).anyTimes();
        EasyMock.expect(mockTarget.getHandSize()).andReturn(7).anyTimes();

        EasyMock.expect(mockTarget.getHand()).andReturn(new ArrayList<>(List.of(mockStolenCard))).anyTimes();

        mockTarget.removeCard(mockStolenCard);
        EasyMock.expectLastCall().once();

        mockInitiator.addCard(mockStolenCard);
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockGc, mockGame, mockInitiator, mockTarget, mockStolenCard);

        SelfishRobinHoodCardController controller = new SelfishRobinHoodCardController();
        controller.executeCardAction(mockGc, mockInitiator, Optional.empty());

        EasyMock.verify(mockGc, mockGame, mockInitiator, mockTarget, mockStolenCard);
    }

    @Test
    void executeCardAction_MultiPlayerInitiatorRichest_NoSteals() {
        GameController mockGc = EasyMock.createMock(GameController.class);
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockInitiator = EasyMock.createMock(Player.class);
        Player p1 = EasyMock.createMock(Player.class);
        Player p2 = EasyMock.createMock(Player.class);
        Player p3 = EasyMock.createMock(Player.class);
        Player p4 = EasyMock.createMock(Player.class);

        EasyMock.expect(mockGc.getGame()).andReturn(mockGame).anyTimes();
        EasyMock.expect(mockGame.getAlivePlayers()).andReturn(List.of(mockInitiator, p1, p2, p3, p4)).anyTimes();

        EasyMock.expect(mockInitiator.getHandSize()).andReturn(5).anyTimes();

        EasyMock.expect(p1.getHandSize()).andReturn(2).anyTimes();
        EasyMock.expect(p2.getHandSize()).andReturn(2).anyTimes();
        EasyMock.expect(p3.getHandSize()).andReturn(2).anyTimes();
        EasyMock.expect(p4.getHandSize()).andReturn(2).anyTimes();

        EasyMock.replay(mockGc, mockGame, mockInitiator, p1, p2, p3, p4);

        SelfishRobinHoodCardController controller = new SelfishRobinHoodCardController();
        controller.executeCardAction(mockGc, mockInitiator, Optional.empty());

        EasyMock.verify(mockGc, mockGame, mockInitiator, p1, p2, p3, p4);
    }

    @Test
    void executeCardAction_MultiPlayerInitiatorPoorest_MaximumSteals() {
        GameController mockGc = EasyMock.createMock(GameController.class);
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockInitiator = EasyMock.createMock(Player.class);
        Player p1 = EasyMock.createMock(Player.class);
        Player p2 = EasyMock.createMock(Player.class);
        Player p3 = EasyMock.createMock(Player.class);
        Player p4 = EasyMock.createMock(Player.class);
        Card mockStolenCard = EasyMock.createMock(Card.class);

        EasyMock.expect(mockGc.getGame()).andReturn(mockGame).anyTimes();
        List<Player> board = List.of(mockInitiator, p1, p2, p3, p4);
        EasyMock.expect(mockGame.getAlivePlayers()).andReturn(board).anyTimes();

        EasyMock.expect(mockInitiator.getHandSize()).andReturn(1).anyTimes();

        for (int i = 1; i <= 4; i++) {
            Player currentMock = board.get(i);
            EasyMock.expect(currentMock.getHandSize()).andReturn(5).anyTimes();
            EasyMock.expect(currentMock.getHand()).andReturn(new ArrayList<>(List.of(mockStolenCard))).anyTimes();

            currentMock.removeCard(mockStolenCard);
            EasyMock.expectLastCall().once();

            mockInitiator.addCard(mockStolenCard);
            EasyMock.expectLastCall().once();
        }

        EasyMock.replay(mockGc, mockGame, mockInitiator, p1, p2, p3, p4, mockStolenCard);

        SelfishRobinHoodCardController controller = new SelfishRobinHoodCardController();
        controller.executeCardAction(mockGc, mockInitiator, Optional.empty());

        EasyMock.verify(mockGc, mockGame, mockInitiator, p1, p2, p3, p4, mockStolenCard);
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
