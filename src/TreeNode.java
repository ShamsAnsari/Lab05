public class TreeNode<E> {
    private E data;
    private TreeNode<E> rightChild;
    private TreeNode<E> leftChild;

    public TreeNode(E data, TreeNode<E> rightChild, TreeNode<E> leftChild) {
        this.data = data;
        this.rightChild = rightChild;
        this.leftChild = leftChild;
    }

    public TreeNode(E data) {
        this.data = data;
    }

    public TreeNode(){

    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public TreeNode<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode<E> rightChild) {
        this.rightChild = rightChild;
    }

    public TreeNode<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode<E> leftChild) {
        this.leftChild = leftChild;
    }

}
