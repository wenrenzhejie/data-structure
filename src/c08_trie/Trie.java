package c08_trie;

import java.util.TreeMap;

public class Trie {
    private Node root;
    private int size;
    public int getSize(){
        return this.size;
    }
    public boolean isEmpty(){
        return this.size == 0;
    }
    public Trie(){
        this.root = new Node();
        this.size = 0;
    }
    //向Trie中添加一个单词(非递归实现)
    public void add(String word){
        Node current = this.root;
        for (int i = 0;i < word.length();i++){
            Character c = word.charAt(i);
            if (current.next.get(c) == null){
                current.next.put(c,new Node());
            }
            current = current.next.get(c);
        }
        if (!current.isWord){
            current.isWord = true;
            this.size ++;
        }
    }
    //向Trie中添加一个单词(递归实现)
    public void addR(String word){
        Node node = addR(this.root, word, 0);
        if (!node.isWord){
            node.isWord = true;
            this.size ++;
        }
    }
    private Node addR(Node current,String word,int i){
        if (i == word.length()){
            return current;
        }
        Character c = word.charAt(i);
        if (current.next.get(c) == null){
            current.next.put(c,new Node());
        }
        return addR(current.next.get(c), word, ++i);
    }
    //查询一个单词是否在Trie中(非递归实现)
    public boolean contains(String word){
        Node current = this.root;
        for (int i = 0;i < word.length();i++){
            char c = word.charAt(i);
            if (current.next.get(c) == null){
                return false;
            }
            current = current.next.get(c);
        }
        return current.isWord;
    }
    //查询一个单词是否在Trie中(递归实现)
    public boolean containsR(String word){
        return containsR(this.root,word,0);
    }
    private boolean containsR(Node current,String word,int i){
        if (i < word.length()){
            if (current.next.get(word.charAt(i)) == null){
                return false;
            }else {
                return containsR(current.next.get(word.charAt(i)),word,++i);
            }
        }
        return current.isWord;
    }
    //查询是否有以某个前缀开头的单词
    public boolean isPrefix(String prefix){
        Node node = this.root;
        for (int i = 0;i < prefix.length();i++){
            char c = prefix.charAt(i);
            if (node.next.get(c) == null){
                return false;
            }
            node = node.next.get(c);
        }
        return true;
    }
    private class Node{
        private boolean isWord;
        private TreeMap<Character,Node> next;
        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }
        public Node(){
            this(false);
        }
    }
}
