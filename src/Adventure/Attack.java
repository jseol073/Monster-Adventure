package Adventure;

import java.util.ArrayList;
import java.util.Arrays;

public class Attack {

    /**
     *
     * @param command
     * @param roomIndex
     * @param layout
     * @return
     */
    public static String attack(String command, int roomIndex, Layout layout) {
        Monster currMonster = Dual.getMonsterInRoom(command, roomIndex, layout);
        double plyrDamage = Adventure.playerAttack - currMonster.getDefense();
        double monsterDamage = currMonster.getAttack() - Adventure.player.getDefense();
        currMonster.setHealth(currMonster.getHealth() - plyrDamage);
        Adventure.player.setHealth(Adventure.playerHealth - monsterDamage);

        if (Adventure.player.getHealth() <= 0.0) {
            Adventure.isEndGame = true;
            return "You died.";
        }
        if (currMonster.getHealth() <= 0.0) {
            Adventure.isDual = false;
            removeMonsterFromArr(currMonster, roomIndex, layout);
            return increaseExp(Adventure.playerLevel, currMonster);
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
                return "Player does not have that item.";
            }
        } else {
            if (doesPlayerHaveItem(maybeItemName)) {
                playersItem = getPlayersItem(maybeItemName);
            } else {
                return "Player does not have that item.";
            }
        }
        Monster currMonster = Dual.getMonsterInRoom(Adventure.command, roomIndex, layout);
        double plyrDamage = Adventure.playerAttack + playersItem.getDamage() - currMonster.getDefense();
        double monsterDamage = currMonster.getAttack() - Adventure.player.getDefense();
        currMonster.setHealth(currMonster.getHealth() - plyrDamage);
        Adventure.player.setHealth(Adventure.playerHealth - monsterDamage);

        if (Adventure.player.getHealth() <= 0.0) {
            Adventure.isEndGame = true;
            return "You died.";
        }
        if (currMonster.getHealth() <= 0.0) {
            Adventure.isDual = false;
            removeMonsterFromArr(currMonster, roomIndex, layout);
            //increase players expererience
            return increaseExp(Adventure.playerLevel, currMonster);
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

    public static String increaseExp(int plyrLevel, Monster currMonster) {
        double experience = 0;
        if (plyrLevel <= 2) {
            experience = (((currMonster.getAttack() + currMonster.getDefense()) / 2)
                    + currMonster.getFullHealth()) * 20;
            System.out.printf("Experience: %.2f\n", experience);
            if (plyrLevel == 1) {
                if (experience >= 25.00) {
                    Adventure.player.setLevel(2);
                    Adventure.player.setAttack(Adventure.playerAttack * 1.5);
                    Adventure.player.setDefense(Adventure.playerDefense * 1.5);
                    Adventure.player.setHealth(Adventure.player.getFullHealth() * 1.3);
                    System.out.print("You have leveled up to 2!\n");
                }
            } else if (plyrLevel == 2) {
                if (experience >= 50.00) {
                    Adventure.player.setLevel(3);
                    Adventure.player.setAttack(Adventure.playerAttack * 1.5);
                    Adventure.player.setDefense(Adventure.playerDefense * 1.5);
                    Adventure.player.setHealth(Adventure.player.getFullHealth() * 1.3);
                    System.out.print("You have leveled up to 3!\n");
                }
            }
        }
        else if (plyrLevel >= 3) {

        }
        return String.format("You beat %s", currMonster.getName());
    }


}
