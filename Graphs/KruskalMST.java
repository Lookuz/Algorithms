import java.util.*;

/**
 * Algorithm implementation of Kruskal's Algorithm which finds
 * the minimum spanning tree on a undirected graph.
 * Uses the concept of Union-Find, Weighted Union and Path Compression.
 */
public class KruskalMST {

    private int[] parent; // array that stores the parent of the ith vertex
    private DirectedGraph graph; // Implementation uses directed graph in an
    // undirected manner
    private int[] size; // arrau that stores the size of a component tree
    private int numVertex;

    KruskalMST(int numVertex, DirectedGraph graph) {
        this.numVertex = numVertex;
        this.parent = new int[numVertex];
        this.size = new int[numVertex];
        this.graph = graph;
        for(int i = 0; i < numVertex; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    public DirectedGraph findMST() {
        DirectedGraph result = new DirectedGraph(this.numVertex); // resulting
        // graph to store MST
        PriorityQueue<Edge> minHeap = getMinEdges(); // Get a minHeap of
        // Edges to process
        HashSet<Integer> visited = new HashSet<>(); // Set that stores the
        // indexes of vertices that are processed

        while(visited.size() < this.numVertex) {
            Edge curr = minHeap.poll();

            if(find(curr.getTo(), curr.getFrom())) // If both vertices are
                // already connected, skip
                continue;

            union(curr.getTo(), curr.getFrom()); // join both components
            result.addEdge(curr.getFrom(), curr.getTo(), curr.getWeight());
            visited.add(curr.getTo());
            visited.add(curr.getFrom());
        }
        return result;
    }

    private PriorityQueue<Edge> getMinEdges() {
        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        for(LinkedList<Edge> list : this.graph._adjList)
            for(Edge e : list)
                minHeap.offer(e);

        return minHeap;
    }

    /**
     * Method that finds the root of 2 components, and returns if the 2
     * components are connected
     * @return true if connected, else false
     */
    private boolean find(int first, int second) {
        // Gets the root of each vertex, and applies path compression
        while(this.parent[first] != first) {
            this.parent[first] = this.parent[parent[first]];
            first = this.parent[first];
        }
        while(this.parent[second] != second) {
            this.parent[second] = this.parent[parent[second]];
            first = this.parent[second];
        }

        return (first == second);
    }

    /**
     * Method that connects 2 components if they're not connected,
     * else prints error message saying that cycle is detected.
     */
    private boolean union(int first, int second) {
        // Get root of both components
        first = findRoot(first);
        second = findRoot(second);

        /**
         * Using weighted union to merge smaller tree into larger tree using
         * size array
         */
        if (second != first) { // if different component, merge both components
            if (this.size[first] > this.size[second]) {// if first component
                // larger than second, merge second into first
                this.parent[second] = first;
                this.size[first] += this.size[second];
            } else {
                this.parent[first] = second;
                this.size[second] += this.size[first];
            }

            return true;
        }
        // Else there is a cycle
        return false;
    }

    /**
     * Method that finds the root of a vertex.
     * @param child
     * @return the root of the vertex child
     */
    private int findRoot(int child) {
        while(this.parent[child] != child)
            child = this.parent[child];

        return child;
    }
}