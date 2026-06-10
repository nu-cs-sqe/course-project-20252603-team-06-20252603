package domain;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BountyCardControllerTests {

    @Test
    public void executeCardAction_OneOtherPlayerZeroCards_ReturnsEmpty() {
        Game game = EasyMock.createMock(Game.class);
        Player user = EasyMock.createMock(Player.class);
        Player other = EasyMock.createMock(Player.class);

        EasyMock.expect(game.getAlivePlayers()).andReturn(List.of(user, other));
        EasyMock.expect(other.getHandSize()).andReturn(0);

        EasyMock.replay(game, user, other);

        BountyCardController controller = new BountyCardController();
        Optional<List<Card>> result = controller.executeCardAction(game, user, Optional.empty());

        assertTrue(result.isEmpty());
        EasyMock.verify(game, user, other);
    }

    @Test
    public void executeCardAction_OneOtherPlayerOneCard_StealsCard() {
    Game game = EasyMock.createMock(Game.class);
    Player user = EasyMock.createMock(Player.class);
    Player other = EasyMock.createMock(Player.class);
    Card card = new Card(CardType.CAT_CARD_1);

    EasyMock.expect(game.getAlivePlayers()).andReturn(List.of(user, other));
    EasyMock.expect(other.getHandSize()).andReturn(1);
    EasyMock.expect(other.getHand()).andReturn(new ArrayList<>(List.of(card)));
    other.removeCard(card);
    user.addCard(card);

    EasyMock.replay(game, user, other);

    BountyCardController controller = new BountyCardController();
    Optional<List<Card>> result = controller.executeCardAction(game, user, Optional.empty());

    assertTrue(result.isEmpty());
    EasyMock.verify(game, user, other);
    }

    @Test
    public void executeCardAction_OneOtherPlayerManyCards_StealsOneCard() {
    Game game = EasyMock.createMock(Game.class);
    Player user = EasyMock.createMock(Player.class);
    Player other = EasyMock.createMock(Player.class);

    ArrayList<Card> hand = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
        hand.add(new Card(CardType.CAT_CARD_1));
    }

    EasyMock.expect(game.getAlivePlayers()).andReturn(List.of(user, other));
    EasyMock.expect(other.getHandSize()).andReturn(4);
    EasyMock.expect(other.getHand()).andReturn(hand);
    other.removeCard(EasyMock.isA(Card.class));
    user.addCard(EasyMock.isA(Card.class));

    EasyMock.replay(game, user, other);

    BountyCardController controller = new BountyCardController();
    Optional<List<Card>> result = controller.executeCardAction(game, user, Optional.empty());

    assertTrue(result.isEmpty());
    EasyMock.verify(game, user, other);
}

@Test
public void executeCardAction_MultipleOtherPlayersAllZeroCards_ReturnsEmpty() {
    Game game = EasyMock.createMock(Game.class);
    Player user = EasyMock.createMock(Player.class);
    Player other1 = EasyMock.createMock(Player.class);
    Player other2 = EasyMock.createMock(Player.class);

    EasyMock.expect(game.getAlivePlayers()).andReturn(List.of(user, other1, other2));
    EasyMock.expect(other1.getHandSize()).andReturn(0);
    EasyMock.expect(other2.getHandSize()).andReturn(0);

    EasyMock.replay(game, user, other1, other2);

    BountyCardController controller = new BountyCardController();
    Optional<List<Card>> result = controller.executeCardAction(game, user, Optional.empty());

    assertTrue(result.isEmpty());
    EasyMock.verify(game, user, other1, other2);
}
}