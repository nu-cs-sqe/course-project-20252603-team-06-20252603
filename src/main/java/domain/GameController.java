package domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.EnumMap;
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

    public int getCurrentPlayerIndex() { 
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
        Map<CardType, CardController> cardToControllerMap = new EnumMap<>(CardType.class);
        cardToControllerMap.put(CardType.ATTACK, new AttackCardController());
        cardToControllerMap.put(CardType.DEFUSE, new DefuseCardController());
        cardToControllerMap.put(CardType.SEE_THE_FUTURE, new SeeTheFutureCardController());
        cardToControllerMap.put(CardType.SHUFFLE, new ShuffleCardController());
        cardToControllerMap.put(CardType.SKIP, new SkipCardController());
        cardToControllerMap.put(CardType.NOPE, new NopeCardController());
        cardToControllerMap.put(CardType.DRAW_FROM_BOTTOM, new DrawFromBottomCardController());
        cardToControllerMap.put(CardType.CAT_CARD_1, new CatCardController());
        cardToControllerMap.put(CardType.CAT_CARD_2, new CatCardController());
        cardToControllerMap.put(CardType.CAT_CARD_3, new CatCardController());
        cardToControllerMap.put(CardType.CAT_CARD_4, new CatCardController());

        if (cardToControllerMap.containsKey(card.getType())) {
            return cardToControllerMap.get(card.getType());
        }

        throw new IllegalArgumentException("invalid test type");
    }

    public void advanceTurn() {
        this.currentPlayerIndex = this.nextPlayerIndex;
        this.nextPlayerIndex = (this.currentPlayerIndex + 1) % this.game.getAlivePlayerCount();
    }

    public boolean isTargetValid(CardType type, Player initiator, Player target){
        return type.canHaveTarget() && target != initiator;
    }

    public boolean cardsAllMatchingCatCards(ArrayList<Card> cards){
        CardType firstType = cards.get(0).getType();
        if (!firstType.canHaveTarget()) return false;
        for (Card card : cards) {
            if (!card.getType().equals(firstType)) return false;
        }
        return true;
    }

    public boolean playerHasCards(Player initiator, ArrayList<Card> cards) {
        throw new IllegalArgumentException("cards list cannot be empty");
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
