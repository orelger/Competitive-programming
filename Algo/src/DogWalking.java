import java.util.Arrays;
import java.util.Scanner;

/**
 * Author: Orel Gershonovich
 * Release: 28.4.21
 */

public class DogWalking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("Please give me the number of test: ");
        int t = sc.nextInt();
        int n, k;
        int[] dogs, sum, diff;
        sum = new int[t];

        for (int i = 0; i < t; i++) {
            //System.out.println("Please give me the number of dogs: ");
            n = sc.nextInt();
            //System.out.println("Please give me the number of employees: ");
            k = sc.nextInt();

            dogs = new int[n];
            for (int j = 0; j < n; j++) {
                dogs[j] = sc.nextInt();
            }

            //Sorting the dogs array
            Arrays.sort(dogs);

            if (k == 1)
                sum[i] = dogs[dogs.length - 1] - dogs[0];
            else if (k == n)
                sum[i] = 0;
            else {
                int sumDiff = 0;
                int sub = sum[i] = dogs[dogs.length - 1] - dogs[0];
                diff = new int[n - 1];
                for (int j = 1; j < n; j++)
                    diff[j - 1] = dogs[j] - dogs[j - 1];

                Arrays.sort(diff);

                int lasItem = diff.length - 1;
                while (k > 1) {
                    sumDiff += diff[lasItem--];
                    k--;
                }
                sum[i] = sub - sumDiff;
            }
        }

        //Print the results
        for (int i = 0; i < t; i++) {
            System.out.println(sum[i]);
        }
    }
}
