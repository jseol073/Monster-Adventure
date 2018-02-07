package Adventure;

import java.util.ArrayList;
import java.util.Arrays;

public class TakeAndDropMethods {
    public static final String TAKE = "take";
    public static final String DROP = "drop";

    /**
     *
     * @param userInput
     * @param roomIndex
     * @param layout
     * @return
     */
    // takes user's input and see if it contains an item of the current list it's on
    public static String getItemName(String userInput, int roomIndex, Layout layout) {
        String userInputLwrCase = userInput.toLowerCase();
        String itemName = "";
        String[] currItemArr = layout.getRooms()[roomIndex].getItems();
        for (int itemIndex = 0; itemIndex < currItemArr.length; itemIndex++) {
            String currItem = currItemArr[itemIndex].toLowerCase();
            if (userInputLwrCase.contains(currItem)) {
                itemName = currItem;
            }
        }
        return itemName;
    }

//    public static String getItemName(String userInput, Layout layout) {
//        String userInputLwrCase = userInput.toLowerCase();
//        String itemName = "";
//        Room[] allRooms = layout.getRooms();
//        for (int i = 0; i < allRooms.length; i++) {
//            String[] allItems = allRooms[i].getItems();
//            ArrayList<String> allItemsList = new ArrayList<>();
//            if (allItems != null) {
//                allItemsList = new ArrayList<>(Arrays.asList(allItems));
//            }
//            for (int j = 0; j < allItemsList.size(); j++) {
//                if (userInputLwrCase.contains(allItemsList.get(j))) {
//                    itemName = allItemsList.get(j);
//                }
//            }
//        }
//        return itemName;
//    }

//    public static boolean isItemNameValid(String itemName) {
//
//    }

    //removes item from the list when user takes something

    /**
     *
     * @param userInput
     * @param roomIndex
     * @param layout
     * @return
     */
    public static boolean removeItemFromList(String userInput, int roomIndex, Layout layout) {
        if (!(isTakeOrDrop(userInput))) {
            return false;
        }
        String userInputLwrCase = userInput.toLowerCase();
        String[] oldTempItemArr = layout.getRooms()[roomIndex].getItems();
        String itemName = getItemName(userInput, roomIndex, layout);
        String[] currItemArr = layout.getRooms()[roomIndex].getItems();
        ArrayList<String> currItemList = new ArrayList<>(Arrays.asList(currItemArr));
        for (int itemIndex = 0; itemIndex < currItemList.size(); itemIndex++) {
            String currItem = currItemList.get(itemIndex).toLowerCase();
            if (userInputLwrCase.contains(currItem)) {
                currItemList.remove(itemName);
            }
        }
        currItemArr = currItemList.toArray(new String[currItemList.size()]);
        layout.getRooms()[roomIndex].setItems(currItemArr);
        String[] newItemArr = layout.getRooms()[roomIndex].getItems();
        if (newItemArr != oldTempItemArr) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param userInput
     * @param roomIndex
     * @param layout
     * @return
     */
    public static boolean dropToAddItemToList(String userInput, int roomIndex, Layout layout) {
        if (isTakeOrDrop(userInput)) {
            return false;
        }
        String itemName = getItemName(userInput, roomIndex, layout);
        String[] currItemArr = layout.getRooms()[roomIndex].getItems();
        String[] tempItemArr = currItemArr;
        ArrayList<String> currItemList = new ArrayList<>();
        if (currItemArr != null) {
            currItemList = new ArrayList<>(Arrays.asList(currItemArr));
        }
        currItemList.add(itemName);
        currItemArr = currItemList.toArray(new String[currItemList.size()]);
        layout.getRooms()[roomIndex].setItems(currItemArr);
        String[] newItemArr = layout.getRooms()[roomIndex].getItems();
        if (newItemArr != tempItemArr) {
            return true;
        }
        return false;
    }

    /**
     * Helper method for removeItemFromList and dropToAddItemToList
     * Only for testcases
     * @param userInput
     * @return boolean true if take command, false if drop command
     */
    public static boolean isTakeOrDrop(String userInput) {
        String userInputLwrCase = userInput.toLowerCase();
        if (userInputLwrCase.contains(TAKE)) {
            return true;
        }
        return false;
    }

}
