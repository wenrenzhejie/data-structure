package c10_avl;

import c05_set_map.Map;
import c06_priority_heap.MaxHeap;

import java.util.ArrayList;
import java.util.List;

public class AVLTree2<K extends Comparable<K>,V> implements Map<K,V> {
    private Node root;
    private int size;

    public AVLTree2(){
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
        node.height = 1 + Math.max(getHeight(node.left),getHeight(node.right));
        if (Math.abs(getBalanceFactor(node)) > 1){
            System.out.println("不平衡了");
        }
        int balanceFactor = getBalanceFactor(node);
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            node = rightRotate(node);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) < 0){
            node = leftRotate(node);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            node = rightRotate(node);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) >= 0){
            node.right = rightRotate(node.right);
            node = leftRotate(node);
        }
        return node;
    }

    private Node leftRotate(Node node) {
        Node x = node.right;
        Node left = x.left;
        x.left = node;
        node.right = left;
        return x;
    }
    private Node rightRotate(Node node) {
        Node x = node.left;
        Node right = x.right;
        x.right = node;
        node.left = right;
        return x;
    }

    //检查是否为二叉树(利用二叉树中序遍历的特点)
    private boolean isBST(){
        List<K> list = new ArrayList<K>();
        inorder(this.root,list);
        for (int i = 1;i < list.size();i++){
            if (list.get(i - 1).compareTo(list.get(i)) > 0){
                return false;
            }
        }
        return true;
    }
    private boolean isBalanced(){
        return isBalanced(this.root);
    }
    private boolean isBalanced(Node node){
        if (node == null){
            return true;
        }
        if (Math.abs(getBalanceFactor(node)) > 1){
            return false;
        }else {
            boolean left = isBalanced(node.left);
            boolean right = isBalanced(node.right);
            return left && right;
        }
    }
    private void inorder(Node node,List<K> list){
        if (node == null){
            return;
        }
        inorder(node.left,list);
        list.add(node.key);
        inorder(node.right,list);
    }
    private int getHeight(Node node){
        if (node == null){
            return 0;
        }
        return node.height;
    }
    private int getBalanceFactor(Node node){
        return getHeight(node.left) - getHeight(node.right);
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
        Node retNode = null;
        if (node.key.compareTo(key) == 0){
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                this.size--;
                retNode = rightNode;
            }else
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                this.size--;
                retNode = leftNode;
            }else {
                Node minimum = minimum(node.right);
                Node rightNode = remove(node.right,minimum.key);
                minimum.left = node.left;
                minimum.right = rightNode;
                node.left = null;
                node.right = null;
                retNode = minimum;
            }
        }else if (node.key.compareTo(key) > 0){
            node.left = remove(node.left,key);
            retNode =node;
        }else {
            node.right = remove(node.right,key);
            retNode = node;
        }
        retNode.height = 1 + Math.max(getHeight(retNode.left),getHeight(retNode.right));
        int balanceFactor = getBalanceFactor(retNode);
        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){
            retNode = rightRotate(retNode);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) < 0){
            retNode = leftRotate(retNode);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            retNode.left = leftRotate(retNode.left);
            retNode = rightRotate(retNode);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) >= 0){
            retNode.right = rightRotate(retNode.right);
            retNode = leftRotate(retNode);
        }
        return retNode;
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
        private int height;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            this.height = 1;
        }
    }
}

