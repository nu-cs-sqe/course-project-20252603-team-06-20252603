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
    @ParameterizedTest
    @ValueSource(ints = {1, 6})
    public void constructor_InvalidPlayerCount_ThrowsException(int invalidCount) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Game(invalidCount);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5})
    public void constructor_ValidPlayerCount_MakesGame(int validCount) {
        Game game = new Game(validCount);
        assertEquals(validCount, game.getTotalPlayerCount());
        assertEquals(validCount, game.getAlivePlayerCount());
    }

    @ParameterizedTest
    @CsvSource({
            "2, 1",
            "5, 4"
    })
    public void setup_ValidNumPlayers_DealsCorrectCardsAndKittens(
            int playerCount, int expectedKittens) {
        Game game = new Game(playerCount);
        game.setup();

        for (Player player : game.getTotalPlayers()) {
            assertEquals(7, player.getHandSize());
        }

        long actualKittens = game.getDeck().getCards().stream()
                .filter(card -> card.getType() == CardType.EXPLODING_KITTEN)
                .count();

        assertEquals(expectedKittens, actualKittens);
    }

    @Test
    public void draw_FromDeckWithOneCard(){
        Game game = new Game(5);

        Deck deck = new Deck(1);
        Card deckOnlyCard = deck.getCards().get(0);
        int deckOriginalSize = deck.count();

        Player player = new Player("Test Name");
        assertEquals(0, player.getHandSize());

        game.draw(player, deck);

        assertTrue(player.hasCard(deckOnlyCard.getType()));
        assertEquals(deckOriginalSize - 1, deck.count());
    }

    @Test
    public void draw_FromEmptyDeck(){
        Game game = new Game(5);
        Deck deck = new Deck(0);
        Player player = new Player("Test Name");

        assertThrows(IllegalArgumentException.class, () -> {
            game.draw(player, deck);
        });
    }

    @Test
    public void draw_FromDeckWithManyCards(){
        Game game = new Game(5);

        Deck deck = new Deck();
        Card deckTopCard = deck.getCards().get(0);
        int deckOriginalSize = deck.count();

        Player player = new Player("Test Name");
        assertEquals(0, player.getHandSize());

        game.draw(player, deck);

        assertTrue(player.hasCard(deckTopCard.getType()));
        assertEquals(deckOriginalSize - 1, deck.count());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5})
    public void getTotalPlayerCount_ValidBounds_ReturnsCorrectCount(int playerCount) {
        Game game = new Game(playerCount);
        assertEquals(playerCount, game.getTotalPlayerCount());
    }

    @ParameterizedTest
    @CsvSource({
            "2, 0, 2",
            "2, 1, 1",
            "5, 4, 1"
    })
    public void getAlivePlayerCount_AfterEliminations_ReturnsCount(
            int initialPlayers, int eliminations, int expectedRemaining) {
        Game game = new Game(initialPlayers);

        for (int i = 0; i < eliminations; i++) {
            game.removeAlivePlayer(game.getAlivePlayers().get(0));
        }

        assertEquals(expectedRemaining, game.getAlivePlayerCount());
    }

    @Test
    public void getTotalPlayers_ThreePlayers_ReturnsAllThreePlayers() {
        Game game = new Game(2);
        List<Player> totalPlayers = game.getTotalPlayers();
        assertEquals(2, totalPlayers.size());
    }

    @Test
    public void getAlivePlayers_NoPlayersEliminated_ReturnsAllPlayers() {
        Game game = new Game(5);
        List<Player> alivePlayers = game.getAlivePlayers();
        assertEquals(5, alivePlayers.size());
    }

    @Test
    public void getAlivePlayers_OnePlayerEliminated_ReturnsFourPlayers() {
        Game game = new Game(5);
        Player playerToRemove = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(playerToRemove);
        List<Player> alivePlayers = game.getAlivePlayers();
        assertEquals(4, alivePlayers.size());
    }

    @Test
    public void removeAlivePlayer_ManyPlayers_RemovesPlayer() {
        Game game = new Game(5);
        Player playerToRemove = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(playerToRemove);
        assertEquals(4, game.getAlivePlayerCount());
        assertFalse(game.getAlivePlayers().contains(playerToRemove));
    }

    @Test
    public void removeAlivePlayer_OnePlayer_EmptiesList() {
        Game game = new Game(2);
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        assertEquals(0, game.getAlivePlayerCount());
    }

    @Test
    public void removeAlivePlayer_EmptyList_ThrowsException() {
        Game game = new Game(2);
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        Player extraPlayer = new Player("Extra");
        assertThrows(IllegalArgumentException.class, () -> {
            game.removeAlivePlayer(extraPlayer);
        });
    }

    @Test
    public void removeAlivePlayer_PlayerNotInAliveList_ThrowsException() {
        Game game = new Game(2);
        Player playerToRemove = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(playerToRemove);
        assertThrows(IllegalArgumentException.class, () -> {
            game.removeAlivePlayer(playerToRemove);
        });
    }

    @Test
    public void addAlivePlayer_NoAlivePlayers_AddsPlayer() {
        Game game = new Game(2);
        Player playerToAdd = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        game.addAlivePlayer(playerToAdd);
        assertEquals(1, game.getAlivePlayerCount());
    }

    @Test
    public void addAlivePlayer_SomeAlivePlayers_AddsPlayer() {
        Game game = new Game(5);
        Player playerToAdd = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(playerToAdd);
        game.addAlivePlayer(playerToAdd);
        assertEquals(5, game.getAlivePlayerCount());
    }

    @Test
    public void addAlivePlayer_PlayerNotInGame_ThrowsException() {
        Game game = new Game(2);
        Player extraPlayer = new Player("Extra");
        assertThrows(IllegalArgumentException.class, () -> {
            game.addAlivePlayer(extraPlayer);
        });
    }

    @Test
    public void addAlivePlayer_PlayerAlreadyAlive_ThrowsException() {
        Game game = new Game(2);
        Player alivePlayer = game.getAlivePlayers().get(0);
        assertThrows(IllegalArgumentException.class, () -> {
            game.addAlivePlayer(alivePlayer);
        });
    }
}
