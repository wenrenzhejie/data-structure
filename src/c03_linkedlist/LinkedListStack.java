package c03_linkedlist;

import c02_stack_queue.Stack;
import sun.dc.pr.PRError;

public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> linkedList;
    public LinkedListStack(){
        linkedList = new LinkedList<>();
    }
    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.getSize() == 0;
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }
    public String toString(){
        return linkedList.toString();
    }
}
