package Adventure;

import java.util.Arrays;
import java.util.Objects;

public class Room {
    private String name;
    private String description;
    private String[] items;
    private Directions[] directions;

    public Room() {
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getItems() {
        return items;
    }

    public Directions[] getDirections() {
        return directions;
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
