package linkedin;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class LinkCleaner {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("result.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("result_CLEAN.txt", true));

        HashMap<String, String> linkMap = new HashMap<String, String>();
        while (true) {
            String LINK = reader.readLine();
            if (LINK == null) break;
            String ID = getID(LINK);
            linkMap.put(ID, LINK);

        }
        Set<String> keys = linkMap.keySet();
        for (String key : keys) {
            writer.write(linkMap.get(key));
            writer.newLine();
        }
        writer.close();
        writer.close();
        System.out.println("done");

    }

    public static String getID(String input) {
        int addition = "view/".length();
        int first = input.indexOf("view/") + addition;
        int last = input.indexOf("/", first + addition);
        return input.substring(first, last);
    }
}
