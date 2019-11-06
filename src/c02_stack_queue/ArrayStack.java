package c02_stack_queue;

import c01_array.Array;

public class ArrayStack<E> implements Stack<E> {
    private Array<E> array;
    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }
    public ArrayStack(){
        array = new Array<>();
    }
    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }
    //获取栈的容量
    public int getCapacity(){
        return array.getCapacity();
    }
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0;i < this.getSize();i++){
            stringBuilder.append(array.get(i));
            if (i != this.getSize() -1 ){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("] top");
        return stringBuilder.toString();
    }
}
