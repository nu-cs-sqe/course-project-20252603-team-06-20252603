package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class SwapTopAndBottomCardControllerTests {
    @Test
    void executeCardAction_EmptyDeck_ThrowsException() {
        GameController mockGc = EasyMock.createMock(GameController.class);
        Game mockGame = EasyMock.createMock(Game.class);
        Deck mockDeck = EasyMock.createMock(Deck.class);
        Player mockUser = EasyMock.createMock(Player.class);

        EasyMock.expect(mockGc.getGame()).andReturn(mockGame).anyTimes();
        EasyMock.expect(mockGame.getDeck()).andReturn(mockDeck).anyTimes();
        EasyMock.expect(mockDeck.count()).andReturn(0);

        EasyMock.replay(mockGc, mockGame, mockDeck, mockUser);

        SwapTopAndBottomCardController controller = new SwapTopAndBottomCardController();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            controller.executeCardAction(mockGc, mockUser, Optional.empty());
        });

        assertEquals("not enough cards to swap", exception.getMessage());
        EasyMock.verify(mockGc, mockGame, mockDeck, mockUser);
    }

    @Test
    void executeCardAction_OneCardDeck_ThrowsException() {
        Game game = Game.createGame(2);
        GameController gc = new GameController(game);
        Player user = game.getAlivePlayers().get(0);

        while (game.getDeck().count() > 1) {
            game.getDeck().takeTopCard();
        }

        SwapTopAndBottomCardController controller = new SwapTopAndBottomCardController();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            controller.executeCardAction(gc, user, Optional.empty());
        });
        assertEquals("not enough cards to swap", exception.getMessage());
    }

    @Test
    void executeCardAction_TwoCardDeck_SwapsCards() {
        Game game = Game.createGame(2);
        GameController gc = new GameController(game);
        Player user = game.getAlivePlayers().get(0);
        Deck deck = game.getDeck();

        while (deck.count() > 0) {
            deck.takeTopCard();
        }

        Card originalBottom = Card.createCard(CardType.SKIP);
        Card originalTop = Card.createCard(CardType.DEFUSE);
        deck.insert(originalBottom, 0);
        deck.insert(originalTop, 0);

        SwapTopAndBottomCardController controller = new SwapTopAndBottomCardController();
        controller.executeCardAction(gc, user, Optional.empty());

        assertEquals(2, deck.count());
        assertEquals(originalBottom, deck.getCards().get(0), "New top should be old bottom");
        assertEquals(originalTop, deck.getCards().get(1), "New bottom should be old top");
    }

    @Test
    void executeCardAction_ThreeCardDeck_SwapsEndsLeavesMiddle() {
        Game game = Game.createGame(2);
        GameController gc = new GameController(game);
        Player user = game.getAlivePlayers().get(0);
        Deck deck = game.getDeck();

        while (deck.count() > 0) {
            deck.takeTopCard();
        }

        Card originalBottom = Card.createCard(CardType.SKIP);
        Card originalMiddle = Card.createCard(CardType.NOPE);
        Card originalTop = Card.createCard(CardType.DEFUSE);
        deck.insert(originalBottom, 0);
        deck.insert(originalMiddle, 0);
        deck.insert(originalTop, 0);

        SwapTopAndBottomCardController controller = new SwapTopAndBottomCardController();
        controller.executeCardAction(gc, user, Optional.empty());

        assertEquals(3, deck.count());
        assertEquals(originalBottom, deck.getCards().get(0));
        assertEquals(originalMiddle, deck.getCards().get(1));
        assertEquals(originalTop, deck.getCards().get(2));
    }

    @Test
    void executeCardAction_FiveCardDeck_SwapsEndsLeavesMiddles() {
        Game game = Game.createGame(2);
        GameController gc = new GameController(game);
        Player user = game.getAlivePlayers().get(0);
        Deck deck = game.getDeck();

        while (deck.count() > 0) {
            deck.takeTopCard();
        }

        Card originalBottom = Card.createCard(CardType.ATTACK);
        Card middle1 = Card.createCard(CardType.SKIP);
        Card middle2 = Card.createCard(CardType.NOPE);
        Card middle3 = Card.createCard(CardType.DEFUSE);
        Card originalTop = Card.createCard(CardType.SHUFFLE);

        deck.insert(originalBottom, 0);
        deck.insert(middle3, 0);
        deck.insert(middle2, 0);
        deck.insert(middle1, 0);
        deck.insert(originalTop, 0);

        SwapTopAndBottomCardController controller = new SwapTopAndBottomCardController();
        controller.executeCardAction(gc, user, Optional.empty());

        assertEquals(5, deck.count());
        assertEquals(originalBottom, deck.getCards().get(0));
        assertEquals(middle1, deck.getCards().get(1));
        assertEquals(middle2, deck.getCards().get(2));
        assertEquals(middle3, deck.getCards().get(3));
        assertEquals(originalTop, deck.getCards().get(4));
    }
}
