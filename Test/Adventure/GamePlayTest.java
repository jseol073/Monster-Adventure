package Adventure;

import org.junit.Test;

import static org.junit.Assert.*;

public class GamePlayTest {

    @Test
    public void userInputCommandTest() {
        String userInput = "take something";
        String output = "takeOrDrop";
        assertEquals(output, GamePlay.userInputCommand(userInput));
    }

    @Test
    public void userInputCommandShortTest() {
        String userInput = "ta";
        String output = "I don't understand";
        assertEquals(output, GamePlay.userInputCommand(userInput));
    }

    @Test
    public void userInputCommandFalseArgTest() {
        String userInput = "test test";
        String output = "I don't understand";
        assertEquals(output, GamePlay.userInputCommand(userInput));
    }

    @Test
    public void userInputCommandEdgeCaseTest() {
        String userInput = "test";
        String output = "I don't understand";
        assertEquals(output, GamePlay.userInputCommand(userInput));
    }
}