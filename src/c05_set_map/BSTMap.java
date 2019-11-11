package c05_set_map;

import javax.print.attribute.standard.NumberOfDocuments;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class BSTMap<K extends Comparable<K>,V> implements Map<K,V>{
    private Node root;
    private int size;

    public BSTMap(){
        this.root = null;
        this.size = 0;
    }
    @Override
    public void add(K key, V value) {
        this.root = add(this.root,key,value);
    }
    private Node add(Node node,K key,V value){
        if (node == null){
            return new Node(key,value);
        }
        if (key.compareTo(node.key) > 0){
            node.right = add(node.right,key,value);
        }else if (key.compareTo(node.key) < 0){
            node.left = add(node.left,key,value);
        }else {
            node.value = value;
        }
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = get(this.root, key);
        if (node == null){
            return null;
        }else {
            this.root = remove(this.root,key);
            return node.value;
        }
    }
    private Node remove(Node node,K key){
        if (node == null){
            return null;
        }
        if (node.key.compareTo(key) == 0){
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                this.size--;
                return rightNode;
            }else
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                this.size--;
                return leftNode;
            }else {
                Node minimum = minimum(node.right);
                Node rightNode = removeMin(node.right);
                minimum.left = node.left;
                minimum.right = rightNode;
                node.left = null;
                node.right = null;
                return minimum;
            }
        }else if (node.key.compareTo(key) > 0){
               node.left = remove(node.left,key);
               return node;
        }else {
            node.right = remove(node.right,key);
            return node;
        }
    }
    //最小值
    public V minimum(){
        if (this.root == null){
            return null;
        }
        return minimum(this.root).value;
    }
    private Node minimum(Node node){
            if (node.left == null){
                return node;
            }else {
                return minimum(node.left);
            }
    }
    public V removeMin(){
        V v = minimum();
        this.root = removeMin(this.root);
        return v;
    }
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            this.size -- ;
            return rightNode;
        }else {
            node.left = removeMin(node.left);
            return node;
        }
    }
    //最大值
    public V maximum(){
        if (this.root == null){
            return null;
        }
        return maximum(this.root).value;
    }
    private Node maximum(Node node){
        if (node.right == null){
            return node;
        }else {
            return maximum(node.right);
        }
    }
    public V removeMax(){
        V v = maximum();
        this.root = removeMax(this.root);
        return v;
    }
    private Node removeMax(Node node){
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            return leftNode;
        }else {
            node.right = removeMax(node.right);
            return node;
        }
    }
    @Override
    public void set(K key, V value) {
        Node node = get(this.root,key);
        if (node == null){
            throw new RuntimeException(key+"不存在");
        }else {
            node.value = value;
        }
    }
    @Override
    public V get(K key) {
        Node node = get(this.root, key);
        return node == null?null:node.value;
    }
    private Node get(Node node,K key){
        if (node == null){
            return null;
        }
        if (node.key.compareTo(key) == 0){
            return node;
        }else if (node.key.compareTo(key) > 0){
            return get(node.left,key);
        }else{
            return get(node.right,key);
        }
    }

    @Override
    public boolean contains(K key) {
        Node node = get(this.root, key);
        return node==null?false:true;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    private class Node{
        private K key;
        private V value;
        private Node left,right;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }
}
