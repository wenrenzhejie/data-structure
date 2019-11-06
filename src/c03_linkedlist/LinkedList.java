package c03_linkedlist;

public class LinkedList<E> {
    private Node dummyHead;
    private int size;

    public LinkedList(){
        this.dummyHead = new Node(null,null);
    }
    //获取链表中元素的个数
    public int getSize(){
        return this.size;
    }
    //判断链表是否为空
    public boolean isEmpty(){
        return this.size == 0;
    }
    //在链表头添加新元素
    public void addFirst(E e){
        /*Node node = new Node(e);
        node.next = head;
        head = node;*/
        add(0,e);
    }
    //在链表的index位置添加元素
    public void add(int index,E e){
        if (index < 0 || index > this.size){
            throw new IllegalArgumentException("您给出的索引不合法");
        }
       /* if (index == 0){
            addFirst(e);
        }*/
        Node current = this.dummyHead;
        for (int i = 0;i < index;i++){
            current = current.next;
        }
        current.next = new Node(e,current.next);
        this.size ++;
    }
    //在链表末尾添加新的元素
    public void addLast(E e){
        add(this.size,e);
    }

    //根据索引获取指定位置的元素
    public E get(int index){
        if (index < 0 || index >= this.size){
            throw new IllegalArgumentException("您给出的索引不合法");
        }
        Node current = this.dummyHead.next;
        for (int i = 0;i < index;i++){
            current = current.next;
        }
        return current.e;
    }
    //获取第一个元素
    public E getFirst(){
        return get(0);
    }
    //获取最后一个元素
    public E getLast(){
        return get(this.size - 1);
    }
    //修改指定索引的元素
    public void set(int index,E e){
        if (index < 0 || index >= this.size){
            throw new IllegalArgumentException("您给出的索引不合法");
        }
        Node current = this.dummyHead.next;
        for (int i = 0;i < index;i++){
            current = current.next;
        }
        current.e = e;
    }
    //查找链表中是否包含某个元素
    public boolean contains(E e){
        Node current = this.dummyHead;
        while (current.next != null){
            current = current.next;
            if (e.equals(current.e)){
                return true;
            }
        }
        return false;
    }
    //删除指定索引的元素
    public E remove(int index){
        if (index < 0 || index >= this.size){
            throw new IllegalArgumentException("您给出的索引不合法");
        }
        Node prev = this.dummyHead;
        for (int i = 0;i < index;i++){
            prev = prev.next;
        }
        Node delNode = prev.next;
        E e = delNode.e;
        prev.next = delNode.next;
        delNode.next = null;
        delNode = null;
        size--;
        return e;
    }
    public E removeFirst(){
        return remove(0);
    }
    public E removeLast(){
        return remove(this.size -1);
    }
    public String toString(){
       /* StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Node current = this.dummyHead.next;
        while (current != null){
            stringBuilder.append(current.e);
            current = current.next;
            if (current != null){
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();*/
        StringBuilder stringBuilder = new StringBuilder();
        for (Node current = this.dummyHead.next;current != null;current = current.next){
            stringBuilder.append(current+"-->");
        }
        stringBuilder.append("NULL");
        return stringBuilder.toString();
    }

    private class Node{
        public E e;
        public Node next;

        public Node(E e,Node next){
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
