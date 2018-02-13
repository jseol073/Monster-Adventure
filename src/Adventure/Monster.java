package Adventure;

import com.google.gson.annotations.SerializedName;

public class Monster {
    private String name;
    private double attack;
    private double defense;
    private double health;
    private Double fullHealth;

    public Monster() {
    }

    public String getName() {
        return name;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public Double getFullHealth() {
        return fullHealth;
    }

    public void setFullHealth(double fullHealth) {
        this.fullHealth = fullHealth;
    }

    public String monsterGetHealthBar(double currHealth) {
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
