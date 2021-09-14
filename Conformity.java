// Tang Wai Mun, Jody Lorah (A0205580L)
// Collaborated with Edeline Tenges and Wong Qin Liang

// key: sorted combi in string
// value: number of occurences of the combi

// For each value of key, check through hashmap
//  if key already has a value, value ++
//  else store it into hash map starting with 1

// initiate int max, repeatCounter
// loop through again to get highest value stored.
//  store the highest value as max if value < max
//  if value = max, increment repeatCounter
    
import java.util.*;

public class Conformity {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<>();
        int n = sc.nextInt();
        

        for (int i = 0; i < n; i++) {
            int[] temp = new int[5];
            for (int j = 0; j < 5; j++) { //store all inputs in array
                temp[j] = sc.nextInt();
            }
            Arrays.sort(temp);
            String key = Arrays.toString(temp);

            if (!map.containsKey(key)) { //if this map does not have key value yet
                map.put(key, 1);
            } else if (map.containsKey(key)){ //map has value, increment by 1
                int v = map.get(key);
                v++;
                map.put(key, v);
            } 
        }

        Collection<Integer> c = map.values();
       
        //for loop to count most popular course
        int max = 0;
        int numOccurences = 0;

        for (Integer i : c) {
            if (i > max) {
                max = i;
                numOccurences = 1;
            } else if (i == max) {
                numOccurences++;
            }
        }

        //print answer
        if (numOccurences == 1) {
            System.out.println(max);
        } else {
            System.out.println(numOccurences * max);
        }
    }
}