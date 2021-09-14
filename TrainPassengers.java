// Tang Wai Mun, Jody Lorah A0205580L

// scan all data and put it into an array. it would be an array of array of 3 ints.
// have a flagger initialised to true

// check arr[0][0] == 0 (train starts journey empty). if no, flagger = false
// check arr[num of stations - 1][3] == 0. if not, flagger = false, break
// if yes, loop through to see if total boarded (index 1) and total alighted (index 0) 
//         are equal at the end of the loop. if no, flagger = false, break

// loop through and check:
// 1) net ppl who boarded at this stop (boarded - alighted) < totalCap 
// 2) number boarded - number left > 0, number waiting == 0


// if flagger is true, print possible
// else print impossible

import java.util.*;

public class TrainPassengers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalCap = sc.nextInt();
        int numOfStations = sc.nextInt();
        sc.nextLine();
        
        int[][] data = new int[numOfStations][3];

        for (int i = 0; i < numOfStations; i++) { //input all data into double array
            for (int j = 0; j < 3; j++) {
                data[i][j] = sc.nextInt();
            }
        }

        int finalTotalBoarded = 0;
        int finalTotalAlighted = 0;
        for (int i = 0; i < numOfStations; i++) {
            finalTotalAlighted += data[i][0];
            finalTotalBoarded += data[i][1];
        }

        boolean flagger = true;
        int boarded = 0;
        int alighted = 0;

        if (data[0][0] != 0) { //train does not start empty
            flagger = false;
        } else if (data[numOfStations - 1][2] != 0) { //someone waiting at the last station
            flagger = false;
        } else if (finalTotalAlighted != finalTotalBoarded) {
            flagger = false;
        } else {
            for (int i = 0; i < numOfStations; i++) {
                boarded += data[i][1];
                alighted += data[i][0];

                if (boarded - alighted > totalCap) { //net ppl who boarded at this stop (boarded - alighted) > totalCap, flagger false
                    flagger = false;
                } else if ((boarded - alighted < totalCap) && (data[i][2] != 0)) { //can take more ppl and there are ppl waiting
                    flagger = false;
                }
            }
        }

        if (flagger) {
            System.out.println("possible");
        } else {
            System.out.println("impossible");

        }
    }
}