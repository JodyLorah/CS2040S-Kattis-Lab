// Tang Wai Mun, Jody Lorah A0205580L


//create a method to find the fastest 3 runners based on second time from an array

//in main method,
//parse in all inputs into an array of arrays
//for all first timings in the array, add the shortest 3 timings of the remaining array.
//if timing is shortest than currShortest, replace currShortest. store id of this first person.
//else, carry on with the array
//print out everything

import java.util.*;

public class BestRelayTeam {
    public static double[][] shortest3Times(double[][] arr, int size) {
        double[][] temp = new double[3][3];

        for (int i = 0; i < 3; i++) { //store first 3 runners in temp
            temp[i] = arr[i];
        }

        //set first runner to be dummy slowest, then compare with the rest.
        double slowest = temp[0][2];
        int index = 0;
        for (int i = 1; i < 3; i++) {
            if (temp[i][2] > slowest) {
                slowest = temp[i][2];
                index = i;
            }
        }

        //compare each elem from org array to the temp, to get the actual fastest 3 of whole array.
        if (size > 3) { //if array has more than 3 items
            for (int i = 3; i < size; i++) { //for loop to find 3 shortest timings
                if (arr[i][2] < temp[0][2]) { //if first person on list is slowest runner, replace
                    temp[index] = arr[i];
                } else if (arr[i][2] < temp[1][2]) {//if 2nd person on list is slowest runner, replace
                    temp[index] = arr[i];
                } else if (arr[i][2] < temp[2][2]) {//if 3rd person on list is slowest runner, replace
                    temp[index] = arr[i];
                }

                slowest = temp[0][2]; //reset slowest to first item as dummy
                index = 0;
                for (int j = 1; j < 3; j++) {
                    if (temp[j][2] > slowest) { //rerank slowest person
                        slowest = temp[j][2];
                        index = j;
                    }
                }
            }
        } 

        return temp;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numRunners = sc.nextInt();
        sc.nextLine();
        
        double[][] data = new double[numRunners][3];
        String[] names = new String[numRunners];

        for (int i = 0; i < numRunners; i++) { //input all data into array ([id, 1st time, 2nd time])
                names[i] = sc.next();
                data[i][0] = i;
                data[i][1] = sc.nextDouble();
                data[i][2] = sc.nextDouble();
        }

        //loop through to find shortest combi of time possible
        double shortestTime = 0;
        int id = 0;
        double[][] otherRunners = new double[3][3];
        for (int i = 0; i < numRunners; i++) {
            double currTime = data[i][1];
            double[][] removedIndexArray = new double[numRunners - 1][3];
            for (int j = 0; j < numRunners; j++) { //array which stores rest of array
                if (i != j && j < i) {
                    removedIndexArray[j] = data[j];
                } else if (i != j && j > i) {
                    removedIndexArray[j - 1] = data[j];
                }
            }
            double[][] fastestRunners = shortest3Times(removedIndexArray, numRunners - 1);

            //loop to set currTime to total time taken
            for (int k = 0; k < 3; k++) {
                currTime += fastestRunners[k][2];
            }

            if (shortestTime == 0 || currTime < shortestTime) {
                shortestTime = currTime;
                id = i;
                otherRunners = fastestRunners;
            }

        }

        //printing out
        System.out.println(shortestTime);
        System.out.println(names[id]);
        for (int i = 0; i < 3; i++) { //print names of other runners
            System.out.println(names[(int) otherRunners[i][0]]);
        }
    }
}
