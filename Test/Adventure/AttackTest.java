package Adventure;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AttackTest {
    private Layout layout = new Layout();
    private String command;
    private int roomIndex;

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        String jsonContent = Adventure.getFileContentsAsString("siebel.json");
        layout = gson.fromJson(jsonContent, Layout.class);
    }

    @Test
    public void expForHigherLevelsTest() {
        int playerLevel = 3;
        double output = 75;
        assertEquals(output, Attack.expForHigherLevels(playerLevel), 0.01);
    }

    @Test
    public void expForHigherLevelsOtherTest() {
        int playerLevel = 4;
        double output = 125;
        assertEquals(output, Attack.expForHigherLevels(playerLevel), 0.01);
    }
}