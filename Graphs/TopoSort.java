import java.util.*;

/**
 * Performs Topological sort on a directed graph.
 * Nodes that come before another will appear earlier in the
 * topological order
 * If there are non-unique weights, Topo Sort may not be unique.
 * If graph contains a cycle, Topological Sort will not be correct.
 */
public class TopoSort {

    private DirectedGraph graph; // Graph to perform Topo Sort on
    private HashSet<Integer> unvisited; // HashSet that contains the
    // unprocessed vertices
    private Queue<Integer> queue; // Queue that contains the order of which
    // vertices that enqueued in topological order
    private int size;
    private int[] inDeg;

    TopoSort(int size, DirectedGraph graph) {
        this.size = size;
        this.graph = graph;
        this.queue = new LinkedList<Integer>();
        this.inDeg = new int[size];
        this.unvisited = new HashSet<>();
        for(int i = 0; i < size; i++) {
            this.unvisited.add(i);
        }
    }

    // Method that increments the in degree upon adding an edge
    public void setEdge(int from, int to) {
        this.inDeg[to]++;
    }

    /**
     * Method that performs topo sort on the graph in this class.
     * @return a Queue containing the topological order of vertices.
     */
    public Queue<Integer> topoSort() {
        Queue<Integer> result = new LinkedList<Integer>(); // Queue that
        // stores the result of the topological sort
        // Enqueue all vertices with In-Degree 0
        for(int i = 0; i < size; i++)
            if(inDeg[i] == 0) {
                this.queue.offer(i); // Remove the vertices
                // enqueued from the unprocessed vertices.
                this.unvisited.remove(i); // Mark curr as processed
            }

        while(!queue.isEmpty()) {
            int curr = this.queue.poll();

            for(Edge e : this.graph._adjList.get(curr)) {
                this.inDeg[e.getTo()]--; // Decrement the in degree of the
                // neighbours
                if(this.unvisited.contains(e.getTo()) && this.inDeg[e.getTo()] == 0) {
                    // if the neighbour is still unprocessed,
                    // and contains in-degree 0, enqueue for next.
                    this.queue.offer(e.getTo());
                    this.unvisited.remove(e.getTo());
                }
            }

            result.offer(curr);
        }
        return result;
    }
}