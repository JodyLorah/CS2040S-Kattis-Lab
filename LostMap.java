/* Tang Wai Mun, Jody Lorah (A0205580L)
    Collaborated with Edeline Tenges and Wong Qin Liang

    use prim demo and add each integerPair as village i, village j, distance weight
    when connecting the vertex for the first time, print the pair of edges
*/

import java.util.PriorityQueue;
import java.io.*;

public class LostMap {
  public static void main(String[] args) throws Exception {
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    String x = br.readLine();     
    int V = Integer.parseInt(x);   
    PriorityQueue<Verts> pq = new PriorityQueue<Verts>();
    boolean[] taken = new boolean[V];
    int[][] map = new int[V][V];

    for (int i = 0; i < V; i++) {
        String[] temp = br.readLine().split(" ");
        for (int j = 0; j < V; j++) {
            int val = Integer.parseInt(temp[j]);
            map[i][j] = val;
        }
    }

    for (int i = 0; i < V; i++) {
        if (i!= 0 && !taken[i]) {
            pq.add(new Verts(0, i, map[0][i]));
        }
    }

    while (pq.size() != 0) {
        Verts curr = pq.poll();
        if (!taken[curr.village2]) {
            taken[curr.village1] = true;
            taken[curr.village2] = true;
            pw.println(curr);
            pw.flush();

            for (int i = 0; i < V; i++) {
                if (i != curr.village2 && !taken[i]) {
                    pq.add(new Verts(curr.village2, i, map[curr.village2][i]));
                }
            }

        }       
    }
  }
}

class Verts implements Comparable<Verts> {
  public int village1, village2, dist;

  public Verts(int v1, int v2, int w) {
    village1 = v1;
    village2 = v2;
    dist = w;
  }

  @Override
  public String toString() {
      return String.format("%d %d", this.village1 + 1, this.village2 + 1);
  }

  public int compareTo(Verts o) {
    if (this.dist != o.dist)
      return this.dist - o.dist;
    else
      return this.village2 - o.village2;
  }
}
