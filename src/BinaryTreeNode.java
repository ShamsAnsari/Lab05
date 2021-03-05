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
 * Represents a node in a binary tree
 */
public class BinaryTreeNode<E> {
    /**
     * Value of this node
     */
    private E data;

    /**
     * Right child of node
     */
    private BinaryTreeNode<E> right;

    /**
     * Left child of node
     */
    private BinaryTreeNode<E> left;

    /**
     * Constructs a node with given data, and children
     * @param data value of node
     * @param right right child of node
     * @param left left child of node
     */
    public BinaryTreeNode(E data, BinaryTreeNode<E> right, BinaryTreeNode<E> left) {
        this.data = data;
        this.right = right;
        this.left = left;
    }

    /**
     * COnstructs a node with given value and no children
     * @param data value of node
     */
    public BinaryTreeNode(E data) {
        this.data = data;
    }

    /**
     * Default Constructor
     */
    public BinaryTreeNode(){

    }

    /**
     * @return data
     */
    public E getData() {
        return data;
    }

    /**
     * Sets data
     * @param data value of node
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Gets right child if node
     * @return right child of node
     */
    public BinaryTreeNode<E> getRight() {
        return right;
    }

    /**
     * Sets right child of node
     * @param right node
     */
    public void setRight(BinaryTreeNode<E> right) {
        this.right = right;
    }

    /**
     * Gets left child of node
     * @return BinaryTreeNode
     */
    public BinaryTreeNode<E> getLeft() {
        return left;
    }

    /**
     * Sets left child of node
     * @param left BinaryTreeNode
     */
    public void setLeft(BinaryTreeNode<E> left) {
        this.left = left;
    }

}
