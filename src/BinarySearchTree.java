
/**
 * Lab 5
 *
 * @Author Shams Ansari
 * purpose: To learn about Binary Search Trees
 * <p>
 * Prof: Manish Goel
 * Class: CIS22C
 * @Date: 3/4/2021
 * <p>
 * <p>
 * Represents a Binary Search Tree. Element E must implement comparable
 */
public class BinarySearchTree<E> {

    /**
     * Root node of this tree
     */
    private BinaryTreeNode<E> root;
    /**
     * Number of elements in this tree
     */
    private int count;


    /**
     * Constructs a empty Binary Search Tree
     *
     */
    public BinarySearchTree() {

    }

    /**
     * Inserts the given element into the BST
     *
     * @param element element to insert
     */
    public void insert(E element) {
        BinaryTreeNode<E> node = new BinaryTreeNode<>(element);
        if (root == null) {
            root = node;
        } else {
            BinaryTreeNode<E> currentNode = root;
            while (currentNode != null) {
                if (((Comparable) node.getData()).compareTo(currentNode.getData()) < 0) {
                    if (currentNode.getLeft() == null) {
                        currentNode.setLeft(node);
                        currentNode = null;
                    } else {
                        currentNode = currentNode.getLeft();
                    }
                } else {
                    if (currentNode.getRight() == null) {
                        currentNode.setRight(node);
                        currentNode = null;
                    } else {
                        currentNode = currentNode.getRight();
                    }

                }
            }
        }
        count++;
    }

    /**
     * Removes the element from the binary tree
     *
     * @param element Element to remove
     */
    public void remove(E element) {
        BinaryTreeNode<E> parent = null;
        BinaryTreeNode<E> curr = root;
        while (curr != null) { // Search Node
            if (curr.getData().equals(element)) { // Node found
                if (curr.getLeft() == null && curr.getRight() == null) { // Remove leaf
                    if (parent == null) { //Node is root
                        root = null;
                    } else if (parent.getLeft() == curr) {
                        parent.setLeft(null);
                    } else {
                        parent.setRight(null);
                    }
                } else if (curr.getRight() == null) { //Remove node with only left child
                    if (parent == null) {//Node is root
                        root = curr.getLeft();
                    } else if (parent.getLeft() == curr) {
                        parent.setLeft(curr.getLeft());
                    } else {
                        parent.setRight(curr.getLeft());
                    }
                } else if (curr.getLeft() == null) { //Remove node with only right child
                    if (parent == null) {//Node is root
                        root = curr.getRight();
                    } else if (parent.getLeft() == curr) {
                        parent.setLeft(curr.getRight());
                    } else {
                        parent.setRight(curr.getRight());
                    }
                } else { //Remove node with two children
                    // Find successor (leftmost child of right subtree)
                    BinaryTreeNode<E> succ = curr.getRight();
                    while (succ.getLeft() != null)
                        succ = succ.getLeft();
                    E successorData = succ.getData();
                    remove(succ.getData());
                    curr.setData(successorData);
                }
                count--;
                return; //Node found and removed
            } else if (((Comparable) curr.getData()).compareTo(element) < 0) {
                parent = curr;
                curr = curr.getRight();
            } else {
                parent = curr;
                curr = curr.getLeft();
            }
        }
        return; // Node not found

    }

    /**
     * Searches for Node with given element
     *
     * @param element key to search for
     * @return If node is found, return node, else return null
     */
    public BinaryTreeNode<E> search(E element) {
        BinaryTreeNode<E> cur = root;
        while (cur != null) {
            if (cur.getData().equals(element)) {
                return cur; //Found
            } else if (((Comparable) element).compareTo(cur.getData()) < 0) {
                cur = cur.getLeft();
            } else {
                cur = cur.getRight();
            }
        }
        return null; //Not found
    }

    /**
     * Finds the node with the smallest value in the given tree/subtree
     *
     * @param node root node of subtree
     * @return Smallest element
     */
    public E smallest(BinaryTreeNode<E> node) {
        BinaryTreeNode<E> current = node;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getData();
    }

