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
    void constructor_InvalidPlayerCount_ThrowsException(int invalidCount) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Game(invalidCount);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5})
    void constructor_ValidPlayerCount_MakesGame(int validCount) {
        Game game = new Game(validCount);
        assertEquals(validCount, game.getTotalPlayerCount());
        assertEquals(validCount, game.getAlivePlayerCount());
    }

    @ParameterizedTest
    @CsvSource({
            "2, 1",
            "5, 4"
    })
    void setup_ValidNumPlayers_DealsCorrectCardsAndKittens(
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

    @ParameterizedTest
    @ValueSource(ints = {1, 20})
    void draw_FromValidDeck_UpdatesHandAndDeck(int deckSize) {
        Game game = new Game(5);
        Deck deck = new Deck(deckSize);
        Card topCard = deck.getCards().get(0);
        Player player = new Player("Test Name");

        game.draw(player, deck);

        assertTrue(player.hasCard(topCard.getType()));
        assertEquals(deckSize - 1, deck.count());
    }

    @Test
    void draw_FromEmptyDeck(){
        Game game = new Game(5);
        Deck deck = new Deck(0);
        Player player = new Player("Test Name");

        assertThrows(IllegalArgumentException.class, () -> {
            game.draw(player, deck);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5})
    void getTotalPlayerCount_ValidBounds_ReturnsCorrectCount(int playerCount) {
        Game game = new Game(playerCount);
        assertEquals(playerCount, game.getTotalPlayerCount());
    }

    @ParameterizedTest
    @CsvSource({
            "2, 0, 2",
            "2, 1, 1",
            "5, 4, 1"
    })
    void getAlivePlayerCount_AfterEliminations_ReturnsCount(
            int initialPlayers, int eliminations, int expectedRemaining) {
        Game game = new Game(initialPlayers);

        for (int i = 0; i < eliminations; i++) {
            game.removeAlivePlayer(game.getAlivePlayers().get(0));
        }

        assertEquals(expectedRemaining, game.getAlivePlayerCount());
    }

    @Test
    void getTotalPlayers_ThreePlayers_ReturnsAllThreePlayers() {
        Game game = new Game(2);
        List<Player> totalPlayers = game.getTotalPlayers();
        assertEquals(2, totalPlayers.size());
    }

    @Test
    void getAlivePlayers_NoPlayersEliminated_ReturnsAllPlayers() {
        Game game = new Game(5);
        List<Player> alivePlayers = game.getAlivePlayers();
        assertEquals(5, alivePlayers.size());
    }

    @Test
    void getAlivePlayers_OnePlayerEliminated_ReturnsFourPlayers() {
        Game game = new Game(5);
        Player playerToRemove = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(playerToRemove);
        List<Player> alivePlayers = game.getAlivePlayers();
        assertEquals(4, alivePlayers.size());
    }

    @Test
    void removeAlivePlayer_ManyPlayers_RemovesPlayer() {
        Game game = new Game(5);
        Player playerToRemove = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(playerToRemove);
        assertEquals(4, game.getAlivePlayerCount());
        assertFalse(game.getAlivePlayers().contains(playerToRemove));
    }

    @Test
    void removeAlivePlayer_OnePlayer_EmptiesList() {
        Game game = new Game(2);
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        assertEquals(0, game.getAlivePlayerCount());
    }

    @Test
    void removeAlivePlayer_EmptyList_ThrowsException() {
        Game game = new Game(2);
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        Player extraPlayer = new Player("Extra");
        assertThrows(IllegalArgumentException.class, () -> {
            game.removeAlivePlayer(extraPlayer);
        });
    }

    @Test
    void removeAlivePlayer_PlayerNotInAliveList_ThrowsException() {
        Game game = new Game(2);
        Player playerToRemove = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(playerToRemove);
        assertThrows(IllegalArgumentException.class, () -> {
            game.removeAlivePlayer(playerToRemove);
        });
    }

    @Test
    void addAlivePlayer_NoAlivePlayers_AddsPlayer() {
        Game game = new Game(2);
        Player playerToAdd = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        game.removeAlivePlayer(game.getAlivePlayers().get(0));
        game.addAlivePlayer(playerToAdd);
        assertEquals(1, game.getAlivePlayerCount());
    }

    @Test
    void addAlivePlayer_SomeAlivePlayers_AddsPlayer() {
        Game game = new Game(5);
        Player playerToAdd = game.getAlivePlayers().get(0);
        game.removeAlivePlayer(playerToAdd);
        game.addAlivePlayer(playerToAdd);
        assertEquals(5, game.getAlivePlayerCount());
    }

    @Test
    void addAlivePlayer_PlayerNotInGame_ThrowsException() {
        Game game = new Game(2);
        Player extraPlayer = new Player("Extra");
        assertThrows(IllegalArgumentException.class, () -> {
            game.addAlivePlayer(extraPlayer);
        });
    }

    @Test
    void addAlivePlayer_PlayerAlreadyAlive_ThrowsException() {
        Game game = new Game(2);
        Player alivePlayer = game.getAlivePlayers().get(0);
        assertThrows(IllegalArgumentException.class, () -> {
            game.addAlivePlayer(alivePlayer);
        });
    }
}
