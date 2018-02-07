package Adventure;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Adventure {

    private static final int STATUS_OK = 200;
    private static final String QUIT = "QUIT";

    public static void main(String[] args) {
        Layout layout = new Layout();
        int gameRoomIndex = 0;
        // this is a 'for each' loop; they are useful when you want to do something to
        // every element of a collection and you don't care about the index of the element
        for (String arg : args) {
            System.out.print("\"" + arg + "\" ");
        }
        //Catching exceptions regarding the url
        String url = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
        try {
            layout = makeApiRequest(url);
        } catch (UnirestException e) {
            System.out.println("Network not responding");
        } catch (MalformedURLException e) {
            System.out.println("Bad URL: " + url);
        }
        // GAME FUNCTIONS:
        ArrayList<String> usersItems = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Press any key to play");
        while (sc.hasNextLine()) {
            if (sc.nextLine().equalsIgnoreCase(QUIT)) {
                break;
            }
            System.out.println("You are on " + layout.getRooms()[gameRoomIndex].getName());
            if (layout.getRooms()[gameRoomIndex].getName().equals(layout.getStartingRoom())) {
                System.out.println("Your journey begins here.");
            }
            System.out.println(layout.getRooms()[gameRoomIndex].getDescription());
            String[] itemArr = layout.getRooms()[gameRoomIndex].getItems();
            ArrayList<String> itemList = new ArrayList<>();
            if (itemArr != null) {
                itemList = new ArrayList<>(Arrays.asList(itemArr));
            }
            if (itemList.isEmpty() || itemList == null) {
                System.out.println("This room does not have any items");
            } else {
                System.out.println("This room has these items: " + itemList.toString());
            }
            System.out.println("From here you can go: "
                    + GoMethods.getDirectionNamesAsList(gameRoomIndex, layout).toString());
            if (layout.getRooms()[gameRoomIndex].getName().equals(layout.getEndingRoom())) {
                System.out.println("You have reached the final destination.");
                break;
            }
            String userInput = sc.nextLine();
            String whichCommand = GamePlay.userInputCommand(userInput);
            if (whichCommand.equals(GamePlay.GO)) {
                gameRoomIndex = GoMethods.goToNextRoom(userInput, gameRoomIndex, layout);
            }  else if (whichCommand.equals(GamePlay.TAKE_OR_DROP)) {
                if (TakeAndDropMethods.isTakeOrDrop(userInput)) {
                    TakeAndDropMethods.removeItemFromList(userInput, gameRoomIndex, layout);
                    System.out.println(TakeAndDropMethods.getItemName(userInput, gameRoomIndex, layout));
                    usersItems.add(TakeAndDropMethods.getItemName(userInput, gameRoomIndex, layout));
                } else if (!(TakeAndDropMethods.isTakeOrDrop(userInput))) {
                    TakeAndDropMethods.dropToAddItemToList(userInput, gameRoomIndex, layout);
                    usersItems.remove(TakeAndDropMethods.getItemName(userInput, gameRoomIndex, layout));
                }
            } else if (whichCommand.equals(GamePlay.LIST)) {
                if (usersItems == null || usersItems.isEmpty()) {
                    System.out.println("You are carrying nothing");
                } else {
                    System.out.println("You are carrying " + usersItems.toString());
                }
                if (itemList.isEmpty() || itemList == null) {
                    System.out.println("This room is empty.");
                } else {
                    System.out.println("This room has these items: " + itemList.toString());
                }
                System.out.println("Press enter again to continue");
            }
        }
        sc.close();
    }

    /**
     *
     * @param url
     * @return Layout object through gson
     * @throws UnirestException
     * @throws MalformedURLException
     */
    public static Layout makeApiRequest(String url) throws UnirestException, MalformedURLException {
        final HttpResponse<String> stringHttpResponse;
        Layout layout = new Layout();
        // This will throw MalformedURLException if the url is malformed.
        new URL(url);
        stringHttpResponse = Unirest.get(url).asString();
        // Check to see if the request was successful; if so, convert the payload JSON into Java objects
        if (stringHttpResponse.getStatus() == STATUS_OK) {
            String json = stringHttpResponse.getBody();
            Gson gson = new Gson();
            layout = gson.fromJson(json, Layout.class);
        }
        return layout;
    }
}

