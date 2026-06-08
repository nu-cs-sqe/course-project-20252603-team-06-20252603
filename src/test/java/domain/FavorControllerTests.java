package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.UserInput;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FavorControllerTests {

    @Test
    void executeCardAction_targetOneCard_cardGiven() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);
        Player target = game.getTotalPlayers().get(1);
        Card attack = new Card(CardType.ATTACK);
        target.addCard(attack);

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getCardToGive(target.getHand())).andReturn(attack);
        EasyMock.replay(userInput);

        FavorController controller = new FavorController(userInput);
        controller.executeCardAction(game, initiator, Optional.of(target));

        assertEquals(1, initiator.getHandSize());
        assertTrue(initiator.hasCard(CardType.ATTACK));
        assertEquals(0, target.getHandSize());

        EasyMock.verify(userInput);
    }

    @Test
    void executeCardAction_targetTwoCards_cardGiven() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);
        Player target = game.getTotalPlayers().get(1);
        Card attack = new Card(CardType.ATTACK);
        Card skip = new Card(CardType.SKIP);
        target.addCard(attack);
        target.addCard(skip);

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getCardToGive(target.getHand())).andReturn(attack);
        EasyMock.replay(userInput);

        FavorController controller = new FavorController(userInput);
        controller.executeCardAction(game, initiator, Optional.of(target));

        assertEquals(1, initiator.getHandSize());
        assertTrue(initiator.hasCard(CardType.ATTACK));
        assertEquals(1, target.getHandSize());
        assertTrue(target.hasCard(CardType.SKIP));

        EasyMock.verify(userInput);
    }

    @Test
    void executeCardAction_targetDuplicateCards_cardGiven() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);
        Player target = game.getTotalPlayers().get(1);
        Card attack1 = new Card(CardType.ATTACK);
        Card attack2 = new Card(CardType.ATTACK);
        target.addCard(attack1);
        target.addCard(attack2);

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getCardToGive(target.getHand())).andReturn(attack1);
        EasyMock.replay(userInput);

        FavorController controller = new FavorController(userInput);
        controller.executeCardAction(game, initiator, Optional.of(target));

        assertEquals(1, initiator.getHandSize());
        assertTrue(initiator.hasCard(CardType.ATTACK));
        assertEquals(1, target.getHandSize());
        assertTrue(target.hasCard(CardType.ATTACK));

        EasyMock.verify(userInput);
    }
}