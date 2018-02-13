package Adventure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TakeAndDropMethods {
    public static final String TAKE = "take";

    public static Item getItem(String command, int roomIndex, Layout layout) {
        String[] commandSplit = command.split("\\s+");
        Item[] itemArr = layout.getRooms()[roomIndex].getItems();
        if (itemArr != null && !(itemArr.length == 0)) {
            if (commandSplit.length == 1) {
                for (int i = 0; i < itemArr.length; i++) {
                    if (commandSplit[0].equalsIgnoreCase(itemArr[i].getName())) {
                        return itemArr[i];
                    }
                }
            } else if (commandSplit.length == 2) {
                String splitToWord = "";
                for (int i = 0; i < commandSplit.length; i++) {
                    splitToWord = String.format("%s %s", commandSplit[0], commandSplit[1]);
                }
                for (int i = 0; i < itemArr.length; i++) {
                    if (splitToWord.equalsIgnoreCase(itemArr[i].getName())) {
                        return itemArr[i];
                    }
                }
            }
        }
        return null;
    }

    /**
     * removes item from the itemList of the room the user is when the user takes something
     * In the main method, that item is added to the usersItemList
     * @param item
     * @param roomIndex
     * @param layout
     * @return boolean true if prevList does not equal newList for testcases;
     * otherwise, it is a void method
     */
    public static boolean removeItemFromList(Item item, int roomIndex, Layout layout) {
        Item[] oldTempItemArr = layout.getRooms()[roomIndex].getItems();
        String itemName = item.getName();
        Item[] currItemArr = layout.getRooms()[roomIndex].getItems();
        ArrayList<Item> currItemList = new ArrayList<>(Arrays.asList(currItemArr));
        for (int itemIndex = 0; itemIndex < currItemList.size(); itemIndex++) {
            String currItem = currItemList.get(itemIndex).getName().toLowerCase();
            if (itemName.equalsIgnoreCase(currItem)) {
                currItemList.remove(item);
            }
        }
        currItemArr = currItemList.toArray(new Item[currItemList.size()]);
        layout.getRooms()[roomIndex].setItems(currItemArr);
        Item[] newItemArr = layout.getRooms()[roomIndex].getItems();

        if (newItemArr != oldTempItemArr) {
            return true;
        }
        return false;
  }

    /**
     * adds item to the itemList of the room the user is when the user drops the item
     * In the main method, that item is removed from the usersItemList
     * @param item
     * @param roomIndex
     * @param layout
     * @return boolean true if prevList does not equal newList for testcases;
     * otherwise, it is a void method
     */
    public static boolean dropItemToRoom(Item item, int roomIndex, Layout layout) {
        Item[] currItemArr = layout.getRooms()[roomIndex].getItems();
        Item[] tempItemArr = currItemArr;
        ArrayList<Item> currItemList = new ArrayList<>();
        if (currItemArr != null) {
            currItemList = new ArrayList<>(Arrays.asList(currItemArr));
        }
        currItemList.add(item);
        currItemArr = currItemList.toArray(new Item[currItemList.size()]);
        layout.getRooms()[roomIndex].setItems(currItemArr);

        Item[] newItemArr = layout.getRooms()[roomIndex].getItems();
        if (newItemArr != tempItemArr) {
            return true;
        }
        return false;
    }

    public static void takeItem(String command, int roomIndex, Layout layout) {
        Item takenItem = getItem(command, roomIndex, layout);
        if (takenItem != null) {
            removeItemFromList(takenItem, roomIndex, layout);
            Adventure.playerItemList.add(takenItem);
            Item[] playerItemArr = Adventure.playerItemList.toArray(new Item[Adventure.playerItemList.size()]);
            Adventure.player.setItems(playerItemArr);
        }
    }

    public static void dropItem(String command, int roomIndex, Layout layout) {
        Item dropItem = null;
        for (int i = 0; i < Adventure.playerItemList.size(); i++) {
            if (command.equalsIgnoreCase(Adventure.playerItemList.get(i).getName())) {
                dropItem = Adventure.playerItemList.get(i);
            }
        }

        if (dropItem != null) {
            dropItemToRoom(dropItem, roomIndex, layout);
            Adventure.playerItemList.remove(dropItem);
            Item[] playerItemArr = Adventure.playerItemList.toArray(new Item[Adventure.playerItemList.size()]);
            Adventure.player.setItems(playerItemArr);
        }
    }
}
