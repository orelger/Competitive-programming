import java.math.BigInteger;
import java.util.Scanner;

/**
 * A solution for Running up stairs - IEEEXtreme 11.0
 *
 * @author: Orel Gershonovich
 * @see: <a href="https://www.csacademy.com/">https://www.csacademy.com/</a>
 * @since: 25.4.21
 */

public class RunningUpStairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("Enter a num please: ");
        int t = sc.nextInt();

        BigInteger n, answer, num1, num2, counter;
        BigInteger[] sum = new BigInteger[t];

        for (int i = 0; i < t; i++) {
            //System.out.println("Give me a num: ");
            answer = BigInteger.ONE;
            num1 = BigInteger.ONE;
            num2 = BigInteger.TWO;
            counter = BigInteger.TWO;
            n = sc.nextBigInteger();
            while (counter.intValue() < n.intValue()) {
                //Compute fib series with while loop
                answer = num2.add(num1);
                num1 = num2;
                num2 = answer;

                //Increment in one the while loop
                counter = counter.add(BigInteger.ONE);
            }
            if (n.equals(BigInteger.ONE)) {
                //First num in fib series
                sum[i] = BigInteger.ONE;
            } else if (n.equals(BigInteger.TWO)) {
                //Second num in fib series
                sum[i] = BigInteger.TWO;
            } else {
                sum[i] = answer;
            }
        }

        for (int i = 0; i < t; i++) {
            System.out.println(sum[i]);
        }
    }
}
