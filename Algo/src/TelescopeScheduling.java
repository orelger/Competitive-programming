import java.util.*;

/**
 * Author: Orel Gershonovich
 * Release: 10.4.21
 */

public class TelescopeScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("How many stars?");
        int starsAmount = sc.nextInt();
        List<Tuple> starsList = new ArrayList<>();
        int startTime;
        int endTime;
        int desirabilityTime;


        for (int i = 0; i < starsAmount; i++) {
            //System.out.println("Give me the start time: ");
            startTime = sc.nextInt();
            //System.out.println("Give me the end time: ");
            endTime = sc.nextInt();
            //System.out.println("Give me the desirability time: ");
            desirabilityTime = sc.nextInt();

            Tuple star = new Tuple(startTime, endTime, desirabilityTime);
            starsList.add(star);
        }

        //Checks input stars to stars array list
        //System.out.println(starsList);

        //Sort by end time
        Collections.sort(starsList, Comparator.comparingInt(star -> star.endTime));
        //System.out.println(starsList);

        //Check start time > end time
        int[] p = buildPArray(starsList);

        //Compute the sum of the desirability of the stars
        int[] m = new int[starsList.size() + 1];
        m[0] = 0;
        for (int k = 0; k < starsList.size(); k++) {
            Tuple currActivity = starsList.get(k);
            m[k + 1] = Math.max(currActivity.desirabilityTime + m[p[k]], m[k]);
        }

        System.out.println(m[m.length - 1]);
    }

    private static int[] buildPArray(List<Tuple> starsList) {
        int[] p = new int[starsList.size()];
        p[0] = 0;

        for (int i = starsList.size() - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (starsList.get(i).startTime > starsList.get(j).endTime) {
                    p[i] = j + 1;
                    break;
                }
            }
        }

//        for (int i = 0; i < p.length; i++) {
//            System.out.println(p[i]);
//        }

        return p;
    }

    private static class Tuple {
        int startTime;
        int endTime;
        int desirabilityTime;


        public Tuple(int startTime, int endTime, int desirabilityTime) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.desirabilityTime = desirabilityTime;
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "startTime=" + startTime +
                    ", endTime=" + endTime +
                    ", desirabilityTime=" + desirabilityTime +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Tuple)) return false;
            Tuple tuple = (Tuple) o;
            return startTime == tuple.startTime &&
                    endTime == tuple.endTime &&
                    desirabilityTime == tuple.desirabilityTime;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startTime, endTime, desirabilityTime);
        }
    }
}
