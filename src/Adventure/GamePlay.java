package Adventure;

public class GamePlay {
    public static final String GO = "go";
    public static final String TAKE_OR_DROP = "takeOrDrop";
    public static final String LIST = "list";
    public static final String FALSE_COMMAND = "I don't understand";
    private static final int MIN_STR_LENGTH = 4;

    public static String userInputCommand(String userInput) {
        if (userInput.length() >= MIN_STR_LENGTH) {
            if (userInput.substring(0, 2).equalsIgnoreCase("go")) {
                return GO;
            } else if (userInput.substring(0, 4).equalsIgnoreCase("take")
                    || userInput.substring(0, 4).equalsIgnoreCase("drop")) {
                return TAKE_OR_DROP;
            } else if (userInput.substring(0, 4).equalsIgnoreCase("list")) {
                return LIST;
            }
        }
        return FALSE_COMMAND;
    }
}
