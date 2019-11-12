package c06_priority_heap;

import c01_array.Array;
import com.sun.org.apache.bcel.internal.generic.SWAP;
import org.omg.CORBA.DATA_CONVERSION;

public class MinHeap<E extends Comparable<E>> {
    private Array<E> data;
    public MinHeap(int capacity){
        data = new Array<>(capacity);
    }
    public MinHeap(){
        data = new Array<>();
    }
    public MinHeap(E[] arr){
        data = new Array<>(arr);
        for (int i = parent(data.getSize() - 1); i >= 0;i--){
            siftDown(i);
        }
    }
    public int size(){
        return data.getSize();
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
    //添加元素
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }
    private void siftUp(int i) {
        while (i > 0 && data.get(i).compareTo(data.get(parent(i))) < 0){
            data.swap(i,parent(i));
            i = parent(i);
        }
    }
    //获取堆中最小元素
    public E findMin(){
        if (data.isEmpty()){
            return null;
        }else {
            return data.get(0);
        }
    }
    //移除堆中最小元素
    public E extractMin(){
        E min = findMin();
        data.swap(0,data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return min;
    }
    //取出堆中最小元素并插入一个新的元素
    public E replace(E e){
        E min = findMin();
        data.set(0,e);
        siftDown(0);
        return min;
    }
    //根据用户传来的数组，生成最小堆
    private void siftDown(int i) {
        while (leftChild(i) < data.getSize()){
            int j = leftChild(i);
                if (j+1 < data.getSize()&&data.get(j+1).compareTo(data.get(j)) < 0){
                    j = j + 1;
                }
            if (data.get(i).compareTo(data.get(j)) < 0){
                break;
            }
            data.swap(i,j);
            i = j;
        }
    }
    private int parent(int index){
        return (index - 1) / 2;
    }
    private int leftChild(int index){
        return index * 2 + 1;
    }
    private int rightChild(int index){
        return index * 2 + 2;
    }
}
