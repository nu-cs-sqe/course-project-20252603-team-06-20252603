package Code;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    public void constructor_ValidPlayerCounts_CreatesCorrectNumberOfPlayers(int playerCount) {
        Game game = new Game(playerCount);

        assertEquals(playerCount, game.getPlayerCount());

        assertNull(game.getCurrentPlayer());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 6, 7})
    public void createGame_InvalidPlayerCounts_ThrowsIllegalArgumentException(int playerCount) {
        assertThrows(IllegalArgumentException.class, () -> {
            Game.createGame(playerCount);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    public void createGame_ValidPlayerCounts_CreatesCorrectNumberOfPlayers(int playerCount) {
        Game game = Game.createGame(playerCount);

        assertEquals(playerCount, game.getPlayerCount());

        assertNull(game.getCurrentPlayer());
    }

    @ParameterizedTest
    @CsvSource({
            "2,100",
            "3,100",
            "4,100",
            "5,100",
            "2,14",
            "3,21",
            "4,28",
            "5,35",
    })
    public void setup_ValidPlayerAndCardCounts_DealsCards(int playerCount, int cardCount) {
        Deck mockDeck = EasyMock.createMock(Deck.class);
        List<Player> realPlayers = new ArrayList<>();

        for (int i = 0; i < playerCount; i++) {
            realPlayers.add(new Player("Player " + (i + 1)));
        }

        EasyMock.expect(mockDeck.count()).andStubReturn(cardCount);

        mockDeck.insert(EasyMock.anyObject(Card.class), EasyMock.eq(0));
        EasyMock.expectLastCall().times(playerCount - 1);

        mockDeck.shuffle();
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockDeck);

        new Game(mockDeck, realPlayers).setup();

        EasyMock.verify(mockDeck);

        for (Player p : realPlayers) {
            assertEquals(8, p.getHandSize(), "Player should have exactly 8 cards");
            assertTrue(p.hasDefuse(), "Player should have received a Defuse card");
        }
    }

    @Test
    public void setup_FivePlayersButNotEnoughCards_IllegalStateException() {
        Deck mockDeck = EasyMock.createMock(Deck.class);
        List<Player> realPlayers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            realPlayers.add(new Player("Player " + i));
        }

        EasyMock.expect(mockDeck.count()).andStubReturn(34);

        EasyMock.replay(mockDeck);

        assertThrows(IllegalStateException.class, () -> {
            new Game(mockDeck, realPlayers).setup();
        });
    }

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
