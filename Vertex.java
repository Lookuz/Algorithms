import java.util.*;

// Augmented vertex that stores shortest distance from source
// Used to store SSSP result
// Natural ordering : (1) lowest distance, then (2) smallest idx
class Vertex implements Comparable<Vertex> {
    private int _index, _dist;
    private LinkedList<Edge> _edges;

    public Vertex(int index) {
        _index = index; _dist = 1000000000; // assume unreachable
        _edges = new LinkedList<Edge>();
    }

    public Vertex(int index, int _dist) {
        this._index = index;
        this._dist = _dist;
    }

    public String toString() { return "(V" + _index +": "+ _dist + ")"; }
    public int compareTo(Vertex other) {
        if (_dist != other._dist) return _dist - other._dist;
        return _index - other._index;
    }
    public int getIndex() { return _index; }
    public int getDistance() { return _dist; }
    public Iterable<Edge> edges() { return _edges; }
    public void setDistance(int newDist) { _dist = newDist; }
    public void addEdge(Edge edge) { _edges.add(edge); }
}