package ui;

import domain.Card;
import domain.CardType;
import domain.Game;
import domain.Player;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== EXPLODING WILDKITTENS ===");
        int playerCount = promptPlayerCount();

        // TODO: Game constructor is package-private — needs to be made public
        // TODO: replace Game with GameController once merged
        // Game game = new Game(playerCount);
        // game.setup();
        // runGameLoop(game);
    }

    private static int promptPlayerCount() {
        while (true) {
            System.out.print("Enter number of players (2-5): ");
            try {
                int n = Integer.parseInt(scanner.nextLine().trim());
                if (n >= 2 && n <= 5) return n;
                System.out.println("Must be between 2 and 5.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    private static void runGameLoop(Game game) {
        while (game.getAlivePlayerCount() > 1) {
            List<Player> players = game.getAlivePlayers();
            // TODO: replace currentPlayer lookup with gameController.getCurrentPlayer()
            Player current = players.get(0);
            takeTurn(game, current);
        }

        System.out.println("\n" + game.getAlivePlayers().get(0).getPlayerName() + " wins!");
    }

    private static void takeTurn(Game game, Player player) {
        System.out.println("\n--- " + player.getPlayerName() + "'s turn ---");
        CardType lastPlayed = null;

        while (true) {
            displayHand(player);
            System.out.println("(p) Play a card   (e) End turn");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("p")) {
                lastPlayed = playCard(game, player);
            } else {
                if (lastPlayed != CardType.SKIP && lastPlayed != CardType.ATTACK) {
                    // TODO: draw a card
                    // game.draw(player, game.getDeck());
                    System.out.println(player.getPlayerName() + " drew a card.");
                }
                // TODO: gameController.advanceTurn();
                return;
            }
        }
    }

    private static CardType playCard(Game game, Player player) {
        List<Card> hand = player.getHand();
        if (hand.isEmpty()) {
            System.out.println("No cards to play.");
            return null;
        }

        for (int i = 0; i < hand.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + hand.get(i).getType());
        }
        System.out.print("Choose a card (1-" + hand.size() + "): ");

        try {
            int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (idx < 0 || idx >= hand.size()) {
                System.out.println("Invalid choice.");
                return null;
            }
            Card card = hand.get(idx);
            // TODO: route to the appropriate CardController based on card.getType()
            // Optional<List<Card>> result = CONTROLLERS.get(card.getType()).executeCardAction(game, player, Optional.empty());
            // displayResult(result);
            System.out.println("Played: " + card.getType());
            return card.getType();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return null;
        }
    }

    private static void displayResult(Optional<List<Card>> result) {
        if (result.isEmpty()) return;
        System.out.println("Result:");
        for (Card c : result.get()) {
            System.out.println("  - " + c.getType());
        }
    }

    private static void displayHand(Player player) {
        List<Card> hand = player.getHand();
        System.out.print("Hand: ");
        if (hand.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        for (int i = 0; i < hand.size(); i++) {
            System.out.print((i + 1) + ". " + hand.get(i).getType() + "  ");
        }
        System.out.println();
    }
}
