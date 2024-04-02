package github.otowave.search;

import java.util.ArrayList;

public class SearchHandler {
    static String formatArrayList(ArrayList<Integer> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i));
                sb.append(", ");
            }

            sb.deleteCharAt(sb.lastIndexOf(", "));
        }
        return sb.toString();
    }
}
