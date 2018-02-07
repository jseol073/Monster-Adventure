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
        String output = "coin";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput));
    }

    @Test
    public void getItemNameCaseTest() {
        userInput = "take CoIn";
        String output = "coin";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput));
    }

    @Test
    public void getItemNameIndexTest() {
        userInput = "take bagel";
        String output = "bagel";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput));
    }

    @Test
    public void getItemNameIndexEmptyTest() {
        userInput = "";
        String output = "";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput));
    }

    @Test
    public void isTakeTest() {
        userInput = "take bagel";
        assertEquals(true, TakeAndDropMethods.isTake(userInput));
    }

    @Test
    public void isTakeDropTest() {
        userInput = "drop bagel";
        assertEquals(false, TakeAndDropMethods.isTake(userInput));
    }

    @Test
    public void getItemNameOtherItemTest() {
        userInput = "take sweatshirt";
        String output = "sweatshirt";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput));
    }

    @Test
    public void getItemNameWhiteSpaceTest() {
        userInput = "take s ";
        String output = "";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput));
    }

    @Test
    public void getItemNameDropTest() {
        userInput = "drop s ";
        String output = "";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput));
    }

    @Test
    public void getItemNameTwoWordTest() {
        userInput = "take USB-C connector";
        String output = "USB-C connector";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput));
    }

    @Test
    public void getItemNameTwoWordSpaceTest() {
        userInput = "take    USB-C connector       ";
        String output = "USB-C connector";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput));
    }

    @Test
    public void getItemNameTwoWordCaseTest() {
        userInput = "take    USB-C cOnnEctor       ";
        String output = "USB-C connector";
        assertEquals(output, TakeAndDropMethods.getItemName(userInput));
    }

    @Test
    public void removeItemFromListTakeTest() {
        userInput = "take coin";
        roomIndex = 0;
        boolean output = true;
        assertEquals(output, TakeAndDropMethods.removeItemFromList(userInput, roomIndex, layout));
    }

    @Test
    public void removeItemFromListTakeTrueTest() {
        userInput = "drop pizza";
        roomIndex = 0;
        boolean output = false;
        assertEquals(output, TakeAndDropMethods.removeItemFromList(userInput, roomIndex, layout));
    }

    @Test
    public void removeItemFromListFalseCommandTest() {
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