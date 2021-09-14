/*
Tang Wai Mun, Jody Lorah (A0205580L)

have an object class coordinates to store the coordinates of start, end and cannons, as well as vertex num
vertex nums 0 to n-1 rep cannons, and the rest are paths the person has to run

edge weight would be distance ran, vertex num is where the person has to run

input all cannons as vertices, start point and end point.
for each cannon, have an edge of the shortest dist btw both using pythagoras theorem. compute the time taken to travel that dist.

once all edges are done, find sssp between first node and final node.
for each distance between cannon and 
*/

import java.lang.Math;

import java.util.*;

class Coordinates {
    public float x,y;

    public Coordinates(float a, float b) {
        this.x = a;
        this.y = b;
    }

    
}

public class HumanCannonballRun {
    public static void floydWarshall(float graph[][]) 
    { 
        int V = graph.length;
        float dist[][] = new float[V][V]; 
        int i, j, k; 
  
        /* Initialize the solution matrix same as input graph matrix. 
           Or we can say the initial values of shortest distances 
           are based on shortest paths considering no intermediate 
           vertex. */
        for (i = 0; i < V; i++) 
            for (j = 0; j < V; j++) 
                dist[i][j] = graph[i][j]; 
  
        /* Add all vertices one by one to the set of intermediate 
           vertices. 
          ---> Before start of an iteration, we have shortest 
               distances between all pairs of vertices such that 
               the shortest distances consider only the vertices in 
               set {0, 1, 2, .. k-1} as intermediate vertices. 
          ----> After the end of an iteration, vertex no. k is added 
                to the set of intermediate vertices and the set 
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < V; k++) 
        { 
            // Pick all vertices as source one by one 
            for (i = 0; i < V; i++) 
            { 
                // Pick all vertices as destination for the 
                // above picked source 
                for (j = 0; j < V; j++) 
                { 
                    // If vertex k is on the shortest path from 
                    // i to j, then update the value of dist[i][j] 
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                } 
            } 
        } 
        System.out.println(dist[0][V-1]);

    }
    public static float timeTaken(float a, float b, float c, float d) {
        float dist = (float) Math.sqrt((c - a)*(c - a) + (d - b)*(d - b));
            return 2 + Math.abs((dist - 50)/5);
    }
    public static void main(String[] args) {
        final float INF = 99999;

        

        Scanner sc = new Scanner(System.in);
        Coordinates start = new Coordinates(sc.nextFloat(), sc.nextFloat()); //0
        Coordinates end = new Coordinates(sc.nextFloat(), sc.nextFloat()); //n + 1
        int n = sc.nextInt();
        Coordinates[] cannons = new Coordinates[n + 2];

        for (int i = 1; i < n + 1; i++) {
            cannons[i] = new Coordinates(sc.nextFloat(), sc.nextFloat());
        }
        cannons[0] = start;
        cannons[n + 1] = end;

        float[][] timingGraph = new float[n + 2][n + 2]; 

        for (int i  = 0; i < n + 2; i++) {
            float c = cannons[i].x;
            float a = cannons[0].x;
            float b = cannons[0].y;
            float d = cannons[i].y;
            float dist = (float) Math.sqrt((c - a)*(c - a) + (d - b)*(d - b));
            timingGraph[0][i] = dist / 5;

        }

        for (int i  = 0; i < n + 2; i++) {
            float c = cannons[i].x;
            float a = cannons[n + 1].x;
            float b = cannons[n + 1].y;
            float d = cannons[i].y;
            float dist = (float) Math.sqrt((c - a)*(c - a) + (d - b)*(d - b));
            timingGraph[n + 1][i] = dist / 5;

        }

        for (int i = 1; i < n + 1; i ++) {
            for (int j = 0; j < n + 2; j++) {
                if (j == i) {
                    timingGraph[i][j] = 0;
                } else if (j == 0 || j == n + 2) {
                    timingGraph[i][j] = timingGraph[j][i];
            
                } else {
                    timingGraph[i][j] = timeTaken(cannons[i].x,cannons[i].y, cannons[j].x, cannons[j].y);
                }
            }
        }


        floydWarshall(timingGraph);

       

    }
}