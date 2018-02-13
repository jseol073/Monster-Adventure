package Adventure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Room {
    private String name;
    private String description;
    private Item[] items;
    private Directions[] directions;
    private String[] monstersInRoom;
    private HashMap<String, Double> itemMap;

    public Room() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Directions[] getDirections() {
        return directions;
    }

    public String[] getMonstersInRoom() {
        return monstersInRoom;
    }

    public void setMonstersInRoom(String[] monstersInRoom) {
        this.monstersInRoom = monstersInRoom;
    }

    public HashMap<String, Double> getItemMap() {
        if (this.getItems() != null) {
            for (int itemIndex = 0; itemIndex < this.getItems().length; itemIndex++) {
                itemMap.put(this.getItems()[itemIndex].getName(), this.getItems()[itemIndex].getDamage());
            }
        }
        return itemMap;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", items=" + Arrays.toString(items) +
                ", directions=" + Arrays.toString(directions) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name) &&
                Objects.equals(description, room.description) &&
                Arrays.equals(items, room.items) &&
                Arrays.equals(directions, room.directions);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, description);
        result = 31 * result + Arrays.hashCode(items);
        result = 31 * result + Arrays.hashCode(directions);
        return result;
    }
}
