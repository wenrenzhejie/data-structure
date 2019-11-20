package c06_priority_heap;

import c01_array.Array;

public class MaxHeap2<E extends Comparable<E>> {
    public Array<E> data;
    public MaxHeap2(int capacity){
        data = new Array<>(capacity);
    }
    public MaxHeap2(){
        data = new Array<>();
    }
    public MaxHeap2(E[] array){
        data = new Array<>(array);
        for (int i = parent(data.getSize() - 1);i >= 0;i--){
            siftDown(i);
        }
    }
    public void add(E e){
        this.data.addLast(e);
        siftUp(this.getSize() - 1);
    }
    private void siftUp(int i){
        while (i > 0 && data.get(i).compareTo(data.get(parent(i))) > 0){
            data.swap(i,parent(i));
            i = parent(i);
        }
    }
    public E extractMax(){
        E max = findMax();
        data.swap(0,data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return max;
    }
    private void siftDown(int i) {
        int j = leftChild(i);
        while (j < data.getSize() - 1){
            if (rightChild(i) < data.getSize() - 1 && data.get(leftChild(i)).compareTo(data.get(rightChild(i))) < 0){
                j = rightChild(i);
            }
            if (data.get(i).compareTo(data.get(j)) >= 0){
                break;
            }else {
                i = j;
            }
        }
    }
    private E findMax(){
        if (!data.isEmpty()){
            return data.get(0);
        }else {
            return null;
        }
    }
    public E replace(E e){
        E max = findMax();
        data.set(0,e);
        siftDown(0);
        return max;
    }
    private int parent(int index){
        return (index - 1) / 2;
    }
    private int leftChild(int index){
        return 2 * index + 1;
    }
    private int rightChild(int index){
        return 2 * index + 2;
    }
    public int getSize(){
        return data.getSize();
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
}
