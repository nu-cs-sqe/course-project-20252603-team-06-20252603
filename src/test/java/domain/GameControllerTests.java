package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

import ui.GameControllerView;


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
    void setCurrentPlayerTurnsLeft_NegativeTurnsLeft_IllegalArgumentException() {
        int newCurrentPlayerTurnsLeft = -1;

        Game mockGame = mock(Game.class);
        GameController controller = new GameController(mockGame);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.setCurrentPlayerTurnsLeft(newCurrentPlayerTurnsLeft);
        });
        assertEquals("invalid turn count", exception.getMessage());
    }

    @Test
    void setCurrentPlayerTurnsLeft_ZeroTurnsLeft_SetsCurrentPlayerTurnsLeft() {
        int newCurrentPlayerTurnsLeft = 0;

        Game mockGame = mock(Game.class);
        GameController controller = new GameController(mockGame);

        controller.setCurrentPlayerTurnsLeft(newCurrentPlayerTurnsLeft);

        assertEquals(newCurrentPlayerTurnsLeft, controller.getCurrentPlayerTurnsLeft());
    }

    @Test
    void setCurrentPlayerTurnsLeft_FourTurnsLeft_SetsCurrentPlayerTurnsLeft() {
        int newCurrentPlayerTurnsLeft = 4;

        Game mockGame = mock(Game.class);
        GameController controller = new GameController(mockGame);

        controller.setCurrentPlayerTurnsLeft(newCurrentPlayerTurnsLeft);

        assertEquals(newCurrentPlayerTurnsLeft, controller.getCurrentPlayerTurnsLeft());
    }

    @Test
    void setNextPlayerTurnsLeft_NegativeTurnsLeft_IllegalArgumentException() {
        int newNextPlayerTurnsLeft = -1;

        Game mockGame = mock(Game.class);
        GameController controller = new GameController(mockGame);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.setNextPlayerTurnsLeft(newNextPlayerTurnsLeft);
        });
        assertEquals("invalid turn count", exception.getMessage());
    }

    @Test
    void setNextPlayerTurnsLeft_ZeroTurnsLeft_SetsNextPlayerTurnsLeft() {
        int newNextPlayerTurnsLeft = 0;

        Game mockGame = mock(Game.class);
        GameController controller = new GameController(mockGame);

        controller.setNextPlayerTurnsLeft(newNextPlayerTurnsLeft);

        assertEquals(newNextPlayerTurnsLeft, controller.getNextPlayerTurnsLeft());
    }

    @Test
    void setNextPlayerTurnsLeft_SevenTurnsLeft_SetsNextPlayerTurnsLeft() {
        int newNextPlayerTurnsLeft = 7;

        Game mockGame = mock(Game.class);
        GameController controller = new GameController(mockGame);

        controller.setNextPlayerTurnsLeft(newNextPlayerTurnsLeft);

        assertEquals(newNextPlayerTurnsLeft, controller.getNextPlayerTurnsLeft());
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
    void isTargetValid_CatCardAndValidTarget_ReturnsTrue() {
        GameController controller = new GameController(null);
        Player initiator = EasyMock.createMock(Player.class);
        Player target = EasyMock.createMock(Player.class);

        EasyMock.replay(initiator, target);

        assertTrue(controller.isTargetValid(CardType.CAT_CARD_1, initiator, target));

        EasyMock.verify(initiator, target);
    }

    @Test
    void isTargetValid_NonCatCardAndValidTarget_ReturnsFalse() {
        GameController controller = new GameController(null);
        Player initiator = EasyMock.createMock(Player.class);
        Player target = EasyMock.createMock(Player.class);

        EasyMock.replay(initiator, target);

        assertFalse(controller.isTargetValid(CardType.SKIP, initiator, target));

        EasyMock.verify(initiator, target);
    }

    @Test
    void isTargetValid_CatCardTargetIsSelf_ReturnsFalse() {
        GameController controller = new GameController(null);
        Player initiator = EasyMock.createMock(Player.class);

        EasyMock.replay(initiator);

        assertFalse(controller.isTargetValid(CardType.CAT_CARD_1, initiator, initiator));

        EasyMock.verify(initiator);
    }

    @Test
    void cardsAllMatchingCatCards_EmptyList_ThrowsIndexOutOfBoundsException() {
        GameController controller = new GameController(null);
        ArrayList<Card> cards = new ArrayList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> {
            Boolean result = controller.cardsAllMatchingCatCards(cards);
        });
    }


    @Test
    void cardsAllMatchingCatCards_SingleCatCard_ReturnsTrue() {
        GameController controller = new GameController(null);
        Card mockCard = EasyMock.createMock(Card.class);
        EasyMock.expect(mockCard.getType()).andReturn(CardType.CAT_CARD_1).anyTimes();
        EasyMock.replay(mockCard);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(mockCard);

        assertTrue(controller.cardsAllMatchingCatCards(cards));

        EasyMock.verify(mockCard);
    }

    @Test
    void cardsAllMatchingCatCards_SingleNonCatCard_ReturnsFalse() {
        GameController controller = new GameController(null);
        Card mockCard = EasyMock.createMock(Card.class);
        EasyMock.expect(mockCard.getType()).andReturn(CardType.SKIP).anyTimes();
        EasyMock.replay(mockCard);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(mockCard);

        assertFalse(controller.cardsAllMatchingCatCards(cards));

        EasyMock.verify(mockCard);
    }

    @Test
    void cardsAllMatchingCatCards_AllMatchingCatCards_ReturnsTrue() {
        GameController controller = new GameController(null);
        Card mockCard = EasyMock.createMock(Card.class);
        EasyMock.expect(mockCard.getType()).andReturn(CardType.CAT_CARD_1).anyTimes();
        EasyMock.replay(mockCard);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(mockCard);
        cards.add(mockCard);
        cards.add(mockCard);

        assertTrue(controller.cardsAllMatchingCatCards(cards));

        EasyMock.verify(mockCard);
    }

    @Test
    void cardsAllMatchingCatCards_AllNonCatCards_ReturnsFalse() {
        GameController controller = new GameController(null);
        Card mockCard = EasyMock.createMock(Card.class);
        EasyMock.expect(mockCard.getType()).andReturn(CardType.SKIP).anyTimes();
        EasyMock.replay(mockCard);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(mockCard);
        cards.add(mockCard);
        cards.add(mockCard);

        assertFalse(controller.cardsAllMatchingCatCards(cards));

        EasyMock.verify(mockCard);
    }

    @Test
    void cardsAllMatchingCatCards_MismatchAfterMatch_ReturnsFalse() {
        GameController controller = new GameController(null);
        Card mockCatCard = EasyMock.createMock(Card.class);
        Card mockSkipCard = EasyMock.createMock(Card.class);
        EasyMock.expect(mockCatCard.getType()).andReturn(CardType.CAT_CARD_1).anyTimes();
        EasyMock.expect(mockSkipCard.getType()).andReturn(CardType.SKIP).anyTimes();
        EasyMock.replay(mockCatCard, mockSkipCard);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(mockCatCard);
        cards.add(mockCatCard);
        cards.add(mockSkipCard);

        assertFalse(controller.cardsAllMatchingCatCards(cards));

        EasyMock.verify(mockCatCard, mockSkipCard);
    }

    @Test
    void cardsAllMatchingCatCards_MatchAfterMismatch_ReturnsFalse() {
        GameController controller = new GameController(null);
        Card mockSkipCard = EasyMock.createMock(Card.class);
        Card mockCatCard = EasyMock.createMock(Card.class);
        EasyMock.expect(mockSkipCard.getType()).andReturn(CardType.SKIP).anyTimes();
        EasyMock.expect(mockCatCard.getType()).andReturn(CardType.CAT_CARD_1).anyTimes();
        EasyMock.replay(mockSkipCard, mockCatCard);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(mockSkipCard);
        cards.add(mockCatCard);
        cards.add(mockCatCard);

        assertFalse(controller.cardsAllMatchingCatCards(cards));

        EasyMock.verify(mockSkipCard, mockCatCard);
    }

    @Test
    void cardsAllMatchingCatCards_MismatchAtLast_ReturnsFalse() {
        GameController controller = new GameController(null);
        Card mockCatCard1 = EasyMock.createMock(Card.class);
        Card mockCatCard2 = EasyMock.createMock(Card.class);
        EasyMock.expect(mockCatCard1.getType()).andReturn(CardType.CAT_CARD_1).anyTimes();
        EasyMock.expect(mockCatCard2.getType()).andReturn(CardType.CAT_CARD_2).anyTimes();
        EasyMock.replay(mockCatCard1, mockCatCard2);

        ArrayList<Card> cards = new ArrayList<>();
        cards.add(mockCatCard1);
        cards.add(mockCatCard1);
        cards.add(mockCatCard2);

        assertFalse(controller.cardsAllMatchingCatCards(cards));

        EasyMock.verify(mockCatCard1, mockCatCard2);
    }

    @Test
    void playerHasCards_EmptyCards_IllegalArgumentException() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player = game.getAlivePlayers().get(0);

        ArrayList<Card> cards = new ArrayList<Card>();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.playerHasCards(player, cards);
        });

        assertEquals("cards list cannot be empty", exception.getMessage());
    }

    @Test
    void playerHasCards_OneCardInHand_ReturnTrue() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player = game.getAlivePlayers().get(0);

        player.addCard(new Card(CardType.CAT_CARD_1));
        player.addCard(new Card(CardType.SKIP));

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_1));

        assertTrue(controller.playerHasCards(player, cards));
        ;
    }

    @Test
    void playerHasCards_OneCardNotInHand_ReturnFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player = game.getAlivePlayers().get(0);

        player.addCard(new Card(CardType.CAT_CARD_1));
        player.addCard(new Card(CardType.SKIP));

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_2));

        assertFalse(controller.playerHasCards(player, cards));
        ;
    }

    @Test
    void playerHasCards_OneCardButHandEmpty_ReturnFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player = game.getAlivePlayers().get(0);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_2));

        assertFalse(controller.playerHasCards(player, cards));
        ;
    }

    @Test
    void playerHasCards_TwoSameCardsButOnlyHaveOneInHand_ReturnFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player = game.getAlivePlayers().get(0);

        player.addCard(new Card(CardType.CAT_CARD_1));

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_1));
        cards.add(new Card(CardType.CAT_CARD_1));

        assertFalse(controller.playerHasCards(player, cards));
        ;
    }

    @Test
    void playerHasCards_TwoSameCardsAndHaveBothInHand_ReturnTrue() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player = game.getAlivePlayers().get(0);

        player.addCard(new Card(CardType.CAT_CARD_2));
        player.addCard(new Card(CardType.CAT_CARD_2));

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_2));
        cards.add(new Card(CardType.CAT_CARD_2));

        assertTrue(controller.playerHasCards(player, cards));
        ;
    }

    @Test
    void playerHasCards_TwoDifferentCardsButOnlyHaveOneInHand_ReturnFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player = game.getAlivePlayers().get(0);

        player.addCard(new Card(CardType.CAT_CARD_2));
        player.addCard(new Card(CardType.SHUFFLE));

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.SKIP));
        cards.add(new Card(CardType.CAT_CARD_2));

        assertFalse(controller.playerHasCards(player, cards));
        ;
    }

    @Test
    void playerHasCards_TwoDifferentCardsButHaveNeitherInHand_ReturnFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player = game.getAlivePlayers().get(0);

        player.addCard(new Card(CardType.SHUFFLE));
        player.addCard(new Card(CardType.SEE_THE_FUTURE));

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_2));
        cards.add(new Card(CardType.SKIP));

        assertFalse(controller.playerHasCards(player, cards));
        ;
    }

    @Test
    void playerHasCards_TwoDifferentCardsAndHaveBothInHand_ReturnTrue() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player = game.getAlivePlayers().get(0);

        player.addCard(new Card(CardType.CAT_CARD_4));
        player.addCard(new Card(CardType.SEE_THE_FUTURE));
        player.addCard(new Card(CardType.SHUFFLE));
        player.addCard(new Card(CardType.SKIP));

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.SKIP));
        cards.add(new Card(CardType.CAT_CARD_4));

        assertTrue(controller.playerHasCards(player, cards));
        ;
    }

    @Test
    void playerHasCards_ThreeDifferentCardsButHaveOnlyOneInHand_ReturnFalse() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player = game.getAlivePlayers().get(0);

        player.addCard(new Card(CardType.CAT_CARD_3));

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_3));
        cards.add(new Card(CardType.CAT_CARD_2));
        cards.add(new Card(CardType.CAT_CARD_4));

        assertFalse(controller.playerHasCards(player, cards));
        ;
    }

    @Test
    void playerHasCards_ThreeSameCardsAndHaveAllThreeInHand_ReturnTrue() {
        Game game = new Game(2);
        GameController controller = new GameController(game);
        Player player = game.getAlivePlayers().get(0);

        player.addCard(new Card(CardType.CAT_CARD_4));
        player.addCard(new Card(CardType.ATTACK));
        player.addCard(new Card(CardType.CAT_CARD_4));
        player.addCard(new Card(CardType.CAT_CARD_4));
        player.addCard(new Card(CardType.SHUFFLE));
        player.addCard(new Card(CardType.SKIP));


        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(CardType.CAT_CARD_4));
        cards.add(new Card(CardType.CAT_CARD_4));
        cards.add(new Card(CardType.CAT_CARD_4));

        assertTrue(controller.playerHasCards(player, cards));
        ;
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
        player1.addCard(new Card(CardType.SKIP));

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

        player1.addCard(new Card(CardType.CAT_CARD_1));
        player1.addCard(new Card(CardType.CAT_CARD_1));

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

        player1.addCard(new Card(CardType.CAT_CARD_1));
        player1.addCard(new Card(CardType.CAT_CARD_1));
        player1.addCard(new Card(CardType.CAT_CARD_1));

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

    @Test
    void advanceTurn_CurrentIsIndex3_CurrentIsIndex0AndNextIsIndex1() {
        Game mockGame = mock(Game.class);

        expect(mockGame.getAlivePlayerCount()).andReturn(4).anyTimes();
        replay(mockGame);

        GameController controller = new GameController(mockGame);

        controller.setCurrentPlayerIndex(3);
        controller.setNextPlayerIndex(0);

        controller.advanceTurn();

        assertEquals(0, controller.getCurrentPlayerIndex(),
                "Current player should advance to index 0");
        assertEquals(1, controller.getNextPlayerIndex(),
                "Next player should advance to index 1");

        verify(mockGame);
    }

    @Test
    void advanceTurn_MinimumNumOfPlayers_CurrentIsIndex0AndNextIsIndex1() {
        Game mockGame = mock(Game.class);

        expect(mockGame.getAlivePlayerCount()).andReturn(2).anyTimes();
        replay(mockGame);

        GameController controller = new GameController(mockGame);

        controller.setCurrentPlayerIndex(0);
        controller.setNextPlayerIndex(1);

        controller.advanceTurn();

        assertEquals(1, controller.getCurrentPlayerIndex(),
                "Current player should advance to index 1");
        assertEquals(0, controller.getNextPlayerIndex(),
                "Next player should advance to index 0");

        verify(mockGame);
    }

    @Test
    void advanceTurn_5PlayersMaxBoundary_CurrentIsIndex4AndNextIsIndex0() {
        Game mockGame = mock(Game.class);

        expect(mockGame.getAlivePlayerCount()).andReturn(5).anyTimes();
        replay(mockGame);

        GameController controller = new GameController(mockGame);

        controller.setCurrentPlayerIndex(4);
        controller.setNextPlayerIndex(0);

        controller.advanceTurn();

        assertEquals(0, controller.getCurrentPlayerIndex(),
                "Current player should advance to index 1");
        assertEquals(1, controller.getNextPlayerIndex(),
                "Next player should advance to index 0");

        verify(mockGame);
    }

    @Test
    void takeTurn_emptyInput_InvalidOutputFunction() {
        int currentPlayerIndex = 0;
        String mockUserChoice = "";
        ArrayList<Player> alivePlayers = new ArrayList<Player>();

        Game mockGame = mock(Game.class);
        Player mockPlayer1 = mock(Player.class);
        GameControllerView mockControllerView = EasyMock.createMock(GameControllerView.class);

        alivePlayers.add(mockPlayer1);

        expect(mockGame.getAlivePlayerCount()).andReturn(2);
        expect(mockGame.getAlivePlayers()).andReturn(alivePlayers);
        mockControllerView.displayCurrentPlayerAndCardsInHand(mockPlayer1);
        expectLastCall();
        expect(mockControllerView.getCardChoiceOrDraw()).andReturn(mockUserChoice);
        mockControllerView.displayInvalidChoice(mockUserChoice);
        expectLastCall();

        replay(mockGame, mockControllerView, mockPlayer1);

        GameController controller = new GameController(mockGame);
        controller.setCurrentPlayerIndex(currentPlayerIndex);
        controller.takeTurn(mockControllerView);

        verify(mockGame, mockControllerView, mockPlayer1);
    }

    @Test
    void takeTurn_NonemptyInvalidInput_InvalidOutputFunction() {
        int currentPlayerIndex = 0;
        String mockUserChoice = "skip";
        ArrayList<Player> alivePlayers = new ArrayList<Player>();

        Game mockGame = mock(Game.class);
        Player mockPlayer1 = mock(Player.class);
        GameControllerView mockControllerView = EasyMock.createMock(GameControllerView.class);

        alivePlayers.add(mockPlayer1);

        expect(mockGame.getAlivePlayerCount()).andReturn(2);
        expect(mockGame.getAlivePlayers()).andReturn(alivePlayers);
        mockControllerView.displayCurrentPlayerAndCardsInHand(mockPlayer1);
        expectLastCall();
        expect(mockControllerView.getCardChoiceOrDraw()).andReturn(mockUserChoice);
        mockControllerView.displayInvalidChoice(mockUserChoice);
        expectLastCall();

        replay(mockGame, mockControllerView, mockPlayer1);

        GameController controller = new GameController(mockGame);
        controller.setCurrentPlayerIndex(currentPlayerIndex);
        controller.takeTurn(mockControllerView);

        verify(mockGame, mockControllerView, mockPlayer1);


    }

    @Test
    void takeTurn_InputIsD_DrawCardAndReduceTurnsLeft() {
        int currentPlayerIndex = 0;
        String mockUserChoice = "d";
        ArrayList<Player> alivePlayers = new ArrayList<Player>();

        Game mockGame = mock(Game.class);
        Player mockPlayer = mock(Player.class);
        Deck mockDeck = mock(Deck.class);
        GameControllerView mockControllerView = mock(GameControllerView.class);

        alivePlayers.add(mockPlayer);

        expect(mockGame.getAlivePlayerCount()).andReturn(2).anyTimes();
        expect(mockGame.getAlivePlayers()).andReturn(alivePlayers);

        mockControllerView.displayCurrentPlayerAndCardsInHand(mockPlayer);
        expectLastCall();

        expect(mockControllerView.getCardChoiceOrDraw()).andReturn(mockUserChoice);

        expect(mockGame.getDeck()).andReturn(mockDeck);
        mockGame.draw(mockPlayer, mockDeck);
        expectLastCall();

        replay(mockGame, mockPlayer, mockDeck, mockControllerView);

        GameController controller = new GameController(mockGame);
        controller.setCurrentPlayerIndex(currentPlayerIndex);
        controller.setCurrentPlayerTurnsLeft(1);

        controller.takeTurn(mockControllerView);

        assertEquals(0, controller.getCurrentPlayerTurnsLeft());

        verify(mockGame, mockPlayer, mockDeck, mockControllerView);
    }

    @Test
    void takeTurn_InputIsSingleCatCard_CallsDisplayInvalidMove() {
        int currentPlayerIndex = 0;
        String mockUserChoice = "1";
        ArrayList<Player> alivePlayers = new ArrayList<Player>();
        ArrayList<Card> hand = new ArrayList<Card>();

        Game mockGame = mock(Game.class);
        Player mockPlayer = mock(Player.class);
        Card mockSkipCard = mock(Card.class);
        Card mockCatCard = mock(Card.class);
        GameControllerView mockControllerView = mock(GameControllerView.class);

        alivePlayers.add(mockPlayer);
        hand.add(mockSkipCard);
        hand.add(mockCatCard);

        expect(mockGame.getAlivePlayerCount()).andReturn(2);
        expect(mockGame.getAlivePlayers()).andReturn(alivePlayers);

        mockControllerView.displayCurrentPlayerAndCardsInHand(mockPlayer);
        expectLastCall();

        expect(mockControllerView.getCardChoiceOrDraw()).andReturn(mockUserChoice);

        expect(mockPlayer.getHand()).andReturn(hand);

        mockControllerView.displayInvalidMove(isA(ArrayList.class));
        expectLastCall();

        replay(mockGame, mockPlayer, mockSkipCard, mockCatCard, mockControllerView);

        GameController controller = new GameController(mockGame);
        controller.setCurrentPlayerIndex(currentPlayerIndex);
        controller.setCurrentPlayerTurnsLeft(1);

        controller.takeTurn(mockControllerView);

        assertEquals(1, controller.getCurrentPlayerTurnsLeft());
        verify(mockGame, mockPlayer, mockSkipCard, mockCatCard, mockControllerView);
    }

    @Test
    void takeTurn_InputIsOutOfBounds_CallsDisplayInvalidIndex() {
        int currentPlayerIndex = 0;
        String mockUserChoice = "5";
        ArrayList<Player> alivePlayers = new ArrayList<Player>();
        ArrayList<Card> hand = new ArrayList<Card>();

        Game mockGame = mock(Game.class);
        Player mockPlayer = mock(Player.class);
        Card mockSkipCard = mock(Card.class);
        Card mockCatCard = mock(Card.class);
        GameControllerView mockControllerView = mock(GameControllerView.class);

        alivePlayers.add(mockPlayer);
        hand.add(mockSkipCard);
        hand.add(mockCatCard);

        expect(mockGame.getAlivePlayerCount()).andReturn(2).anyTimes();
        expect(mockGame.getAlivePlayers()).andReturn(alivePlayers);

        mockControllerView.displayCurrentPlayerAndCardsInHand(mockPlayer);
        expectLastCall();

        expect(mockControllerView.getCardChoiceOrDraw()).andReturn(mockUserChoice);

        expect(mockPlayer.getHand()).andReturn(hand);

        mockControllerView.displayInvalidIndex(mockUserChoice);
        expectLastCall();

        replay(mockGame, mockPlayer, mockSkipCard, mockCatCard, mockControllerView);

        GameController controller = new GameController(mockGame);
        controller.setCurrentPlayerIndex(currentPlayerIndex);
        controller.setCurrentPlayerTurnsLeft(1);

        controller.takeTurn(mockControllerView);

        assertEquals(1, controller.getCurrentPlayerTurnsLeft());
        verify(mockGame, mockPlayer, mockSkipCard, mockCatCard, mockControllerView);
    }
}
