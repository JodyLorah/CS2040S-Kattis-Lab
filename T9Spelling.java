//Tang Wai Mun, Jody Lorah (A0205580L)

// assign each character to the respective number, based on the keypad.
// read through every line and store it in an array
// loop through for each line
//     - change string to char array
//     - loop through char array, change all to number code
//     - loop through number code array, check if each element contains the next, or the next contains the element. if yes, element + " "
// print out the lines

import java.util.*;

public class T9Spelling {
    public static void main(String[] args) {
        String[] dict = new String[256];
        dict['a'] = "2";
        dict['b'] = "22";
        dict['c'] = "222";
        dict['d'] = "3";
        dict['e'] = "33";
        dict['f'] = "333";
        dict['g'] = "4";
        dict['h'] = "44";
        dict['i'] = "444";
        dict['j'] = "5";
        dict['k'] = "55";
        dict['l'] = "555";
        dict['m'] = "6";
        dict['n'] = "66";
        dict['o'] = "666";
        dict['p'] = "7";
        dict['q'] = "77";
        dict['r'] = "777";
        dict['s'] = "7777";
        dict['t'] = "8";
        dict['u'] = "88";
        dict['v'] = "888";
        dict['w'] = "9";
        dict['x'] = "99";
        dict['y'] = "999";
        dict['z'] = "9999";
        dict[' '] = "0";

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String input[] = new String[n];

        for (int i = 0; i < n; i++) {
            input[i] = sc.nextLine();
        }

        for (int t = 0; t < n; t++) {
            char[] c = input[t].toCharArray();
            System.out.println(Arrays.toString(c));
            
            String str[] = new String[c.length];
            for (int j = 0; j < c.length; j++) { //changing all chars to num
                str[j] = dict[c[j]];
            }
            for (int k = 0; k < str.length - 1; k++) { //to check if any consecutive nums
                if (str[k].contains(str[k + 1]) || str[k + 1].contains(str[k])) {
                    str[k] += " ";
                }
            }

            StringBuilder sb = new StringBuilder(); // empty StringBuilder
            for (int i = 0; i < str.length; i++) {
                sb.append(str[i]);
            }

            String s = sb.toString();

            
            System.out.printf("Case #%d: %s \n", t + 1, s);
        }
    }
}
   