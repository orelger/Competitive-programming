import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author: Orel Gershonovich
 * Release: 1.5.21
 */

public class GoldbachsSecondConjecture {
    public static BigInteger firstPrimeNum;
    public static BigInteger second;
    public static int MAX = 1000;

    // Array to store all prime less
    public static ArrayList<Long> primes = new ArrayList<>();

    public static void main(String[] args) {
        boolean isPrime = false, finished = false;
        int i = 0, j = 0;
        Scanner sc = new Scanner(System.in);

        //System.out.println("Give me an odd num greater than 5: ");
        BigInteger num = sc.nextBigInteger();
        firstPrimeNum = num;

        //Chose 5000 because less than 4000 some checks was failed
        while (i < 5000 && !finished) {
            firstPrimeNum = firstPrimeNum.subtract(BigInteger.ONE);
            isPrime = firstPrimeNum.isProbablePrime(1);
            if (isPrime) {
                second = firstPrimeNum;
                while (j < 5000) {
                    second = second.subtract(BigInteger.ONE);
                    if (second.isProbablePrime(1)) {
                        twoPrimeNumbers(num.subtract(second).intValue(), second.toString(10));
                        finished = true;
                        break;
                    }
                    j++;
                }
            }
            i++;
        }
    }

    /***********************************************************
     From here until the end of the code the script was taken from GeeksForGeeks.
     I have made some changes for solving the
     Goldbach's Second Conjecture HackerRank problem
     *************************************************************/

    // Utility function for Sieve of Sundaram
    static void sieveSundaram() {
        // In general Sieve of Sundaram, produces
        // primes smaller than (2*x + 2) for
        // a number given number x. Since
        // we want primes smaller than MAX,
        // we reduce MAX to half This array is
        // used to separate numbers of the form
        // i + j + 2*i*j from others where 1 <= i <= j
        boolean[] marked = new boolean[MAX / 2 + 100];

        // Main logic of Sundaram. Mark all numbers which
        // do not generate prime number by doing 2*i+1
        for (int i = 1; i <= (Math.sqrt(MAX) - 1) / 2; i++)
            for (int j = (i * (i + 1)) << 1; j <= MAX / 2; j = j + 2 * i + 1)
                marked[j] = true;

        // Since 2 is a prime number
        primes.add((long) 2);

        // Print other primes. Remaining primes are of the
        // form 2*i + 1 such that marked[i] is false.
        for (int i = 1; i <= MAX / 2; i++)
            if (marked[i] == false)
                primes.add((long) (2 * i + 1));
    }

    static void twoPrimeNumbers(int num, String first) {
        sieveSundaram();

        // Return if number is not even or less than 3
        if (num <= 2 || num % 2 != 0) {
            //System.out.println("Invalid Input ");
            return;
        }

        // Check only upto half of number
        for (int i = 0; primes.get(i) <= num / 2; i++) {
            // find difference by subtracting
            // current prime from n
            long diff = num - primes.get(i);

            // Search if the difference is
            // also a prime number
            if (primes.contains(diff)) {
                // Express as a sum of primes
                System.out.println(first + " " + primes.get(i) + " " + diff);
                return;
            }
        }
    }
}
