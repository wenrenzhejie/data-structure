package c06_priority_heap;

import c01_array.Array;
//最大堆
public class MaxHeap<E extends Comparable<E>> {
    public Array<E> data;
    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }
    public MaxHeap(){
        data = new Array<>();
    }

    //添加元素
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }
    //元素的上浮,保证父节点比子节点都要大
    private void siftUp(int i) {
            while (i > 0 && data.get(parent(i)).compareTo(data.get(i)) < 0){
                data.swap(i,parent(i));
                i = parent(i);
            }
    }

    //获取堆中最大的元素
    public E findMax(){
        if (data.isEmpty()){
            return null;
        }else {
            return data.get(0);
        }
    }
    //取出堆中最大的元素
    public E extractMax(){
        E max = findMax();
        data.swap(data.getSize()-1,0);
        data.removeLast();
        siftDown(0);
        return max;
    }
    //下浮操作
    private void siftDown(int i) {
        while (leftChild(i) < data.getSize()){
            int j = leftChild(i);
            if ((j+1) < data.getSize()&&data.get(j+1).compareTo(data.get(j)) > 0){
                j = j+1;
        }
            if (data.get(i).compareTo(data.get(j)) >= 0){
                break;
            }
            data.swap(i,j);
            i = j;
        }
    }
    //取出堆中最大的元素，并插入一个新的元素
    public E replace(E e){
        E max = findMax();
        data.set(0,e);
        siftDown(0);
        return max;
    }
    //根据用户传来的数组，生成一个最大堆
    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        for (int i = parent(data.getSize() - 1);i >= 0;i--){
            siftDown(i);
        }
    }

    public int size(){
        return data.getSize();
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }

    //给定子元素索引，获取其父元素索引
    public int parent(int index){
        if (index == 0){
            throw new RuntimeException("根节点无父节点");
        }
        return (index-1) / 2;
    }
    public int leftChild(int index){
        return index*2+1;
    }
    public int rightChild(int index){
        return  index*2+2 ;
    }
}
