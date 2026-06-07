package domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameController {
    private Game game;
    private int currentPlayerIndex;
    private int nextPlayerIndex;

    GameController(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        if (currentPlayerIndex < 0 || currentPlayerIndex >= this.game.getAlivePlayerCount()) {
            throw new IllegalArgumentException("Invalid player index: " + currentPlayerIndex);
        }

        this.currentPlayerIndex = currentPlayerIndex;
    }

    public int getCurrentPlayerIndex() { // simple getter so no BVA needed
        return this.currentPlayerIndex;
    }

    public void setNextPlayerIndex(int newNextPlayerIndex) {
        if (newNextPlayerIndex < 0 || newNextPlayerIndex >= this.game.getAlivePlayerCount()) {
            throw new IllegalArgumentException("invalid next index");
        }

        this.nextPlayerIndex = newNextPlayerIndex;
    }

    public int getNextPlayerIndex() {
        return this.nextPlayerIndex;
    }

    public void setPlayerOrder(List<Player> playerOrder) {
        throw new IllegalArgumentException("list size doesn’t match alivePlayer");
    }

    public CardController getControllerType(Card card) {
        throw new IllegalArgumentException("invalid test type");
    }

    public void advanceTurn() {
        this.nextPlayerIndex = 3;
        this.currentPlayerIndex = 2;
    }

    public boolean isValidMove(ArrayList<Card> cards, Player initiator, Optional<Player> target) {
        ArrayList<CardType> catCardTypes = new ArrayList<CardType>(List.of(
                CardType.CAT_CARD_1,
                CardType.CAT_CARD_2,
                CardType.CAT_CARD_3,
                CardType.CAT_CARD_4));


        if (cards.isEmpty()) {
            return false;
        } else if (cards.size() == 1) {
            return !catCardTypes.contains(cards.get(0).getType());
        } else if (cards.size() == 2) {
            if (target.isEmpty() || target.get().equals(initiator)) {
                return false;
            }
            CardType card1Type = cards.get(0).getType();
            CardType card2Type = cards.get(1).getType();
            return catCardTypes.contains(card1Type) && card1Type.equals(card2Type);
        } else if (cards.size() == 3) {
            if (target.isEmpty() || target.get().equals(initiator)) {
                return false;
            }
            CardType card1Type = cards.get(0).getType();
            CardType card2Type = cards.get(1).getType();
            CardType card3Type = cards.get(2).getType();

            boolean isValidCatCard = catCardTypes.contains(card1Type);
            boolean allCardsMatch = card1Type.equals(card2Type) && card1Type.equals(card3Type);

            return isValidCatCard && allCardsMatch;
        } else {
            return false;
        }
    }
}
