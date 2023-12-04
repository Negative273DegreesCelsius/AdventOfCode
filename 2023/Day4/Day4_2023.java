package Day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day4_2023 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("2023\\Day4\\Input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int lines = 0;
        try {
            while (reader.readLine() != null) lines++;
            reader.close();
        } catch (IOException e) { }

        BufferedReader br = new BufferedReader(new FileReader(file));

        int sum = 0, sum2 = 0;
        HashMap<Integer, Integer> cardCopies = new HashMap<>();
        try {
            String str;
            while ((str = br.readLine()) != null) {
                ArrayList<Integer> winning = new ArrayList<>();
                ArrayList<Integer> nums = new ArrayList<>();
                int count = 0;

                str = str.trim().replaceAll(" +", " ");
                int currentCard = Integer.parseInt((str.replaceAll(":", "").split(" "))[1]);
                if (cardCopies.containsKey(currentCard))
                    cardCopies.put(currentCard, cardCopies.get(currentCard) + 1);
                else
                    cardCopies.put(currentCard, 1);

                str = str.replaceAll("  ", " ");
                for (String wNum : (str.substring(str.indexOf(":") + 2, str.indexOf(" |"))).split(" ")) {
                    winning.add(Integer.parseInt(wNum));
                }
                for (String num : (str.substring(str.indexOf("|") + 2)).split(" ")) {
                    nums.add(Integer.parseInt(num));
                }
                for (int wNum : winning) {
                    if (nums.contains(wNum)) count++;
                }

                if (count != 0) sum += Math.pow(2, count - 1);
                for (int i = 1; i <= count; i++) {
                    if (cardCopies.containsKey(currentCard + i)) 
                        cardCopies.put(currentCard + i, cardCopies.get(currentCard + i) + cardCopies.get(currentCard));
                    else
                        cardCopies.put(currentCard + i, cardCopies.get(currentCard));
                }
            }
        } catch (IOException e) { }
        for (Map.Entry<Integer, Integer> entry : cardCopies.entrySet()) {
            if (entry.getKey() <= lines) sum2 += entry.getValue();
        }
        System.out.println(sum);
        System.out.println(sum2);
    }
}
