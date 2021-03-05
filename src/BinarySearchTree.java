
import java.util.Comparator;

public class BinarySearchTree<E> {

    private TreeNode<E> root;
    private int count;
    private Comparator<E> c;

    public BinarySearchTree(Comparator<E> c) {
        this.c = c;
    }


    public TreeNode<E> search(E element){

    }
    public void insert(E element) {
        TreeNode<E> node = new TreeNode<>(element);
        if (root == null) {
            root = node;
        } else {
            TreeNode<E> currentNode = root;
            while (currentNode != null) {
                if (c.compare(node.getData(), currentNode.getData()) < 0) {
                    if (currentNode.getLeftChild() == null) {
                        currentNode.setLeftChild(node);
                        currentNode = null;
                    } else {
                        currentNode = currentNode.getLeftChild();
                    }
                } else {
                    if (currentNode.getRightChild() == null) {
                        currentNode.setRightChild(node);
                        currentNode = null;
                    } else {
                        currentNode = currentNode.getRightChild();
                    }

                }
            }
        }
        count += 1;
    }

    public void remove(E element) {
        TreeNode<E> parent = null;
        TreeNode<E> curr = root;
        while (curr != null) { // Search Node
            if (curr.getData().equals(element)) { // Node found
                if (curr.getLeftChild() == null && curr.getRightChild() == null) { // Remove leaf
                    if (parent == null) { //Node is root
                        root = null;
                    } else if (parent.getLeftChild() == curr) {
                        parent.setLeftChild(null);
                    } else {
                        parent.setRightChild(null);
                    }
                } else if (curr.getRightChild() == null) { //Remove node with only left child
                    if (parent == null) {//Node is root
                        root = curr.getLeftChild();
                    } else if (parent.getLeftChild() == curr) {
                        parent.setLeftChild(curr.getLeftChild());
                    } else {
                        parent.setRightChild(curr.getLeftChild());
                    }
                } else if (curr.getLeftChild() == null) { //Remove node with only right child
                    if (parent == null) {//Node is root
                        root = curr.getRightChild();
                    } else if (parent.getLeftChild() == curr) {
                        parent.setLeftChild(curr.getRightChild());
                    } else {
                        parent.setRightChild(curr.getRightChild());
                    }
                } else { //Remove node with two children
                    // Find successor (leftmost child of right subtree)
                    TreeNode<E> succ = curr.getRightChild();
                    while (succ.getLeftChild() != null)
                        succ = succ.getLeftChild();
                    E successorData = succ.getData();
                    remove(succ.getData());
                    curr.setData(successorData);
                }
                count -= 1;
                return; //Node found and removed
            } else if (c.compare(curr.getData(), element) < 0) {
                parent = curr;
                curr = curr.getRightChild();
            } else {
                parent = curr;
                curr = curr.getLeftChild();
            }
        }
        return; // Node not found

    }

    public int getCount(){
        return count;
    }
    public TreeNode<E> getRoot() {
        return root;
    }
}
