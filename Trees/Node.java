import java.util.*;

/**
 * Class that simulates a node in a tree data structure.
 * Each Node contains it's left and right subtree in the form of left and
 * right nodes.
 */
public class Node {

    public int value; // value of the current node.
    public int height; // height of the tree with the current node as the root.
    public Node left; // left subtree of the current node.
    public Node right; // right subtree of the current node.

    Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}