    /**
     * Finds the node with the larget value in the given tree/subtree
     *
     * @param node root node of subtree
     * @return Largest element
     */
    public E largest(BinaryTreeNode<E> node) {
        BinaryTreeNode<E> current = node;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current.getData();
    }

    /**
     * Gets the level order traversal (Breadth first search) of the BST iteratively using a queue
     *
     * @return Queue E of traversal
     */
    public Queue<E> levelOrder() {
        //Storage Queue, not with use of algorithm
        Queue<E> traversal = new Queue<>();

        //Queue with use of algorithm
        Queue<BinaryTreeNode<E>> queue = new Queue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<E> cur = queue.dequeue();
            traversal.enqueue(cur.getData());
            if (cur.getLeft() != null) {
                queue.enqueue(cur.getLeft());
            }
            if (cur.getRight() != null) {
                queue.enqueue(cur.getRight());
            }
        }
        return traversal;
    }

    /**
     * Gets the pre order traversal of the BST iteratively using a Stack
     *
     * @return Queue E of traversal
     */
    public Queue<E> preorder() {
        //Storing traversal queue, not with use of algorithm
        Queue<E> traversal = new Queue<>();

        //Stack with use of algorithm
        Stack<BinaryTreeNode<E>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode<E> node = stack.pop();
            traversal.enqueue(node.getData());
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
        return traversal;
    }

    /**
     * Gets the post order traversal of the BST recursively
     *
     * @return Queue E of traversal
     */
    public Queue<E> postorder() {
        Queue<E> traversal = new Queue<>(); // Queue for storing traversal, not with use of algorithm
        postorder(root, traversal);
        return traversal;
    }

    /**
     * Recursive method for postorder()
     * @param node current node
     * @param traversal Queue of traversal
     */
    private void postorder(BinaryTreeNode<E> node, Queue<E> traversal) {
        if (node != null) {
            postorder(node.getLeft(), traversal);
            postorder(node.getRight(), traversal);
            traversal.enqueue(node.getData());
        }
    }

    /**
     * Gets the inorder traversal of the BST iteratively using a Stack
     *
     * @return Queue E of traversal
     */
    public Queue<E> inorder() {
        //Storing the traversal, not for use in algorithm
        Queue<E> traversal = new Queue<>();

        //Stack for use in algorithm
        Stack<BinaryTreeNode<E>> stack = new Stack<>();
        BinaryTreeNode<E> curr = root;

        while (curr != null || !stack.isEmpty()) {

            //Reach leftmost node of current node
            while (curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            }
            // curr is always null at this point
            curr = stack.pop();
            traversal.enqueue(curr.getData());
            // At this point: Visited curr node and its left subtree
            //Now to visit rigt subtree
            curr = curr.getRight();
        }

        return traversal;
    }

    /**
     * Prints all traversal in order specified below
     * @return string output of traversal in this order: levelorder, preorder, inorder, postorder
     */
    public String print() {

        //String[] traversal = {levelOrder(), preorder(), inorder(), postorder()};
        Queue<E> preorder_traversal = preorder();
        Queue<E> inorder_traversal = inorder();
        Queue<E> postorder_traversal = postorder();
        Queue<E> levelorder_traversal = levelOrder();

        String output = "Level order: " + levelorder_traversal.toString() + "\n"
                + "Preorder: " + preorder_traversal.toString() + "\n"
                + "Inorder: " + inorder_traversal.toString() + "\n"
                + "Postorder: " + postorder_traversal.toString() + "\n";
        System.out.print(output);
        return output;

    }

    /**
     * Clears tree
     */
    public void clear() {
        root = null;
    }

    /**
     * Checks if the BST is empty
     *
     * @return True if the BST is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null && count == 0;
    }

    /**
     * Size of BST
     *
     * @return Number of elements in BST
     */
    public int getCount() {
        return count;
    }

    /**
     * @return Root of BST
     */
    public BinaryTreeNode<E> getRoot() {
        return root;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return preorder().toString();
    }
}
