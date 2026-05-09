public class Player {

    public Player(String playerName) {
        if  (playerName == null) {
            throw new IllegalArgumentException("player name can not be null");
        }
        throw new IllegalArgumentException("player name can not be empty");
    }
}
