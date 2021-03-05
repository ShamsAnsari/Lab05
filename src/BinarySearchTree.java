/**
 * Lab 5
 *
 * @Author Shams Ansari
 * purpose: To learn about Binary Search Trees
 * <p>
 * Prof: Manish Goel
 * Class: CIS22C
 * @Date: 3/4/2021
 *
 *
 * Represents a Binary Search Tree
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
     * Constructs a Binary Search Tree with given node as the root
     *
     * @param root Root node of this tree
     */
    public BinarySearchTree(BinaryTreeNode<E> root) {
        this.root = root;
        count++;
    }

    /**
     * Constructs a empty Binary Search Tree
     */
    public BinarySearchTree() {

    }

    //TODO: print(), deleteSubTree()
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
     * Finds the node with the smallest value in the given tree/subtree
     *
     * @param node root node of subtree
     * @return Node if found, else null
     */
    public BinaryTreeNode<E> findSmallest(BinaryTreeNode<E> node) {
        if (node == null) {
            return null;
        }
        if (node.getLeft() == null) {
            return node;
        }

        return findSmallest(node.getLeft());
    }

    /**
     * Prints BST in level order using Queue (iterative)
     */
    public void levelOrder() {
        Queue<BinaryTreeNode<E>> queue = new Queue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<E> cur = queue.dequeue();
            System.out.print(cur.getData() + " ");
            if (cur.getLeft() != null) {
                queue.enqueue(cur.getLeft());
            }
            if (cur.getRight() != null) {
                queue.enqueue(cur.getRight());
            }
        }
    }

    /**
     * Prints BST in preorder (recursively)
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Does the recursive work for preorder() function
     *
     * @param node Current node
     */
    private void preorder(BinaryTreeNode<E> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getData() + " ");
        preorder(node.getLeft());
        preorder(node.getRight());
    }

    /**
     * Prints BST in postorder (recursively)
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Does the recursive work for postorder()  function
     *
     * @param node Current node
     */
    private void postorder(BinaryTreeNode<E> node) {
        if (node == null) {
            return;
        }

        postorder(node.getLeft());
        postorder(node.getRight());
        System.out.print(node.getData() + " ");
    }

    /**
     * Prints BST inorder (recursively)
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Does the recursive work for inorder() function
     * @param node Current node
     */
    private void inorder(BinaryTreeNode<E> node) {
        if (node == null) {
            return;
        }
        inorder(node.getLeft());
        System.out.print(node.getData() + " ");
        inorder(node.getRight());
    }

    /**
     * Checks if the BST is empty
     * @return True if the BST is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null && count == 0;
    }

    /**
     * Size of BST
     * @return Number of elements in BST
     */
    public int getCount() {
        return count;
    }

    /**
     *
     * @return Root of BST
     */
    public BinaryTreeNode<E> getRoot() {
        return root;
    }
}
