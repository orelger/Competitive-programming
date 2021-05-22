import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Author: Orel Gershonovich
 * Release: 5.3.21
 */

public class MagicSquare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextInt();

        List<Integer> nonEqual = new ArrayList<>(); //Save the error if is not a magic square
        int[][] magicSquare = new int[n][n];
        int val;

        //Inserting values to the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                val = sc.nextInt();
                magicSquare[i][j] = val;
            }
        }

        //Sum the main diagonal
        int sumDiagonal = 0;
        for (int i = 0; i < n; i++) {
            sumDiagonal += magicSquare[i][i];
        }


        //Sum all of the rows
        int[] sumRows = new int[n];
        int sumRow = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumRow += magicSquare[i][j];
            }
            sumRows[i] = sumRow;
            if (sumDiagonal != sumRow) {
                nonEqual.add(i + 1);
            }
            sumRow = 0;
        }

        //Sum all of the columns
        int[] sumColumns = new int[n];
        int sumColumn = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumColumn += magicSquare[j][i];
            }
            sumColumns[i] = sumColumn;
            if (sumDiagonal != sumColumn) {
                nonEqual.add(-i - 1);
            }
            sumColumn = 0;
        }

        //Sum the anti diagonal
        int sumAntiDiagonal = 0;
        for (int i = 0, j = n - 1; i < n && j >= 0; i++, j--) {
            sumAntiDiagonal += magicSquare[i][j];
        }

        //Is magic
        if (sumDiagonal == sumAntiDiagonal && nonEqual.isEmpty()) {
            System.out.println("0");
        } else {
            if (sumDiagonal == sumAntiDiagonal) {
                nonEqual.sort(Comparator.naturalOrder());
                System.out.println(nonEqual.size());
                for (int x : nonEqual) {
                    System.out.println(x);
                }
            } else {
                nonEqual.add(0);
                nonEqual.sort(Comparator.naturalOrder());
                System.out.println(nonEqual.size());
                for (int x : nonEqual) {
                    System.out.println(x);
                }
            }
        }
    }
}
