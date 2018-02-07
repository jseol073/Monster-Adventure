package Adventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoMethods {

    /**
     *
     * @param userInput
     * @param roomIndex
     * @param layout
     * @return index of where the next room will be
     */
    public static int goToNextRoom(String userInput, int roomIndex, Layout layout) {
        Directions[] directions = layout.getRooms()[roomIndex].getDirections();
        String intendedDirection = getADirection(userInput);
        String roomName = "";
        if (!canGoDirection(intendedDirection, roomIndex, layout)) {
            System.out.println("Can't go there");
            return roomIndex;
        }
        for (int directionIndex = 0; directionIndex < directions.length; directionIndex++) {
            if (intendedDirection.equalsIgnoreCase(directions[directionIndex].getDirectionName())) {
                roomName = directions[directionIndex].getRoom();
            }
        }
        int indexOfNextRoom = 0;
        Room[] roomArray = layout.getRooms();
        for (int rIndex = 0; rIndex < roomArray.length; rIndex++) {
            if (roomName.equalsIgnoreCase(roomArray[rIndex].getName())) {
                indexOfNextRoom = rIndex;
            }
        }
        return indexOfNextRoom;
    }

    /**
     * helper method for getNextIndex
     * @param userInput
     * @return the direction the user wants to go
     */
    //helper method for getNextIndex
    //getting direction from user's input
    public static String getADirection(String userInput) {
        String aDirection = "";
        String commandToLowerCase = userInput.toLowerCase();
        String[] directionArr = {"east", "west", "north", "south", "northeast", "down", "up"};
        for (int i = 0; i < directionArr.length; i++) {
            if (commandToLowerCase.contains(directionArr[i])) {
                aDirection = directionArr[i];
            }
        }
        return aDirection;
    }

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
}
