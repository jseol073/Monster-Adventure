package Adventure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GamePlay {
    public static final String GO = "go";
    public static final String TAKE = "take";
    public static final String DROP = "drop";
    public static final String LIST = "list";
    public static final String PLAYER_INFO = "playerinfo";
    public static final String DUAL = "dual";
    public static final String FALSE_COMMAND = "I don't understand";
    private static final int MIN_ARR_LENGTH = 2;
    private static final int MIN_STR_LENGTH = 4;

    public static int getStartingRoom(Layout layout) {
        Room[] allRooms = layout.getRooms();
        String startingRoom = layout.getStartingRoom();
        int startingRoomIndex = 0;
        for (int roomIndex = 0; roomIndex < allRooms.length; roomIndex++) {
            if (allRooms[roomIndex].getName().equalsIgnoreCase(startingRoom)) {
                startingRoomIndex = roomIndex;
            }
        }
        return startingRoomIndex;
    }

    public static String jsonInfo(int roomIndex, Layout layout) {
        String roomName = layout.getRooms()[roomIndex].getName();
        String description = layout.getRooms()[roomIndex].getDescription();
        Directions[] directions = layout.getRooms()[roomIndex].getDirections();
        ArrayList<String> directionNamesList = new ArrayList<>();
        for (int dirIndex = 0; dirIndex < directions.length; dirIndex++) {
            directionNamesList.add(directions[dirIndex].getDirectionName());
        }
        String directionNames = "From here you can go: " +directionNamesList.toString();
        String itemInfo = "This room is empty.";
        HashMap<String, Double> itemMap = new HashMap<>();
        if (layout.getRooms()[roomIndex].getItems() != null) {
            if (layout.getRooms()[roomIndex].getItems().length != 0) {
                Item[] itemArr = layout.getRooms()[roomIndex].getItems();
                for (int itemIndex = 0; itemIndex < itemArr.length; itemIndex++) {
                    itemMap.put(itemArr[itemIndex].getName(), itemArr[itemIndex].getDamage());
                }
                itemInfo = String.format("This room has these items: %s", itemMap.toString());
            }
        }

        if (layout.getRooms()[roomIndex].getName().equalsIgnoreCase(layout.getStartingRoom())) {

        }

        return String.format("%s\n%s\n%s\n%s", roomName, description, directionNames, itemInfo);
    }

    public static String handleUserInput(String userInput, int roomIndex, Layout layout) {
        String userInputLwrCase = userInput.toLowerCase();
        String lwrCaseTrimmed = userInputLwrCase.trim();
        String[] inputSplitArr = lwrCaseTrimmed.split("\\s+", MIN_ARR_LENGTH);
        if (inputSplitArr[0].equalsIgnoreCase(GO)) {
                return GoMethods.goingToNextRoom(inputSplitArr[1], roomIndex, layout);
        } else if (inputSplitArr[0].equalsIgnoreCase(TAKE)
                    || inputSplitArr[0].equalsIgnoreCase(DROP)) {
            if (inputSplitArr[0].equalsIgnoreCase(TAKE)) {
                TakeAndDropMethods.takeItem(inputSplitArr[1], roomIndex, layout);
            } else if (inputSplitArr[0].equalsIgnoreCase(DROP)) {
                TakeAndDropMethods.dropItem(inputSplitArr[1], roomIndex, layout);
            }
        } else if (inputSplitArr[0].equalsIgnoreCase(LIST)) {
                return List.listPlayerItems(layout);
        } else if (inputSplitArr[0].equalsIgnoreCase(PLAYER_INFO)) {
                return PlayerInfo.getPlayerInfo();
        } else if (inputSplitArr[0].equalsIgnoreCase(DUAL)) {

        } else {
            return FALSE_COMMAND;
        }
        return "";
    }


}
