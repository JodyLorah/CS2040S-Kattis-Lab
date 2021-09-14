/*
Tang Wai Mun, Jody Lorah (A0205580L) collaborated with Wong Qin Liang and Edeline Tenges

create a node object which has a key (E) and stores a value (G) and an id.
create a set id method
implement custom comparator

create an array of size n to store the queries
loop through the array

if the array has an add instruction, add the node into the tree. set current value of i to id of node.
if it is a query instruction,
have a while (tree not empty && energy > 0)
    find the greatest floor of tree and X, gold += G, X -= E
    if tree.floor is null, break*/

import java.util.*;

class Node implements Comparable<Node> {
    int e, g, id;

    Node(int e, int g) {
        this.e = e;
        this.g = g;
    }

    void setId(int n) {
        this.id = n;
    }

    @Override
    public int compareTo(Node n2) {
        if (this.e == n2.e && this.g == n2.g) {
            return this.id - n2.id;
        } else if (this.e == n2.e) {
            return this.g - n2.g;
        } else {
            return this.e - n2.e;
        }
    }
}

public class KattisQuest{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[][] instructions = new String[n][3];
        TreeSet<Node> ts = new TreeSet<Node>();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String x = sc.nextLine();
            instructions[i] = x.split(" ");
        }


        for (int i = 0; i < n; i++) {
            long gold = 0;
            if (instructions[i][0].equals("add")) {
                int e = Integer.parseInt(instructions[i][1]);
                int g = Integer.parseInt(instructions[i][2]);
                Node nd = new Node(e, g);
                nd.setId(i);
                ts.add(nd);
            } else { //query
                int x = Integer.parseInt(instructions[i][1]);
                while (!ts.isEmpty() && x > 0) {
                    Node temp = new Node(x, 100000);
                    temp.setId(i);
                    Node greatest = ts.floor(temp);
                    if (greatest == null) {
                        break;
                    } else {
                    gold += greatest.g;
                    x -= greatest.e;
                    ts.remove(greatest);
                    }
                    
                }

                System.out.println(gold);
            }
        }
    }
}