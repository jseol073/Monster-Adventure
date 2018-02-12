package Adventure;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Adventure {

    private static final int STATUS_OK = 200;
    private static final String QUIT = "QUIT";
    private static final String EXIT = "EXIT";
    public static int gameRoomIndex;
    public static Player player = new Player();
    public static ArrayList<Item> playerItemList = new ArrayList<>();
    public static int playerLevel;
    public static double playerAttack;
    public static double playerDefense;
    public static double playerHealth;
    public static boolean isDual = false;
    public static double monsterHealth;

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
        String jsonContent = getFileContentsAsString("siebel.json");
        Layout layout = new Layout();
        layout = gson.fromJson(jsonContent, Layout.class);
        System.out.println(layout.toString());
        gameRoomIndex = GamePlay.getStartingRoom(layout);
        player = layout.getPlayer();

        // this is a 'for each' loop; they are useful when you want to do something to
        // every element of a collection and you don't care about the index of the element
        for (String arg : args) {
            System.out.print("\"" + arg + "\" ");
        }

//        if (args.length <= 0) {
//            System.out.println("File is too small");
//        }
        //Catching exceptions regarding the url
//        String url = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
//        try {
//            layout = makeApiRequest(url);
//        } catch (UnirestException e) {
//            System.out.println("Network not responding");
//        } catch (MalformedURLException e) {
//            System.out.println("Bad URL: " + url);
//        }

        // GAME FUNCTIONS:
        //ArrayList<Item> usersItems = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Press any key to play");
        while (sc.hasNextLine() && (!(sc.nextLine().equalsIgnoreCase(QUIT))
            || !(sc.nextLine().equalsIgnoreCase(EXIT)))) {
            if (!isDual) {
                System.out.println(GamePlay.jsonInfo(gameRoomIndex, layout));
                String userInput = sc.nextLine();
                System.out.println(GamePlay.handleUserInput(userInput, gameRoomIndex, layout));
            }


        }
        sc.close();
    }

    /**
     *
     * @param url
     * @return Layout object through gson and unirest
     * @throws UnirestException
     * @throws MalformedURLException
     */
//    public static Layout makeApiRequest(String url) throws UnirestException, MalformedURLException {
//        final HttpResponse<String> stringHttpResponse;
//        Layout layout = new Layout();
//        // This will throw MalformedURLException if the url is malformed.
//        new URL(url);
//        stringHttpResponse = Unirest.get(url).asString();
//        // Check to see if the request was successful; if so, convert the payload JSON into Java objects
//        if (stringHttpResponse.getStatus() == STATUS_OK) {
//            String json = stringHttpResponse.getBody();
//            Gson gson = new Gson();
//            layout = gson.fromJson(json, Layout.class);
//        }
//        return layout;
//    }
}

