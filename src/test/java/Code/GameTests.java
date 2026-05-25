package Code;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 6, 7})
    public void constructor_InvalidPlayerCounts_ThrowsIllegalArgumentException(int playerCount) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Game(playerCount);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    public void constructor_ValidPlayerCounts_CreatesCorrectNumberOfPlayers(int playerCount) {
        Game game = new Game(playerCount);

        assertEquals(playerCount, game.getPlayerCount());

        // From your original tests, explicitly checking null for the 2-player baseline
        if (playerCount == 2) {
            assertNull(game.getCurrentPlayer());
        }
    }

//    @Test
//    public void setup_TwoPlayers_DealsOneDefuseAndSevenCardsToEachPlayer() {
//        Deck mockDeck = EasyMock.createMock(Deck.class);
//        Player mockP1 = EasyMock.createMock(Player.class);
//        Player mockP2 = EasyMock.createMock(Player.class);
//        Card mockDefuse = EasyMock.createMock(Card.class);
//        Card mockCard = EasyMock.createMock(Card.class);
//
//        EasyMock.expect(mockDeck.count()).andStubReturn(100);
//        EasyMock.expect(mockDeck.drawDefuse()).andReturn(mockDefuse).times(2);
//        EasyMock.expect(mockDeck.draw()).andReturn(mockCard).times(14);
//
//        mockP1.addCard(mockDefuse);
//        EasyMock.expectLastCall().once();
//        mockP1.addCard(mockCard);
//        EasyMock.expectLastCall().times(7);
//        mockP2.addCard(mockDefuse);
//        EasyMock.expectLastCall().once();
//        mockP2.addCard(mockCard);
//        EasyMock.expectLastCall().times(7);
//
//        mockDeck.insert(EasyMock.anyObject(Card.class));
//        EasyMock.expectLastCall().anyTimes();
//        mockDeck.shuffle();
//        EasyMock.expectLastCall().anyTimes();
//
//        EasyMock.replay(mockDeck, mockP1, mockP2, mockDefuse, mockCard);
//
//        new Game(mockDeck, List.of(mockP1, mockP2)).setup();
//
//        EasyMock.verify(mockDeck, mockP1, mockP2);
//    }
//
//    @Test
//    public void setup_ThreePlayers_DealsOneDefuseAndSevenCardsToEachPlayer() {
//        Deck mockDeck = EasyMock.createMock(Deck.class);
//        Player mockP1 = EasyMock.createMock(Player.class);
//        Player mockP2 = EasyMock.createMock(Player.class);
//        Player mockP3 = EasyMock.createMock(Player.class);
//        Card mockDefuse = EasyMock.createMock(Card.class);
//        Card mockCard = EasyMock.createMock(Card.class);
//
//        EasyMock.expect(mockDeck.count()).andStubReturn(100);
//        EasyMock.expect(mockDeck.drawDefuse()).andReturn(mockDefuse).times(3);
//        EasyMock.expect(mockDeck.draw()).andReturn(mockCard).times(21);
//
//        mockP1.addCard(mockDefuse);
//        EasyMock.expectLastCall().once();
//        mockP1.addCard(mockCard);
//        EasyMock.expectLastCall().times(7);
//        mockP2.addCard(mockDefuse);
//        EasyMock.expectLastCall().once();
//        mockP2.addCard(mockCard);
//        EasyMock.expectLastCall().times(7);
//        mockP3.addCard(mockDefuse);
//        EasyMock.expectLastCall().once();
//        mockP3.addCard(mockCard);
//        EasyMock.expectLastCall().times(7);
//
//        mockDeck.insert(EasyMock.anyObject(Card.class));
//        EasyMock.expectLastCall().anyTimes();
//        mockDeck.shuffle();
//        EasyMock.expectLastCall().anyTimes();
//
//        EasyMock.replay(mockDeck, mockP1, mockP2, mockP3, mockDefuse, mockCard);
//
//        new Game(mockDeck, List.of(mockP1, mockP2, mockP3)).setup();
//
//        EasyMock.verify(mockDeck, mockP1, mockP2, mockP3);
//    }
//
//    @Test
//    public void setup_FivePlayers_DealsOneDefuseAndSevenCardsToEachPlayer() {
//        Deck mockDeck = EasyMock.createMock(Deck.class);
//        Player mockP1 = EasyMock.createMock(Player.class);
//        Player mockP2 = EasyMock.createMock(Player.class);
//        Player mockP3 = EasyMock.createMock(Player.class);
//        Player mockP4 = EasyMock.createMock(Player.class);
//        Player mockP5 = EasyMock.createMock(Player.class);
//        Card mockDefuse = EasyMock.createMock(Card.class);
//        Card mockCard = EasyMock.createMock(Card.class);
//
//        EasyMock.expect(mockDeck.count()).andStubReturn(100);
//        EasyMock.expect(mockDeck.drawDefuse()).andReturn(mockDefuse).times(5);
//        EasyMock.expect(mockDeck.draw()).andReturn(mockCard).times(35);
//
//        mockP1.addCard(mockDefuse);
//        EasyMock.expectLastCall().once();
//        mockP1.addCard(mockCard);
//        EasyMock.expectLastCall().times(7);
//        mockP2.addCard(mockDefuse);
//        EasyMock.expectLastCall().once();
//        mockP2.addCard(mockCard);
//        EasyMock.expectLastCall().times(7);
//        mockP3.addCard(mockDefuse);
//        EasyMock.expectLastCall().once();
//        mockP3.addCard(mockCard);
//        EasyMock.expectLastCall().times(7);
//        mockP4.addCard(mockDefuse);
//        EasyMock.expectLastCall().once();
//        mockP4.addCard(mockCard);
//        EasyMock.expectLastCall().times(7);
//        mockP5.addCard(mockDefuse);
//        EasyMock.expectLastCall().once();
//        mockP5.addCard(mockCard);
//        EasyMock.expectLastCall().times(7);
//
//        mockDeck.insert(EasyMock.anyObject(Card.class));
//        EasyMock.expectLastCall().anyTimes();
//        mockDeck.shuffle();
//        EasyMock.expectLastCall().anyTimes();
//
//        EasyMock.replay(mockDeck, mockP1, mockP2, mockP3, mockP4, mockP5, mockDefuse, mockCard);
//
//        new Game(mockDeck, List.of(mockP1, mockP2, mockP3, mockP4, mockP5)).setup();
//
//        EasyMock.verify(mockDeck, mockP1, mockP2, mockP3, mockP4, mockP5);
//    }
//
//    @Test
//    public void setup_FivePlayersButNotEnoughCards_IllegalStateException() {
//        Deck mockDeck = EasyMock.createMock(Deck.class);
//        Player mockP1 = EasyMock.createMock(Player.class);
//        Player mockP2 = EasyMock.createMock(Player.class);
//        Player mockP3 = EasyMock.createMock(Player.class);
//        Player mockP4 = EasyMock.createMock(Player.class);
//        Player mockP5 = EasyMock.createMock(Player.class);
//
//        EasyMock.expect(mockDeck.count()).andStubReturn(34);
//
//        EasyMock.replay(mockDeck);
//
//        Exception exception = assertThrows(IllegalStateException.class, () -> {
//            new Game(mockDeck, List.of(mockP1, mockP2, mockP3, mockP4, mockP5)).setup();
//        });
//    }
//
//    @Test
//    public void runGame_TwoPlayers_FirstPlayerTakesTurn() {
//        Deck mockDeck = EasyMock.createMock(Deck.class);
//        Player mockP1 = EasyMock.createMock(Player.class);
//        Player mockP2 = EasyMock.createMock(Player.class);
//        Card mockDefuse = EasyMock.createMock(Card.class);
//        Card mockCard = EasyMock.createMock(Card.class);
//        Game.Shuffler mockShuffler = EasyMock.createMock(Game.Shuffler.class);
//
//        EasyMock.expect(mockDeck.count()).andStubReturn(100);
//        EasyMock.expect(mockDeck.drawDefuse()).andReturn(mockDefuse).times(2);
//        EasyMock.expect(mockDeck.draw()).andReturn(mockCard).times(14);
//
//        mockP1.addCard(mockDefuse);
//        EasyMock.expectLastCall().once();
//        mockP1.addCard(mockCard);
//        EasyMock.expectLastCall().times(7);
//        mockP2.addCard(mockDefuse);
//        EasyMock.expectLastCall().once();
//        mockP2.addCard(mockCard);
//        EasyMock.expectLastCall().times(7);
//
//        mockDeck.insert(EasyMock.anyObject(Card.class));
//        EasyMock.expectLastCall().anyTimes();
//        mockDeck.shuffle();
//        EasyMock.expectLastCall().anyTimes();
//
//        mockShuffler.shuffle(List.of(mockP1, mockP2));
//        EasyMock.expectLastCall();
//        EasyMock.expect(mockP1.isAlive()).andReturn(true);
//        mockP1.takeTurn();
//        EasyMock.expectLastCall();
//        EasyMock.expect(mockP2.isAlive()).andReturn(true);
//        mockP2.takeTurn();
//        EasyMock.expectLastCall();
//
//        EasyMock.replay(mockDeck, mockP1, mockP2, mockDefuse, mockCard, mockShuffler);
//
//        Game game = new Game(mockDeck, List.of(mockP1, mockP2));
//        game.setup();
//        game.runGame(mockShuffler);
//
//        EasyMock.verify(mockDeck, mockP1, mockP2, mockShuffler);
//
//
//    }
}
