package Adventure;

import java.util.ArrayList;
import java.util.Arrays;

public class List {
    public static String listPlayerItems() {
        ArrayList<String> plyrItemNameList = new ArrayList<>();
        if (Adventure.playerItemList == null || Adventure.playerItemList.isEmpty()) {
            return "You are carrying nothing";
        }
        for (int i = 0; i < Adventure.playerItemList.size(); i++) {
            plyrItemNameList.add(Adventure.playerItemList.get(i).getName());
        }
        return String.format("You are carrying: %s", plyrItemNameList.toString());
    }
}
