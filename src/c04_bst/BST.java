package c04_bst;

import com.sun.deploy.nativesandbox.NativeSandboxBroker;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.nio.channels.FileLock;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {
    private Node root;
    private int size;

    public int getSize(){
        return this.size;
    }
    public boolean isEmpty(){
        return this.size == 0;
    }
    public BST(){
        this.root = null;
        this.size = 0;
    }
    //添加一个元素(递归实现)
    public void add(E e){
        this.root = add(this.root,e);
    }
    //向以node为根的二分搜索树中插入元素,并返回新根
    private Node add(Node node,E e){
        if (node == null){
            this.size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) > 0){
            node.right = add(node.right,e);
        }else if (e.compareTo(node.e) < 0){
            node.left = add(node.left,e);
        }
        return node;
    }

    //查找是否包含一个元素(递归实现)
    public boolean contains(E e){
        return contains(this.root,e);
    }
    private boolean contains(Node node,E e){
        if (node == null){
            return false;
        }
        if (e.compareTo(node.e) > 0){
            return contains(node.right,e);
        }else if (e.compareTo(node.e) < 0){
            return contains(node.left,e);
        }else {
            return true;
        }
    }
    //查找是否包含一个元素（非递归实现）
    public boolean contains1(E e){
        if (this.root == null){
            return false;
        }
        Node node = root;
        while (node != null){
            if (e.compareTo(node.e) > 0){
                node = node.right;
            }else if (e.compareTo(node.e) < 0){
                node = node.left;
            }else {
                return true;
            }
        }
        return false;
    }

    //二分搜索树的前序遍历（先根遍历--->递归实现）
    public void preOrder(){
        preOrder(this.root);
    }
    private void preOrder(Node node){
        /*//先写递归终止条件
        if (node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);*/
        if (node != null){
            System.out.println(node.e);
            preOrder(node.left);
            preOrder(node.right);
        }
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
    //二分搜索树的中序遍历（中根遍历--->递归实现）中序遍历恰好按照升序输出该树
    public void inOrder(){
        inOrder(this.root);
    }
    private void inOrder(Node node){
        if (node != null){
            inOrder(node.left);
            System.out.println(node.e);
            inOrder(node.right);
        }
    }
    //二分搜索树的后序遍历（后根遍历--->递归实现)
    public void postOrder(){
        postOrder(this.root);
    }
    private void postOrder(Node node){
        if (node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.e);
        }
    }

    //二分搜索树的层次遍历（一般使用非递归实现）
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        while (!queue.isEmpty()){
            Node node = queue.remove();
            if (node != null){
                System.out.println(node.e);
                queue.add(node.left);
                queue.add(node.right);
            }
        }

    }
    //寻找二分搜索树中的最小值(递归实现)
    public E minimum(){
        if (this.size == 0){
            throw new IllegalArgumentException("无最小值");
        }
        return minimum(this.root).e;
    }
    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }
        return minimum(node.left);
    }
    //寻找二分搜索树中的最小值（非递归实现）
    public E miniumNR(){
        if(this.root == null){
            throw new IllegalArgumentException("无最小值");
        }
        Node node = this.root;
        while (node.left != null){
            node = node.left;
        }
        return node.e;
    }
    //寻找二分搜索树中的最大值(递归实现)
    public E maximum(){
        if (this.size == 0){
            throw new IllegalArgumentException("无最小值");
        }
        return maximum(this.root).e;
    }
    private Node maximum(Node node){
        if (node.right == null){
            return node;
        }
        return maximum(node.right);
    }

    //删除二分搜索树中的最小值
    public E removeMin(){
        E e = minimum();
        this.root = removeMin(this.root);
        return e;
    }
    //删除掉以node为根的二分搜索树中的最小节点
    //并返回新的根
    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            this.size --;
            node.right = null;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }
    //删除二分搜索树中的最大值
    public E removeMax(){
        E e = maximum();
        this.root = removeMax(this.root);
        return e;
    }
    //删除以node为根的二分搜索树的最大值，并返回新的根

    //删除二分搜索树中的任意值
    public void remove(E e){
        remove(this.root,e);
    }
    //删除掉以node为根的值为e的节点,并返回新的根
    private Node remove(Node node,E e){
        if (node == null){
            return null;
        }
        if (e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
            return node;
        }else if (e.compareTo(node.e) < 0){
            node.left = remove(node.left,e);
            return node;
        }else {
            if (node.left == null){
                Node nodeRight = node.right;
                node.right = null;
                this.size --;
                return nodeRight;
            }else if (node.right == null){
                Node nodeLeft = node.left;
                node.left = null;
                this.size--;
                return nodeLeft;
            }else {
                Node minimum = minimum(node.right);
                minimum.right = removeMin(node.right);
                minimum.left = node.left;
                node.left = node.right = null;
                return minimum;
            }
        }
    }
    private Node removeMax(Node node){
        if (node.right == null){
            Node nodeLeft = node.left;
            node.right = null;
            this.size --;
            return node.left;
        }
        node.right = removeMax(node.right);
        return node;
    }
    //floor and ceil
    public E floor(E e){
        if (this.size == 0 || e .compareTo(minimum()) < 0){
            return null;
        }
        return floor(this.root,e).e;
    }
    //以floor为根的子树（书中的递归貌似都是遍历）
    private Node floor(Node node,E e){
        if (node == null){
            return null;
        }else if (e.compareTo(node.e) == 0){
            return node;
        }else if (e.compareTo(node.e) < 0){
            return floor(node.left, e);
        }else {
            Node floor = floor(node.right, e);
            if (floor == null){
                return node;
            }else {
                return floor;
            }
        }
    }

    public E ceil(E e){
        return ceil(this.root,e).e;
    }
    public Node ceil(Node node,E e){
        if (node == null){
            return null;
        }
        if (e.compareTo(node.e) == 0){
            return node;
        }else if (e.compareTo(node.e) < 0){
            Node ceil = ceil(node.left, e);
            if (ceil == null){
                return ceil;
            }else {
                return ceil;
            }
        }else {
            return ceil(node.right,e);
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        generateString(this.root,0,stringBuilder);
        return stringBuilder.toString();
    }

    private void generateString(Node node, int depth,StringBuilder stringBuilder) {
        if (node != null){
            stringBuilder.append(generateDepth(depth)+node.e+"\n");
            generateString(node.left,depth+1,stringBuilder);
            generateString(node.right,depth+1,stringBuilder);
        }
    }
    private String generateDepth(int depth){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i < depth;i++){
            stringBuilder.append("---");
        }
        return stringBuilder.toString();
    }

/*
    第一种递归实现方法
   public void add(E e){
        if (root == null){
            root = new Node(e);
        }else {
            add(root,e);
        }
    }
    private void add(Node node,E e){
        if (e.compareTo(node.e) > 0){
            if (node.right == null){
                node.right = new Node(e);
            }else {
                add(node.right,e);
            }
            this.size++;
        }else if (e.compareTo(node.e) < 0){
            if (node.left == null){
                node.left = new Node(e);
            }else {
                add(node.left,e);
            }
            this.size++;
        }
    }
*/


    private class Node{
        private E e;
        private Node left;
        private Node right;

        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }
}
