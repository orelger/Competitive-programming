import java.util.Scanner;

/**
 * Author: Orel Gershonovich
 * Date: 12.5.21
 */

public class XtremeRappers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("Please give me 2 numbers");
        long j, k, min, max;

        j = sc.nextLong();
        k = sc.nextLong();

        //finding max and min number
        max = Math.max(j, k);
        min = Math.min(j, k);

        //First case: if min num is 0
        if (min == 0)
            System.out.println(0);
        else if (max >= 2 * min)
            //Second case: there are min options becase the max is bigger than min in 2 mul
            System.out.println(min);
        else
            System.out.println((max + min) / 3);

    }
}
