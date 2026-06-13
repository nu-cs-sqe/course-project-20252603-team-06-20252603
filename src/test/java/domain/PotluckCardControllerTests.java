package domain;

import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;
import ui.PotluckCardControllerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PotluckCardControllerTests {
    @Test
    public void executeCardAction_InvalidInputAndTwoPlayers_InvalidOutputThenCardsRemoved() {
        String userChoice = "DRAW_FROM_BOTTOM";
        String userChoice2 = "10";
        String userChoice3 = "1";
        String userChoice4 = "0";

        GameController mockGameController = EasyMock.createMock(GameController.class);
        Game mockGame = EasyMock.createMock(Game.class);
        Player mockCurrentPlayer = EasyMock.createMock(Player.class);
        Player mockPlayer2 = EasyMock.createMock(Player.class);
        Deck mockDeck = EasyMock.createMock(Deck.class);
        Card mockSkipCard = EasyMock.createMock(Card.class);
        Card mockDrawFromBottomCard = EasyMock.createMock(Card.class);
        Card mockCatCard = EasyMock.createMock(Card.class);
        PotluckCardControllerView mockInput = EasyMock.createMock(PotluckCardControllerView.class);

        ArrayList<Player> alivePlayers = new ArrayList<Player>();
        alivePlayers.add(mockCurrentPlayer);
        alivePlayers.add(mockPlayer2);

        ArrayList<Card> currentPlayerHand = new ArrayList<Card>();
        currentPlayerHand.add(mockSkipCard);
        currentPlayerHand.add(mockDrawFromBottomCard);

        ArrayList<Card> player2Hand = new ArrayList<Card>();
        player2Hand.add(mockCatCard);

        EasyMock.expect(mockGameController.getGame()).andReturn(mockGame);
        EasyMock.expect(mockGame.getDeck()).andReturn(mockDeck);
        EasyMock.expect(mockGame.getAlivePlayers()).andReturn(alivePlayers);
        EasyMock.expect(mockGame.getAlivePlayerCount()).andReturn(2);
        EasyMock.expect(mockGameController.getCurrentPlayerIndex()).andReturn(0);

        EasyMock.expect(mockCurrentPlayer.getHand()).andReturn(currentPlayerHand).times(3);
        EasyMock.expect(mockPlayer2.getHand()).andReturn(player2Hand);
        EasyMock.expect(mockCurrentPlayer.getHandSize()).andReturn(2).times(3);
        EasyMock.expect(mockPlayer2.getHandSize()).andReturn(1);

        mockInput.displayPlayerAndCardsInHand(mockCurrentPlayer);
        EasyMock.expectLastCall().times(3);

        mockInput.displayPlayerAndCardsInHand(mockPlayer2);
        EasyMock.expectLastCall();

        EasyMock.expect(mockInput.getCardChoice()).andReturn(userChoice).once();
        EasyMock.expect(mockInput.getCardChoice()).andReturn(userChoice2).once();
        EasyMock.expect(mockInput.getCardChoice()).andReturn(userChoice3).once();
        EasyMock.expect(mockInput.getCardChoice()).andReturn(userChoice4).once();

        mockInput.displayInvalidIndex(userChoice);
        EasyMock.expectLastCall();

        mockInput.displayInvalidIndex(userChoice2);
        EasyMock.expectLastCall();

        mockDeck.insert(mockDrawFromBottomCard, 0);
        EasyMock.expectLastCall().once();

        mockDeck.insert(mockCatCard, 0);
        EasyMock.expectLastCall().once();

        mockInput.displayValidCard(mockDrawFromBottomCard);
        EasyMock.expectLastCall();

        mockInput.displayValidCard(mockCatCard);
        EasyMock.expectLastCall();

        EasyMock.replay(mockGameController, mockGame, mockCurrentPlayer,
                mockPlayer2, mockInput, mockDeck, mockSkipCard,
                mockDrawFromBottomCard, mockCatCard);

        PotluckCardController controller = new PotluckCardController(mockInput);
        controller.executeCardAction(mockGameController, mockCurrentPlayer, Optional.empty());

        EasyMock.verify(mockGameController, mockGame, mockCurrentPlayer,
                mockPlayer2, mockInput, mockDeck, mockSkipCard,
                mockDrawFromBottomCard, mockCatCard);

        }
}

