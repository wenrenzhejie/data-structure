package c02_stack_queue;

import c01_array.Array;

public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;
    public ArrayQueue(){
        array = new Array<E>();
    }
    public ArrayQueue(int capacity){
        array = new Array<E>(capacity);
    }
    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.getSize() == 0;
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("front [");
        for (int i = 0;i < array.getSize();i++){
            stringBuilder.append(array.get(i));
            if (i != array.getSize() - 1){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }
}
