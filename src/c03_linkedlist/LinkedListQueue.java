package c03_linkedlist;
import c02_stack_queue.Queue;

public class LinkedListQueue<E> implements Queue<E> {
    private Node head,tail;
    private int size;

    public LinkedListQueue(){
        head = null;
        tail = null;
    }
    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void enqueue(E e) {
        if (isEmpty()){
            head = new Node(e);
            tail = head;
        }else {
            Node node = new Node(e);
            tail.next = node;
            tail = node;
        }
        size ++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("队列为空");
        }
        Node node = this.head;
        E e = node.e;
        this.head = node.next;
        node.next = null;
        node = null;
        this.size --;
        return e;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("队列为空");
        }
        return this.head.e;
    }
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        Node current = this.head;
        while (current != null){
            stringBuilder.append(current+"-->");
            current = current.next;
        }
        stringBuilder.append("NULL");
        return stringBuilder.toString();
    }

    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
        public Node(E e){
            this(e,null);
        }
        public Node(){
            this(null,null);
        }

        public String toString(){
            return e.toString();
        }
    }
}
