import java.util.*;

public class DirectedGraph {

    public ArrayList<LinkedList<Edge>> _adjList;

    public DirectedGraph(int numVertices) {
        _adjList = new ArrayList<LinkedList<Edge>>();
        while (numVertices-- > 0) _adjList.add(new LinkedList<Edge>());
    }
    public void addEdge(int from, int to, int wt) {
        _adjList.get(from).add(new Edge(from, to, wt));
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ " + _adjList.size() + " vertices :\n");
        for (int row = 0; row < _adjList.size(); row++) {
            sb.append("\t" + row + " :");
            for (Edge edge : _adjList.get(row)) sb.append(" " + edge);
            sb.append("\n");
        }
        return sb + "]";
    }
    // change repr. to allow easy printing, but loses dest. distances
    public static DirectedGraph toDirectedGraph(ArrayList<Vertex> aug) {
        DirectedGraph newGraph = new DirectedGraph(aug.size());
        for (Vertex vert : aug)
            for (Edge edge : vert.edges())
                newGraph._adjList.get(edge.getFrom()).add(edge);
        return newGraph;
    }
}