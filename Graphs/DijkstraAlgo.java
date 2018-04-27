import java.util.*;

/**
 * Dijkstra's algorithm for Single-Source Shortest Path.
 * PRE-CONDITIONS: no negative edges
 * Result is an ArrayList of vertices that each store the SHORTEST PATH from
 * the source vertex.
 */
public class DijkstraAlgo {

    private DirectedGraph graph;
    private ArrayList<Vertex> vertexList;
    private boolean[] visited;
    private PriorityQueue<Vertex> minHeap; // MinHeap that keeps track of
    // current minimum vertex
    private int size;

    DijkstraAlgo(DirectedGraph graph, int size) {
        this.graph = graph;
        this.size = size;
        this.visited = new boolean[size];
        this.minHeap = new PriorityQueue<>();
        this.vertexList = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            this.vertexList.add(new Vertex(i));
        }
    }

    public ArrayList<Vertex> computeShortestPaths(int sourceVert) {
        this.vertexList.get(sourceVert).setDistance(0);
        this.minHeap.offer(this.vertexList.get(sourceVert));
        int to;

        while(!minHeap.isEmpty()) {
            Vertex curr = minHeap.poll(); // Get min item
            int currIdx = curr.getIndex(); // Get min item index
            if(visited[currIdx])
                continue; // If the node is already processed, skip. This
            // eliminates the problem of queuing multiple of the same node.
            for(Edge e : graph._adjList.get(currIdx)) { // For each edge
                // belonging to the current node
                curr.addEdge(e);
                to = e.getTo(); // get the index of the edge going to
                if(visited[to])
                    continue; // if neighbour is already processed, skip
                int newDist = curr.getDistance() + e.getWeight();
                if(newDist < this.vertexList.get(to).getDistance()) { // if
                    // distance(v) + weight(v,w) < distance(w)
                    this.vertexList.get(to).setDistance(newDist); // set
                    // neighbour as new distance3
                    this.minHeap.offer(new Vertex(to, newDist));
                }
            }
            visited[currIdx] = true;
        }
        return this.vertexList;
    }
}