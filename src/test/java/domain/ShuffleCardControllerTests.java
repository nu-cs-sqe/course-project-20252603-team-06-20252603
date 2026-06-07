package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

public class ShuffleCardControllerTests {

    @Test
    void executeCardAction_OnEmptyDeck() {
        Game game = EasyMock.createMock(Game.class);
        Deck deck = EasyMock.createMock(Deck.class);
        ShuffleCardController controller = new ShuffleCardController();

        EasyMock.expect(game.getDeck()).andReturn(deck);
        deck.shuffle();
        EasyMock.replay(deck);

        EasyMock.verify(deck);
    }
}
