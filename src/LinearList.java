/**
 * Lab 4
 *
 * @Author Shams Ansari
 * Learn about Lists, Stacks and Queues
 * <p>
 * Prof: Manish Goel
 * Class: CIS22C
 * @Date: 2/14/2021
 * <p>
 * LinearList is superclass of LinkedList, Stack and Queue
 */
public abstract class LinearList<E> {


    /**
     * Number of elements in list
     */
    private int count;

    /**
     * Head of list
     */
    private LinkNode<E> start;

    /**
     * Tail of list
     */
    private LinkNode<E> end;


    /**
     * Appends element
     *
     * @param element element to append
     */
    public void add(E element) {
        LinkNode<E> node = new LinkNode<>(element);
        if (getStart() == null) { //First node
            setStart(node);
            setEnd(node);
        } else {
            getEnd().setNext(node);
            setEnd(node);
        }
        addCount(1);
    }

    /**
     * Removes and returns start of list
     *
     * @return head of list
     */
    public E remove() {
        if (isEmpty()) {
            return null;
        }

        LinkNode<E> node = getStart();
        setStart(node.getNext());

        if (node == getEnd()) {
            setEnd(null);
        }
        addCount(-1);
        return node.getData();
    }

    /**
     * Returns but not remove start of list
     *
     * @return element at start of list
     */
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return getStart().getData();
    }

    /**
     * Checks if elements exists in list
     *
     * @param element element to check
     * @return True if element exist, False otherwise
     */
    public boolean contains(E element) {
        return indexOf(element) > -1;
    }

    /**
     * Gets index of element
     *
     * @param element element to find
     * @return If found, return index otherwise return -1
     */
    public int indexOf(E element) {
        int i = 0;
        //Traverse list
        for (LinkNode<E> node = getStart(); node != null; node = node.getNext(), i++) {
            if (node.getData().equals(element)) {
                return i; // element found
            }
        }
        //element not found
        return -1;
    }

    /**
     * Removes all elements from list.
     */
    public void clear() {
        int len = getCount();
        for (int i = 0; i < len; i++) {
            remove();
        }
    }

    /**
     * Converts list into array.
     *
     * @return an array of elements
     */
    public Object[] toArray() {
        if (isEmpty()) {
            return new Object[0];
        }
        Object[] elements = new Object[getCount()];
        int i = 0;
        for (LinkNode<E> node = getStart(); node != null; node = node.getNext())
            elements[i++] = node.getData();
        return elements;
    }

    /**
     * Checks if list is empty
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return getCount() == 0;
    }

    /**
     * Size of list
     * @return size of list
     */
    public int size() {
        return getCount();
    }

    /**
     * Checks for equality.
     * @param list LinearList
     * @return True if all elements
     */
    public boolean equals(LinearList<E> list) {
        if (list.size() != size()) {
            return false;
        }
        for (LinkNode<E> node = getStart(), nodeB = list.getStart();
             node != null; node = node.getNext(), nodeB = nodeB.getNext()) {
            if (!node.getData().equals(nodeB.getData())) {
                return false;
            }
        }
        return true;
    }



    /**
     * Prints list
     */
    public void print() {
        System.out.println(this);
    }

    /**
     * String is in format [ a, b, d, c, f, g]
     * @return list in string format
     */
    @Override
    public String toString() {
        String out = "[ ";
        for (E node : (E[]) toArray()) {
            out += node + " ";
        }
        out += "]";
        return out;

    }



    //===================
    //Getters and setters
    //===================

    public void addCount(int n) {
        count += n;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LinkNode<E> getStart() {
        return start;
    }

    public void setStart(LinkNode<E> start) {
        this.start = start;
    }

    public LinkNode<E> getEnd() {
        return end;
    }

    public void setEnd(LinkNode<E> end) {
        this.end = end;
    }

}
