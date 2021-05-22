import java.util.*;

/**
 * Author: Orel Gershonovich
 * Release: 19.3.21
 */

public class CarSpark {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N;
        //System.out.println("How many checks?");
        int T = sc.nextInt();
        int startTime;
        int endTime;
        int amount;

        //Insert details
        for (int i = 0; i < T; i++) {
            List<Tuple> marks = new ArrayList<>();
            N = sc.nextInt();
            for (int z = 0; z < N; z++) {
                //System.out.println("Give me the start time");
                startTime = sc.nextInt();
                //System.out.println("Give me the end time");
                endTime = sc.nextInt();
                //System.out.println("Give me the amount");
                amount = sc.nextInt();

                marks.add(new Tuple(startTime, endTime, amount));
            }
            // Sort activities based on finish time
            marks.sort(Comparator.comparingInt(interval -> interval.endTime));

            //Find the nearest activity from left that has no intersection with current activity.
            int[] p = buildPArray(marks);

            // Now compute optimum value
            int[] m = new int[marks.size() + 1];
            m[0] = 0;
            for (int k = 0; k < marks.size(); k++) {
                Tuple currActivity = marks.get(k);
                m[k + 1] = Math.max(currActivity.amount + m[p[k]], m[k]);
            }

            //findSolution(marks,m,p,marks.size()-1);
            System.out.println(m[m.length - 1]);
        }


    }

    private static int[] buildPArray(List<Tuple> marks) {
        int[] p = new int[marks.size()];
        p[0] = 0;

        for (int i = marks.size() - 1; i > 0; i--) {
            Tuple currActivity = marks.get(i);

            // Find the nearest activity from left that has no intersection with current activity.
            boolean found = false;
            for (int j = i - 1; j >= 0 && !found; j--) {
                Tuple lastActivity = marks.get(j);
                if (lastActivity.endTime <= currActivity.startTime) {
                    found = true;
                    p[i] = j + 1;
                }
            }
            if (!found) {
                p[i] = 0;
            }
        }

        return p;
    }


    private static void findSolution(List<Tuple> intervals, int[] m, int[] p, int i) {
        if (i <= 0) {
            return;
        }

        Tuple currInterval = intervals.get(i);
        if (currInterval.amount + m[p[i]] >= m[i - 1]) {
            System.out.println(currInterval);
            findSolution(intervals, m, p, p[i] - 1);
        } else {
            findSolution(intervals, m, p, i - 1);
        }
    }


    private static class Tuple {
        int startTime;
        int endTime;
        int amount;

        public Tuple(int startTime, int endTime, int amount) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", amount=" + amount +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Tuple)) return false;
            Tuple tuple = (Tuple) o;
            return startTime == tuple.startTime &&
                    endTime == tuple.endTime &&
                    amount == tuple.amount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startTime, endTime, amount);
        }
    }
}
