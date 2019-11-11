package c04_bst;

import com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.CORBA.PolicyOperations;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST2<E extends Comparable<E>> {
    private Node root;
    private int size;
    public BST2(){
        this.root = null;
        this.size = 0;
    }
    public int getSize(){
        return this.size;
    }
    public boolean isEmpty(){
        return this.size == 0;
    }
    //添加元素(递归实现)
    public void add(E e){
                          //假设在根节点处添加
        this.root = add(this.root,e);
    }
    private Node add(Node node,E e){
        if (node == null){
            this.size ++;
            return new Node(e);
        }
        if (node.e.compareTo(e) > 0){
                          //下一步假设在node.left处插入
            node.left = add(node.left,e);
            return node;
        }else if (node.e.compareTo(e) < 0){
            node.right = add(node.right,e);
            return node;
        }else {
            return node;
        }
    }
    //查找是否包含一个元素（递归实现）
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
    //查找是否包含一个元素（非递归实现）
    public boolean containsNR(E e){
        Node node = this.root;
        while (node !=null){
            if (node.e.compareTo(e) == 0){
                return true;
            }else if (node.e.compareTo(e) > 0){
                node = node.left;
            }else {
                node = node.right;
            }
        }
        return false;
    }
    //二分搜索树的前序遍历（递归实现）
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
    //二分搜索树的前序遍历（非递归实现）
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(this.root);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            if (pop != null){
                System.out.println(pop.e);
                stack.push(pop.right);
                stack.push(pop.left);
            }
        }
    }
    //二分搜索树的层次遍历
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        while (!queue.isEmpty()){
            Node remove = queue.remove();
            if (remove != null){
                System.out.println(remove.e);
                queue.add(remove.left);
                queue.add(remove.right);
            }

        }
    }
    //寻找二分搜索树中的最小值
    public E minimum(){
        if (this.root == null){
            throw new RuntimeException("空树");
        }
        return minimum(this.root).e;
    }
    private Node minimum(Node node){
       if (node.left == null){
           return node;
       }else {
           return minimum(node.left);
       }
    }
    //寻找二分搜索树中的最大值
    public E maximum(){
        if (this.root == null){
            throw new RuntimeException("空树");
        }
        return maximum(this.root).e;
    }
    private Node maximum(Node node){
        if (node.right == null){
            return node;
        }else {
            return maximum(node.right);
        }
    }
    //删除二分搜索树中的最小值
    public E removeMin(){
        E e = minimum();
        this.root = removeMin(this.root);
        return e;
    }
    //二分搜索树中的操作貌似都是假设下一个需要访问的节点是需要进行操作的，从而完成查找遍历
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            this.size --;
            return rightNode;
        }else {
            node.left = removeMin(node.left);
            return node;
        }
    }
    //删除二分搜索树中的最大值
    public E removeMax(){
        E e = maximum();
        this.root = removeMax(this.root);
        return e;
    }
    private Node removeMax(Node node){
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            this.size --;
            return leftNode;
        }else {
            node.right = removeMax(node.right);
            return node;
        }
    }
    //删除二分搜索树中的任意值
    public void remove(E e){
        if (this.root == null){
            throw new RuntimeException("空树");
        }
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
            }else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                return leftNode;
            }else {
                Node rightMinimumNode = minimum(node.right);
                Node rightNode = removeMin(node.right);
                rightMinimumNode.left = node.left;
                rightMinimumNode.right = rightNode;
                node.left = null;
                node.right = null;
                return rightMinimumNode;
            }
        }else if (node.e.compareTo(e) > 0){
            node.left = remove(node.left,e);
            return node;
        }else {
            node.right = remove(node.right,e);
            return node;
        }
    }
    //floor
    public E floor(E e){
        if (this.root == null || e.compareTo(minimum()) > 0){
            throw new RuntimeException("空树");
        }
        return floor(this.root,e).e;
    }
    private Node floor(Node node,E e){
        if (node == null){
            return null;
        }
        if (node.e.compareTo(e) == 0){
            return node;
        }else if (node.e.compareTo(e) > 0){
            return floor(node.left,e);
        }else { // node.e < e
            Node floor = floor(node.right, e);
            if (floor == null){
                return node;
            }else {
                return floor;
            }
        }
    }
    //ceil
    public E ceil(E e){
        if (this.root == null || e.compareTo(maximum()) > 0){
            throw new RuntimeException("空树或者所有元素都比给定的值小");
        }
        return ceil(this.root,e).e;
    }
    private Node ceil(Node node,E e){
        if (node == null){
            return null;
        }
        if (node.e.compareTo(e) == 0){
            return node;
        }else if(node.e.compareTo(e) < 0){
            return ceil(node.right,e);
        }else { //node.e > e
            Node ceil = ceil(node.left,e);
            if (ceil != null){
                return ceil;
            }else {
                return node;
            }
        }
    }
    private class Node{
        private E e;
        private Node left,right;

        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }
}
