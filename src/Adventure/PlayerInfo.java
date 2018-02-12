package Adventure;

import java.util.ArrayList;

public class PlayerInfo {
    public static final int FULL_HEALTH = 20;

    public static String getPlayerInfo() {
        Adventure.playerLevel = Adventure.player.getLevel();
        Adventure.playerAttack = Adventure.player.getAttack();
        Adventure.playerDefense = Adventure.player.getDefense();
        Adventure.playerHealth = Adventure.player.getHealth();
        String healthBar = playerHealthBar(FULL_HEALTH, Adventure.playerHealth);
        return String.format("Player Level: %d \nPlayer's Attack: %.2f\nPlayer's Defense: %.2f\n" +
                "Player's health: %s", Adventure.playerLevel, Adventure.playerAttack,
                Adventure.playerDefense, healthBar);
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
