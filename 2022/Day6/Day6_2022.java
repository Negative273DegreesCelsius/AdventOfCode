package Day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day6_2022 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("2022\\Day6\\Input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        int markerPos = 4;
        int messagePos = 14;
        try {
            String input = br.readLine();
            while (markerPos < input.length()) {
                String subStr = input.substring(markerPos - 4, markerPos);
                boolean duplicate = false;
                for (int i = 0; i < subStr.length() - 1; i++) {
                    for (int j = i + 1; j < subStr.length(); j++) {
                        if (subStr.charAt(i) == subStr.charAt(j)) {
                            duplicate = true;
                            break;
                        }
                    }
                    if (duplicate) break;
                }
                if (!duplicate) break;
                markerPos++;
            }
            while (messagePos < input.length()) {
                String subStr = input.substring(messagePos - 14, messagePos);
                boolean duplicate = false;
                for (int i = 0; i < subStr.length() - 1; i++) {
                    for (int j = i + 1; j < subStr.length(); j++) {
                        if (subStr.charAt(i) == subStr.charAt(j)) {
                            duplicate = true;
                            break;
                        }
                    }
                    if (duplicate) break;
                }
                if (!duplicate) break;
                messagePos++;
            }
            br.close();
        } catch (IOException e) { }
        System.out.println("Marker Position: " + markerPos);
        System.out.println("Start of Message Position: " + messagePos);
    }
}
