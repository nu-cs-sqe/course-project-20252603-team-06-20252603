package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {
    @Test
    public void createGame_OneBelowMinNumPlayer_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Game.createGame(1);
        });
    }

    @Test
    public void createGame_MinNumPlayers_MakesGame(){
        Game game = Game.createGame(2);
        assertEquals(2, game.getTotalPlayerCount());
        assertEquals(2, game.getAlivePlayerCount());
    }

    @Test
    public void createGame_MaxNumPlayers_MakesGame(){
        Game game = Game.createGame(5);
        assertEquals(5, game.getTotalPlayerCount());
        assertEquals(5, game.getAlivePlayerCount());
    }

    @Test
    public void createGame_OneAboveMaxNumPlayers_MakesGame(){
        assertThrows(IllegalArgumentException.class, () -> {
            Game.createGame(6);
        });
    }

    @Test
    public void setup_MinimumNumPlayers() {
        Game game = Game.createGame(2);
        game.setup();

        for (Player player : game.getTotalPlayers()) {
            assertEquals(7, player.getHandSize());
        }

        int kittenCount = 0;
        for (Card card : game.getDeck().getCards()) {
            if (card.getType() == CardType.EXPLODING_KITTEN) {
                kittenCount++;
            }
        }
        assertEquals(1, kittenCount);
    }

    @Test
    public void setup_MaximumNumPlayers(){
        Game game = Game.createGame(5);
        game.setup();

        for (Player player : game.getTotalPlayers()) {
            assertEquals(7, player.getHandSize());
        }

        int kittenCount = 0;
        for (Card card : game.getDeck().getCards()) {
            if (card.getType() == CardType.EXPLODING_KITTEN) {
                kittenCount++;
            }
        }
        assertEquals(4, kittenCount);
    }

    @Test
    public void draw_FromDeckWithOneCard(){
        Game game = Game.createGame(5);

        Deck deck = new Deck(1);
        Card deckOnlyCard = deck.getCards().get(0);
        int deckOriginalSize = deck.count();

        Player player = Player.createPlayer("Test Name");
        assertEquals(0, player.getHandSize());

        game.draw(player, deck);

        assertTrue(player.hasCard(deckOnlyCard.getType()));
        assertEquals(deckOriginalSize - 1, deck.count());
    }

    @Test
    public void draw_FromEmptyDeck(){
        Game game = Game.createGame(5);
        Deck deck = new Deck(0);
        Player player = Player.createPlayer("Test Name");

        assertThrows(IllegalArgumentException.class, () -> {
            game.draw(player, deck);
        });
    }

    @Test
    public void draw_FromDeckWithManyCards(){
        Game game = Game.createGame(5);

        Deck deck = new Deck();
        Card deckTopCard = deck.getCards().get(0);
        int deckOriginalSize = deck.count();

        Player player = Player.createPlayer("Test Name");
        assertEquals(0, player.getHandSize());

        game.draw(player, deck);

        assertTrue(player.hasCard(deckTopCard.getType()));
        assertEquals(deckOriginalSize - 1, deck.count());
    }

    @Test
    public void getTotalPlayerCount_MinNumPlayers_ReturnsTwo() {
        Game game = Game.createGame(2);
        assertEquals(2, game.getTotalPlayerCount());
    }

    @Test
    public void getTotalPlayerCount_MaxNumPlayers_ReturnsFive() {
        Game game = Game.createGame(5);
        assertEquals(5, game.getTotalPlayerCount());
    }

    @Test
    public void getAlivePlayerCount_NoPlayersEliminated_ReturnsTwo() {
        Game game = Game.createGame(2);
        assertEquals(2, game.getAlivePlayerCount());
    }

    @Test
    public void getAlivePlayerCount_OnePlayerEliminated_ReturnsOne() {
        Game game = Game.createGame(2);
        Player playerToRemove = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(playerToRemove);
        assertEquals(1, game.getAlivePlayerCount());
    }

    @Test
    public void getAlivePlayerCount_LastPlayerRemaining_ReturnsOne() {
        Game game = Game.createGame(5);
        for (int i = 0; i < 4; i++) {
            Player playerToRemove = game.getAlivePlayers().get(0);
            game.removeAlivePlayer(playerToRemove);
        }
        assertEquals(1, game.getAlivePlayerCount());
    }

    @Test
    public void getTotalPlayers_ThreePlayers_ReturnsAllThreePlayers() {
        Game game = Game.createGame(2);
        List<Player> totalPlayers = game.getTotalPlayers();
        assertEquals(2, totalPlayers.size());
    }

    @Test
    public void getAlivePlayers_NoPlayersEliminated_ReturnsAllPlayers() {
        Game game = Game.createGame(5);
        List<Player> alivePlayers = game.getAlivePlayers();
        assertEquals(5, alivePlayers.size());
    }

    @Test
    public void getAlivePlayers_OnePlayerEliminated_ReturnsFourPlayers() {
        Game game = Game.createGame(5);
        Player playerToRemove = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(playerToRemove);
        List<Player> alivePlayers = game.getAlivePlayers();
        assertEquals(4, alivePlayers.size());
    }

    @Test
    public void removeAlivePlayer_ManyPlayers_RemovesPlayer() {
        Game game = Game.createGame(5);
        Player playerToRemove = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(playerToRemove);
        assertEquals(4, game.getAlivePlayerCount());
        assertFalse(game.getAlivePlayers().contains(playerToRemove));
    }

    @Test
    public void removeAlivePlayer_OnePlayer_EmptiesList() {
        Game game = Game.createGame(2);
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        assertEquals(0, game.getAlivePlayerCount());
    }

    @Test
    public void removeAlivePlayer_EmptyList_ThrowsException() {
        Game game = Game.createGame(2);
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        Player extraPlayer = Player.createPlayer("Extra");
        assertThrows(IllegalArgumentException.class, () -> {
            game.removeAlivePlayer(extraPlayer);
        });
    }

    @Test
    public void removeAlivePlayer_PlayerNotInAliveList_ThrowsException() {
        Game game = Game.createGame(2);
        Player playerToRemove = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(playerToRemove);
        assertThrows(IllegalArgumentException.class, () -> {
            game.removeAlivePlayer(playerToRemove);
        });
    }

    @Test
    public void addAlivePlayer_NoAlivePlayers_AddsPlayer() {
        Game game = Game.createGame(2);
        Player playerToAdd = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        game.addAlivePlayer(playerToAdd);
        assertEquals(1, game.getAlivePlayerCount());
    }

    @Test
    public void addAlivePlayer_SomeAlivePlayers_AddsPlayer() {
        Game game = Game.createGame(5);
        Player playerToAdd = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(playerToAdd);
        game.addAlivePlayer(playerToAdd);
        assertEquals(5, game.getAlivePlayerCount());
    }

    @Test
    public void addAlivePlayer_PlayerNotInGame_ThrowsException() {
        Game game = Game.createGame(2);
        Player extraPlayer = Player.createPlayer("Extra");
        assertThrows(IllegalArgumentException.class, () -> {
            game.addAlivePlayer(extraPlayer);
        });
    }

    @Test
    public void addAlivePlayer_PlayerAlreadyAlive_ThrowsException() {
        Game game = Game.createGame(2);
        Player alivePlayer = game.getAlivePlayers().get(0);
        assertThrows(IllegalArgumentException.class, () -> {
            game.addAlivePlayer(alivePlayer);
        });
    }
}
