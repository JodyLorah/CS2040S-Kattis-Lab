import java.util.*;

public class PF {
    public static int[] factorize(int value) {
        int limit = (int)(Math.ceil(Math.sqrt(value)));
        int[] temp = new int[10];
        int index = 0;
        while (value % 2 == 0) {
            value /= 2;
            temp[0] = 2;
            index = 1;
        }
        for (int i = 3; i <= limit; i += 2) {
            if (value % i == 0) {
                temp[index] = i;
                index++;
            }
            while (value % i == 0) {
                value /= i;
            }
        }
        if (value != 1) {
            temp[index] = value;
            index++;
        }
        int[] result = new int[index];
        for (int i = 0; i < index; i++) {
            result[i] = temp[i];
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(factorize(2)));
        System.out.println(Arrays.toString(factorize(3)));
        System.out.println(Arrays.toString(factorize(25)));
        System.out.println(Arrays.toString(factorize(31)));
        System.out.println(Arrays.toString(factorize(40)));
        System.out.println(Arrays.toString(factorize(8417872)));
        System.out.println(Arrays.toString(factorize(9699690)));
        System.out.println(Arrays.toString(factorize(9764873)));
        System.out.println(Arrays.toString(factorize(9764877)));
        System.out.println(Arrays.toString(factorize(9840769)));
    }
}