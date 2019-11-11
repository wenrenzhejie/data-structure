package c05_set_map;

import org.omg.CORBA.PUBLIC_MEMBER;

public interface Set<E> {
    public void add(E e);
    public void remove(E e);
    public boolean contains(E e);
    public int getSize();
    public boolean isEmpty();
}
