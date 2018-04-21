import java.util.*;

/**
 * Class that implements the union-find operations in a disjoint set.
 * Useful in Kruskal's algorithm, cycle detecton.
 * Uses the idea of marking components by it's component number/root
 */
public class UnionFind {

    private int[] parent; // Array that contains the parent of the ith element
    private DirectedGraph graph;
    private int[] size; // Size of each subtree of connected elements
    private int numVertex;

    UnionFind(int numVertex, DirectedGraph graph) {
        this.numVertex = numVertex;
        this.parent = new int[numVertex];
        this.size = new int[numVertex];
        this.graph = graph;
        for(int i = 0; i < numVertex; i++) {
            this.parent[i] = i;
        }
    }

    /**
     * Method that connects 2 components if they're not connected,
     * else prints error message saying that cycle is detected.
     */
    public boolean union(int first, int second) {
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
     * Method that finds the root of 2 components, and returns if the 2
     * components are connected
     * @return true if connected, else false
     */
    public boolean find(int first, int second) {
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
     * Method that finds the root of a vertex.
     * @param child
     * @return the root of the vertex child
     */
    public int findRoot(int child) {
        while(this.parent[child] != child)
            child = this.parent[child];

        return child;
    }
}