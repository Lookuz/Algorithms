#Graphs<br />
Implementation of a directed graph, and various graph algorithms to find Minimum Spanning Tree and Shortest Paths. All graph algorithms uses the DirectedGraph, Vertex and Edge classes.

###Single-Source Shortest Paths
Algorithms that find the shortest distance from a node to another node in a graph structure.
####Dijkstra's Algorithm
Algorithm that finds the shortest path from the source vertex to any other vertex. At initialization, the distance of the source vertex is set to 0, and the rest of the distances of the other vertices are set to *infinity*.
At each stage, the vertex with the shortest distance, vertex *u* is picked. Let the distance between the source vertex and any vertex v be *d(v)*. If the distance from the source vertex to any vertex *u* is known, and *u* is connected to *v*, then the distance from the source vertex to the vertex *v* *d(v)* is given by the formula *d(v) = d(u) + d(u,v) = d(u) + weight(u,v)* where distance to the vertex *v* is given by distance from source vertex to vertex *u*, plus the weight of the edge *e* connecting vertices *u* and *v*.
<br/>
The distance *d(u) + weight(u,v)* is taken for each neighbour *v* of a vertex *u* whose shortest distance is known, and if this new distance is smaller than the current distance of the vertex *v*, the distance of vertex *v* is updated with the new distance. When the vertex *u* is selected to calculate the distance of it's neighbours, it is marked as visited and never processed again.
<br/>
This algorithm for Dijkstra's SSSP uses a *modified* algorithm implementing lazy deletion using a PriorityQueue/Heap, where old/outdated nodes that are already processed are deleted only when they are removed from the front of the heap.
Time complexity for removing each vertex with the shortest element in *O(lgV)*.
Time complexity to run the algorithm on the entire graph is *O((V + E)lgV)*.
<br />

###Minimum Spanning Tree
Algorithms that form a Minimum Spanning Tree(MST) from a graph, which is a subset of edges from a connected graph that connects all vertices in a graph without any cycles, using minimum total edge weight. if there are *N* vertices in the graph, then the number of edges in the MST is *N - 1*.
Minimum Spanning Trees calculate the minimum total cost of the graph, as well as the *easiest* path from one node to the next(The highest weight of an edge from one vertex to another).
This implewmentation uses a PriorityQueue/Heap to maintain a Minimum Heap of Edges.
<br />
####Prim's Algorithm
Algorithm that forms the MST from an undirected graph, by growing the MST one vertex at a time. The MST is first formed with the source vertex. At each stage, the edge *e* with the smallest weight that is connected to the current minimum tree is picked, and if it connects the tree to an unvisited *u*, *u* is added the spanning tree.
Time complexity for getting shortest edge is *O(lgV)*.
Time complexity for running the algorithm on the entire graph is *O((V + E)lgV)*.
<br />

####Union-Find Disjoint Set
A disjoint set is a data structure that tracks the partitioning of elements in a graph into subsets. Elements in the same subset are in the same component and hence in the same subset.
This implementation uses a Tree-Set structure to store elements in the same subset, which will have the same root. This implementation supports *O(lgN)* time complexity for both Quick-Find and Quick-Union.
Operations supported for Find(which searches if 2 subsets are connected) and Union(that joins 2 subsets).
<br />
####Kruskal's Algorithm
Algorithm that forms an MST by connecting 2 trees in a graph at any time. Uses the disjoint set to identify connected components(Trees). Each vertex at initialization is a tree with a single node.
At initialization, a PriorityQueue/Heap is used to maintain a minimum heap of all edges in the graph. Each step of the Algorithm retrieves the smallest weighted edge in the graphd, which takes *O(lg(V + E))* time complexity.
Time complexity of running the algorithm on a graph is *O(Elg(V + E))*.
