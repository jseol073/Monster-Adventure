package Adventure;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TakeAndDropMethodsTest {
    private static String userInput;
    private static int roomIndex;
    private Layout layout = new Layout();

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        String url = "https://courses.engr.illinois.edu/cs126/adventure/siebel.json";
        // Make an HTTP request to the above URL
        final HttpResponse<String> stringHttpResponse = Unirest.get(url).asString();
        // Check to see if the request was successful; if so, convert the payload JSON into Java objects
        if (stringHttpResponse.getStatus() == 200) {
            String json = stringHttpResponse.getBody();
            layout = gson.fromJson(json, Layout.class);
        }
    }

    @Test
    public void getItemNameTest() {
        userInput = "take coin";
        roomIndex = 0;
        String output = "coin";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput, roomIndex, layout));
    }

    @Test
    public void getItemNameCaseTest() {
        userInput = "take CoIn";
        roomIndex = 0;
        String output = "coin";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput, roomIndex, layout));
    }

    @Test
    public void getItemNameIndexTest() {
        userInput = "take bagel";
        roomIndex = 5;
        String output = "bagel";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput, roomIndex, layout));
    }

    @Test
    public void getItemNameIndexEmptyTest() {
        userInput = "";
        roomIndex = 5;
        String output = "";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput, roomIndex, layout));
    }

    @Test
    public void getItemNameIndexNullTest() {
        userInput = "take bagel";
        roomIndex = 3;
        String output = "";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput, roomIndex, layout));
    }

    @Test
    public void isTakeOrDropTakeTest() {
        userInput = "take bagel";
        String output = "take";
        assertEquals(output, TakeAndDropMethods.isTakeOrDrop(userInput));
    }

    @Test
    public void isTakeOrDropDropTest() {
        userInput = "drop bagel";
        String output = "drop";
        assertEquals(output, TakeAndDropMethods.isTakeOrDrop(userInput));
    }

    @Test
    public void searchAllItemsTest() {
        userInput = "dROp BaGeL";
        roomIndex = 5;
        String output = "bagel";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput, roomIndex, layout));
    }

    @Test
    public void searchAllItemsOtherItemTest() {
        userInput = "take sweatshirt";
        String output = "sweatshirt";
        roomIndex = 1;
        assertEquals(output, TakeAndDropMethods.getItemName(userInput, roomIndex, layout));
    }

    @Test
    public void getItemNameWhiteSpaceTest() {
        userInput = "take s ";
        String output = "";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput, roomIndex, layout));
    }

    @Test
    public void removeItemFromListTakeTest() {
        userInput = "take coin";
        roomIndex = 0;
        boolean output = true;
        assertEquals(output, TakeAndDropMethods.removeItemFromList(userInput, roomIndex, layout));
    }

    @Test
    public void removeItemFromListFalseArgTest() {
        userInput = "take bagel";
        roomIndex = 0;
        boolean output = true;
        assertEquals(output, TakeAndDropMethods.removeItemFromList(userInput, roomIndex, layout));
    }

    @Test
    public void dropToAddItemTest() {
        userInput = "drop bagel";
        roomIndex = 0;
        boolean output = false;
        assertEquals(output, TakeAndDropMethods.removeItemFromList(userInput, roomIndex, layout));
    }
}