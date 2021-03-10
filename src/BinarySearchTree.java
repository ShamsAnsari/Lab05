
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
public class BinarySearchTree<E extends Comparable<E>> {

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
     */
    public BinarySearchTree() {

    }

    /**
     * Inserts the given element into the BST
     *
     * @param element element to insert
     */
    public void insert(E element) {

        if(search(element) != null){
            return;
        }
        BinaryTreeNode<E> node = new BinaryTreeNode<>(element);
        if (root == null) {
            root = node;
        } else {
            BinaryTreeNode<E> currentNode = root;
            while (currentNode != null) {
                if (node.getData().compareTo(currentNode.getData()) < 0) {
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
     * <p>
     *  Pseudo code for if/when node found:
     * ------ if node is leaf node
     * ---------- if node is root node
     * -------------- root = null
     * ---------- else if node is left node of parent
     * -------------- set parent's left node to null
     * ---------- else
     * -------------- set parent's right node to null
     *
     * ------ else if node has only left child
     * ---------- if node is root node
     * -------------- root = null
     * ----------- else if node is left node of parent
     * -------------- set parent's left to node's left
     * ----------- else node is right node of parent
     * -------------- set parent's right to node's left
     *
     * ------ else if node has only right child
     * ---------- if node is root node
     * -------------- root = null
     * ----------- else if node is left node of parent
     * -------------- set parent's left to node's right
     * ----------- else node is right node of parent
     * -------------- set parent's right to node's right
     *
     * ------ else remove node with 2 children
     * ---------- find the leftmost child of right subtree
     * ---------- copy successor data
     * ---------- recursively remove successor node
     * ---------- set node to successor data
     * ------ decrement count
     *
     *
     * @param element Element to remove
     */
    public void remove(E element) {
        BinaryTreeNode<E> parent = null;
        BinaryTreeNode<E> curr = root;
        while (curr != null) { // Search Node
            if (curr.getData().compareTo(element) == 0) { // Node found, see pseudocode
                if (curr.getLeft() == null && curr.getRight() == null) { // Remove leaf
                    if (parent == null) { //Node is root
                        root = null;
                    } else if (parent.getLeft() == curr) { // Parent's left node
                        parent.setLeft(null);
                    } else { // Parent's right node
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
            } else if (curr.getData().compareTo(element) < 0) {
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
            if (cur.getData().compareTo(element) == 0) {
                return cur; //Found
            } else if (element.compareTo(cur.getData()) < 0) {
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
        if (root == null) {
            return traversal;
        }
        //Queue with use of algorithm
        Queue<BinaryTreeNode<E>> queue = new Queue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<E> cur = queue.dequeue();
            traversal.enqueue(cur.getData()); // put node in queue

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
        if (root == null) {
            return traversal;
        }
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
     *
     * @param node      current node
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
     *
     * @return string output of traversal in this order: level order, inorder, preorder, postorder
     */
    public String print() {

        //String[] traversal = {levelOrder(), preorder(), inorder(), postorder()};
        Queue<E> preorder_traversal = preorder();
        Queue<E> inorder_traversal = inorder();
        Queue<E> postorder_traversal = postorder();
        Queue<E> levelorder_traversal = levelOrder();

        String output = "Level order: " + levelorder_traversal.toString() + "\n"
                + "Inorder: " + inorder_traversal.toString() + "\n"
                + "Preorder: " + preorder_traversal.toString() + "\n"
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
     * Adapted from https://stackoverflow.com/a/27153988/10200349 To work for binary search tree and my specific classes.
     *
     * @param prefix  the path to node ex: |, └──, ┌──, (edge)
     *
     * @param isTail  for recursion, a type of edge (|)
     * @param sb the current tree
     * @param node the current node
     * @return A 2D representation of the tree
     */
    private StringBuilder toString2D(StringBuilder prefix, boolean isTail, StringBuilder sb, BinaryTreeNode<E> node) {
        if (node.getRight() != null) {
            toString2D(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, node.getRight());
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.getData().toString()).append("\n");
        if (node.getLeft() != null) {
            toString2D(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, node.getLeft());
        }
        return sb;
    }

    /**
     * Converts tree to string
     * @return A 2D string representation of tree (Horizontal)
     */
    @Override
    public String toString() {
        if (root == null) {
            return null;
        }
        return toString2D(new StringBuilder(), true, new StringBuilder(), root).toString();

    }

}
