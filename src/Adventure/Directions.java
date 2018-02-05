package Adventure;

import java.util.Objects;

public class Directions {
    private String directionName;
    private String room;

    public Directions() {
    }

    @Override
    public String toString() {
        return "Directions{" +
                "directionName = '" + directionName + '\'' +
                ", room = '" + room + '\'' +
                '}';
    }

    public String getDirectionName() {
        return directionName;
    }

    public String getRoom() {
        return room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directions that = (Directions) o;
        return Objects.equals(directionName, that.directionName) &&
                Objects.equals(room, that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(directionName, room);
    }
}
