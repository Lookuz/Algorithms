import java.util.*;

/**
 * Prim's algorithm for MST
 * PRE-CONDITIONS:
 * Connected graph
 * Undirected graph -> if directed, MST results depends on the starting vertex
 * Used to find minimum total cost of the graph.
 * Can also be used to find 'easiest' path from 1 node to another,
 * i.e the maximum weight along the path from one node to another.
 * MST is unique ONLY if the weights are unique(no repeated weight values).
 * If Graph used is DIRECTED, Tree result will be SPANNING but not MINIMUM
 * (See Edmond's Algorithm)
 */
public class PrimMST {

    private DirectedGraph graph;
    private HashSet<Integer> unvisited; // HashSet that tracks if there are
    // still any unconnected nodes
    private ArrayList<Vertex> vertexList;
    private PriorityQueue<Edge> minHeap; // Min heap keeps track of all
    // possible edges at any point in time
    private int size;

    PrimMST(DirectedGraph graph, int size) {
        this.size = size;
        this.unvisited = new HashSet<Integer>();
        this.graph = graph;
        this.minHeap = new PriorityQueue<>();
        for (int i = 0; i < size; i++) {
            this.unvisited.add(i);
        }
    }

    public DirectedGraph findMST(int startVert) {
        DirectedGraph result = new DirectedGraph(this.size); // MST to be
        // returned
        for(Edge e : this.graph._adjList.get(startVert))
            this.minHeap.offer(e);
        this.unvisited.remove(startVert);

        while(!unvisited.isEmpty()) {
            Edge minEdge = minHeap.poll();
            if(!this.unvisited.contains(minEdge.getTo())) // if connecting
                // vertex already in graph, skip because will create cycle otherwise
                continue;

            this.unvisited.remove(minEdge.getTo());
            result.addEdge(minEdge.getFrom(), minEdge.getTo(), minEdge
                    .getWeight());
            result.addEdge(minEdge.getTo(), minEdge.getFrom(), minEdge
                    .getWeight());

            for(Edge e : this.graph._adjList.get(minEdge.getTo())) // For each
                // edge in the connecting vertex, add to minHeap
                this.minHeap.offer(e);
        }
        return result;
    }
}