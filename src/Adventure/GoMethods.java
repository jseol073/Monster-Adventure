package Adventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoMethods {

    /**
     * helper method for getNextIndex
     * @param aDirection, output of getADirection method
     * @param roomIndex
     * @param layout
     * @return boolean to find whether the next direction is valid
     */
    public static boolean canGoDirection(String aDirection, int roomIndex, Layout layout) {
        List<String> directionList = getDirectionNamesAsList(roomIndex, layout);
        for (int i = 0; i < directionList.size(); i++) {
            if (directionList.get(i).equalsIgnoreCase(aDirection)) {
                return true;
            }
        }
        return false;
    }

    /**
     * helper method for canGoDirection method
     * @param roomIndex
     * @param layout
     * @return creates list of the possible directions by directionName
     */
    public static List<String> getDirectionNamesAsList(int roomIndex, Layout layout) {
        Directions[] directions = layout.getRooms()[roomIndex].getDirections();
        ArrayList<String> directionsList = new ArrayList<>();
        for (int i = 0; i < directions.length; i++) {
            directionsList.add(directions[i].getDirectionName());
        }
        return directionsList;
    }

    public static String goingToNextRoom(String command, int roomIndex, Layout layout) {
        String falseCommand = "";
        String aDirection = getDirectionName(command, roomIndex, layout);
        Directions[] directions = layout.getRooms()[roomIndex].getDirections();
        String aDirectionRoom = getADirectionRoom(aDirection, directions);
        Room[] roomArr = layout.getRooms();
        if (canGoDirection(aDirection, roomIndex, layout)) {
            for (int rIndex = 0; rIndex < roomArr.length; rIndex++) {
                if (aDirectionRoom.equalsIgnoreCase(roomArr[rIndex].getName())) {
                    Adventure.gameRoomIndex = rIndex;
                }
            }
        }
        return falseCommand;
    }

    public static String getDirectionName(String command, int roomIndex, Layout layout) {
        String aDirection = String.format("Can't go to %s", command);
        List<String> directionNameList = getDirectionNamesAsList(roomIndex, layout);
        String commandTrim = command.trim();
        for (int i = 0; i < directionNameList.size(); i++) {
            if (commandTrim.equalsIgnoreCase(directionNameList.get(i))) {
                aDirection = directionNameList.get(i);
            }
        }
        return aDirection;
    }

    public static String getADirectionRoom(String aDirection, Directions[] directions) {
        String directionRoom = "";
        for (int i = 0; i < directions.length; i++) {
            if (aDirection.equalsIgnoreCase(directions[i].getDirectionName())) {
                directionRoom = directions[i].getRoom();
            }
        }
        return directionRoom;
    }
}
