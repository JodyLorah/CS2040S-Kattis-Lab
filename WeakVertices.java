// Tang Wai Mun, Jody Lorah (A0205580L) collaborated with Edeline Tenges and Wong Qin Liang

// have a results array

// loop through all inputs, add all to an adjacency matrix, making all of the vertice index to be the 
// number of neighbours
// if vertex has < 2 neighbours (size < 2), add vertex as 0 to results array
// if vertex has >= 2 neighbours, loop through to check if the neighbours are interconnected

import java.util.*;

public class WeakVertices {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        while (n != -1) {
            // adding to adjacency matrix
            int[][] adjMatrix = new int[n][n];
            int[] results = new int[n];
            for (int i = 0; i < n; i++) {
                int numOfNeighbours = 0;
                String x = sc.nextLine();
                String[] temp = x.split(" ");
                for (int j = 0; j < temp.length; j++) {
                    adjMatrix[i][j] = Integer.parseInt(temp[j]);
                    if (Integer.parseInt(temp[j]) == 1) {
                        numOfNeighbours++;
                    }
                }
                adjMatrix[i][i] = numOfNeighbours; //set the index of the vertice num to be numOfNeighbours
            }
            
            // check through neighbours
            for (int i = 0; i < n; i++) {
                if (adjMatrix[i][i] < 2) { //vertice has less than 2 edges
                    results[i] = 0; //set to weak
                } else {
                    for (int j = i + 1; j < n; j++) {
                        int a = adjMatrix[i][j];
                        if (a == 0) { //if there is no edge, move on
                        } else { //if there is an edge, check with rest of edges
                            for (int k = j + 1; k < n; k++) {
                                int b = adjMatrix[i][k];
                                if (b == 0) {
                                } else {
                                    if (adjMatrix[j][k] == 1) { //there is an edge
                                        results[i] = 2;
                                        results[j] = 2;
                                        results[k] = 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //loop to print results
            for (int i = 0; i < n; i++) {
                if (results[i] == 0) {
                    System.out.printf("%s ", i);
                }
            }
            System.out.println();






            n = Integer.parseInt(sc.nextLine());
        }
    }
}