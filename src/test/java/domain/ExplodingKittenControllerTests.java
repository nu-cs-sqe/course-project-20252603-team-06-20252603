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
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockUser = EasyMock.createMock(Player.class);
        Deck mockDeck = EasyMock.createMock(Deck.class);

        EasyMock.expect(mockGame.getDeck()).andReturn(mockDeck).anyTimes();
        EasyMock.expect(mockUser.hasDefuse()).andReturn(false).anyTimes();
        mockUser.kill();
        EasyMock.expectLastCall().once();
        mockGame.removeAlivePlayer(mockUser);
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockGame, mockUser, mockDeck);

        ExplodingKittenController controller = new ExplodingKittenController(new UserInput());
        controller.executeCardAction(mockGame, mockUser, Optional.empty());

        EasyMock.verify(mockGame, mockUser, mockDeck);
    }

    @Test
    public void executeCardAction_oneDefuseOneCard_playerLivesEmptyHand() {
        Game game = new Game(2);
        Player user = game.getTotalPlayers().get(0);
        user.addCard(new Card(CardType.DEFUSE));
        int deckSizeBefore = game.getDeck().count();

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getInsertPosition(deckSizeBefore)).andStubReturn(0);
        EasyMock.replay(userInput);

        ExplodingKittenController controller = new ExplodingKittenController(userInput);
        controller.executeCardAction(game, user, Optional.empty());

        assertTrue(user.isAlive());
        assertEquals(new ArrayList<>(), user.getHand());
        assertEquals(deckSizeBefore + 1, game.getDeck().count());
        assertEquals(CardType.EXPLODING_KITTEN, game.getDeck().getCards().get(0).getType());

        EasyMock.verify(userInput);
    }

    @Test
    public void executeCardAction_twoDefuses_playerLivesOneDefuse() {
        Game game = new Game(2);
        Player user = game.getTotalPlayers().get(0);
        user.addCard(new Card(CardType.DEFUSE));
        user.addCard(new Card(CardType.DEFUSE));
        int deckSizeBefore = game.getDeck().count();

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getInsertPosition(deckSizeBefore)).andStubReturn(0);
        EasyMock.replay(userInput);

        ExplodingKittenController controller = new ExplodingKittenController(userInput);
        controller.executeCardAction(game, user, Optional.empty());

        assertTrue(user.isAlive());
        assertEquals(1, user.getHandSize());
        assertTrue(user.hasDefuse());
        assertEquals(deckSizeBefore + 1, game.getDeck().count());
        assertEquals(CardType.EXPLODING_KITTEN, game.getDeck().getCards().get(0).getType());

        EasyMock.verify(userInput);
    }

    @Test
    public void executeCardAction_lastCardDefuseTwoCards_playerLivesOneCard() {
        Game game = new Game(2);
        Player user = game.getTotalPlayers().get(0);
        user.addCard(new Card(CardType.ATTACK));
        user.addCard(new Card(CardType.DEFUSE));
        int deckSizeBefore = game.getDeck().count();

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getInsertPosition(deckSizeBefore)).andStubReturn(0);
        EasyMock.replay(userInput);

        ExplodingKittenController controller = new ExplodingKittenController(userInput);
        controller.executeCardAction(game, user, Optional.empty());

        assertTrue(user.isAlive());
        assertFalse(user.hasDefuse());
        assertEquals(1, user.getHandSize());
        assertTrue(user.hasCard(CardType.ATTACK));
        assertEquals(deckSizeBefore + 1, game.getDeck().count());
        assertEquals(CardType.EXPLODING_KITTEN, game.getDeck().getCards().get(0).getType());

        EasyMock.verify(userInput);
    }

    @Test
    public void executeCardAction_firstCardDefuseThreeCards_playerLivesOneCard() {
        Game game = new Game(2);
        Player user = game.getTotalPlayers().get(0);
        user.addCard(new Card(CardType.DEFUSE));
        user.addCard(new Card(CardType.ATTACK));
        user.addCard(new Card(CardType.SKIP));
        int deckSizeBefore = game.getDeck().count();

        UserInput userInput = EasyMock.createMock(UserInput.class);
        EasyMock.expect(userInput.getInsertPosition(deckSizeBefore)).andStubReturn(0);
        EasyMock.replay(userInput);

        ExplodingKittenController controller = new ExplodingKittenController(userInput);
        controller.executeCardAction(game, user, Optional.empty());

        assertTrue(user.isAlive());
        assertFalse(user.hasDefuse());
        assertEquals(2, user.getHandSize());
        assertTrue(user.hasCard(CardType.ATTACK));
        assertTrue(user.hasCard(CardType.SKIP));
        assertEquals(deckSizeBefore + 1, game.getDeck().count());
        assertEquals(CardType.EXPLODING_KITTEN, game.getDeck().getCards().get(0).getType());

        EasyMock.verify(userInput);
    }

    @Test
    public void executeCardAction_noDefuseTwoCards_playerKilled() {
        Game game = new Game(2);
        Player user = game.getTotalPlayers().get(0);
        user.addCard(new Card(CardType.ATTACK));
        user.addCard(new Card(CardType.SKIP));
        int deckSizeBefore = game.getDeck().count();

        UserInput userInput = new UserInput();

        ExplodingKittenController controller = new ExplodingKittenController(userInput);
        controller.executeCardAction(game, user, Optional.empty());

        assertFalse(user.isAlive());
        assertEquals(2, user.getHandSize());
        assertTrue(user.hasCard(CardType.ATTACK));
        assertTrue(user.hasCard(CardType.SKIP));
        assertEquals(deckSizeBefore, game.getDeck().count());
    }
}

