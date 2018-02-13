package Adventure;

public class Player {
    private String name;
    private Item[] items;
    private double attack;
    private double defense;
    private double health;
    private Double fullHealth;
    private int level;

    public Player() {
    }

    public String getName() {
        return name;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public Double getFullHealth() {
        return fullHealth;
    }

    public void setFullHealth(Double fullHealth) {
        this.fullHealth = fullHealth;
    }
}
