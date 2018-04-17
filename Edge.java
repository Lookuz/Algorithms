import java.util.*;

// Natural ordering : lowest (1) wt, (2) from vert., then (3) to vert.
class Edge implements Comparable<Edge> {
    private int _from, _to, _wt;
    public Edge(int from, int to, int wt) {
        _from = from; _to = to; _wt = wt;
    }
    public String toString() { return "("+_from+"->"+_to+": "+_wt+")"; }
    public int compareTo(Edge other) {
        if (_wt != other._wt) return _wt - other._wt;
        if (_from != other._from) return _from - other._from;
        return _to - other._to;
    }
    public int getFrom() { return _from; }
    public int getTo() { return _to; }
    public int getWeight() { return _wt; }
}