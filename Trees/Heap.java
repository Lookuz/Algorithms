/**
 * Class that simulates a Heap/PriorityQueue class. Elements with highest
 * priority will be at the top of the heap(nearer to index 0 in heap array).
 * This implementation uses an integer array, using minHeap priority.
 * Supports efficient insert, delete operations.
 *
 * Heap Property: At each level, the value of a parent node must have a
 * greater priority over that of the children nodes. There is no ordering
 * between priorities of children nodes.
 * Heap must always fulfil complete tree property.
 */
public class Heap {
    private int[] arr; //array used to store heap elements, topmost element
    // in a heap is always at index 0.
    // obtain parent: arr[(n - 1)/2]
    // obtain children: left child: arr[2n + 1] right child: arr[2n + 2]
    private int size; // size of the array

    /**
     * Empty constructor.
     * Initializes default size to 10.
     */
    Heap() {
        this.arr = new int[10];
        this.size = 0;
    }

    /**
     * Constructor that initializes a Heap using an existing array.
     * @param arr Array to be assigned to current Heap.
     */
    Heap(int[] arr) {
        this.arr = arr;
        this.size = arr.length;
    }

    /**
     * Builds a heap by constructing a heap from an existing array.
     * @param heap Array to be constructed into a Heap.
     * @return new Heap object with the constructed Heap array.
     */
    public static Heap heapConstruction(int[] heap) {
        for(int i = heap.length/2; i >= 0; i--)
            Heap.heapRebuild(i, heap);

        return new Heap(heap);
    }

    /**
     * Method that does building of a heap by bubbling down at a parent node
     * recursively.
     * @param index Index to rebuild heap from
     * @param heap Array to rebuild.
     * @return The rebuilt Heap array
     */
    private static int[] heapRebuild(int index, int[] heap) {
        int left = (2 * index) + 1;
        int right = (2 * index) + 2;
        int child;
        if(left >= heap.length) // only left child
            return heap;
        else if(right >= heap.length)
            child = heap[right];
        else
            child = (heap[left] < heap[right]) ? heap[left] :
                    heap[right];

        if (heap[child] < heap[index]) {
            Heap.swap(child, index, heap);
            Heap.heapRebuild(child, heap);
        }

        return heap;
    }

    /**
     * Method that inserts a key into the Heap.
     * @param key Element to be inserted.
     */
    public void insert(int key) {
        if(this.arr.length >= this.size)
            this.reHeapify();

        this.arr[size++] = key;
        this.bubbleUp(size - 1);
    }

    /**
     * Method that polls the first element in the Heap(topmost element).
     * Removing the element is O(1) time complexity, replacing the new root
     * is O(lgN) time complexity.
     * @return The highest priority element in the Heap
     */
    public int delete() {
        if(this.size == 0)
            return -1;

        int result = this.arr[0];
        this.arr[0] = this.arr[this.size - 1]; // swap root with last element
        this.size--; // remove last element(previous root)
        this.bubbleDown(0); // bubble down new root to correct position

        return result;
    }

    /**
     * Method that checks if an element needs to be bubbled down to maintain
     * Heap property.
     * @param index Position in array to start bubbling.
     */
    private void bubbleUp(int index) {
        if(index == 0)
            return;

        int parent = (index - 1)/2;
        if (parent >= 0 && this.arr[index] < this.arr[parent]) {
            Heap.swap(index, parent, this.arr);
            this.bubbleUp(parent);
        }
    }

    /**
     * Method that checks if an element need sto be bubbled down to maintain
     * Heap property.
     * @param index Position in array to start bubbling.
     */
    private void bubbleDown(int index) {
        int left = (2 * index) + 1;
        int right = (2 * index) + 2;
        int child;

        if(left >= this.size) // if no children
            return;
        else if(right >= this.size)  // if only have left child
            child = this.arr[left];
        else
            child = (this.arr[left] < this.arr[right]) ? this.arr[left] :
                this.arr[right]; // Take child with the higher priority

        if(this.arr[child] < this.arr[index]) {
            Heap.swap(child, index, this.arr);
            this.bubbleDown(child);
        }
    }

    private static void swap(int first, int second, int[] arr) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    /**
     * Method that increases the size of the heap by putting all the elements
     * of thc current Heap into a new Heap array.
     */
    private void reHeapify() {
        int[] newArray = new int[this.size * 2];
        for(int i = 0; i < this.size; i++)
            newArray[i] = this.arr[i];

        this.arr = newArray;
    }
}
