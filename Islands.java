/*
Tang Wai Mun, Jody Lorah (A0205580L)

store all inputs in an array of size [r][c]
store all indexes of L in an arraylist
call dfs to that index if its not visited

use dfs search to traverse. if I am land, neighbour is c, change it to l
	ammend neighbour method to be +-1 of r and c, 
	provided neighbour 0<= neighbour < r and 0 <= neighbour < c

count components(islands) */

import java.util.*;

public class Islands {
    public static void DFSrec(int r, int c, int[][] visited, char[][] matrix) {
        visited[r][c] = 1;
        int rows = visited.length;
        int cols = visited[0].length;
        if (matrix[r][c] == 'L') {
            if (r - 1 >= 0) {
                if (visited[r-1][c] == 0 && matrix[r - 1][c] != 'W') {
                    matrix[r - 1][c] = 'L';
                    DFSrec(r - 1, c, visited, matrix);
                }
            } if (c - 1 >= 0) {
                if (visited[r][c-1] == 0 && matrix[r][c - 1] != 'W') {
                    matrix[r][c - 1] = 'L';
                    DFSrec(r, c - 1, visited, matrix);
                }
            } if (r + 1 < rows) {
                if (visited[r+1][c] == 0 && matrix[r + 1][c] != 'W') {
                    matrix[r + 1][c] = 'L';
                    DFSrec(r + 1, c, visited, matrix);     
                }
            } if (c + 1 < cols) {
                if (visited[r][c+1] == 0 && matrix[r][c + 1] != 'W') {
                    matrix[r][c + 1] = 'L';
                    DFSrec(r, c + 1, visited, matrix);
                }
            }
        }       

    }

    public static void main (String args[]) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        sc.nextLine();

        char[][] matrix = new char[rows][cols];
        int[][] visited = new int[rows][cols];
        ArrayList<int[]> lands = new ArrayList<int[]>();

        for (int i = 0; i < rows; i++) {
            matrix[i] = sc.nextLine().toCharArray();
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visited[i][j] = 0;
                if (matrix[i][j] == 'L') {
                    int[] temp = new int[2];
                    temp[0] = i;
                    temp[1] = j;
                    lands.add(temp);
                }
            }
        }

        for (int i = 0; i < lands.size(); i++) {
            int[] temp = lands.get(i);
            DFSrec(temp[0], temp[1], visited, matrix);
        }

        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visited[i][j] = 0;
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (visited[i][j] == 0 && matrix[i][j] == 'L') {
                    counter++;
                    DFSrec(i, j, visited, matrix);
                } else if (matrix[i][j] == 'W' || matrix[i][j] == 'C') {
                    visited[i][j] = 1;
                }
            }
        }

        System.out.println(counter);

    }
}