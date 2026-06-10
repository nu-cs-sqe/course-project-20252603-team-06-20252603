package domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.EnumMap;
import java.util.Optional;

import ui.GameControllerView;

public class GameController {
    private Game game;
    private int currentPlayerIndex;
    private int nextPlayerIndex;
    private int currentPlayerTurnsLeft;
    private int nextPlayerTurnsLeft;

    GameController(Game game) {
        this.game = game;
    }

    Game getGame() {
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

    public void setCurrentPlayerTurnsLeft(int currentPlayerTurnsLeft) {
        if (currentPlayerTurnsLeft < 0) {
            throw new IllegalArgumentException("invalid turn count");
        }
        this.currentPlayerTurnsLeft = currentPlayerTurnsLeft;
    }

    public int getCurrentPlayerTurnsLeft() {
        return this.currentPlayerTurnsLeft;
    }

    public void setNextPlayerTurnsLeft(int nextPlayerTurnsLeft) {
        if (nextPlayerTurnsLeft < 0) {
            throw new IllegalArgumentException("invalid turn count");
        }
        this.nextPlayerTurnsLeft = nextPlayerTurnsLeft;

    }

    public int getNextPlayerTurnsLeft() {
        return this.nextPlayerTurnsLeft;
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

    public boolean isTargetValid(CardType type, Player initiator, Player target) {
        return type.canHaveTarget() && target != initiator;
    }

    public boolean cardsAllMatchingCatCards(ArrayList<Card> cards) {
        CardType firstType = cards.get(0).getType();
        if (!firstType.canHaveTarget()) return false;
        for (Card card : cards) {
            if (!card.getType().equals(firstType)) return false;
        }
        return true;
    }

    public boolean playerHasCards(Player initiator, ArrayList<Card> cards) {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("cards list cannot be empty");
        }
        ArrayList<Card> hand = initiator.getHand();
        ArrayList<CardType> handTypes = new ArrayList<CardType>();

        for (Card card : hand) {
            handTypes.add(card.getType());
        }

        for (Card card : cards) {
            CardType cardType = card.getType();
            if (!handTypes.remove(cardType)) {
                return false;
            }
        }

        return true;
    }

    public boolean isValidMove(ArrayList<Card> cards, Player initiator, Optional<Player> target) {
        if (cards.isEmpty()) return false;
        if (!playerHasCards(initiator, cards)) return false;

        if (cards.size() == 1) {
            CardType type = cards.get(0).getType();
            return !type.canHaveTarget() && target.isEmpty();
        } else if (cards.size() == 2 || cards.size() == 3) {
            return cardsAllMatchingCatCards(cards)
                    && target.isPresent()
                    && isTargetValid(cards.get(0).getType(), target.get(), initiator);
        } else {
            return false;
        }
    }

    public void takeTurn(GameControllerView controllerView) {
        Player currentPlayer = game.getAlivePlayers().get(currentPlayerIndex);

        controllerView.displayCurrentPlayerAndCardsInHand(currentPlayer);
        String userChoice = controllerView.getCardChoiceOrDraw();

        if (userChoice.equalsIgnoreCase("d")) {
            game.draw(currentPlayer, game.getDeck());
            this.currentPlayerTurnsLeft--;
        } else {
            try {
                int cardIndex = Integer.parseInt(userChoice);
                ArrayList<Card> cardsPlayed = new ArrayList<>();
                cardsPlayed.add(currentPlayer.getHand().get(cardIndex));

                controllerView.displayInvalidMove(cardsPlayed);
            } catch (NumberFormatException e) {
                controllerView.displayInvalidChoice(userChoice);
            } catch (IndexOutOfBoundsException e) {
                controllerView.displayInvalidIndex(userChoice);
            }
        }
    }

}
