package Adventure;

import java.util.ArrayList;
import java.util.Arrays;

public class List {
    public static String listPlayerItems(Layout layout) {
        ArrayList<String> plyrItemNameList = new ArrayList<>();
        if (Adventure.playerItemList == null || Adventure.playerItemList.isEmpty()) {
            return "You are carrying nothing";
        }
        for (int i = 0; i < Adventure.playerItemList.size(); i++) {
            plyrItemNameList.add(Adventure.playerItemList.get(i).getName());
        }
        String output = String.format("You are carrying: %s", plyrItemNameList.toString());
        return output;
    }
}
