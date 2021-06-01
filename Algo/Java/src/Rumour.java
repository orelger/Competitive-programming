import java.util.Scanner;

/**
 * Author: Orel Gershonovich
 * Release: 19.4.21
 */

public class Rumour {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("Give me the length of the tree: ");
        int length = sc.nextInt();
        long firstNum, secondNum;
        int [] sum = new int[length];
        int dist, son;
        boolean log =false;
        long min, max;

        for (int i = 0; i < length; i++) {
            dist = 0;
            son = 0;
            log = false;
            //System.out.println("Give me first num:");
            firstNum = sc.nextLong();
            //System.out.println("Give me second num:");
            secondNum = sc.nextLong();
            min = Math.min(firstNum, secondNum);
            max = Math.max(firstNum, secondNum);
            while ((min != max) && (min * 2 != max) && ((min * 2) + 1 != max)) {
                son = isSon(min, max);
                if (son != 0) {
                    //Max is son of min
                    dist += (int) (Math.log(son) / Math.log(2));
                    sum[i] = dist;
                    log = true;
                    break;
                }

                //Divide by two maybe in the next step will be son and father
                max /= 2;
                min /= 2;

                //Adding 2 because we divide the 2 nodes by two
                dist += 2;
            }

            if (min == max)
                sum[i] = dist;
            else if ((min * 2 == max) || ((min * 2) + 1 == max) && !log)
                sum[i] = dist + 1;
            else if(((min +1) == max) && !log)
                sum[i] = dist + 2;
        }

        for (int i: sum) {
            System.out.println(i);
        }
    }

    /**
     * Getting 2 nodes(Min and max)
     * Checks if min is a father of max
     * @param min - father
     * @param max - son
     * @return 0 - is not a child
     *         mul of 2
     */
    private static int isSon(long min, long max) {
        int counter = 1;
        while(max >= min){
            if(max <= (min + counter - 1)) {
                //Ensure max number is lower than the max real value of the sun
                return counter;
            }
            min *= 2;
            counter *= 2;
        }
        return 0;
    }
}
