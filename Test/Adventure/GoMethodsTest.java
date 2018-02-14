package Adventure;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GoMethodsTest {
    private Layout layout = new Layout();
    private String userInput;
    private int roomIndex;

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        String jsonContent = Adventure.getFileContentsAsString("siebel.json");
        layout = gson.fromJson(jsonContent, Layout.class);
    }

    @Test
    public void goingToNextRoomTest() {
        String command = "north";
        roomIndex = 0;
        String output = "";
        assertEquals(output, GoMethods.goingToNextRoom(command, roomIndex, layout));
    }

    @Test
    public void getDirectionNameTest() {
        String command = "east";
        roomIndex = 0;
        String output = "East";
        assertEquals(output, GoMethods.getDirectionName(command, roomIndex, layout));
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
    public void getDirectionNamesAsListTest() {
        roomIndex = 0;
        String[] directionNameArrr = {"East"};
        List<String> directionNameAsList = new ArrayList<>(Arrays.asList(directionNameArrr));
        assertEquals(directionNameAsList, GoMethods.getDirectionNamesAsList(roomIndex, layout));

    }

    @Test
    public void getDirectionNamesAsListDiffIndexTest() {
        roomIndex = 5;
        String[] directionNameArrr = {"West", "South", "Down"};
        List<String> directionNameAsList = new ArrayList<>(Arrays.asList(directionNameArrr));
        assertEquals(directionNameAsList, GoMethods.getDirectionNamesAsList(roomIndex, layout));

    }
}