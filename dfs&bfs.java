import java.util.*;

public class GraphTraversal {

    // ---------------- DFS using Adjacency Matrix ----------------
    static class DFSGraph {
        int vertices;
        int[][] adjMatrix;
        boolean[] visited;

        DFSGraph(int v) {
            vertices = v;
            adjMatrix = new int[v][v];
            visited = new boolean[v];
        }

        // Add an edge (undirected)
        void addEdge(int src, int dest) {
            adjMatrix[src][dest] = 1;
            adjMatrix[dest][src] = 1;
        }

        // Recursive DFS
        void dfs(int start) {
            visited[start] = true;
            System.out.print((char) (start + 'A') + " "); // Convert 0->A, 1->B, etc.

            for (int i = 0; i < vertices; i++) {
                if (adjMatrix[start][i] == 1 && !visited[i]) {
                    dfs(i);
                }
            }
        }
    }

    // ---------------- BFS using Adjacency List ----------------
    static class BFSGraph {
        int vertices;
        LinkedList<Integer>[] adjList;

        BFSGraph(int v) {
            vertices = v;
            adjList = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        // Add an edge (undirected)
        void addEdge(int src, int dest) {
            adjList[src].add(dest);
            adjList[dest].add(src);
        }

        // BFS traversal
        void bfs(int start) {
            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();

            visited[start] = true;
            queue.add(start);

            while (!queue.isEmpty()) {
                int node = queue.poll();
                System.out.print((char) (node + 'A') + " ");

                for (int neighbor : adjList[node]) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
        }
    }

    // ---------------- Main Function ----------------
    public static void main(String[] args) {
        // Suppose locations: A, B, C, D, E
        // Represented as 0, 1, 2, 3, 4

        System.out.println("DFS using Adjacency Matrix:");
        DFSGraph dfsGraph = new DFSGraph(5);
        dfsGraph.addEdge(0, 1); // A-B
        dfsGraph.addEdge(0, 2); // A-C
        dfsGraph.addEdge(1, 3); // B-D
        dfsGraph.addEdge(2, 4); // C-E
        dfsGraph.addEdge(3, 4); // D-E

        System.out.print("DFS Traversal (Starting from A): ");
        dfsGraph.dfs(0);

        System.out.println("\n\nBFS using Adjacency List:");
        BFSGraph bfsGraph = new BFSGraph(5);
        bfsGraph.addEdge(0, 1); // A-B
        bfsGraph.addEdge(0, 2); // A-C
        bfsGraph.addEdge(1, 3); // B-D
        bfsGraph.addEdge(2, 4); // C-E
        bfsGraph.addEdge(3, 4); // D-E

        System.out.print("BFS Traversal (Starting from A): ");
        bfsGraph.bfs(0);
    }
}
