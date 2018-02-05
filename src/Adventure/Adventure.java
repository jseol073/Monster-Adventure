package Adventure;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.net.URL;

public class Adventure {

    private static final int STATUS_OK = 200;
    private static final String QUIT = "QUIT";

    public static void main(String[] args) {
        Layout layout = new Layout();
        int gameIndex = 0;
        // this is a 'for each' loop; they are useful when you want to do something to
        // every element of a collection and you don't care about the index of the element
        for (String arg : args) {
            System.out.print("\"" + arg + "\" ");
        }
        String url = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
        try {
            layout = makeApiRequest(url);
        } catch (UnirestException e) {
            System.out.println("Network not responding");
        } catch (MalformedURLException e) {
            System.out.println("Bad URL: " + url);
        }
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        System.out.println("Press any key to play");
        while (loop && sc.hasNextLine()) {
            if (sc.nextLine().equalsIgnoreCase(QUIT)) {
                break;
            }
            System.out.println("You are on " + layout.getRooms()[gameIndex].getName());
            if (layout.getRooms()[gameIndex].getName().equals(layout.getStartingRoom())) {
                System.out.println("Your journey begins here.");
            }
            if (layout.getRooms()[gameIndex].getName().equals(layout.getEndingRoom())) {
                System.out.println("You have reached the final destination.");
                break;
            }
            System.out.println(layout.getRooms()[gameIndex].getDescription());
            String[] itemArr = layout.getRooms()[gameIndex].getItems();
            System.out.println("This room has these items: " + Arrays.toString(itemArr));
            Directions[] currDirection = layout.getRooms()[gameIndex].getDirections();
            System.out.println("From here you can go: " + getDirections(currDirection, gameIndex, layout).toString());
            String userInput = sc.nextLine();
            /**
             * if go, take, drop, list
             */
        }
        sc.close();
    }

    public static List<String> getDirections(Directions[] directions, int index, Layout layout) {
        directions = layout.getRooms()[index].getDirections();
        ArrayList<String> directionsList = new ArrayList<>();
        for (int i = 0; i < directions.length; i++) {
            directionsList.add(directions[i].getDirectionName());
        }
        return directionsList;
    }

    static Layout makeApiRequest(String url) throws UnirestException, MalformedURLException {
        final HttpResponse<String> stringHttpResponse;
        Layout layout2 = new Layout();
        // This will throw MalformedURLException if the url is malformed.
        new URL(url);
        stringHttpResponse = Unirest.get(url).asString();
        // Check to see if the request was successful; if so, convert the payload JSON into Java objects
        if (stringHttpResponse.getStatus() == STATUS_OK) {
            String json = stringHttpResponse.getBody();
            Gson gson = new Gson();
            layout2 = gson.fromJson(json, Layout.class);
        }
        return layout2;
    }
}

