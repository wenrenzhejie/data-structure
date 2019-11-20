package c08_trie;

import javafx.beans.binding.BooleanExpression;

import java.util.TreeMap;
import java.util.logging.XMLFormatter;

public class Trie2<E> {
    private Node root;
    private int size;

    public void add(String word){
       Node node = this.root;
       for (int i = 0;i < word.length();i++){
           char c = word.charAt(i);
           if (!node.next.containsKey(c)){
               node.next.put(c,new Node());
           }
           node = node.next.get(c);
       }
       if (!node.isWord){
           this.size ++;
           node.isWord = true;
       }
    }
    public void addR(String word){
        addR(this.root,0,word);
    }
    private void addR(Node node,int index,String word){
        if (index < word.length()){
            char c = word.charAt(index);
            if (!node.next.containsKey(c)){
                node.next.put(c,new Node());
            }
            addR(node.next.get(c),index ++,word);
        }else {
            if (!node.isWord){
                this.size ++;
                node.isWord = true;
            }
        }
    }

    public boolean containsR(String word){
        return containsR(this.root,0,word);
    }
    private boolean containsR(Node node,int index,String word){
        if (index < word.length()){
            char c = word.charAt(index);
            if (!node.next.containsKey(c)){
                return false;
            }else {
                return containsR(node.next.get(c),index ++,word);
            }
        }else {
            return node.isWord;
        }
    }
    public boolean contains(String word){
        Node node = this.root;
        for (int i = 0;i < word.length();i++){
            char c = word.charAt(i);
            if (!node.next.containsKey(c)){
                return false;
            }else {
                node = node.next.get(c);
            }
        }
        return node.isWord;
    }

    public boolean isPrefix(String prefix){
        Node node = this.root;
        for (int i = 0;i < prefix.length();i++){
            char c = prefix.charAt(i);
            if (!node.next.containsKey(c)){
                return false;
            }else {
                node = node.next.get(c);
            }
        }
        return true;
    }
    public Trie2(){
        this.root = new Node();
        this.size = 0;
    }
    public int getSize(){
        return this.size;
    }
    public boolean isEmpty(){
        return this.size == 0;
    }
    private class Node{
        private boolean isWord;
        private TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            this.next = null;
        }
        public Node(){
            this(false);
        }
    }
}
