package c05_set_map;

import c03_linkedlist.LinkedList;
import sun.dc.pr.PRError;

public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> linkedList;
    public LinkedListSet(){
        linkedList = new LinkedList<>();
    }


    @Override
    public void add(E e) {
        if (!linkedList.contains(e)){
            linkedList.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        linkedList.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}
