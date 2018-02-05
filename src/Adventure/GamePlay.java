package Adventure;

import java.util.HashMap;

public class GamePlay {
    private Layout layout = new Layout();
    public final String EAST = "east";
    public final String WEST = "west";
    public final String SOUTH = "south";
    public final String NORTHEAST = "northeast";
    public final String UP = "up";
    public final String DOWN = "down";

    public static
    //getting direction from user's input
    public static String getADirection(String userInput) {
        if (!(userInput.substring(0, 2).equalsIgnoreCase("go"))) {
            throw new IllegalArgumentException("Does not start with go");
        }
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

    //get Directions[] index of next room
    public static int getNextIndex(Directions[] directions, String userInput, int index, Layout layout) {

        directions = layout.getRooms()[index].getDirections();
        String intendedDirection = getADirection(userInput);
        if (!canGoDirection(intendedDirection, directions, index, layout)) {
            return 0;
        }
        return 1;
    }

    //boolean to find whether the next direction is valid
    public static boolean canGoDirection(String aDirection, Directions[] directions, int index, Layout layout) {
        HashMap<String, String> roomAndDirectionMap = getDirectionOptions(directions, index, layout);
        //aDirection = getDirectionCommand(userInput);
        if (roomAndDirectionMap.containsKey(aDirection)) {
            return true;
        }
        return false;
    }

    //hashmap of one Direction object, lists directions and the room it's corresponded to
    public static HashMap<String, String> getDirectionOptions(Directions[] directions, int index, Layout layout) {
        directions = layout.getRooms()[index].getDirections();
        HashMap<String, String> directionsMap = new HashMap<>();
        for (int i = 0; i < directions.length; i++) {
            directionsMap.put(directions[i].getDirectionName(), directions[i].getRoom());
        }
        return directionsMap;
    }
}
