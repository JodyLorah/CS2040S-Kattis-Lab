//Tang Wai Mun, Jody Lorah (A0205580L)

//Create a node object
//Create an doubly linked list to start storing everything in nodes
//Have a front, middle, end pointer to point to each index
//for each instruction, add it to the respective places
//if it is get, print the instruction

import java.io.*;

public class Teque {

    //create node obj with pointers to prev and next obj
    static class Node {
        int data;
        Node prev;
        Node next;

        //constructor for node
        Node(int n) {
            this.data = n;
        }

        int get() {
            return this.data;
        }

        @Override
        public String toString() {
            return String.format("%d", this.data);
        }
    }

    
    //create node class for implementation of doubly linked list to store head middle and tail pointers
    static class DLL  {

        Node head;
        Node middle;
        Node last;
        int size = 0;

        
        int getSize() {
            return this.size;
        }

        Node getHead() {
            return this.head;
        }

        void pushFront(int i) {
            Node curr = new Node(i);
            curr.next = head;
            curr.prev = null;
            if (head != null) { //there are elems in the DLL
                head.prev = curr;
                head = curr;
                size++;

                if ((size - 1) % 2 != 0) { //init size is odd, need to reassign middle pointer
                    middle = middle.prev;
                }
            } else {
                head = curr;
                middle = curr;
                last = curr;
                size = 1;
            }        
        }

        void pushMiddle(int i) {
            Node curr = new Node(i);
            if (middle != null) { //there are elems in the DLL
                curr.next = middle.next;
                middle.next = curr;
                curr.prev = middle;  
                size++;

                if ((size - 1) % 2 == 0) { //init size is even, need to reassign middle pointer
                    middle = curr;
                }
                if (curr.next != null) { //not adding to the end of the list
                    curr.next.prev = curr;
                }
            } else {
                head = curr;
                middle = curr;
                last = curr;
                size = 1;
            }
        }

        void pushBack(int i) {
            Node curr = new Node(i);
            curr.next = null;
            curr.prev = last;
            if (last != null) { //there are elems in the DLL
                last.next = curr;
                size++;
                
                if ((size - 1) % 2 == 0) { //init size is even, need to reassign middle pointer
                    middle = middle.next;
                }
            } else {
                head = curr;
                middle = curr;
                last = curr;
                size = 1;
            } 
        } 
        
        @Override
        public String toString() {
            String s = "";
            Node temp = head;
            while (temp.next != null) {
                s += temp.get();
                temp = temp.next;
            }
            return String.format("%s", s);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] instructions = new String[n];
        int[] list = new int[n];
        DLL d = new DLL();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        // adding of all info rec into instructions and list
        for (int i = 0; i < n; i ++) {
            String x = br.readLine();
            String[] temp = x.split(" ");
            instructions[i] = temp[0];
            list[i] = Integer.parseInt(temp[1]);

        }

        for (int i = 0; i < n; i++) {
            String current = instructions[i];
            int curr = list[i];
            if (current.equals("push_back")) {
                d.pushBack(curr);
            } else if (current.equals("push_front")) {
                d.pushFront(curr);
            } else if (current.equals("push_middle")) {
                d.pushMiddle(curr);
            } else { 
                Node temp = d.head;            
                while (curr > 0) {
                    temp = temp.next;
                    curr--;
                }
                pw.println(temp.get());
            }
        }

        pw.flush();
    }
}
