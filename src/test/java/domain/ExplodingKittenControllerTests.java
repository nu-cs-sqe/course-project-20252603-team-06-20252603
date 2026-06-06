package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.UserInput;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ExplodingKittenControllerTests {

    @Test
    void executeCardAction_noDefuseEmptyHand_playerKilled() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);

        UserInput userInput = new UserInput();

        ExplodingKittenController controller = new ExplodingKittenController(userInput);
        controller.executeCardAction(game, initiator, Optional.empty());

        assertFalse(initiator.isAlive());
        assertEquals(new ArrayList<>(), initiator.getHand());
    }

    @Test
    public void executeCardAction_oneDefuseOneCard_playerLivesEmptyHand() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);
        initiator.addCard(new Card(CardType.DEFUSE));

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getInsertPosition(game.getDeck().count())).andStubReturn(0);
        EasyMock.replay(userInput);

        ExplodingKittenController controller = new ExplodingKittenController(userInput);
        controller.executeCardAction(game, initiator, Optional.empty());

        assertTrue(initiator.isAlive());
        assertEquals(new ArrayList<>(), initiator.getHand());

        EasyMock.verify(userInput);
    }

    @Test
    public void executeCardAction_twoDefuses_playerLivesOneDefuse() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);
        initiator.addCard(new Card(CardType.DEFUSE));
        initiator.addCard(new Card(CardType.DEFUSE));

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getInsertPosition(game.getDeck().count())).andStubReturn(0);
        EasyMock.replay(userInput);

        ExplodingKittenController controller = new ExplodingKittenController(userInput);
        controller.executeCardAction(game, initiator, Optional.empty());

        assertTrue(initiator.isAlive());
        assertEquals(1, initiator.getHandSize());
        assertTrue(initiator.hasDefuse());

        EasyMock.verify(userInput);
    }
}
