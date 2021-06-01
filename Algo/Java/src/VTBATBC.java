import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Orel Gershonovich
 * Release: 10.4.21
 */

public class VTBATBC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("How many test: ");
        int testCases = sc.nextInt();
        int[] answer = new int[testCases];
        int vertices;
        int edges;
        List<EdgeConnection> l1;

        for (int i = 0; i < testCases; i++) {
            //System.out.println("How many vertices: ");
            vertices = sc.nextInt();
            //System.out.println("How many edges: ");
            edges = sc.nextInt();
            Graph g1 = new Graph(vertices, edges);
            l1 = new ArrayList<>();
            for (int j = 0; j < edges; j++) {
                //System.out.println("Give me the first vertex: ");
                int first = sc.nextInt();
                //System.out.println("Give me the second vertex: ");
                int second = sc.nextInt();
                EdgeConnection edge = new EdgeConnection(first, second);
                l1.add(edge);
            }

            g1.addEdgesList(l1);
            //System.out.println(g1);

            if (g1.hasCycle())
                answer[i] = 1;
            else
                answer[i] = 0;

        }

        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }

    }

    private static class EdgeConnection {
        int vertexA;
        int vertexB;

        //Cons
        public EdgeConnection(int vertexA, int vertexB) {
            this.vertexA = Math.min(vertexA, vertexB);
            this.vertexB = Math.max(vertexA, vertexB);
        }

        public int getVertexA() {
            return vertexA;
        }

        public int getVertexB() {
            return vertexB;
        }

        public boolean hasAny(int vertex) {
            return vertex == vertexA || vertex == vertexB;
        }

        @Override
        public String toString() {
            return "EdgeConnection{" +
                    "vertexA=" + vertexA +
                    ", vertexB=" + vertexB +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof EdgeConnection)) return false;
            EdgeConnection that = (EdgeConnection) o;
            return vertexA == that.vertexA &&
                    vertexB == that.vertexB;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertexA, vertexB);
        }
    }

    private static class Graph {
        private int vertices;
        private int edges;
        private List<EdgeConnection> edgesList;

        //Cons
        public Graph(int vertices, int edges) {
            this.vertices = vertices;
            this.edges = edges;
        }

        @Override
        public String toString() {
            return "Graph{" +
                    "vertices=" + vertices +
                    ", edges=" + edges +
                    ", edgesList=" + edgesList +
                    '}';
        }

        public void addEdgesList(List<EdgeConnection> l1) {
            //edgesList = new ArrayList<>();
            edgesList = l1;
        }

        /**
         * Exists cycle in graph
         * @return
         */
        public boolean hasCycle() {
            for (EdgeConnection edge : edgesList) {
                if ((edge.getVertexA() == edge.getVertexB() /* inner loop */ ) ||
                        /* any circle */ hasCycle(edge.getVertexA(), new HashSet<>(), edgesList))
                    return true;
            }
            return false;
        }

        /**
         * private function for recursive traversing in a graph
         * @param vertex - local vertex
         * @param visited - which vertices we have visited
         * @param edges - every edges we have not visited
         * @return
         */
        private boolean hasCycle(int vertex, Set<Integer> visited, List<EdgeConnection> edges) {
            //Finding a circle
            if (visited.contains(vertex)) return true;
            else {
                visited.add(vertex);

                // Find all adjacent vertices, so we will scan the graph from them. (recursively)
                List<EdgeConnection> adjacent = edges.stream().filter(edge -> edge.hasAny(vertex)).collect(Collectors.toList());

                // Go over all adjacent vertices, to scan from them recursively
                for (EdgeConnection edge : adjacent) {
                    // We keep vertexA=min, vertexB=max, but it might be that there is a circle from B to A,
                    // so in this case, we will select vertexA to scan from, instead of vertexB.
                    // 0 -> 1, 1 -> 2, 0 -> 2  (But we need it to be as 2 -> 0)
                    int to = edge.getVertexB();
                    if (to == vertex) {
                        to = edge.getVertexA();
                    }

                    // Collect all edges that we have not visited yet, except the current edge (in the for) because
                    // we do not want to select the same edge when we inside the recursion.
                    // To make sure we do not select the same edge twice.
                    List<EdgeConnection> edgesWithoutCurrentEdge = edges.stream().filter(edge2 -> !edge2.equals(edge)).collect(Collectors.toList());

                    // Continue in recursion
                    if (hasCycle(to, visited, edgesWithoutCurrentEdge)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}

