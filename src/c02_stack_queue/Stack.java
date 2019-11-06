package c02_stack_queue;

public interface Stack<E> {

    public int getSize();
    public boolean isEmpty();
    //入栈
    public void push(E e);
    //出栈
    public E pop();
    //查看栈顶元素
    public E peek();
}
