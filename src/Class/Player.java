
package Class;

public class Player {
    public static int playerId;
    public static String playerUsername;
    public static String playerEmail;
    public static int playerLevel;

    public Player(int playerId, String playerUsername, String playerEmail, int playerLevel) {
        this.playerId = playerId;
        this.playerUsername = playerUsername;
        this.playerEmail = playerEmail;
        this.playerLevel = playerLevel;
    }
    
    public Player() {
        
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getplayerUsername() {
        return playerUsername;
    }

    public void setplayerUsername(String playerName) {
        this.playerUsername = playerUsername;
    }

    public String getPlayerEmail() {
        return playerEmail;
    }

    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
}
