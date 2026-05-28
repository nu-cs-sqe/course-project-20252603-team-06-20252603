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
    public void constructorTest_OneBelowMinNumPlayer_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Game(1);
        });
    }

    @Test
    public void constructorTest_MinNumPlayers_MakesGame(){
        Game game = new Game(2);
        assertEquals(2, game.getTotalPlayerCount());
        assertEquals(2, game.getAlivePlayerCount());
    }

    @Test
    public void constructorTest_MaxNumPlayers_MakesGame(){
        Game game = new Game(5);
        assertEquals(5, game.getTotalPlayerCount());
        assertEquals(5, game.getTotalPlayerCount());
    }

    @Test
    public void constructorTest_OneAboveMaxNumPlayers_MakesGame(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Game(6);
        });
    }

    @Test
    public void setupTest_MinimumPlayers() {
        Game game = new Game(2);
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
    public void setupTest_MaximumPlayers(){
        Game game = new Game(5);
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
}
