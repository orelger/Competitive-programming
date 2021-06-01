import java.util.Scanner;

/**
 * Author: Orel Gershonovich
 * Release: 9.4.21
 */

public class BeetleBag {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("Please give me the number of test: ");
        int t = sc.nextInt();
        int[] sum = new int[t];
        for (int i = 0; i < t; i++) {
            //System.out.println("Please give me the capacity of Beetleman's bag,");
            int capacity = sc.nextInt();
            //System.out.println("Please give me the number of gadgets in Copperman labs");
            int numberGadgets = sc.nextInt();
            int[] weight = new int[numberGadgets];
            int[] power = new int[numberGadgets];

            //Inserting data to the array
            for (int k = 0; k < numberGadgets; k++) {
                //System.out.println("Please give me a weight: ");
                weight[k] = sc.nextInt();
                //System.out.println("Please give me a power: ");
                power[k] = sc.nextInt();
            }

            //sum[i] = knapsackProblemRec(weight, power, capacity, numberGadgets);
            sum[i] = knapsackProblemDynamic(weight, power, capacity, numberGadgets);
        }

        //Print the results
        for (int i = 0; i < sum.length; i++) {
            System.out.println(sum[i]);
        }
    }

    private static int knapsackProblemDynamic(int[] weight, int[] power, int capacity, int numberGadgets) {
        int[][] m = new int[numberGadgets][capacity + 1];

        //Fill first col in zero because the weight is zero
        for (int i = 0; i < numberGadgets; i++) {
            m[i][0] = 0;
        }

        //Fill first row
        for (int i = 1; i < capacity + 1; i++) {
            if (weight[0] > i)
                m[0][i] = 0;
            else
                m[0][i] = power[0];
        }

        for (int i = 1; i < numberGadgets; i++) {
            for (int j = 1; j < capacity + 1; j++) {
                if (j >= weight[i])
                    m[i][j] = Math.max(power[i] + m[i - 1][j - weight[i]], m[i - 1][j]);
                else {
                    m[i][j] = m[i - 1][j];
                }
            }
        }
        //The last cell in the matrix its val is the maximum
        return m[numberGadgets - 1][capacity];
    }


    private static int knapsackProblemRec(int[] weight, int[] power, int capacity, int n) {
        if (n == 0 || capacity == 0)
            return 0;

        if (weight[n - 1] > capacity)
            return knapsackProblemRec(weight, power, capacity, n - 1);
        else
            return Math.max(power[n - 1] + knapsackProblemRec(weight, power, capacity - weight[n - 1], n - 1),
                    knapsackProblemRec(weight, power, capacity, n - 1));
    }
}
