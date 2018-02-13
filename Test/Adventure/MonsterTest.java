package Adventure;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonsterTest {
    private Layout layout = new Layout();

    @Before
    public void setUp() throws Exception {
        Gson gson = new Gson();
        String jsonContent = Adventure.getFileContentsAsString("siebel.json");
        layout = gson.fromJson(jsonContent, Layout.class);
    }

    @Test
    public void monsterHealthTest() {
        Monster currMonster = Dual.getMonsterInRoom("lion", 1, layout);
        double currHealth = 5;
        String output = "#####-----";
        assertEquals(output, currMonster.monsterGetHealthBar(currHealth));
    }
}