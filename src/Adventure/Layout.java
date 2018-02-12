package Adventure;

import java.util.Arrays;
import java.util.Objects;

public class Layout {
    private String startingRoom;
    private String endingRoom;
    private Room[] rooms;
    private Player player;
    private Monster[] monsters;

    public Layout() {
    }

    public String getStartingRoom() {
        return startingRoom;
    }

    public String getEndingRoom() {
        return endingRoom;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public Player getPlayer() {
        return player;
    }

    public Monster[] getMonsters() {
        return monsters;
    }

    @Override
    public String toString() {
        return "Layout{" +
                "startingRoom='" + startingRoom + '\'' +
                ", endingRoom='" + endingRoom + '\'' +
                ", rooms=" + Arrays.toString(rooms) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Layout layout = (Layout) o;
        return Objects.equals(startingRoom, layout.startingRoom) &&
                Objects.equals(endingRoom, layout.endingRoom) &&
                Arrays.equals(rooms, layout.rooms);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(startingRoom, endingRoom);
        result = 31 * result + Arrays.hashCode(rooms);
        return result;
    }
}
