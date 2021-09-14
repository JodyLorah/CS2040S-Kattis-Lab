//Tang Wai Mun, Jody Lorah (A0205580L)

import java.util.*;

public class PeaSoupAndPancakes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arrNum = sc.nextInt();
        String[][] everything = new String[arrNum][];

        //adding things to the first array
        int restaurantNum = 0;

        //loop through each input to place it in the array
        while (sc.hasNextInt()) {
            int numOfMenuItems = sc.nextInt();
            everything[restaurantNum] = new String[numOfMenuItems + 1];
            sc.nextLine();
            for (int i = 0; i < numOfMenuItems + 1; i++) {
                String input = sc.nextLine();
                everything[restaurantNum][i] = input;
            }
            restaurantNum++;
        }

        //will change to true if managed to find restaurant with pea soup and pancakes
        boolean flaggerEndOfEverything = false;

        //check through array to see if any match pea soup and pancakes
        for (int j = 0; j < arrNum; j++) {
            boolean flaggerPeaSoup = false;
            boolean flaggerPancakes = false;
            for (int k = 1; k < everything[j].length; k++) {
                if (everything[j][k].equals("pea soup")) {
                    flaggerPeaSoup = true;
                } else if (everything[j][k].equals("pancakes")) {
                    flaggerPancakes = true;
                }
            }
            if (flaggerPeaSoup && flaggerPancakes) {
                System.out.println(everything[j][0]);
                flaggerEndOfEverything = true;
                break;
            } else {
                flaggerPeaSoup = false;
                flaggerPancakes = false;
            }
        }

        if (!flaggerEndOfEverything) {
            System.out.println("Anywhere is fine I guess");
        }
    }


}