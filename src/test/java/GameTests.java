import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {
    @Test
    public void constructor_ZeroPlayers_IllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Game(0);
        });
    }

    @Test
    public void constructor_OnePlayer_IllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Game(1);
        });
    }

    @Test
    public void constructor_SixPlayers_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Game(6);
        });
    }

    @Test
    public void constructor_TwoPlayers_CreatesTwoPlayersWithNullCurrentPlayer() {
        Game game = new Game(2);
        assertEquals(2, game.getPlayerCount());
        assertNull(game.getCurrentPlayer());
    }

    @Test
    public void constructor_ThreePlayers_CreatesThreePlayers() {
        assertEquals(3, new Game(3).getPlayerCount());
    }

    @Test
    public void constructor_FourPlayers_CreatesFourPlayers() {
        assertEquals(4, new Game(4).getPlayerCount());
    }

    @Test
    public void constructor_FivePlayers_CreatesFivePlayers() {
        assertEquals(5, new Game(5).getPlayerCount());
    }

    @Test
    public void setup_TwoPlayers_DealsOneDefuseAndSevenCardsToEachPlayer() {
        Deck mockDeck = EasyMock.createMock(Deck.class);
        Player mockP1 = EasyMock.createMock(Player.class);
        Player mockP2 = EasyMock.createMock(Player.class);
        Card mockDefuse = EasyMock.createMock(Card.class);
        Card mockCard = EasyMock.createMock(Card.class);

        EasyMock.expect(mockDeck.count()).andStubReturn(100);
        EasyMock.expect(mockDeck.drawDefuse()).andReturn(mockDefuse).times(2);
        EasyMock.expect(mockDeck.draw()).andReturn(mockCard).times(14);

        mockP1.addCard(mockDefuse);
        EasyMock.expectLastCall().once();
        mockP1.addCard(mockCard);
        EasyMock.expectLastCall().times(7);
        mockP2.addCard(mockDefuse);
        EasyMock.expectLastCall().once();
        mockP2.addCard(mockCard);
        EasyMock.expectLastCall().times(7);

        mockDeck.insert(EasyMock.anyObject(Card.class));
        EasyMock.expectLastCall().anyTimes();
        mockDeck.shuffle();
        EasyMock.expectLastCall().anyTimes();

        EasyMock.replay(mockDeck, mockP1, mockP2, mockDefuse, mockCard);

        new Game(mockDeck, List.of(mockP1, mockP2)).setup();

        EasyMock.verify(mockDeck, mockP1, mockP2);
    }
}
