/*
Tang Wai Mun, Jody Lorah (A0205580L) collaborated with Wong Qin Liang and Edeline Tenges

store all users in a list
sort the list


store all workstations as ints rep the end time of user in a pq
loop through list of users
while pq size >= 0,
    if (pq.peek() is null) {
        add user to pq
    }
    pq.poll() = stationAvail
    if (stationAvail <= time new user arrives <= expiring time) {
        update the end time of station
    } else if (stationAvail > arrival time) {
        clear all
        add user to pq 
    }
*/
import java.util.*;

class Sorter implements Comparator<int[]> {
    @Override
    public int compare(int[] a, int[] b) {
        return a[0] - b[0];
    }
}

public class AssigningWorkstations {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int counter = 0;

        ArrayList<int[]> list = new ArrayList<int[]>();
        for (int i = 0; i < n; i++) {
            int[] temp = new int[2];
            for (int j = 0; j < 2; j++) { 
                temp[j] = sc.nextInt();
            }
            list.add(temp);
        }

        Collections.sort(list, new Sorter());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int userArr = list.get(i)[0];
            int userEnd = list.get(i)[1];
           
            while (pq.size() >= 0) {
                if (pq.peek() == null) { //pq is empty, add user
                    pq.add(userEnd + userArr);
                    break;
                } 
    
                int stationAvail = pq.poll(); 

                if (userArr < stationAvail) { //user arrives before all stations are available
                    pq.add(stationAvail);
                    pq.add(userEnd + userArr);
                    break;
                } else if (userArr - stationAvail <= m) { //user arrives in unoccupied and unlocked window 
                    pq.add(userEnd + userArr);
                    counter++;
                    break;
                }
            }
        }
        System.out.println(counter);
    }
}