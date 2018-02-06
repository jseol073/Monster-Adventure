package Adventure;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.*;

public class GoMethodsTest {
    private Layout layout = new Layout();
    private String userInput;
    private int roomIndex;

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
    public void canGoDirectionFalseArgTest() {
        String aDirection = "feaew";
        roomIndex = 0;
        assertEquals(false, GoMethods.canGoDirection(aDirection, roomIndex, layout));
    }

    @Test
    public void canGoDirectionTrueTest() {
        String aDirection = "east";
        roomIndex = 0;
        assertEquals(true, GoMethods.canGoDirection(aDirection, roomIndex, layout));
    }

    @Test
    public void canGoDirectionFalseDirectionTest() {
        String aDirection = "east";
        roomIndex = 3;
        assertEquals(false, GoMethods.canGoDirection(aDirection, roomIndex, layout));
    }

    @Test
    public void goToNextRoomFirstRoomTest() {
        userInput = "go east";
        roomIndex = 0;
        int output = 1;
        assertEquals(output, GoMethods.goToNextRoom(userInput, roomIndex, layout));
    }

    @Test
    public void goToNextRoomOtherRoomTest() {
        userInput = "go south";
        roomIndex = 5;
        int output = 6;
        assertEquals(output, GoMethods.goToNextRoom(userInput, roomIndex, layout));
    }

    @Test
    public void getADirectionTrueTest() {
        userInput = "go east";
        String output = "east";
        assertEquals(output, GoMethods.getADirection(userInput));
    }

    @Test
    public void getADirectionCapsTest() {
        userInput = "go EaSt";
        String output = "east";
        assertEquals(output, GoMethods.getADirection(userInput));
    }

    @Test //********* do later
    public void getADirectionFalseTest() {
        userInput = "go east";
        String output = "east";
        assertEquals(output, GoMethods.getADirection(userInput));
    }

    @Test
    public void getDirectionNamesAsList() {

    }
}