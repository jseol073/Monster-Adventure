package Adventure;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;

public class Dual {
    private static final int MIN_ARR_LENGTH = 3;
    private static final String ATTACK = "attack";
    private static final String ATTACK_WITH_ITEM = "attack with item";
    private static final String DISENGAGE = "disengage";
    private static final String STATUS = "status";
    private static final String LIST = "list";
    private static final String PLAYERINFO = "playerInfo";
    private static final String WITH = "with";
    public static final String FALSE_COMMAND = "I don't understand";

    /**
     * During a duel, this method handles the input of the user
     * @param userInput, the input of the user
     * @param roomIndex, the index of the room where the player currently is
     * @param layout
     * @return
     */
    public static String handleUserInputDual(String userInput, int roomIndex, Layout layout) {
        String userInputLwrCase = userInput.toLowerCase();
        String lwrCaseTrimmed = userInputLwrCase.trim();
        String[] inputSplitArr = lwrCaseTrimmed.split("\\s+", MIN_ARR_LENGTH);
        if (inputSplitArr.length >= MIN_ARR_LENGTH) {
            if (inputSplitArr[0].equalsIgnoreCase(ATTACK)
                    && inputSplitArr[1].equalsIgnoreCase(WITH)) {
                return Attack.attackWithItem(inputSplitArr[2], roomIndex, layout);
            }
        } else if (inputSplitArr.length == 1) {
            if (inputSplitArr[0].equalsIgnoreCase(DISENGAGE)) {
                Adventure.isDual = false;
            } else if (inputSplitArr[0].equalsIgnoreCase(STATUS)) {
                return Dual.jsonInfoDual(Adventure.command, Adventure.gameRoomIndex, layout, Adventure.playerHealth);
            } else if (inputSplitArr[0].equalsIgnoreCase(LIST)) {
                return List.listPlayerItems();
            } else if (inputSplitArr[0].equalsIgnoreCase(PLAYERINFO)) {
                return PlayerInfo.getPlayerInfo();
            } else if (inputSplitArr[0].equalsIgnoreCase(ATTACK)) {
                return Attack.attack(Adventure.command, roomIndex, layout);
            } else if (inputSplitArr[0].equalsIgnoreCase(Adventure.QUIT)
                || inputSplitArr[0].equalsIgnoreCase(Adventure.EXIT)) {
                Adventure.isEndGame = true;
            }
        } else {
            return FALSE_COMMAND;
        }
        return "";
    }

    /**
     * During a duel, after each iteration, this method prints the monster's and player's health
     * @param maybeMonster, possible monster's name
     * @param roomIndex
     * @param layout
     * @param plyrHealth
     * @return
     */
    public static String jsonInfoDual(String maybeMonster, int roomIndex, Layout layout, double plyrHealth) {
        String playerHealthBar = PlayerInfo.playerHealthBar(Adventure.player.getFullHealth(), plyrHealth);
        Monster currMonster = Dual.getMonsterInRoom(maybeMonster, roomIndex, layout);
        double monsterHealth = currMonster.getHealth();
        String monsterHealthBar = currMonster.monsterGetHealthBar(monsterHealth);
        return String.format("You: %s\n%s: %s", playerHealthBar, currMonster.getName(), monsterHealthBar);
    }

    public static boolean canDualMonster(String maybeMonsterName, int roomIndex, Layout layout) {
        Monster[] monsterArr = layout.getMonsters();
        String monsterName = getMonsterName(maybeMonsterName, roomIndex, layout);
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

    /**
     * Helper method for getMonsterinRoom, canDuelMonster, attack, and attackWithItem
     * @param maybeMonster
     * @param roomIndex
     * @param layout
     * @return the string of the monster's name in the room the user is in
     */
    public static String getMonsterName(String maybeMonster, int roomIndex, Layout layout) {
        String monsterName = "";
        if (layout.getRooms()[roomIndex].getMonstersInRoom() != null) {
            String[] monstersInCurrRoom = layout.getRooms()[roomIndex].getMonstersInRoom();
            if (monstersInCurrRoom.length != 0) {
                for (int i = 0; i < monstersInCurrRoom.length; i++) {
                    if (maybeMonster.equalsIgnoreCase(monstersInCurrRoom[i])) {
                        monsterName = monstersInCurrRoom[i];
                    }
                }
            }
        }
        return monsterName;
    }

    /**
     * Checks if user's input of a possible monster name is referred to a monster in the same room as the player's
     * Also sets the monsterFullHealth variable in the monster class. This variable is the health
     * of the monster before the game
     * @param maybeMonster
     * @param roomIndex
     * @param layout
     * @return Monster object
     */
    public static Monster getMonsterInRoom(String maybeMonster, int roomIndex, Layout layout) {
        Monster currMonster = new Monster();
        String monsterName = getMonsterName(maybeMonster, roomIndex, layout);
        Monster[] monstersArr = layout.getMonsters();
        for (int mIndex = 0; mIndex < monstersArr.length; mIndex++) {
            if (monsterName.equalsIgnoreCase(monstersArr[mIndex].getName())) {
                currMonster = monstersArr[mIndex];
                //sets fullHealth of current monster:
                if (currMonster.getFullHealth() == null) {
                    double tempMHealth = currMonster.getHealth();
                    currMonster.setFullHealth(tempMHealth);
                }
            }
        }
        return currMonster;
    }
}
