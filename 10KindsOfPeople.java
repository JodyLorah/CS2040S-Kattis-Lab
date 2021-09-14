/*
Tang Wai Mun, Jody Lorah (A0205580L)

store the matrix in an int[r][c] 2d matrix

traverse through the map using dfs, and set assign all possible neighbours
a group number

for each query,
check if r1c1 and r2c2 have the same group num. if dont, return neither
if they have the same value, return decimal if it is 1 and binary if it is 0

clear out visited array and dfs again
*/

import java.io.*;

public class 10KindsOfPeople {
    public static void DFSrec(int r, int c, int groupNum, int val, int[][] visited, int[][] matrix) {
        int rows = visited.length;
        int cols = visited[0].length;
        
        if (visited[r][c] <= 1){ //if unvisited
            visited[r][c] = groupNum;

            if (matrix[r][c] == val) {
                if (r - 1 >= 1) {
                    if (matrix[r - 1][c] == val) {
                        DFSrec(r - 1, c, groupNum, val, visited, matrix);
                        
                    }
                } if (c - 1 >= 1) {
                    if (matrix[r][c - 1] == val) {
                        DFSrec(r, c - 1, groupNum, val, visited, matrix);

                    }
                } if (r + 1 < rows) {
                    if (matrix[r + 1][c] == val) {
                        DFSrec(r + 1, c, groupNum, val, visited, matrix);     

                    }
                } if (c + 1 < cols) {
                    if (matrix[r][c + 1] == val) {
                        DFSrec(r, c + 1, groupNum, val, visited, matrix);

                    }
                }
            }     
        }
    }

    public static void main (String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] temp = br.readLine().split(" ");        
        int rows = Integer.parseInt(temp[0]);
        int cols = Integer.parseInt(temp[1]);
        int[][] matrix = new int[rows + 1][cols + 1];
        int[][] visited = new int[rows + 1][cols + 1];


        for (int i = 1; i < rows + 1; i++) {
            String x = br.readLine(); 
            char[] t = x.toCharArray();
            for (int j = 1; j < cols + 1; j++) {
                matrix[i][j] = Character.getNumericValue(t[j-1]);
            }
        }

        int groupNum = 2;

        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < cols + 1; j++) {
                if (visited[i][j] <= 1) {
                    DFSrec(i, j, groupNum, matrix[i][j], visited, matrix);
                } 
                groupNum++;
            }
        }

        int n = Integer.parseInt(br.readLine());

        for (int t = 0; t < n; t++) {
            temp = br.readLine().split(" ");
            int r1 = Integer.parseInt(temp[0]);
            int c1 = Integer.parseInt(temp[1]);
            int r2 = Integer.parseInt(temp[2]);
            int c2 = Integer.parseInt(temp[3]);

            if (visited[r1][c1] != visited[r2][c2]) {
                pw.println("neither");
            } else {
                if (matrix[r1][c1] == 1) {
                    pw.println("decimal");
                } else {
                    pw.println("binary");
                }
            }
        }

        pw.flush();
    }
}