package Adventure;

import org.junit.Test;

import static org.junit.Assert.*;

public class GamePlayTest {

    @Test
    public void userInputCommandTakeTest() {
        String userInput = "take something";
        String output = "takeOrDrop";
        assertEquals(output, GamePlay.userInputCommand(userInput));
    }

    @Test
    public void userInputCommandWhiteSpaceTest() {
        String userInput = "    drop";
        String output = "takeOrDrop";
        assertEquals(output, GamePlay.userInputCommand(userInput));
    }

    @Test
    public void userInputCommandListTest() {
        String userInput = "  lIst";
        String output = "list";
        assertEquals(output, GamePlay.userInputCommand(userInput));
    }

    @Test
    public void userInputCommandGoTest() {
        String userInput = " go East";
        String output = "go";
        assertEquals(output, GamePlay.userInputCommand(userInput));
    }

    @Test
    public void userInputCommandShortTest() {
        String userInput = "ta";
        String output = "I don't understand";
        assertEquals(output, GamePlay.userInputCommand(userInput));
    }

    @Test
    public void userInputCommandFalseCommandTest() {
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