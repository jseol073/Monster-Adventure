package Adventure;

import java.util.ArrayList;

public class Dual {
    public static boolean canDualMonster(String command, int roomIndex, Layout layout) {
        Monster[] monsterArr = layout.getMonsters();
        String monsterName = getMonsterName(command, roomIndex, layout);
        ArrayList<String>  monsterNameList = new ArrayList<>();
        for (int monsterIndex = 0; monsterIndex < monsterArr.length; monsterIndex++) {
            monsterNameList.add(monsterArr[monsterIndex].getName());
        }
        for (int i = 0; i < monsterNameList.size(); i++) {
            if (monsterName.equalsIgnoreCase(monsterNameList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static String getMonsterName(String command, int roomIndex, Layout layout) {
        String monsterName = "";
        if (layout.getRooms()[roomIndex].getMonstersInRoom() != null) {
            String[] monstersInCurrRoom = layout.getRooms()[roomIndex].getMonstersInRoom();
            if (monstersInCurrRoom.length != 0) {
                for (int i = 0; i < monstersInCurrRoom.length; i++) {
                    if (command.equalsIgnoreCase(monstersInCurrRoom[i])) {
                        monsterName = monstersInCurrRoom[i];
                    }
                }
            }
        }
        return monsterName;
    }

    public static void dualMonster(String command, int roomIndex, Layout layout) {
        String[] commandSplit = command.split("\\s+");
        String monsterName = getMonsterName(commandSplit[0], roomIndex, layout);
        if (canDualMonster(command, roomIndex, layout)) {

        }
    }

    //public static void attack()
}
