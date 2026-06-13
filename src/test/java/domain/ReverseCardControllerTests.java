package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class ReverseCardControllerTests {

    @Test
    void executeCardAction_TwoPlayers_ReversesOrderAndUpdatesIndices() {
        GameController mockGc = EasyMock.createMock(GameController.class);
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockUser = EasyMock.createMock(Player.class);
        Player p1 = EasyMock.createMock(Player.class);
        Player p2 = EasyMock.createMock(Player.class);

        List<Player> originalList = new ArrayList<>(List.of(p1, p2));
        List<Player> expectedReversedList = new ArrayList<>(List.of(p2, p1));

        EasyMock.expect(mockGc.getGame()).andReturn(mockGame).anyTimes();
        EasyMock.expect(mockGame.getAlivePlayers()).andReturn(originalList);

        EasyMock.expect(mockGc.getCurrentPlayerIndex()).andReturn(0);

        mockGc.setPlayerOrder(expectedReversedList);
        EasyMock.expectLastCall().once();

        mockGc.setCurrentPlayerIndex(1);
        EasyMock.expectLastCall().once();

        mockGc.setNextPlayerIndex(0);
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockGc, mockGame, mockUser, p1, p2);

        ReverseCardController controller = new ReverseCardController();
        controller.executeCardAction(mockGc, mockUser, Optional.empty());

        EasyMock.verify(mockGc, mockGame, mockUser, p1, p2);
    }

    @Test
    void executeCardAction_ThreePlayers_ReversesOrderAndUpdatesIndices() {
        GameController mockGc = EasyMock.createMock(GameController.class);
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockUser = EasyMock.createMock(Player.class);
        Player p1 = EasyMock.createMock(Player.class);
        Player p2 = EasyMock.createMock(Player.class);
        Player p3 = EasyMock.createMock(Player.class);

        List<Player> originalList = new ArrayList<>(List.of(p1, p2, p3));
        List<Player> expectedReversedList = new ArrayList<>(List.of(p3, p2, p1));

        EasyMock.expect(mockGc.getGame()).andReturn(mockGame).anyTimes();
        EasyMock.expect(mockGame.getAlivePlayers()).andReturn(originalList);

        EasyMock.expect(mockGc.getCurrentPlayerIndex()).andReturn(1);

        mockGc.setPlayerOrder(expectedReversedList);
        EasyMock.expectLastCall().once();

        mockGc.setCurrentPlayerIndex(1);
        EasyMock.expectLastCall().once();

        mockGc.setNextPlayerIndex(2);
        EasyMock.expectLastCall().once();

        EasyMock.replay(mockGc, mockGame, mockUser, p1, p2, p3);

        ReverseCardController controller = new ReverseCardController();
        controller.executeCardAction(mockGc, mockUser, Optional.empty());

        EasyMock.verify(mockGc, mockGame, mockUser, p1, p2, p3);
    }

    @Test
    void executeCardAction_FivePlayers_ReversesOrderAndUpdatesIndices() {
        Game game = Game.createGame(5);
        GameController gc = new GameController(game);
        gc.setCurrentPlayerIndex(2);
        gc.setNextPlayerIndex(3);

        Player p1 = game.getAlivePlayers().get(0);
        Player p2 = game.getAlivePlayers().get(1);
        Player p3 = game.getAlivePlayers().get(2);
        Player p4 = game.getAlivePlayers().get(3);
        Player p5 = game.getAlivePlayers().get(4);

        ReverseCardController controller = new ReverseCardController();
        controller.executeCardAction(gc, p3, Optional.empty());

        assertEquals(p5, game.getAlivePlayers().get(0));
        assertEquals(p4, game.getAlivePlayers().get(1));
        assertEquals(p3, game.getAlivePlayers().get(2));
        assertEquals(p2, game.getAlivePlayers().get(3));
        assertEquals(p1, game.getAlivePlayers().get(4));

        assertEquals(2, gc.getCurrentPlayerIndex());
        assertEquals(3, gc.getNextPlayerIndex());
    }
}