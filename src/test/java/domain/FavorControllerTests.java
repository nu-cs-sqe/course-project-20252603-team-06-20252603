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
}