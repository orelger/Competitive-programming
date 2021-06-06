import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A solution for Goldbach's Second Conjecture - Xtreme 10.0
 *
 * @author: Orel Gershonovich
 * @see: <a href="https://www.hackerrank.com/">https://www.hackerrank.com/</a>
 * @since: 1.5.21
 */
public class GoldbachsSecondConjecture {
    public static BigInteger firstPrimeNum;
    public static BigInteger secondPrimeNum;
    public static final int MAX = 1000;

    // Array to store all prime numbers
    public static ArrayList<Long> primes = new ArrayList<>();

    public static void main(String[] args) {
        boolean isPrime = false, finished = false;
        int i = 0, j = 0;
        Scanner sc = new Scanner(System.in);

        //System.out.println("Give me an odd num greater than 5: ");
        BigInteger num = sc.nextBigInteger();
        firstPrimeNum = num;


        // Choose 5000 because less than 4000 some checks was failed.
        while (i < 5000 && !finished) {
            // Because i'm base on Goldbach's conjecture that says:
            // Every even whole number greater than 2 is the sum of two prime numbers.
            // I'm looking for the second number because if i take the first one we can get an error in the computing
            // 3 prime numbers of little prime number like (7,11 and etc)
            firstPrimeNum = firstPrimeNum.subtract(BigInteger.ONE);
            isPrime = firstPrimeNum.isProbablePrime(1);

            if (isPrime) {
                secondPrimeNum = firstPrimeNum;
                while (j < 5000) {
                    secondPrimeNum = secondPrimeNum.subtract(BigInteger.ONE);
                    if (secondPrimeNum.isProbablePrime(1)) {
                        // num.subtract(secondPrimeNum).intValue() - ensures greater than 2
                        // e.g 7-3=4 -> 3,2,2
                        // e.g 11-5=6 -> 5,3,3
                        twoPrimeNumbers(num.subtract(secondPrimeNum).intValue(), secondPrimeNum.toString(10));
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
