package c10_avl;

import c05_set_map.Map;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>,V> implements Map<K,V>{
    private Node root;
    private int size;

    public AVLTree(){
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
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("不平衡的节点");
        }
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            return rightRotate(node);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.left) <= 0){
            return leftRotate(node);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return leftRotate(node);
        }
        //RL
        if (balanceFactor <-1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return rightRotate(node);
        }
        return node;
    }

    private Node leftRotate(Node node) {
        Node x = node.right;
        Node leftNode = x.left;
        x.left = node;
        node.right = leftNode;
        return x;
    }

    private Node rightRotate(Node node){
        Node y = node;
        Node x = node.left;
        Node xRight = x.right;
        x.right = y;
        y.left = xRight;
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        return x;
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
                retNode =  minimum;
            }
        }else if (node.key.compareTo(key) > 0){
               node.left = remove(node.left,key);
               retNode =  node;
        }else {
            node.right = remove(node.right,key);
            retNode =  node;
        }
        if (retNode == null){
            return null;
        }
        retNode.height = 1 + Math.max(getHeight(retNode.left),getHeight(retNode.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("不平衡的节点");
        }
        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){
            return rightRotate(retNode);
        }
        //RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.left) <= 0){
            return leftRotate(retNode);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            retNode.left = leftRotate(retNode.left);
            return leftRotate(retNode);
        }
        //RL
        if (balanceFactor <-1 && getBalanceFactor(retNode.right) > 0){
            retNode.right = rightRotate(retNode.right);
            return rightRotate(retNode);
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

    //检查AVL树是不是二分搜索树
    public boolean isBST(){
        ArrayList<K> list = new ArrayList<>();
        inorder(this.root,list);
        for (int i = 1;i < list.size();i++){
            if (list.get(i - 1).compareTo(list.get(i)) > 0){
                return false;
            }
        }
        return true;
    }
    //检查AVL树是不是平衡二叉树
    public boolean isBalanced(){
        return isBalanced(this.root);
    }

    private boolean isBalanced(Node node) {
        if (node == null){
            return true;
        }else {
            if (Math.abs(getBalanceFactor(node)) > 1){
                return false;
            }else {
                boolean leftBalanced = isBalanced(node.left);
                boolean rightBalanced = isBalanced(node.right);
                return leftBalanced && rightBalanced;
            }
        }

    }

    private void inorder(Node node, ArrayList<K> list) {
        if (node == null){
            return;
        }
        inorder(node.left,list);
        list.add(node.key);
        inorder(node.right,list);

    }

    //获取一个节点的高度值
    private int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }
    //获取一个节点的平衡因子
    private int getBalanceFactor(Node node){
        if (node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }
    private class Node{
        private K key;
        private V value;
        private Node left,right;
        //代表这个节点的高度值
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
