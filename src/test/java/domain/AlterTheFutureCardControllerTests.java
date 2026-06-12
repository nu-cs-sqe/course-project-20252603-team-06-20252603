package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AlterTheFutureCardControllerTests {

    @Test
    public void getTopCards_EmptyDeck_ReturnsEmptyList() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Deck deck = EasyMock.createMock(Deck.class);

        EasyMock.expect(deck.getCards()).andReturn(new ArrayList<>());

        EasyMock.replay(deck);

        List<Card> result = controller.getTopCards(deck);

        assertTrue(result.isEmpty());
        EasyMock.verify(deck);
    }

    @Test
    public void getTopCards_DeckHasOneCard_ReturnsListWithOneCard() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Deck deck = EasyMock.createMock(Deck.class);
        Card cardA = new Card(CardType.CAT_CARD_1);

        EasyMock.expect(deck.getCards()).andReturn(new ArrayList<>(List.of(cardA)));

        EasyMock.replay(deck);

        List<Card> result = controller.getTopCards(deck);

        assertEquals(1, result.size());
        assertEquals(cardA, result.get(0));
        EasyMock.verify(deck);
    }

    @Test
    public void getTopCards_DeckHasTwoCards_ReturnsListWithTwoCards() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Deck deck = EasyMock.createMock(Deck.class);
        Card cardA = new Card(CardType.CAT_CARD_1);
        Card cardB = new Card(CardType.CAT_CARD_2);

        EasyMock.expect(deck.getCards()).andReturn(new ArrayList<>(List.of(cardA, cardB)));

        EasyMock.replay(deck);

        List<Card> result = controller.getTopCards(deck);

        assertEquals(2, result.size());
        assertEquals(cardA, result.get(0));
        assertEquals(cardB, result.get(1));
        EasyMock.verify(deck);
    }

    @Test
    public void getTopCards_DeckHasThreeCards_ReturnsListWithThreeCards() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Deck deck = EasyMock.createMock(Deck.class);
        Card cardA = new Card(CardType.CAT_CARD_1);
        Card cardB = new Card(CardType.CAT_CARD_2);
        Card cardC = new Card(CardType.CAT_CARD_3);

        EasyMock.expect(deck.getCards()).andReturn(new ArrayList<>(List.of(cardA, cardB, cardC)));

        EasyMock.replay(deck);

        List<Card> result = controller.getTopCards(deck);

        assertEquals(3, result.size());
        assertEquals(cardA, result.get(0));
        assertEquals(cardB, result.get(1));
        assertEquals(cardC, result.get(2));
        EasyMock.verify(deck);
    }

    @Test
    public void getTopCards_DeckHasMoreThanThreeCards_ReturnsOnlyTopThree() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Deck deck = EasyMock.createMock(Deck.class);
        Card cardA = new Card(CardType.CAT_CARD_1);
        Card cardB = new Card(CardType.CAT_CARD_2);
        Card cardC = new Card(CardType.CAT_CARD_3);
        Card cardD = new Card(CardType.CAT_CARD_4);

        EasyMock.expect(deck.getCards())
                .andReturn(new ArrayList<>(List.of(cardA, cardB, cardC, cardD)));

        EasyMock.replay(deck);

        List<Card> result = controller.getTopCards(deck);

        assertEquals(3, result.size());
        assertEquals(cardA, result.get(0));
        assertEquals(cardB, result.get(1));
        assertEquals(cardC, result.get(2));
        EasyMock.verify(deck);
    }

    @Test
    public void validateReorder_ReorderedHasFewerCards_ThrowsIllegalArgumentException() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Card card1 = new Card(CardType.CAT_CARD_1);
        Card card2 = new Card(CardType.CAT_CARD_2);
        Card card3 = new Card(CardType.CAT_CARD_3);

        List<Card> original = new ArrayList<>(List.of(card1, card2, card3));
        List<Card> reordered = new ArrayList<>(List.of(card1, card2));

        assertThrows(IllegalArgumentException.class, () ->
            controller.validateReorder(original, reordered)
        );
    }

    @Test
    public void validateReorder_ReorderedHasMoreCards_ThrowsIllegalArgumentException() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Card card1 = new Card(CardType.CAT_CARD_1);
        Card card2 = new Card(CardType.CAT_CARD_2);
        Card card3 = new Card(CardType.CAT_CARD_3);
        Card card4 = new Card(CardType.CAT_CARD_4);

        List<Card> original = new ArrayList<>(List.of(card1, card2, card3));
        List<Card> reordered = new ArrayList<>(List.of(card1, card2, card3, card4));

        assertThrows(IllegalArgumentException.class, () ->
            controller.validateReorder(original, reordered)
        );
    }

    @Test
    public void validateReorder_ReorderedHasSameCountButDifferentCard_ThrowsException() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Card card1 = new Card(CardType.CAT_CARD_1);
        Card card2 = new Card(CardType.CAT_CARD_2);
        Card card3 = new Card(CardType.CAT_CARD_3);
        Card foreignCard = new Card(CardType.CAT_CARD_4);

        List<Card> original = new ArrayList<>(List.of(card1, card2, card3));
        List<Card> reordered = new ArrayList<>(List.of(card1, card2, foreignCard));

        Exception e = assertThrows(IllegalArgumentException.class, () ->
            controller.validateReorder(original, reordered)
        );
        assertEquals("Reordered list contains cards not in the original", e.getMessage());
    }

    @Test
    public void validateReorder_ValidRearrangement_NoExceptionThrown() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Card card1 = new Card(CardType.CAT_CARD_1);
        Card card2 = new Card(CardType.CAT_CARD_2);
        Card card3 = new Card(CardType.CAT_CARD_3);

        List<Card> original = new ArrayList<>(List.of(card1, card2, card3));
        List<Card> reordered = new ArrayList<>(List.of(card3, card1, card2));

        assertDoesNotThrow(() -> controller.validateReorder(original, reordered));
    }

    @Test
    public void validateReorder_IdenticalToOriginal_NoExceptionThrown() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Card card1 = new Card(CardType.CAT_CARD_1);
        Card card2 = new Card(CardType.CAT_CARD_2);
        Card card3 = new Card(CardType.CAT_CARD_3);

        List<Card> original = new ArrayList<>(List.of(card1, card2, card3));
        List<Card> reordered = new ArrayList<>(List.of(card1, card2, card3));

        assertDoesNotThrow(() -> controller.validateReorder(original, reordered));
    }
    
    @Test
    public void applyReorder_OneDeckCard_DeckUnchanged() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Deck deck = EasyMock.createMock(Deck.class);
        Card cardA = new Card(CardType.CAT_CARD_1);

        deck.discard(cardA);
        deck.insert(cardA, 0);

        EasyMock.replay(deck);

        List<Card> original = new ArrayList<>(List.of(cardA));
        List<Card> reordered = new ArrayList<>(List.of(cardA));

        controller.applyReorder(deck, original, reordered);

        EasyMock.verify(deck);
    }
    
    
    @Test
    public void applyReorder_TwoCardsReordered_TopTwoSwapped() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Deck deck = EasyMock.createMock(Deck.class);
        Card cardA = new Card(CardType.CAT_CARD_1);
        Card cardB = new Card(CardType.CAT_CARD_2);

        deck.discard(cardA);
        deck.discard(cardB);
        deck.insert(cardB, 0);
        deck.insert(cardA, 1);

        EasyMock.replay(deck);

        List<Card> original = new ArrayList<>(List.of(cardA, cardB));
        List<Card> reordered = new ArrayList<>(List.of(cardB, cardA));

        controller.applyReorder(deck, original, reordered);

        EasyMock.verify(deck);
    }

    @Test
    public void applyReorder_ThreeCardsReordered_TopThreeReordered() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Deck deck = EasyMock.createMock(Deck.class);
        Card cardA = new Card(CardType.CAT_CARD_1);
        Card cardB = new Card(CardType.CAT_CARD_2);
        Card cardC = new Card(CardType.CAT_CARD_3);

        deck.discard(cardA);
        deck.discard(cardB);
        deck.discard(cardC);
        deck.insert(cardC, 0);
        deck.insert(cardB, 1);
        deck.insert(cardA, 2);

        EasyMock.replay(deck);

        List<Card> original = new ArrayList<>(List.of(cardA, cardB, cardC));
        List<Card> reordered = new ArrayList<>(List.of(cardC, cardB, cardA));

        controller.applyReorder(deck, original, reordered);

        EasyMock.verify(deck);
    }

    @Test
    public void executeCardAction_EmptyDeck_ReturnsEmpty() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        GameController gameController = EasyMock.createMock(GameController.class);
        Game game = EasyMock.createMock(Game.class);
        Player user = EasyMock.createMock(Player.class);
        Deck deck = EasyMock.createMock(Deck.class);

        EasyMock.expect(gameController.getGame()).andReturn(game);
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.getCards()).andReturn(new ArrayList<>());

        EasyMock.replay(gameController, game, user, deck);

        Optional<List<Card>> result = controller.executeCardAction(gameController,
                user,
                Optional.empty());

        assertTrue(result.isEmpty());
        EasyMock.verify(gameController, game, user, deck);
    }

    @Test
    public void executeCardAction_OneDeckCard_ReturnsEmpty() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        GameController gameController = EasyMock.createMock(GameController.class);
        Game game = EasyMock.createMock(Game.class);
        Player user = EasyMock.createMock(Player.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Card cardA = new Card(CardType.CAT_CARD_1);

        EasyMock.expect(gameController.getGame()).andReturn(game);
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.getCards()).andReturn(new ArrayList<>(List.of(cardA)));
        deck.discard(cardA);
        deck.insert(cardA, 0);

        EasyMock.replay(gameController, game, user, deck);

        Optional<List<Card>> result = controller.executeCardAction(gameController,
                user,
                Optional.empty());

        assertTrue(result.isEmpty());
        EasyMock.verify(gameController, game, user, deck);
    }

    @Test
    public void executeCardAction_TwoDeckCards_TopTwoSwapped() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(
            cards -> new ArrayList<>(List.of(cards.get(1), cards.get(0)))
        );
        GameController gameController = EasyMock.createMock(GameController.class);
        Game game = EasyMock.createMock(Game.class);
        Player user = EasyMock.createMock(Player.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Card cardA = new Card(CardType.CAT_CARD_1);
        Card cardB = new Card(CardType.CAT_CARD_2);

        EasyMock.expect(gameController.getGame()).andReturn(game);
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.getCards()).andReturn(new ArrayList<>(List.of(cardA, cardB)));
        deck.discard(cardA);
        deck.discard(cardB);
        deck.insert(cardB, 0);
        deck.insert(cardA, 1);

        EasyMock.replay(gameController, game, user, deck);

        Optional<List<Card>> result = controller.executeCardAction(gameController,
                user,
                Optional.empty());

        assertTrue(result.isEmpty());
        EasyMock.verify(gameController, game, user, deck);
    }

    @Test
    public void executeCardAction_ThreeDeckCards_TopThreeReordered() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(
            cards -> new ArrayList<>(List.of(cards.get(2), cards.get(1), cards.get(0)))
        );
        GameController gameController = EasyMock.createMock(GameController.class);
        Game game = EasyMock.createMock(Game.class);
        Player user = EasyMock.createMock(Player.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Card cardA = new Card(CardType.CAT_CARD_1);
        Card cardB = new Card(CardType.CAT_CARD_2);
        Card cardC = new Card(CardType.CAT_CARD_3);

        EasyMock.expect(gameController.getGame()).andReturn(game);
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.getCards()).andReturn(new ArrayList<>(List.of(cardA, cardB, cardC)));
        deck.discard(cardA);
        deck.discard(cardB);
        deck.discard(cardC);
        deck.insert(cardC, 0);
        deck.insert(cardB, 1);
        deck.insert(cardA, 2);

        EasyMock.replay(gameController, game, user, deck);

        Optional<List<Card>> result = controller.executeCardAction(gameController,
                user,
                Optional.empty());

        assertTrue(result.isEmpty());
        EasyMock.verify(gameController, game, user, deck);
    }
    @Test
    public void executeCardAction_MoreThanThreeDeckCards_OnlyTopThreeReordered() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(
            cards -> new ArrayList<>(List.of(cards.get(2), cards.get(1), cards.get(0)))
        );
        GameController gameController = EasyMock.createMock(GameController.class);
        Game game = EasyMock.createMock(Game.class);
        Player user = EasyMock.createMock(Player.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Card cardA = new Card(CardType.CAT_CARD_1);
        Card cardB = new Card(CardType.CAT_CARD_2);
        Card cardC = new Card(CardType.CAT_CARD_3);
        Card cardD = new Card(CardType.CAT_CARD_4);
        Card cardE = new Card(CardType.SKIP);

        EasyMock.expect(gameController.getGame()).andReturn(game);
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.getCards())
                .andReturn(new ArrayList<>(List.of(cardA, cardB, cardC, cardD, cardE)));
        deck.discard(cardA);
        deck.discard(cardB);
        deck.discard(cardC);
        deck.insert(cardC, 0);
        deck.insert(cardB, 1);
        deck.insert(cardA, 2);

        EasyMock.replay(gameController, game, user, deck);

        Optional<List<Card>> result = controller.executeCardAction(gameController,
                user,
                Optional.empty());

        assertTrue(result.isEmpty());
        EasyMock.verify(gameController, game, user, deck);
    }

    @Test
    public void executeCardAction_InvalidReorder_ThrowsIllegalArgumentException() {
        Card foreignCard = new Card(CardType.SKIP);
        AlterTheFutureCardController controller = new AlterTheFutureCardController(
            cards -> new ArrayList<>(List.of(cards.get(0), cards.get(1), foreignCard))
        );
        GameController gameController = EasyMock.createMock(GameController.class);
        Game game = EasyMock.createMock(Game.class);
        Player user = EasyMock.createMock(Player.class);
        Deck deck = EasyMock.createMock(Deck.class);
        Card cardA = new Card(CardType.CAT_CARD_1);
        Card cardB = new Card(CardType.CAT_CARD_2);
        Card cardC = new Card(CardType.CAT_CARD_3);

        EasyMock.expect(gameController.getGame()).andReturn(game);
        EasyMock.expect(game.getDeck()).andReturn(deck);
        EasyMock.expect(deck.getCards()).andReturn(new ArrayList<>(List.of(cardA, cardB, cardC)));

        EasyMock.replay(gameController, game, user, deck);

        assertThrows(IllegalArgumentException.class, () ->
            controller.executeCardAction(gameController, user, Optional.empty())
        );

        EasyMock.verify(gameController, game, user, deck);
    }
}

