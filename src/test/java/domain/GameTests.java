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
    public void ConstructorTest_OneBelowMinNumPlayer_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Game(1);
        });
    }

    @Test
    public void ConstructorTest_MinNumPlayers_MakesGame(){
        Game game = new Game(2);
        assertEquals(2, game.getTotalPlayerCount());
        assertEquals(2, game.getAlivePlayerCount());
    }

    @Test
    public void ConstructorTest_MaxNumPlayers_MakesGame(){
        Game game = new Game(5);
        assertEquals(5, game.getTotalPlayerCount());
        assertEquals(5, game.getTotalPlayerCount());
    }

    @Test
    public void ConstructorTest_OneAboveMaxNumPlayers_MakesGame(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Game(6);
        });
    }

    @Test
    public void SetupTest_MinimumPlayers_CorrectCardsDealt() {
        Game game = new Game(2);
        game.setup();

        // each player has 8 cards
        for (Player player : game.getTotalPlayers()) {
            assertEquals(8, player.getHandSize());
        }

        // deck contains exactly 1 exploding kitten
        int kittenCount = 0;
        for (Card card : game.getDeck().getCards()) {
            if (card.getType() == CardType.EXPLODING_KITTEN) {
                kittenCount++;
            }
        }
        assertEquals(1, kittenCount);
    }
}
