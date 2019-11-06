package c02_stack_queue;

public interface Queue<E> {
    public int getSize();
    public boolean isEmpty();
    //入队
    public void enqueue(E e);
    //出队
    public E dequeue();
    //查看队列第一个元素
    public E getFront();
}
