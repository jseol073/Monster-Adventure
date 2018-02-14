package Adventure;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerInfoTest {
    private Layout layout = new Layout();

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        String jsonContent = Adventure.getFileContentsAsString("siebel.json");
        layout = gson.fromJson(jsonContent, Layout.class);
    }

    @Test
    public void playerHealthBarFullTest() {
        double fullHeath = 20;
        double currHealth = 20;
        String output = "####################";
        assertEquals(output, PlayerInfo.playerHealthBar(fullHeath, currHealth));
    }

    @Test
    public void playerHealthBarNegativeTest() {
        double fullHeath = 20;
        double currHealth = -5;
        String output = "---------------------";
        assertEquals(output, PlayerInfo.playerHealthBar(fullHeath, currHealth));
    }

    @Test
    public void playerHealthBarTest() {
        double fullHeath = 20;
        double currHealth = 5;
        String output = "#####---------------";
        assertEquals(output, PlayerInfo.playerHealthBar(fullHeath, currHealth));
    }
}