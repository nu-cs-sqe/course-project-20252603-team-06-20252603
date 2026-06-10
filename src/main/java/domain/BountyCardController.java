package domain;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class BountyCardController implements CardController {
    public Optional<List<Card>> executeCardAction(Game game, Player user, Optional<Player> target) {
        List<Player> alive_players = game.getAlivePlayers();
        int alive_player_count = alive_players.size();
        for (int i = 0; i < alive_player_count; i++) {
            Player current_player = alive_players.get(i);
            if (current_player == user) continue;
            if (current_player.getHandSize() > 0) {
                List<Card> hand = current_player.getHand();
                Card stolen = hand.get(new Random().nextInt(hand.size()));
                current_player.removeCard(stolen);
                user.addCard(stolen);
            }
        }
        return Optional.empty();
    }
}