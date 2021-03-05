/**
 * Lab 4
 *
 * @Author Shams Ansari
 * Learn about Lists, Stacks and Queues
 * <p>
 * Prof: Manish Goel
 * Class: CIS22C
 * @Date: 2/14/2021
 *
 *Represents a node in a LinearList
 */
public class LinkNode<E> {

    /**
     * Value/Data of node
     */
    private E data;

    /**
     * Next node
     */
    private LinkNode next;


    /**
     * Default constructor
     */
    public LinkNode() {
    }

    /**
     *Creates a node with value data and null next
     * @param data element value
     */
    public LinkNode(E data) {
        this(data, null);
    }

    /**
     * Creates a Linknode with specified data and next
     * @param data E
     * @param next Linknode
     */
    public LinkNode(E data, LinkNode next) {
        setData(data);
        setNext(next);
    }
    

    //===================
    //Getters and setters
    //===================
    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public LinkNode<E> getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }
}
