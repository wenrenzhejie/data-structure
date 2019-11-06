package c02_stack_queue;

import sun.dc.pr.PRError;

public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int size;
    private int front,tail;

    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity + 1];
    }

    public LoopQueue(){
        this(10);
    }
    @Override
    public int getSize() {
        return this.size;
    }
    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        //判断队列是否满
        if ((tail +1)%data.length == front ){
            //扩容
            resize(getCapacity()*2+1);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        this.size ++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("队列已空");
        }
        E old = data[front];
        data[front] = null;
        front = (front+1)%data.length;
        this.size --;
        //缩容
        if (this.size == this.getCapacity() / 4){
            resize(this.getCapacity()/2 + 1);
        }
        return old;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    /*private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        int i = 0;
        while (front != tail){
            newData[i] = data[front];
            front = (front+1)%data.length;
            i ++;
        }
        this.front = 0;
        this.tail = this.size;
        data = newData;
    }*/

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0;i < this.size;i++){
            newData[i] = data[(i+front)%data.length];
        }
        this.front = 0;
        this.tail = this.size;
        data = newData;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("size %d capacity %d front %d tail %d \n",this.size,this.getCapacity(),this.front,this.tail));
        stringBuilder.append("[");
        for (int i = front;i != tail;i = (i+1)%data.length){
            stringBuilder.append(data[i]);
            if ((i+1)%data.length != tail){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
