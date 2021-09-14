//Tang Wai Mun, Jody Lorah (A0205580L)

// have a node class to store next node, the string and the index of the last of the pointers

// have a buffered reader to take in all inputs 
// have arrays: 1 for words and 1 for concat instructions

// loop through concat instructions array, and assign the next pointers accordingly

// loop through and print out the whole sequence, following which pointer is next

import java.io.*;

//create node class for implementation of Node array[] with pointers for quick adding
class Node {
    String d;
    Node next;
    Node last;

    //constructor for node
    Node(String n) {
        this.d = n;
        this.next = null;
        this.last = this;
    }

    @Override
    public String toString() {
        return String.format("%s", this.d);
    }

    void setLast(Node tail) {
        this.last = tail;
    }

    void setNext(Node next) {
        this.next = next;
    }

    Node getLast() {
        return this.last;
    }

    Node getNext() {
        return this.next;
    }
}


public class JoinStrings {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Node[] words = new Node[n + 1];
        String[][] temp = new String[n - 1][2];

        words[0] = null;

        for (int i = 1; i < n + 1; i++) {
            words[i] = new Node(br.readLine());
        }

        //store all instructions as a string in a temp array
        for (int i = 0; i < n - 1 ; i++) {
            String x = br.readLine();
            temp[i] = x.split(" ");
        }

        //assign pointers for each instruction
        for (int i = 0; i < (n - 1) ; i++) {       
            int k = Integer.parseInt(temp[i][0]);
            int j = Integer.parseInt(temp[i][1]);
            words[k].getLast().setNext(words[j]);
            words[k].setLast(words[j].getLast());   
        }

        //follow the nodes and pointers and print everything accordingly
        if (n > 1) {
            Node p = words[Integer.parseInt(temp[n - 2][0])];
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
            while (p != null) { 
                pw.print(p.d);
                p = p.next; 
            } 
            pw.flush();
        } else {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
            pw.print(words[1].d);
            pw.flush();
        }
    }
}