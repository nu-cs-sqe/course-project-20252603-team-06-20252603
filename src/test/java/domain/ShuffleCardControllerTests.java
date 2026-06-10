package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShuffleCardControllerTests {
    @Test
    public void executeCardAction_OnEmptyDeck(){
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Player initiator = EasyMock.createMock(Player.class);

        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.count()).andReturn(0);
        deck.shuffle();

        EasyMock.replay(game, deck, initiator);

        ShuffleCardController controller = new ShuffleCardController();
        Optional<List<Card>> result = controller.executeCardAction(game, initiator, Optional.empty());

        assertTrue(result.isEmpty());

        EasyMock.verify(game, deck, initiator);
    }

}
