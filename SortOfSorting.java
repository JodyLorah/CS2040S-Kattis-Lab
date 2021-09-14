//Tang Wai Mun, Jody Lorah (A0205580L)

// have extra comparator method to compare both elems with each other

// if (n > 0),
// throw each name into an arraylist of strings

// once n = 0,
// sort the arraylist using comparator.
// print the sorted array of strings


import java.util.*;

class SortByFirst2 implements Comparator<String> { 

    @Override
    public int compare(String a, String b) { 
        if (a.charAt(0) < b.charAt(0)) {
            return -1;
        } else if(a.charAt(0) > b.charAt(0)) {
            return 1;
        } else {
            if (a.charAt(1) < b.charAt(1)) {
                return -1;
            } else if(a.charAt(1) > b.charAt(1)) {
                return 1;
            } else {
                return 0;
            }
        }
    } 
} 

public class SortOfSorting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList <ArrayList<String>> allNames = new ArrayList <ArrayList<String>>(); 
        
        
        while (sc.hasNext()) {
            ArrayList<String> names = new ArrayList<String>(); 
            int n;
            n = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < n; i++) {
                names.add(sc.nextLine());
            }
            Collections.sort(names, new SortByFirst2()); 
            allNames.add(names);
        }        
        
        for (int i = 0; i < allNames.size(); i++) { 
            for (int j = 0; j < allNames.get(i).size(); j++) { 
                System.out.print(allNames.get(i).get(j) + " "); 
            } 
            System.out.println(); 
        } 
    }
}
