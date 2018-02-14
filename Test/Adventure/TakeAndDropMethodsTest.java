package Adventure;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TakeAndDropMethodsTest {
    private static String command;
    private static int roomIndex;
    private Layout layout = new Layout();

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        String jsonContent = Adventure.getFileContentsAsString("siebel.json");
        layout = gson.fromJson(jsonContent, Layout.class);
//        Gson gson = new Gson();
//        String url = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
//        // Make an HTTP request to the above URL
//        final HttpResponse<String> stringHttpResponse = Unirest.get(url).asString();
//        // Check to see if the request was successful; if so, convert the payload JSON into Java objects
//        if (stringHttpResponse.getStatus() == 200) {
//            String json = stringHttpResponse.getBody();
//            layout = gson.fromJson(json, Layout.class);
//        }
    }

    @Test
    public void getItemTest() {
        command = "pepper spray";
        roomIndex = 1;
        Item output = layout.getRooms()[1].getItems()[0];
        assertEquals(output, TakeAndDropMethods.getItem(command, roomIndex, layout));
    }

    @Test
    public void getItemOtherItemTest() {
        command = "gun";
        roomIndex = 2;
        Item output = layout.getRooms()[2].getItems()[0];
        assertEquals(output, TakeAndDropMethods.getItem(command, roomIndex, layout));
    }

    @Test
    public void getItemNullTest() {
        command = " ";
        roomIndex = 1;
        Item output = null;
        assertEquals(output, TakeAndDropMethods.getItem(command, roomIndex, layout));
    }

    @Test
    public void removeItemFromListTakeTest() {
        roomIndex = 0;
        Item item = layout.getRooms()[roomIndex].getItems()[0];
        boolean output = true;
        assertEquals(output, TakeAndDropMethods.removeItemFromList(item, roomIndex, layout));
    }

    @Test
    public void removeItemFromListFalseCommandTest() {
        roomIndex = 3;
        Item item = layout.getRooms()[roomIndex].getItems()[0];
        boolean output = true;
        assertEquals(output, TakeAndDropMethods.removeItemFromList(item, roomIndex, layout));
    }

    @Test
    public void dropToAddItemTest() {
        roomIndex = 0;
        Item item = layout.getRooms()[roomIndex].getItems()[0];
        boolean output = true;
        assertEquals(output, TakeAndDropMethods.removeItemFromList(item, roomIndex, layout));
    }
}