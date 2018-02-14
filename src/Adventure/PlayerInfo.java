package Adventure;

import java.util.ArrayList;

public class PlayerInfo {

    /**
     *
     * @return String of player's level, attack, defense, and health
     */
    public static String getPlayerInfo() {
        String healthBar = playerHealthBar(Adventure.player.getFullHealth(), Adventure.playerHealth);
        return String.format("Player Level: %d \nPlayer's Attack: %.2f\nPlayer's Defense: %.2f\n" +
                "Player's health: %s (%.2f)", Adventure.playerLevel, Adventure.playerAttack,
                Adventure.playerDefense, healthBar, Adventure.playerHealth);
    }

    public static String playerHealthBar(double fullHealth, double currHealth) {
        StringBuilder healthBar = new StringBuilder();
        for (int i = 0; i < currHealth; i++) {
            healthBar.append("#");
        }
        for (int i = 0; i < fullHealth - currHealth; i++) {
            if (healthBar.length() <= fullHealth) {
                healthBar.append("-");
            }
        }
        return healthBar.toString();
    }
}
