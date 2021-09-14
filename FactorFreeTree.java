/* 
Tang Wai Mun, Jody Lorah (A0205580L) collaborated with Md Juhaer Adittya Pasha

Store all inputs in an array
Store all PF of all inputs in an arraylist of array
Traverse the arraylist from left to right to input all PF in a hashtable as keys 
with value of index of the first instance of key
Traverse the arraylist again and input all from right to left in another hashtable

implement a root finder method:
traverse down the arraylist of PF. for each index i, check if the PFs in the array
of the index have the same value in both hashtables. if all PFs have the same value,
it is coprime and can be a root. 

in main method:
have an empty array, and update it with the level everytime a root is found. 
do so until array.length == originalArr.length
    if it returns -1 (impossible), break and print impossible

*/

import java.util.*;
import java.io.*;

public class FactorFreeTree {

    public static int[] factorize(int value) {
        int limit = (int)(Math.ceil(Math.sqrt(value)));
        int[] temp = new int[10];
        int index = 0;
        while (value % 2 == 0) {
            value /= 2;
            temp[0] = 2;
            index = 1;
        }
        for (int i = 3; i <= limit; i += 2) {
            if (value % i == 0) {
                temp[index] = i;
                index++;
            }
            while (value % i == 0) {
                value /= i;
            }
        }
        if (value != 1) {
            temp[index] = value;
            index++;
        }
        int[] result = new int[index];
        for (int i = 0; i < index; i++) {
            result[i] = temp[i];
        }
        return result;
    }
    public static ArrayList<int[]> convertToFactors(int[] arr) {
        ArrayList<int[]> factors = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            factors.add(factorize(arr[i]));
        }
        return factors;
    }

    
    public static void hashFactors(ArrayList<int[]> factors, int[] hashFromLeft, int[] hashFromRight, int n) {
        Map<Integer, Integer> leftHash = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            hashFromLeft[i] = -1;
            for (int temp : factors.get(i)) {
                if (leftHash.containsKey(temp)) { // if hash does not have key, add
                    hashFromLeft[i] = leftHash.get(temp);
                }
                leftHash.put(temp, i);
            }
        }

        Map<Integer, Integer> hash = new HashMap<Integer, Integer>();
        for (int i = n - 1; i >= 0; i--) {
            hashFromRight[i] = 1000001;
            for (int temp : factors.get(i)) {
                if (hash.containsKey(temp)) { //if hash does not have key, add
                    hashFromRight[i] = hash.get(temp);
                }
                hash.put(temp, i);
            }
        }
    }

    static int[] parents = new int[1000000];
    static boolean flagger = true;
    //method to recursively find various roots
    public static void divide(int start, int end, int parentIndex, 
                                int[] hashFromLeft, int[] hashFromRight) { 
        if (start > end) {
            return ;
        } else if (start == end) {
            parents[start] = parentIndex;
        } else {
            for (int i = 0; i < (end - start + 2) / 2; i++) {
                int j = start + i;
				if (hashFromLeft[j] < start && hashFromRight[j] > end )  {
                    parents[j] = parentIndex;
					divide(start , j - 1, j + 1, hashFromLeft, hashFromRight);
					divide(j + 1, end, j + 1, hashFromLeft, hashFromRight);
					return;
				}
                
                int k = end - i;
				if (hashFromLeft[k] < start && hashFromRight[k] > end)  {
                    parents[k] = parentIndex;
					divide(start, k - 1, k + 1, hashFromLeft, hashFromRight);
					divide(k + 1, end, k + 1, hashFromLeft, hashFromRight);
					return;
				}
			}

			// No split point found here
			flagger = false;
		}
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int n = Integer.parseInt(br.readLine());
        int[] hashFromLeft = new int[n];
        int[] hashFromRight = new int[n];
        int[] arr = new int[n];

        String[] temp = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }

        ArrayList<int[]> arraylistOfFactors = convertToFactors(arr);
        hashFactors(arraylistOfFactors, hashFromLeft, hashFromRight, n);

        divide(0, n - 1, 0, hashFromLeft, hashFromRight);
   
        if (!flagger) {
            pw.println("impossible");
        } else {
            for (int i = 0; i < n; i++) {
                pw.printf("%s ", parents[i]);
            }
        }
        pw.flush();
    }

}