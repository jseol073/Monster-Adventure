package Adventure;

public class Attack {

    public static String attackType(String command, int roomIndex, Layout layout) {
        String[] commandSplit = command.split("\\s+");
        if (commandSplit.length >= 2) {

        }

        return "";
    }

    public static String attack(String command, int roomIndex, Layout layout) {
        Monster currMonster = Dual.getMonsterInRoom(command, roomIndex, layout);
        double plyrDamage = Adventure.playerAttack - currMonster.getDefense();
        double monsterDamage = currMonster.getAttack() + Adventure.player.getDefense();
        currMonster.setHealth(PlayerInfo.FULL_HEALTH - plyrDamage);
        return "";
    }
}
