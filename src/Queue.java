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
 * Respresents a Queue ADT
 */
public class Queue<E> extends LinearList<E> {

    /**
     * Default constructor
     */
    public Queue(){

    }

    /**
     * Copy constructor (shallow copy)
     * @param list
     */
    public Queue(LinearList<E> list){
        for(LinkNode<E> node = list.getStart(); node != null; node = node.getNext()){
            enqueue(node.getData());
        }
    }
    /**
     * Adds element to queue
     * @param element element to add
     */
    public void enqueue(E element) {
        add(element);
    }

    /**
     * Removes and returns element from queue
     * @return element at start of queue
     */
    public E dequeue() {
        return remove();
    }

    /**
     * Gets element at front of queue
     * @return element at start of queue
     */
    public E peekFront() {
        return peek();
    }

    /**
     * Gets last element in queue
     * @return last element
     */
    public E peekRear() {
        if (isEmpty()) {
            return null;
        }
        return getEnd().getData();
    }

    /**
     * Checks if two Queue's have the same values
     * @param list LinearList
     * @return true if are equal, false otherwise
     */
    @Override
    public boolean equals(LinearList<E> list) {
        if (list instanceof Queue) {
            return super.equals(list);
        }
        return false;
    }
}
