package ui;

import domain.Card;
import domain.Game;
import domain.Player;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== EXPLODING WILDKITTENS ===");
        int playerCount = promptPlayerCount();

        // TODO: Game constructor is package-private — needs to be made public
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
        List<Player> players = game.getAlivePlayers();
        int currentIndex = 0;

        while (game.getAlivePlayerCount() > 1) {
            players = game.getAlivePlayers();
            Player current = players.get(currentIndex % players.size());
            takeTurn(game, current);
            currentIndex++;
        }

        System.out.println("\n" + game.getAlivePlayers().get(0).getPlayerName() + " wins!");
    }

    private static void takeTurn(Game game, Player player) {
        System.out.println("\n--- " + player.getPlayerName() + "'s turn ---");
        displayHand(player);
        System.out.println("(p) Play a card   (d) Draw a card");

        String choice = scanner.nextLine().trim().toLowerCase();
        if (choice.equals("p")) {
            playCard(game, player);
        } else {
            // TODO: game.getDeck() is package-private — needs to be exposed for drawing
            // game.draw(player, game.getDeck());
            System.out.println(player.getPlayerName() + " drew a card.");
        }
    }

    private static void playCard(Game game, Player player) {
        List<Card> hand = player.getHand();
        if (hand.isEmpty()) {
            System.out.println("No cards to play — drawing instead.");
            return;
        }

        for (int i = 0; i < hand.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + hand.get(i).getType());
        }
        System.out.print("Choose a card (1-" + hand.size() + "): ");

        try {
            int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (idx < 0 || idx >= hand.size()) {
                System.out.println("Invalid choice.");
                return;
            }
            Card card = hand.get(idx);
            // TODO: route to the appropriate CardController based on card.getType()
            System.out.println("Played: " + card.getType());
            // Optional<List<Card>> result = controller.executeCardAction(...);
            // displayResult(result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private static void displayResult(java.util.Optional<java.util.List<Card>> result) {
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
