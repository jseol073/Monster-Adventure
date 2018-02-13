package Adventure;

import java.util.ArrayList;
import java.util.Arrays;

public class Attack {

    public static String attack(String command, int roomIndex, Layout layout) {
        Monster currMonster = Dual.getMonsterInRoom(command, roomIndex, layout);
        double plyrDamage = Adventure.playerAttack - currMonster.getDefense();
        double monsterDamage = currMonster.getAttack() - Adventure.player.getDefense();
        currMonster.setHealth(currMonster.getHealth() - plyrDamage);
        Adventure.player.setHealth(Adventure.playerHealth - monsterDamage);

        if (Adventure.player.getHealth() <= 0.0) {
            Adventure.endGame = true;
            return "You died.";
        }
        if (currMonster.getHealth() <= 0.0) {
            Adventure.isDual = false;
            //remove monster from String[] monstersInRoom
            return String.format("You beat %s", currMonster.getName());
        }
        return "";
    }

    public static String attackWithItem(String maybeItemName, int roomIndex, Layout layout) {
        String[] splitItemName = maybeItemName.split("\\s+");
        Item playersItem = new Item();
        String itemLongName = "";
        if (splitItemName.length >= 2) {
            itemLongName = String.format("%s %s", splitItemName[0], splitItemName[1]);
            if (doesPlayerHaveItem(itemLongName)) {
                playersItem = getPlayersItem(itemLongName);
            } else {
                return "Player does not have any items.";
            }
        } else {
            if (doesPlayerHaveItem(maybeItemName)) {
                playersItem = getPlayersItem(maybeItemName);
            } else {
                return "Player does not have any items.";
            }
        }
        Monster currMonster = Dual.getMonsterInRoom(Adventure.command, roomIndex, layout);
        double plyrDamage = Adventure.playerAttack + playersItem.getDamage() - currMonster.getDefense();
        double monsterDamage = currMonster.getAttack() - Adventure.player.getDefense();
        currMonster.setHealth(currMonster.getHealth() - plyrDamage);
        Adventure.player.setHealth(Adventure.playerHealth - monsterDamage);

        if (Adventure.player.getHealth() <= 0.0) {
            Adventure.endGame = true;
            return "You died.";
        }
        if (currMonster.getHealth() <= 0.0) {
            Adventure.isDual = false;
            removeMonsterFromArr(currMonster, roomIndex, layout);
            //remove lion from String[] monstersInRoom
            return String.format("You beat %s", currMonster.getName());
        }
        return "";
    }

    public static void removeMonsterFromArr(Monster dualMonster, int roomIndex, Layout layout) {
        String monsterName = dualMonster.getName();
        String[] monsterRoomArr = layout.getRooms()[roomIndex].getMonstersInRoom();
        ArrayList<String> monsterRoomList = new ArrayList<>(Arrays.asList(monsterRoomArr));
        for (int mRoomIndex = 0; mRoomIndex < monsterRoomList.size(); mRoomIndex++) {
            if (monsterName.equalsIgnoreCase(monsterRoomList.get(mRoomIndex))) {
                monsterRoomList.remove(monsterRoomList.get(mRoomIndex));
            }
        }
        //Got the following code from
        //https://stackoverflow.com/questions/4042434/converting-arrayliststring-to-string-in-java
        Object[] temp = monsterRoomList.toArray();
        String[] newMonsterRoomArr = Arrays.copyOf(temp, temp.length, String[].class);
        layout.getRooms()[roomIndex].setMonstersInRoom(newMonsterRoomArr);
    }

    public static boolean doesPlayerHaveItem(String maybeItemName) {
        for (int plyrItemIndex = 0; plyrItemIndex < Adventure.playerItemList.size(); plyrItemIndex++) {
            if (maybeItemName.equalsIgnoreCase(Adventure.playerItemList.get(plyrItemIndex).getName())) {
                return true;
            }
        }
        return false;
    }

    public static Item getPlayersItem(String itemName) {
        for (int plyrItemIndex = 0; plyrItemIndex < Adventure.playerItemList.size(); plyrItemIndex++) {
            if (itemName.equalsIgnoreCase(Adventure.playerItemList.get(plyrItemIndex).getName())) {
                return Adventure.playerItemList.get(plyrItemIndex);
            }
        }
        return new Item();
    }
}
