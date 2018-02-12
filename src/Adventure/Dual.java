package Adventure;

import java.util.ArrayList;

public class Dual {
    private static final int MIN_ARR_LENGTH = 2;
    private static final String ATTACK = "attack";
    private static final String ATTACK_WITH_ITEM = "attack with item";
    private static final String DISENGAGE = "disengage";
    private static final String STATUS = "status";
    private static final String LIST = "list";
    private static final String PLAYERINFO = "playerInfo";
    public static final String FALSE_COMMAND = "I don't understand";


    public static String handleUserInputDual(String userInput, int roomIndex, Layout layout) {
        String userInputLwrCase = userInput.toLowerCase();
        String lwrCaseTrimmed = userInputLwrCase.trim();
        String[] inputSplitArr = lwrCaseTrimmed.split("\\s+", MIN_ARR_LENGTH);
        if (inputSplitArr.length >= 2) {
            if (inputSplitArr[0].equalsIgnoreCase(ATTACK)) {
                //return Attack.attackType()
            }
        } else if (inputSplitArr[0].equalsIgnoreCase(DISENGAGE)) {
            Adventure.isDual = false;
        } else if (inputSplitArr[0].equalsIgnoreCase(STATUS)) {
            //Dual.jsonInfoDual(command, gameRoomIndex, layout, playerHealth)
        } else if (inputSplitArr[0].equalsIgnoreCase(LIST)) {
            return List.listPlayerItems(layout);
        } else if (inputSplitArr[0].equalsIgnoreCase(PLAYERINFO)) {
            return PlayerInfo.getPlayerInfo();
        } else if (inputSplitArr[0].equalsIgnoreCase(ATTACK)) {

        } else {
            return FALSE_COMMAND;
        }
        return "";
    }

    public static String jsonInfoDual(String command, int roomIndex, Layout layout, double plyrHealth) {
        String playerHealthBar = PlayerInfo.playerHealthBar(PlayerInfo.FULL_HEALTH, plyrHealth);
        Monster[] monstersArr = layout.getMonsters();
        ArrayList<String> monstersNameList = new ArrayList<>();
        for (int mIndex = 0; mIndex < monstersArr.length; mIndex++) {
            monstersNameList.add(monstersArr[mIndex].getName());
        }
        Monster currMonster = Dual.getMonsterInRoom(command, roomIndex, layout);
        double fullMonsterHealth = currMonster.getFullHealth();
        double monsterHealth = currMonster.getHealth();
        String monsterHealthBar = Monster.monsterHealth(fullMonsterHealth, monsterHealth);
        return String.format("You: %s\n%s: %s", playerHealthBar, currMonster.getName(), monsterHealthBar);
    }

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

    public static Monster getMonsterInRoom(String command, int roomIndex, Layout layout) {
        Monster currMonster = new Monster();
        String monsterName = Dual.getMonsterName(command, roomIndex, layout);
        if (layout.getMonsters() != null) {
            Monster[] monstersArr = layout.getMonsters();
            for (int mIndex = 0; mIndex < monstersArr.length; mIndex++) {
                if (monsterName.equalsIgnoreCase(monstersArr[mIndex].getName())) {
                    currMonster = monstersArr[mIndex];
                }
            }
        }
        return currMonster;
    }

    public static void dualMonster(String command, int roomIndex, Layout layout) {
        String[] commandSplit = command.split("\\s+");
        String monsterName = getMonsterName(commandSplit[0], roomIndex, layout);
        if (canDualMonster(command, roomIndex, layout)) {

        }
    }
}
