import java.util.Scanner;

/**
 * A solution for Xtreme rappers - IEEEXtreme 13.0
 *
 * @author: Orel Gershonovich
 * @see: <a href="https://www.csacademy.com/">https://www.csacademy.com/</a>
 * @since: 12.5.21
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

        if (min == 0) {
            //First case: if min num is zero, there are zero options
            System.out.println(0);
        } else if (max >= 2 * min) {
            //Second case: if max is bigger than min by mul 2, there are min options
            System.out.println(min);
        } else {
            System.out.println((max + min) / 3);
        }
    }
}
