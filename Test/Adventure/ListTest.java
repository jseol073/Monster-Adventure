package Adventure;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {
    private Layout layout = new Layout();

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        String jsonContent = Adventure.getFileContentsAsString("siebel.json");
        layout = gson.fromJson(jsonContent, Layout.class);
    }

    @Test
    public void listPlayerItemsNullTest() {
        layout.getPlayer().setItems(null);
        String output = "You are carrying nothing";
        assertEquals(output, List.listPlayerItems());
    }
}