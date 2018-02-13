package Adventure;

import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Adventure {

    private static final int STATUS_OK = 200;
    public static final String QUIT = "QUIT";
    public static final String EXIT = "EXIT";
    public static int gameRoomIndex;
    public static Player player = new Player();
    public static ArrayList<Item> playerItemList = new ArrayList<>();
    public static int playerLevel;
    public static double playerAttack;
    public static double playerDefense;
    public static double playerHealth;
    public static boolean isDual = false;
    public static String command;
    public static boolean isEndGame = false;

    public static String getFileContentsAsString(String filename) {
        // Java uses Paths as an operating system-independent specification of the location of files.
        // In this case, we're looking for files that are in a directory called 'data' located in the
        // root directory of the project, which is the 'current working directory'.
        final Path path = FileSystems.getDefault().getPath("data", filename);
        try {
            // Read all of the bytes out of the file specified by 'path' and then convert those bytes
            // into a Java String.  Because this operation can fail if the file doesn't exist, we
            // include this in a try/catch block
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            // Since we couldn't find the file, there is no point in trying to continue.  Let the
            // user know what happened and exit the run of the program.  Note: we're only exiting
            // in this way because we haven't talked about exceptions and throwing them in CS 126 yet.
            System.out.println("Couldn't find file: " + filename);
            System.exit(-1);
            return null;  // note that this return will never execute, but Java wants it there.
        }
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        String jsonContent;
        if (args.length <= 0) {
            jsonContent = getFileContentsAsString("siebel.json");

        } else {
            jsonContent = getFileContentsAsString(args[0]);
        }
        Layout layout = gson.fromJson(jsonContent, Layout.class);
        gameRoomIndex = GamePlay.getStartingRoom(layout);
        player = layout.getPlayer();

        if (player.getFullHealth() == null) {
            double tempFullHealth = player.getHealth();
            player.setFullHealth(tempFullHealth);
        }
        // this is a 'for each' loop; they are useful when you want to do something to
        // every element of a collection and you don't care about the index of the element
        for (String arg : args) {
            System.out.print("\"" + arg + "\" ");
        }


        Scanner sc = new Scanner(System.in);
        System.out.println("Press any key and enter to play");
        while (!isEndGame && sc.hasNextLine() &&  (!(sc.nextLine().equalsIgnoreCase(QUIT))
            || !(sc.nextLine().equalsIgnoreCase(EXIT)))) {
            playerLevel = player.getLevel();
            playerDefense = player.getDefense();
            playerHealth = player.getHealth();
            playerAttack = player.getAttack();
            if (!isDual) {
                System.out.println(GamePlay.jsonInfo(gameRoomIndex, layout));
                String userInput = sc.nextLine();
                System.out.println(GamePlay.handleUserInput(userInput, gameRoomIndex, layout));
            } else {
                System.out.println(Dual.jsonInfoDual(command, gameRoomIndex, layout, playerHealth));
                String userInput = sc.nextLine();
                System.out.println(Dual.handleUserInputDual(userInput, gameRoomIndex, layout));
            }
        }
        sc.close();
    }

}

