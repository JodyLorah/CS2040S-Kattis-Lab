//Tang Wai Mun, Jody Lorah (A0205580L)
//discussed with Edeline Tenges A0206187A

// create an arraylist of overall size n storing 2 elements: int 0,1,2,3; and the person index
// 0 is behind back, 1 is palm down, 2 is fist, 3 is coconut 
// at first, elements are stored in arraylist as [[3,1],[3,2],[3,3],...,[3,n]]

// traverse the list according to s. can use modulo to shorten things
// use a while loop to keep it going until only 1 hand is left
// at the index check:
// if 1, remove from array list
// if 2, change elem to 1 and continue traversing again
// if 3, change elem to 2 and insert an elem in the same place with [2, person_index]

// print person index once only 1 hand is left

import java.util.*;

public class CoconutSplat{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList <ArrayList<Integer>> allPlayers = new ArrayList <ArrayList<Integer>>(); 

        int s = sc.nextInt();
        int n = sc.nextInt();

        //initialise all players into the arraylist
        for (int i = 1; i < n + 1; i++) {
            ArrayList<Integer> player = new ArrayList<Integer>();
            player.add(3);
            player.add(i);
            allPlayers.add(player);
        }

        int move = 0;
        while (allPlayers.size() > 1) {
            move = (move + s - 1) % allPlayers.size();            
            if (allPlayers.get(move).get(0) == 1) {
                allPlayers.remove(move);;
            } else if (allPlayers.get(move).get(0) == 2) {
                allPlayers.get(move).set(0, 1);
                move++;
            } else {
                int playerIndex = allPlayers.get(move).get(1);
                ArrayList<Integer> player = new ArrayList<Integer>();
                player.add(2);
                player.add(playerIndex);
                allPlayers.get(move).set(0, 2);
                allPlayers.add(move, player);
            }        
        }
        
        //print player num
        System.out.println(allPlayers.get(0).get(1));

    }
}