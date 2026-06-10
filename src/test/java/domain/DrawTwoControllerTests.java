package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class DrawTwoControllerTests {
    @Test
    public void executeCardAction_emptyDeckEmptyHand_IllegalArgumentException() {
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockUser = EasyMock.createMock(Player.class);
        Deck mockDeck = EasyMock.createMock(Deck.class);

        EasyMock.expect(mockGame.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockDeck.count()).andReturn(0);

        EasyMock.replay(mockGame, mockUser, mockDeck);

        DrawTwoController controller = new DrawTwoController();

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                controller.executeCardAction(mockGame, mockUser, Optional.empty())
        );

        assertEquals("deck needs at least two cards to play draw two card",
                exception.getMessage());

        EasyMock.verify(mockGame, mockUser, mockDeck);
    }

    @Test
    public void executeCardAction_oneCardDeckEmptyHand_IllegalArgumentException() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);

        for (int i = 0; i < 33; i++) {
            game.getDeck().takeTopCard();
        }

        assertEquals(1, game.getDeck().getCards().size());
        assertEquals(new ArrayList<Card>(), initiator.getHand());

        DrawTwoController controller = new DrawTwoController();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.executeCardAction(game, initiator, Optional.empty());
        });

        assertEquals("deck needs at least two cards to play draw two card", exception.getMessage());
    }

    @Test
    public void executeCardAction_twoCardDeckEmptyHand_cardsDrawn() {
        Game game = new Game(2);
        Player initiator = game.getTotalPlayers().get(0);

        for (int i = 0; i < 32; i++) {
            game.getDeck().takeTopCard();
        }

        assertEquals(2, game.getDeck().getCards().size());
        assertEquals(new ArrayList<Card>(), initiator.getHand());

        ArrayList<Card> deckCardsBefore = game.getDeck().getCards();
        Card expectedFirst  = deckCardsBefore.get(0);
        Card expectedSecond = deckCardsBefore.get(1);

        DrawTwoController controller = new DrawTwoController();
        Optional<java.util.List<Card>> result = controller.executeCardAction(game, initiator, Optional.empty());

        ArrayList<Card> expectedHand = new ArrayList<>();
        expectedHand.add(expectedFirst);
        expectedHand.add(expectedSecond);

        assertTrue(result.isEmpty());
        assertEquals(expectedHand, initiator.getHand());
        assertEquals(new ArrayList<Card>(), game.getDeck().getCards());
    }
}
