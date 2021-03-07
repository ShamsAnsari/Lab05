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
 * Represents a Stack ADT
 */
public class Stack<E> extends LinearList<E> {

    /**
     * Default constructor
     */
    public Stack(){

    }

    /**
     * Shallow copy constructor
     * @param list list to copy
     */
    public Stack(LinearList<E> list){
        for(LinkNode<E> node = list.getStart(); node != null; node = node.getNext()){
            push(node.getData());
        }
    }
    /**
     * Remove element from stack
     * @return element
     */
    public E pop() {
        return remove();
    }

    /**
     * puts element in stack
     * @param element element to push
     */
    public void push(E element) {
        add(element);
    }

    /**
     * puts element in stack
     * @param element element to add
     */
    @Override
    public void add(E element) {
        LinkNode<E> node = new LinkNode<>(element);
        if(isEmpty()){
            setStart(node);
            setEnd(node);
        } else{
            node.setNext(getStart());
            setStart(node);
        }
        addCount(1);
    }

    /**
     * Checks if two stacks are equal
     * @param list LinearList
     * @return True if every element is equal, false otherwise
     */
    @Override
    public boolean equals(LinearList<E> list){
        if(list instanceof Stack){
            return super.equals(list);
        }
        return false;
    }

}
