package ui;

import domain.Card;
import domain.AlterTheFutureCardController;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in, StandardCharsets.UTF_8);

    public static void main(String[] args) {
    }

    static List<Card> promptReorder(List<Card> cards) {
        System.out.println("Current top of deck:");
        for (int i = 0; i < cards.size(); i++) {
            System.out.println((i + 1) + ": " + cards.get(i).getType());
        }
        System.out.println("Enter new order as space-separated numbers (e.g. 3 1 2):");
        String[] parts = SCANNER.nextLine().trim().split("\\s+");
        List<Card> reordered = new java.util.ArrayList<>();
        for (String part : parts) {
            reordered.add(cards.get(Integer.parseInt(part) - 1));
        }
        return reordered;
    }

    static AlterTheFutureCardController buildAlterTheFutureController() {
        return new AlterTheFutureCardController(Main::promptReorder);
    }
}
