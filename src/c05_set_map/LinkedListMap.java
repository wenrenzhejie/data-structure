package c05_set_map;
public class LinkedListMap<K,V> implements Map<K,V>{
    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        this.dummyHead = new Node();
        this.size = 0;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null){
            this.dummyHead.next = new Node(key,value,this.dummyHead.next);
            this.size ++;
        }else {
            node.value = value;
        }
    }
    @Override
    public V remove(K key) {
        Node pre = this.dummyHead;
        while (pre.next != null){
            if (pre.next.equals(key)){
                Node node = pre.next;
                pre.next = node.next;
                node.next = null;
                this.size --;
                return node.value;
            }else{
                pre = pre.next;
            }
        }
        return null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node == null){
            throw new RuntimeException("要修改的节点不存在");
        }
        node.value = value;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null?null:node.value;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) == null?false:true;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }
    private Node getNode(K key){
        Node node = this.dummyHead.next;
        while (node != null){
            if (key.equals(node.key)){
                return node;
            }else {
                node = node.next;
            }
        }
        return null;
    }

    private class Node{
        private K key;
        private V value;
        private Node next;
        public Node(K key,V value,Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public Node(K key){
            this(key,null,null);
        }
        public Node(){
            this(null,null,null);
        }
    }

}
