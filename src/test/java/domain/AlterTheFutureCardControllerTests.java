package domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AlterTheFutureCardControllerTests {

    @Test
    public void getTopCards_EmptyDeck_ReturnsEmptyList() {
        AlterTheFutureCardController controller = new AlterTheFutureCardController(cards -> cards);
        Deck deck = new Deck(0);

        List<Card> result = controller.getTopCards(deck);

        assertTrue(result.isEmpty());
    }
}
