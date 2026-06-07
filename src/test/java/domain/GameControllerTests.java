package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;


public class GameControllerTests {
    @Test
    void constructor_Valid_Game_MakesGameController() {
        Game myGame = new Game(4);
        GameController controller = new GameController(myGame);

        assertSame(myGame, controller.getGame());
    }

    @Test
    void setCurrentPlayerIndex_InvalidIndexBelowMinimum_ThrowsIllegalArgumentException() {
        Game game = new Game(3);
        GameController controller = new GameController(game);
        int invalidIndex = -1;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.setCurrentPlayerIndex(invalidIndex);
        });

        String expectedMessage = "Invalid player index: " + invalidIndex;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setCurrentPlayerIndex_InvalidIndexAboveMaximum_ThrowsIllegalArgumentException() {
        Game game = new Game(3);
        GameController controller = new GameController(game);
        int invalidIndex = 3;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.setCurrentPlayerIndex(invalidIndex);
        });

        String expectedMessage = "Invalid player index: " + invalidIndex;
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void setCurrentPlayerIndex_ValidIndexAtMinimum_SetsCurrentPlayerIndexAsInput() {
        Game game = new Game(3);
        GameController controller = new GameController(game);
        int validMinIndex = 0;

        controller.setCurrentPlayerIndex(validMinIndex);

        assertEquals(validMinIndex, controller.getCurrentPlayerIndex());
    }

    @Test
    void setCurrentPlayerIndex_ValidIndexAtMaximum_SetsCurrentPlayerIndexAsInput() {
        Game game = new Game(3);
        GameController controller = new GameController(game);
        int validMaxIndex = controller.getGame().getAlivePlayerCount() - 1;

        controller.setCurrentPlayerIndex(validMaxIndex);

        assertEquals(validMaxIndex, controller.getCurrentPlayerIndex());
    }

    @Test
    void setNextPlayerIndex_InvalidNegativeIndex_ThrowsIllegalArgumentException() {
        Game game = new Game(5);
        GameController controller = new GameController(game);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.setNextPlayerIndex(-1);
        });

        assertEquals("invalid next index", exception.getMessage());
    }

    @Test
    void setNextPlayerIndex_LowerBound_SetsNextPlayerIndex() {
        Game game = new Game(5);
        GameController controller = new GameController(game);

        controller.setNextPlayerIndex(0);

        assertEquals(0, controller.getNextPlayerIndex());
    }

    @Test
    void setNextPlayerIndex_MiddleOfList_SetsNextPlayerIndex() {
        Game game = new Game(5);
        GameController controller = new GameController(game);

        controller.setNextPlayerIndex(2);

        assertEquals(2, controller.getNextPlayerIndex());
    }

    @Test
    void setNextPlayerIndex_UpperBound_SetsNextPlayerIndex() {
        Game game = new Game(5);
        GameController controller = new GameController(game);

        controller.setNextPlayerIndex(4);

        assertEquals(4, controller.getNextPlayerIndex());
    }

    @Test
    void setNextPlayerIndex_InvalidAboveUpperBound_ThrowsIllegalArgumentException() {
        Game game = new Game(5);
        GameController controller = new GameController(game);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.setNextPlayerIndex(5);
        });
        assertEquals("invalid next index", exception.getMessage());
    }

    @Test
    void setPlayerOrder_IllegallyEmptyList_ThrowsIllegalArgumentException() {
        Game game = new Game(5);
        GameController controller = new GameController(game);
        List<Player> emptyOrder = new ArrayList<>();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.setPlayerOrder(emptyOrder);
        });

        assertEquals("list size doesn’t match alivePlayer", exception.getMessage());
    }

    @Test
    void getControllerType_InvalidTestType_IllegalArgumentException() {
        Game game = new Game(2);
        GameController controller = new GameController(game);

        Card card = new Card(CardType.TEST_TYPE);

        assertThrows(IllegalArgumentException.class, () -> {
            controller.getControllerType(card);
        });
    }

    @Test
    void isValidMove_EmptyCards_ReturnsFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player1 = game.getAlivePlayers().get(0);

        ArrayList<Card> cards = new ArrayList<Card>();

        assertFalse(controller.isValidMove(cards, player1, Optional.empty()));
    }

    @Test
    void isValidMove_ValidSingleCard_ReturnsTrue() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player1 = game.getAlivePlayers().get(0);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.SKIP));

        assertTrue(controller.isValidMove(cards, player1, Optional.empty()));
    }

    @Test
    void isValidMove_InvalidSingleCatCard_ReturnsFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player1 = game.getAlivePlayers().get(0);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_1));

        assertFalse(controller.isValidMove(cards, player1, Optional.empty()));
    }

    @Test
    void isValidMove_ValidPairOfCatCards_ReturnsTrue() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player1 = game.getAlivePlayers().get(0);
        Player player2 = game.getAlivePlayers().get(1);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_1));
        cards.add(new Card(CardType.CAT_CARD_1));

        assertTrue(controller.isValidMove(cards, player1, Optional.of(player2)));
    }

    @Test
    void isValidMove_ValidPairOfCatCardsButNoTarget_ReturnsFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player1 = game.getAlivePlayers().get(0);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_1));
        cards.add(new Card(CardType.CAT_CARD_1));

        assertFalse(controller.isValidMove(cards, player1, Optional.empty()));
    }

    @Test
    void isValidMove_ValidPairOfCatCardsButTargetingSelf_ReturnsFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player1 = game.getAlivePlayers().get(0);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_1));
        cards.add(new Card(CardType.CAT_CARD_1));

        assertFalse(controller.isValidMove(cards, player1, Optional.of(player1)));
    }

    @Test
    void isValidMove_InvalidPairOfCatCards_ReturnsFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player1 = game.getAlivePlayers().get(0);
        Player player2 = game.getAlivePlayers().get(1);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_1));
        cards.add(new Card(CardType.CAT_CARD_2));

        assertFalse(controller.isValidMove(cards, player1, Optional.of(player2)));
    }

    @Test
    void isValidMove_InvalidPairOfNonCatCards_ReturnsFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player1 = game.getAlivePlayers().get(0);
        Player player2 = game.getAlivePlayers().get(1);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.SKIP));
        cards.add(new Card(CardType.SKIP));

        assertFalse(controller.isValidMove(cards, player1, Optional.of(player2)));
    }

    @Test
    void isValidMove_ValidTripleOfCatCards_ReturnsTrue() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player1 = game.getAlivePlayers().get(0);
        Player player2 = game.getAlivePlayers().get(1);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_1));
        cards.add(new Card(CardType.CAT_CARD_1));
        cards.add(new Card(CardType.CAT_CARD_1));

        assertTrue(controller.isValidMove(cards, player1, Optional.of(player2)));
    }

    @Test
    void isValidMove_ValidTripleOfCatCardsButNoTarget_ReturnsFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player1 = game.getAlivePlayers().get(0);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_1));
        cards.add(new Card(CardType.CAT_CARD_1));
        cards.add(new Card(CardType.CAT_CARD_1));

        assertFalse(controller.isValidMove(cards, player1, Optional.empty()));
    }

    @Test
    void isValidMove_InvalidTripleOfCatCards_ReturnsFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player1 = game.getAlivePlayers().get(0);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_1));
        cards.add(new Card(CardType.CAT_CARD_3));
        cards.add(new Card(CardType.CAT_CARD_2));

        assertFalse(controller.isValidMove(cards, player1, Optional.empty()));
    }

    @Test
    void isValidMove_InvalidTripleOfNonCatCards_ReturnsFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player1 = game.getAlivePlayers().get(0);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.SKIP));
        cards.add(new Card(CardType.SKIP));
        cards.add(new Card(CardType.SKIP));

        assertFalse(controller.isValidMove(cards, player1, Optional.empty()));
    }

    @Test
    void isValidMove_TooManyCards_ReturnsFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player1 = game.getAlivePlayers().get(0);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_1));
        cards.add(new Card(CardType.CAT_CARD_3));
        cards.add(new Card(CardType.CAT_CARD_2));
        cards.add(new Card(CardType.SHUFFLE));

        assertFalse(controller.isValidMove(cards, player1, Optional.empty()));
    }

    @Test
    void advanceTurn_CurrentIsIndex1_CurrentIsIndex2AndNextIsIndex3() {
        Game mockGame = mock(Game.class);

        expect(mockGame.getAlivePlayerCount()).andReturn(4).anyTimes();
        replay(mockGame);

        GameController controller = new GameController(mockGame);

        controller.setCurrentPlayerIndex(1);
        controller.setNextPlayerIndex(2);

        controller.advanceTurn();

        assertEquals(2, controller.getCurrentPlayerIndex(),
                "Current player should advance to index 2");
        assertEquals(3, controller.getNextPlayerIndex(),
                "Next player should advance to index 3");

        verify(mockGame);
    }

    @Test
    void advanceTurn_CurrentIsIndex2_CurrentIsIndex3AndNextIsIndex0() {
        Game mockGame = mock(Game.class);

        expect(mockGame.getAlivePlayerCount()).andReturn(4).anyTimes();
        replay(mockGame);

        GameController controller = new GameController(mockGame);

        controller.setCurrentPlayerIndex(2);
        controller.setNextPlayerIndex(3);

        controller.advanceTurn();

        assertEquals(3, controller.getCurrentPlayerIndex(),
                "Current player should advance to index 3");
        assertEquals(0, controller.getNextPlayerIndex(),
                "Next player should advance to index 0");

        verify(mockGame);
    }
}
