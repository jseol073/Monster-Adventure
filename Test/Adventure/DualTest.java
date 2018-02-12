package Adventure;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DualTest {
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
    public void canDualMonsterTest() {
        command = "lion";
        roomIndex = 1;
        boolean output = true;
        assertEquals(output, Dual.canDualMonster(command, roomIndex, layout));
    }

    @Test
    public void canDualMonsterOtherMonsterTest() {
        command = "bear";
        roomIndex = 1;
        boolean output = false;
        assertEquals(output, Dual.canDualMonster(command, roomIndex, layout));
    }

    @Test
    public void getMonsterNameTest() {
        command = "lion";
        roomIndex = 1;
        String output = "lion";
        assertEquals(output, Dual.getMonsterName(command, roomIndex, layout));
    }

    @Test
    public void getMonsterNameCaseTest() {
        command = "liOn";
        roomIndex = 1;
        String output = "lion";
        assertEquals(output, Dual.getMonsterName(command, roomIndex, layout));
    }

    @Test
    public void dualMonster() {
    }
}