import java.util.*;

/**
 * Class that simulates a balanced binary search tree that
 * applies AVL balance property
 */
public class AVLTree {

    private Node root;

    AVLTree() {
        this.root = null;
    }

    AVLTree(int key) {
        this.root = new Node(key);
    }

    /**
     * Method that inserts a new key into the current AVL tree.
     * Duplicate keys not allowed.
     * @param key Value to be inserted
     * @param node Root of the tree to be inserted to.
     * @return The new tree with the inserted key.
     */
    public Node insert(int key, Node node) {
        if(node.value == key) {
            return node;
        } else if(node == null) {
            return new Node(key);
        }

        if(key > node.value)
            node.right = insert(key, node.right);
        else
            node.left = insert(key, node.left);

        // Update height of the current node at each level after inserton.
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        return balance(node);
    }

    /**
     * Method that deletes a key from the AVL Tree
     * @param key Value to be deleted
     * @param node Root of the tree to delete the key.
     * @return The new tree with the deleted key.
     */
    public Node delete(int key, Node node) {
        if(node.value == key) {
            // If no children, return null
            if(node.left == null && node.right == null)
                return null;
            // If only have left child, return left subtree
            else if(node.left != null && node.right == null)
                return node.left;
            // If only have right child, return right subtree
            else if(node.left == null && node.right != null)
                return node.right;
            else { // Else find In-Order predecessor to replace current root
                // to maintain BST property.
                int newRoot = findInOrderPredecessor(node);
                delete(newRoot, node);
                node.value = newRoot;
            }
        }

        if(key > node.value)
            node.right = delete(key, node.right);
        else
            node.left = delete(key, node.left);

        return balance(node);
    }

    /**
     * Method that finds if the key is in the current tree.
     * @param key Value to be searched in the tree.
     * @param node Root node of the tree to be searched.
     * @return True if the value is present, False otherwise.
     */
    public boolean search(int key, Node node) {
        if(node.value == key)
            return true;

        if(key > node.value)
            return search(key, node.right);
        else
            return search(key, node.left);
    }

    /**
     * Method that gets the height of the current tree.
     * @param node Root of the tree to find the height.
     * @return The height of the tree with node as it's root.
     */
    public int getHeight(Node node) {
        if(node == null)
            return 0;

        return node.height;
    }

    /**
     * Method that finds the in-order predecessor of the current tree.
     * @param node Root of the tree to find in-order predecessor.
     * @return the value of the in-order predecessor.
     */
    public int findInOrderPredecessor(Node node) {
        Node newRoot = node.left;
        while(newRoot.right != null)
            newRoot = newRoot.right;

        return newRoot.value;
    }

    /**
     * Method that finds the in-order successor of the current tree.
     * @param node Root of the tree to find in-order successor.
     * @return the value of the in-order successor.
     */
    public int findInOrderSuccessor(Node node) {
        Node newRoot = node.right;
        while(newRoot.left != null)
            newRoot = newRoot.left;

        return newRoot.value;
    }

    /**
     * Method that balances the tree with node as the current root.
     * Does the appropriate rotation if difference in height between tghe 2
     * subtrees is greater than 1.
     * @param node The current root of the tree.
     * @return the balanced tree
     */
    private Node balance(Node node) {
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        if(leftHeight - rightHeight > 1) { // Left subtree higher than right
            int leftLeft = getHeight(node.left.left);
            int leftRight = getHeight(node.left.right);
            if(leftLeft > leftRight) // left left case
                return rotateRight(node);
            else { // left right case
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        } else if(rightHeight - leftHeight > 1) { // Right subtree higher
            // than left
            int rightLeft = getHeight(node.right.left);
            int rightRight = getHeight(node.right.right);
            if(rightRight > rightLeft) // right right case
                return rotateLeft(node);
            else { // right left case
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }
        // No violation, return node
        return node;
    }

    /**
     * Rotates the root node of the current tree to the left.
     * @param node Root of the current tree.
     * @return Rotated tree.
     */
    private Node rotateLeft(Node node) {
        // Perform left rotation
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        //update heights
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight
                (newRoot.right));

        return newRoot;
    }

    /**
     * Rotates the root node of the current tree to the right.
     * @param node Root of the current tree.
     * @return Rotated tree.
     */
    private Node rotateRight(Node node) {
        // Perform right rotation
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        // update heights
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight
                (newRoot.right));

        return newRoot;
    }

}