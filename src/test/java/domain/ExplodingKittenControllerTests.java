package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import ui.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ExplodingKittenControllerTests {

    @Test
    void executeCardAction_noDefuseEmptyHand_playerKilled() {
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockUser = EasyMock.createMock(Player.class);
        Deck mockDeck = EasyMock.createMock(Deck.class);
        UserInput mockInput = EasyMock.createMock(UserInput.class);

        EasyMock.expect(mockGame.getDeck()).andReturn(mockDeck).anyTimes();
        EasyMock.expect(mockUser.hasDefuse()).andReturn(false).anyTimes();
        mockUser.kill();
        EasyMock.expectLastCall().once();
        mockGame.removeAlivePlayer(mockUser);
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockGame, mockUser, mockDeck);

        ExplodingKittenController controller = new ExplodingKittenController(mockInput);
        controller.executeCardAction(mockGame, mockUser, Optional.empty());

        EasyMock.verify(mockGame, mockUser, mockDeck);
    }

    @Test
    public void executeCardAction_oneDefuseOneCard_playerLivesEmptyHand() {
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockUser = EasyMock.createMock(Player.class);
        Deck mockDeck = EasyMock.createMock(Deck.class);
        UserInput mockInput = EasyMock.createMock(UserInput.class);

        Card defuse = new Card(CardType.DEFUSE);
        int deckSize = 5;

        EasyMock.expect(mockGame.getDeck()).andReturn(mockDeck).anyTimes();
        EasyMock.expect(mockUser.hasDefuse()).andReturn(true).anyTimes();
        EasyMock.expect(mockUser.getHand()).andReturn(new ArrayList<>(List.of(defuse))).anyTimes();
        mockUser.removeCard(defuse);
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockDeck.count()).andReturn(deckSize).anyTimes();
        EasyMock.expect(mockInput.getInsertPosition(deckSize)).andReturn(0);
        mockDeck.insert(EasyMock.isA(Card.class), EasyMock.eq(0));
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockGame, mockUser, mockDeck, mockInput);

        ExplodingKittenController controller = new ExplodingKittenController(mockInput);
        controller.executeCardAction(mockGame, mockUser, Optional.empty());

        EasyMock.verify(mockGame, mockUser, mockDeck, mockInput);
    }

    @Test
    public void executeCardAction_twoDefuses_playerLivesOneDefuse() {
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockUser = EasyMock.createMock(Player.class);
        Deck mockDeck = EasyMock.createMock(Deck.class);
        UserInput mockInput = EasyMock.createMock(UserInput.class);

        Card defuse1 = new Card(CardType.DEFUSE);
        Card defuse2 = new Card(CardType.DEFUSE);
        int deckSize = 5;

        EasyMock.expect(mockGame.getDeck()).andReturn(mockDeck).anyTimes();
        EasyMock.expect(mockUser.hasDefuse()).andReturn(true).anyTimes();
        EasyMock.expect(mockUser.getHand()).andReturn(new ArrayList<>(List.of(defuse1, defuse2))).anyTimes();
        mockUser.removeCard(defuse1);
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockDeck.count()).andReturn(deckSize).anyTimes();
        EasyMock.expect(mockInput.getInsertPosition(deckSize)).andReturn(0);
        mockDeck.insert(EasyMock.anyObject(Card.class), EasyMock.eq(0));
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockGame, mockUser, mockDeck, mockInput);

        ExplodingKittenController controller = new ExplodingKittenController(mockInput);
        controller.executeCardAction(mockGame, mockUser, Optional.empty());

        EasyMock.verify(mockGame, mockUser, mockDeck, mockInput);
    }

    @Test
    public void executeCardAction_lastCardDefuseTwoCards_playerLivesOneCard() {
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockUser = EasyMock.createMock(Player.class);
        Deck mockDeck = EasyMock.createMock(Deck.class);
        UserInput mockInput = EasyMock.createMock(UserInput.class);

        Card attack = new Card(CardType.ATTACK);
        Card defuse = new Card(CardType.DEFUSE);
        int deckSize = 5;

        EasyMock.expect(mockGame.getDeck()).andReturn(mockDeck).anyTimes();
        EasyMock.expect(mockUser.hasDefuse()).andReturn(true).anyTimes();
        EasyMock.expect(mockUser.getHand()).andReturn(new ArrayList<>(List.of(attack, defuse))).anyTimes();
        mockUser.removeCard(defuse);
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockDeck.count()).andReturn(deckSize).anyTimes();
        EasyMock.expect(mockInput.getInsertPosition(deckSize)).andReturn(0);
        mockDeck.insert(EasyMock.anyObject(Card.class), EasyMock.eq(0));
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockGame, mockUser, mockDeck, mockInput);

        ExplodingKittenController controller = new ExplodingKittenController(mockInput);
        controller.executeCardAction(mockGame, mockUser, Optional.empty());

        EasyMock.verify(mockGame, mockUser, mockDeck, mockInput);
    }

    @Test
    public void executeCardAction_firstCardDefuseThreeCards_playerLivesOneCard() {
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockUser = EasyMock.createMock(Player.class);
        Deck mockDeck = EasyMock.createMock(Deck.class);
        UserInput mockInput = EasyMock.createMock(UserInput.class);

        Card defuse = new Card(CardType.DEFUSE);
        Card attack = new Card(CardType.ATTACK);
        Card skip = new Card(CardType.SKIP);
        int deckSize = 5;

        EasyMock.expect(mockGame.getDeck()).andReturn(mockDeck).anyTimes();
        EasyMock.expect(mockUser.hasDefuse()).andReturn(true).anyTimes();
        EasyMock.expect(mockUser.getHand()).andReturn(new ArrayList<>(List.of(defuse, attack, skip))).anyTimes();
        mockUser.removeCard(defuse);
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockDeck.count()).andReturn(deckSize).anyTimes();
        EasyMock.expect(mockInput.getInsertPosition(deckSize)).andReturn(0);
        mockDeck.insert(EasyMock.anyObject(Card.class), EasyMock.eq(0));
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockGame, mockUser, mockDeck, mockInput);

        ExplodingKittenController controller = new ExplodingKittenController(mockInput);
        controller.executeCardAction(mockGame, mockUser, Optional.empty());

        EasyMock.verify(mockGame, mockUser, mockDeck, mockInput);
    }
}

