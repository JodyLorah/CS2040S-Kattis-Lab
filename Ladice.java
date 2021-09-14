/*Tang Wai Mun, Jody Lorah A0205580L

main idea is to have a ufds linking all related drawers tgt. this means that so long as the linked 
drawers are not all occupied, if a certain specified drawer is taken, the rest can be shifted to accomodate.
each drawer starts off with 0 number of items and has size 1

have a ufds of all the drawers.
ufds object will store all the parents, ranks, size of drawers and the number of items 
in each set in an array. the number of the drawer i is the index of the respective
items in the array
    ufds to have methods findSet, isSameSet, unionSet (adds the size and the number of items in array)

create a ufds of size L
for each of the number of items n (num of lines of instructions), loop through
link both drawers tgt (unionSet both drawers), and find the parent (to compare spaces)
if the number of drawers the parent can take is more than the num of items it has,
    add the item (increment the num items of the parent), print LADICA
else print SMECE */

import java.util.*;
import java.io.*;

class UnionFind {
    public int[] p, rank, size, numOfItems;

    public UnionFind(int N) {
        p = new int[N];
        rank = new int[N];
        size = new int[N];
        numOfItems = new int[N];

        for (int i= 1; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
            size[i] = 1;
            numOfItems[i] = 0;
        }
    }

    public int findSet(int i) {
        if (p[i] == i) {
            return i;
        } else {
            p[i] = findSet(p[i]);
            return p[i];
        }
    }
    
    public Boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            int x = findSet(i), y = findSet(j);
            if (rank[x] > rank[y]) {
                p[y] = x;
                size[x] += size[y];
                numOfItems[x] += numOfItems[y];
            } else {
                p[x] = y;
                size[y] += size[x];
                numOfItems[y] += numOfItems[x];
                if (rank[x] == rank[y]) {
                    rank[y] = rank[y] + 1;
                }
            }
        }
    }
}

public class Ladice {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] temp = new String[2];
        temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int l = Integer.parseInt(temp[1]);
        UnionFind ufds = new UnionFind(l + 1);

        for (int i = 0; i < n; i++) {
            String[] t = new String[2];
            t = br.readLine().split(" ");
            int x = Integer.parseInt(t[0]);
            int y = Integer.parseInt(t[1]);        

            // link both drawers tgt
            ufds.unionSet(x, y);

            int parent = ufds.findSet(x);

            // if parent drawer size can allow for 1 more drawer to be added in, 
            // add in (increment num of items)
            if (ufds.size[parent] <= ufds.numOfItems[parent]) {
                pw.println("SMECE");
            } else {
                pw.println("LADICA");
                ufds.numOfItems[parent]++;
            }
        
        }
        pw.flush();

    }
}