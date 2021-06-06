import java.util.Scanner;

/**
 * A solution for Always Be In Control - Xtreme 10.0
 *
 * @author: Orel Gershonovich
 * @see: <a href="https://www.hackerrank.com/">https://www.hackerrank.com/</a>
 * @since: : 2.5.21
 */
public class AlwaysBeInControl {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] arrA2 = new double[]{0.0, 0.0, 1.880, 1.023, 0.729, 0.577, 0.483, 0.419, 0.373, 0.337, 0.308};

        //System.out.println("Please give me the number of test: ");
        int tests = sc.nextInt();

        for (int i = 0; i < tests; i++) {
            // A variable for representing "outcontrol" if any of the four limitation are occurred
            boolean inControl = true;

            //System.out.println("Please give me the number of data and the number of elements in a subgroup: ");
            int data = sc.nextInt();
            int elementsInSubgroup = sc.nextInt();
            int[] dataArr = new int[data];

            //Insert element to arr
            for (int j = 0; j < data; j++) {
                dataArr[j] = sc.nextInt();
            }

            int runner = 1;
            double sum = 0;
            double[] avg;
            int[] range;

            // Creating arrays according to the required size
            if ((data % elementsInSubgroup) == 0) {
                avg = new double[(data / elementsInSubgroup)];
                range = new int[(data / elementsInSubgroup)];
            } else {
                avg = new double[(data / elementsInSubgroup) + 1];
                range = new int[(data / elementsInSubgroup) + 1];
            }

            int avgIndex = 0, rangeIndex = 0;
            int min = dataArr[0];
            int minItemArr = dataArr[0];
            int maxItemArr = dataArr[0];
            int max = dataArr[0];

            for (int j = 0; j < dataArr.length; j++) {
                //Find the min and max item in the array
                if (dataArr[j] < minItemArr)
                    minItemArr = dataArr[j];
                if (dataArr[j] > maxItemArr)
                    maxItemArr = dataArr[j];

                //Find the min and max item in the sub array
                if (runner != elementsInSubgroup) {
                    if (dataArr[j] > max)
                        max = dataArr[j];

                    if (dataArr[j] < min)
                        min = dataArr[j];

                    sum += dataArr[j];
                    if (j != dataArr.length - 1)
                        runner++;
                } else {
                    // Init the runner to the next subgroup
                    runner = 1;
                    avg[avgIndex++] = (sum + dataArr[j]) / elementsInSubgroup;

                    //Check if the last item in sub array is the max or min
                    if (dataArr[j] > max)
                        max = dataArr[j];
                    if (dataArr[j] < min)
                        min = dataArr[j];

                    range[rangeIndex++] = max - min;
                    sum = 0;
                    if (j != dataArr.length - 1) {
                        //Init the max and min for the next subgroup
                        max = dataArr[j + 1];
                        min = dataArr[j + 1];
                    }
                }

                //Check extreme case
                if (j == dataArr.length - 1 && (data % elementsInSubgroup) != 0) {
                    if (dataArr[j] > max)
                        max = dataArr[j];

                    if (dataArr[j] < min)
                        min = dataArr[j];

                    if (runner == 1)
                        avg[avgIndex++] = max;
                    else
                        avg[avgIndex++] = sum / runner;

                    range[rangeIndex++] = max - min;
                }
            }

//            for (int j = 0; j < avg.length; j++) {
//                System.out.println(avg[j]);
//            }
//            System.out.println("-----------------------------");
//            for (int j = 0; j < range.length; j++) {
//                System.out.println(range[j]);
//            }

            double xAvg = 0;
            double rAvg = 0;
            for (int j = 0; j < avg.length; j++) {
                xAvg += avg[j];
                rAvg += range[j];
            }

            xAvg /= avg.length;
            rAvg /= range.length;
            //System.out.println("xAvg= " + xAvg + " rAvg= " + rAvg);

            double ucl = xAvg + (rAvg * arrA2[elementsInSubgroup]); // 3
            double lcl = xAvg - (rAvg * arrA2[elementsInSubgroup]); //-3
            double cl = Math.nextUp(xAvg);
            double sigma = (ucl - cl) / 3;
            double sigmaM = (lcl - cl) / 3;
            double midUpSigma = ucl - sigma; //2
            double midDownSigma = lcl - sigmaM; //-2
            double upperSigma = midUpSigma - sigma; //1
            double lowerSigma = midDownSigma - sigmaM; //-1

            //System.out.println("ucl= " + ucl + " lcl= " + lcl + " cl= " + cl + " sigma= " + sigma);
            //System.out.println("upper sigma= " + upperSigma + " lower Sigma= " + lowerSigma);
            //System.out.println("midUpSigma= " + midUpSigma + " midDownSigma= " + midDownSigma);

            //First check - A single point falls outside the 3-sigma control limits.
            if (maxItemArr > ucl || minItemArr < lcl) {
                inControl = false;
            }

            //Second check - At least two out of three successive values fall on the same side of,
            // and more than two sigma units away from, the center line.
            for (int j = 0; j < dataArr.length - 2; j++) {
                if ((dataArr[j] > cl && dataArr[j + 1] > cl) || (dataArr[j] > cl && dataArr[j + 2] > cl) || (dataArr[j + 1] > cl && dataArr[j + 2] > cl)) {
                    if ((dataArr[j] > midUpSigma && dataArr[j + 1] > midUpSigma) || (dataArr[j + 1] > midUpSigma && dataArr[j + 2] > midUpSigma)
                            || (dataArr[j] > midUpSigma && dataArr[j + 2] > midUpSigma)) {
                        inControl = false;
                    }
                }
                if ((dataArr[j] < cl && dataArr[j + 1] < cl) || (dataArr[j] < cl && dataArr[j + 2] < cl) || (dataArr[j + 1] < cl && dataArr[j + 2] < cl)) {
                    if ((dataArr[j] < midDownSigma && dataArr[j + 1] < midDownSigma) || (dataArr[j + 1] < midDownSigma && dataArr[j + 2] < midDownSigma)
                            || (dataArr[j] < midDownSigma && dataArr[j + 2] < midDownSigma)) {
                        inControl = false;
                    }
                }
            }

            //Third check - At least four out of five successive values fall on the same side of,
            // and more than one sigma unit away from, the center line.
            for (int j = 0; j < dataArr.length - 4; j++) {
                if ((dataArr[j] > cl && dataArr[j + 1] > cl && dataArr[j + 2] > cl && dataArr[j + 3] > cl && dataArr[j + 4] > cl) ||
                        (dataArr[j] > cl && dataArr[j + 1] > cl && dataArr[j + 2] > cl && dataArr[j + 3] > cl) ||
                        (dataArr[j + 1] > cl && dataArr[j + 2] > cl && dataArr[j + 3] > cl && dataArr[j + 4] > cl) ||
                        (dataArr[j] > cl && dataArr[j + 1] > cl && dataArr[j + 2] > cl && dataArr[j + 4] > cl) ||
                        (dataArr[j] > cl && dataArr[j + 1] > cl && dataArr[j + 3] > cl && dataArr[j + 4] > cl) ||
                        (dataArr[j] > cl && dataArr[j + 2] > cl && dataArr[j + 3] > cl && dataArr[j + 4] > cl)) {
                    if ((dataArr[j] > upperSigma && dataArr[j + 1] > upperSigma && dataArr[j + 2] > upperSigma && dataArr[j + 3] > upperSigma) ||
                            (dataArr[j + 1] > upperSigma && dataArr[j + 2] > upperSigma && dataArr[j + 3] > upperSigma && dataArr[j + 4] > upperSigma) ||
                            (dataArr[j] > upperSigma && dataArr[j + 1] > upperSigma && dataArr[j + 2] > upperSigma && dataArr[j + 4] > upperSigma) ||
                            (dataArr[j] > upperSigma && dataArr[j + 1] > upperSigma && dataArr[j + 3] > upperSigma && dataArr[j + 4] > upperSigma) ||
                            (dataArr[j] > upperSigma && dataArr[j + 2] > upperSigma && dataArr[j + 3] > upperSigma && dataArr[j + 4] > upperSigma)) {
                        inControl = false;
                    }
                }
                if ((dataArr[j] < cl && dataArr[j + 1] < cl && dataArr[j + 2] < cl && dataArr[j + 3] < cl && dataArr[j + 4] < cl) ||
                        (dataArr[j] < cl && dataArr[j + 1] < cl && dataArr[j + 2] < cl && dataArr[j + 3] < cl) ||
                        (dataArr[j + 1] < cl && dataArr[j + 2] < cl && dataArr[j + 3] < cl && dataArr[j + 4] < cl) ||
                        (dataArr[j] < cl && dataArr[j + 1] < cl && dataArr[j + 2] < cl && dataArr[j + 4] < cl) ||
                        (dataArr[j] < cl && dataArr[j + 1] < cl && dataArr[j + 3] < cl && dataArr[j + 4] < cl) ||
                        (dataArr[j] < cl && dataArr[j + 2] < cl && dataArr[j + 3] < cl && dataArr[j + 4] < cl)) {
                    if ((dataArr[j] < lowerSigma && dataArr[j + 1] < lowerSigma && dataArr[j + 2] < lowerSigma && dataArr[j + 3] < lowerSigma) ||
                            (dataArr[j + 1] < lowerSigma && dataArr[j + 2] < lowerSigma && dataArr[j + 3] < lowerSigma && dataArr[j + 4] < lowerSigma) ||
                            (dataArr[j] < lowerSigma && dataArr[j + 1] < lowerSigma && dataArr[j + 2] < lowerSigma && dataArr[j + 4] < lowerSigma) ||
                            (dataArr[j] < lowerSigma && dataArr[j + 1] < lowerSigma && dataArr[j + 3] < lowerSigma && dataArr[j + 4] < lowerSigma) ||
                            (dataArr[j] < lowerSigma && dataArr[j + 2] < lowerSigma && dataArr[j + 3] < lowerSigma && dataArr[j + 4] < lowerSigma)) {
                        inControl = false;
                    }
                }
            }

            //Forth check - At least eight successive values fall on the same side of the center line.
            for (int j = 0; j < dataArr.length - 7; j++) {
                if ((dataArr[j] > cl && dataArr[j + 1] > cl && dataArr[j + 2] > cl && dataArr[j + 3] > cl && dataArr[j + 4] > cl
                        && dataArr[j + 5] > cl && dataArr[j + 6] > cl && dataArr[j + 7] > cl) ||
                        (dataArr[j] < cl && dataArr[j + 1] < cl && dataArr[j + 2] < cl && dataArr[j + 3] < cl && dataArr[j + 4] < cl
                                && dataArr[j + 5] < cl && dataArr[j + 6] < cl && dataArr[j + 7] < cl)) {
                    inControl = false;
                    break;
                }
            }
            if (inControl)
                System.out.println("In Control");
            else
                System.out.println("Out of Control");
        }
    }
}
