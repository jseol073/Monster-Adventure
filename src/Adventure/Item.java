package Adventure;

import java.util.HashMap;

public class Item {
    private String name;
    private double damage;
    private HashMap<String, Double> itemMap;

    public Item(){
    }

    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }

    public HashMap<String, Double> getItemMap() {
        itemMap.put(this.getName(), this.getDamage());
        return itemMap;
    }
}
