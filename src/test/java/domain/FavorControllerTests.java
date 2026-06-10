package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FavorControllerTests {

    @Test
    void executeCardAction_targetOneCard_cardGiven() {
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockUser = EasyMock.createMock(Player.class);
        Player mockTarget = EasyMock.createMock(Player.class);
        UserInput mockInput = EasyMock.createMock(UserInput.class);

        Card attack = new Card(CardType.ATTACK);
        ArrayList<Card> targetHand = new ArrayList<>(List.of(attack));

        EasyMock.expect(mockTarget.getHand()).andReturn(targetHand).anyTimes();
        EasyMock.expect(mockInput.getCardToGive(targetHand)).andReturn(attack);
        mockTarget.removeCard(attack);
        EasyMock.expectLastCall().once();
        mockUser.addCard(attack);
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockGame, mockUser, mockTarget, mockInput);

        FavorController controller = new FavorController(mockInput);
        controller.executeCardAction(mockGame, mockUser, Optional.of(mockTarget));

        EasyMock.verify(mockGame, mockUser, mockTarget, mockInput);
    }

    @Test
    void executeCardAction_targetTwoCards_cardGiven() {
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockUser = EasyMock.createMock(Player.class);
        Player mockTarget = EasyMock.createMock(Player.class);
        UserInput mockInput = EasyMock.createMock(UserInput.class);

        Card attack = new Card(CardType.ATTACK);
        Card skip = new Card(CardType.SKIP);
        ArrayList<Card> targetHand = new ArrayList<>(List.of(attack, skip));

        EasyMock.expect(mockTarget.getHand()).andReturn(targetHand).anyTimes();
        EasyMock.expect(mockInput.getCardToGive(targetHand)).andReturn(attack);
        mockTarget.removeCard(attack);
        EasyMock.expectLastCall().once();
        mockUser.addCard(attack);
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockGame, mockUser, mockTarget, mockInput);

        FavorController controller = new FavorController(mockInput);
        controller.executeCardAction(mockGame, mockUser, Optional.of(mockTarget));

        EasyMock.verify(mockGame, mockUser, mockTarget, mockInput);
    }

    @Test
    void executeCardAction_targetDuplicateCards_cardGiven() {
        Game game = new Game(2);
        Player user = game.getTotalPlayers().get(0);
        Player target = game.getTotalPlayers().get(1);
        Card attack1 = new Card(CardType.ATTACK);
        Card attack2 = new Card(CardType.ATTACK);
        target.addCard(attack1);
        target.addCard(attack2);

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getCardToGive(target.getHand())).andReturn(attack1);
        EasyMock.replay(userInput);

        FavorController controller = new FavorController(userInput);
        controller.executeCardAction(game, user, Optional.of(target));

        assertEquals(1, user.getHandSize());
        assertTrue(user.hasCard(CardType.ATTACK));
        assertEquals(1, target.getHandSize());
        assertTrue(target.hasCard(CardType.ATTACK));

        EasyMock.verify(userInput);
    }

    @Test
    void executeCardAction_userDuplicateCards_cardGiven() {
        Game game = new Game(2);
        Player user = game.getTotalPlayers().get(0);
        Player target = game.getTotalPlayers().get(1);
        Card userAttack = new Card(CardType.ATTACK);
        Card targetAttack = new Card(CardType.ATTACK);
        user.addCard(userAttack);
        target.addCard(targetAttack);

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getCardToGive(target.getHand())).andReturn(targetAttack);
        EasyMock.replay(userInput);

        FavorController controller = new FavorController(userInput);
        controller.executeCardAction(game, user, Optional.of(target));

        assertEquals(2, user.getHandSize());
        for (Card card : user.getHand()) {
            assertEquals(CardType.ATTACK, card.getType());
        }
        assertEquals(0, target.getHandSize());

        EasyMock.verify(userInput);
    }

    @Test
    void executeCardAction_targetFirstCard_cardGiven() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);
        Player target = game.getTotalPlayers().get(1);
        Card skip = new Card(CardType.SKIP);
        Card attack = new Card(CardType.ATTACK);
        Card defuse = new Card(CardType.DEFUSE);
        target.addCard(skip);
        target.addCard(attack);
        target.addCard(defuse);

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getCardToGive(target.getHand())).andReturn(skip);
        EasyMock.replay(userInput);

        FavorController controller = new FavorController(userInput);
        controller.executeCardAction(game, initiator, Optional.of(target));

        assertEquals(1, initiator.getHandSize());
        assertTrue(initiator.hasCard(CardType.SKIP));
        assertEquals(2, target.getHandSize());
        assertTrue(target.hasCard(CardType.ATTACK));
        assertTrue(target.hasCard(CardType.DEFUSE));

        EasyMock.verify(userInput);
    }

    @Test
    void executeCardAction_targetLastCard_cardGiven() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);
        Player target = game.getTotalPlayers().get(1);
        Card skip = new Card(CardType.SKIP);
        Card attack = new Card(CardType.ATTACK);
        Card defuse = new Card(CardType.DEFUSE);
        target.addCard(skip);
        target.addCard(attack);
        target.addCard(defuse);

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getCardToGive(target.getHand())).andReturn(defuse);
        EasyMock.replay(userInput);

        FavorController controller = new FavorController(userInput);
        controller.executeCardAction(game, initiator, Optional.of(target));

        assertEquals(1, initiator.getHandSize());
        assertTrue(initiator.hasCard(CardType.DEFUSE));
        assertEquals(2, target.getHandSize());
        assertTrue(target.hasCard(CardType.SKIP));
        assertTrue(target.hasCard(CardType.ATTACK));

        EasyMock.verify(userInput);
    }

    @Test
    void executeCardAction_userTwoCards_cardGiven() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);
        Player target = game.getTotalPlayers().get(1);
        Card defuse = new Card(CardType.DEFUSE);
        Card skip = new Card(CardType.SKIP);
        Card attack = new Card(CardType.ATTACK);
        initiator.addCard(defuse);
        initiator.addCard(skip);
        target.addCard(attack);

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getCardToGive(target.getHand())).andReturn(attack);
        EasyMock.replay(userInput);

        FavorController controller = new FavorController(userInput);
        controller.executeCardAction(game, initiator, Optional.of(target));

        assertEquals(3, initiator.getHandSize());
        assertTrue(initiator.hasCard(CardType.ATTACK));
        assertTrue(initiator.hasCard(CardType.DEFUSE));
        assertTrue(initiator.hasCard(CardType.SKIP));
        assertEquals(0, target.getHandSize());

        EasyMock.verify(userInput);
    }

    @Test
    void executeCardAction_cardNotInTarget_illegalArgumentException() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);
        Player target = game.getTotalPlayers().get(1);
        Card attack = new Card(CardType.ATTACK);
        Card defuse = new Card(CardType.DEFUSE);
        target.addCard(attack);

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getCardToGive(target.getHand())).andReturn(defuse);
        EasyMock.replay(userInput);

        FavorController controller = new FavorController(userInput);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.executeCardAction(game, initiator, Optional.of(target));
        });

        assertEquals("card to remove not in hand", exception.getMessage());

        assertEquals(0, initiator.getHandSize());
        assertEquals(1, target.getHandSize());
        assertTrue(target.hasCard(CardType.ATTACK));

        EasyMock.verify(userInput);
    }
}