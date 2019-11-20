package c04_bst;

import sun.dc.pr.PRError;

import java.lang.ref.PhantomReference;

public class BST3<E extends Comparable<E>> {
    private Node root;
    private int size;

    public BST3(){
        this.root = null;
        this.size = 0;
    }
    public int getSize(){
        return this.size;
    }
    public boolean isEmpty(){
        return this.size == 0;
    }
    public void add(E e){
        this.root = add(this.root,e);
    }
    private Node add(Node node,E e){
        if (node == null){
            this.size ++;
            return new Node(e);
        }
        if (node.e.compareTo(e) > 0){
            node.left = add(node.left,e);
        }else if (node.e.compareTo(e) < 0){
            node.right = add(node.right,e);
        }else {
            node.e = e;
        }
        return node;
    }

    public boolean contains(E e){
        return contains(this.root,e);
    }
    private boolean contains(Node node,E e){
        if (node == null){
            return false;
        }
        if (node.e.compareTo(e) == 0){
            return true;
        }else if (node.e.compareTo(e) > 0){
            return contains(node.left,e);
        }else {
            return contains(node.right,e);
        }
    }

    public E maximum(){
        if (this.root == null){
            return null;
        }
        return maximum(this.root).e;
    }
    private Node maximum(Node node){
        if (node.right == null){
            return node;
        }
        return maximum(node.right);
    }

    public E minimum(){
        if (this.root == null){
            return null;
        }
        return minimum(this.root).e;
    }
    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    public void preOrder(){
        preOrder(this.root);
    }
    private void preOrder(Node node){
        if (node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public E removeMin(){
        E minimum = minimum();
        this.root = removeMin(this.root);
        return minimum;
    }
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            return rightNode;
        }else {
            node.left = removeMin(node.left);
            return node;
        }
    }

    public E removeMax(){
        E maximum = maximum();
        removeMax(this.root);
        return maximum;
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

    public void remove(E e){
        this.root = remove(this.root,e);
    }
    private Node remove(Node node,E e){
        if (node == null){
            return null;
        }
        if (node.e.compareTo(e) == 0){
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                return rightNode;
            }
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                return leftNode;
            }
            Node rightNode = node.right;
            Node minimum = minimum(node.right);
            minimum.left = node.left;
            minimum.right = removeMin(node.right);
            return minimum;
        }else if (node.e.compareTo(e) > 0){
            node.left = remove(node.left,e);
        }else {
            node.right = remove(node.right,e);
        }
        return node;
    }

    public E ceil(E e){
        E maximum = maximum();
        if (maximum == null || e.compareTo(maximum()) > 0){
            return null;
        }
        return ceil(this.root,e).e;

    }
    private Node ceil(Node node,E e){
        if (node == null){
            return null;
        }
        if (node.e.compareTo(e) == 0){
            return node;
        }else if (node.e.compareTo(e) < 0){
            return ceil(node.right,e);
        }else{
            Node ceil = ceil(node.left, e);
            if (ceil == null){
                return node;
            }else {
                return ceil;
            }
        }
    }

    public E floor(E e){
        E minimum = minimum();
        if (minimum == null || minimum.compareTo(e) > 0){
            return null;
        }
        return floor(this.root,e).e;
    }
    private Node floor(Node node,E e){
        if (node == null){
            return null;
        }
        if (node.e.compareTo(e) == 0){
            return node;
        }else if (node.e.compareTo(e) > 0 ){
            return floor(node.left,e);
        }else {  //node.e < e
            Node floor = floor(node.right, e);
            if (floor != null){
                return floor;
            }else {
                return node;
            }
        }
    }

    private class Node{
        E e;
        Node left;
        Node right;
        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
        }


    }
}
