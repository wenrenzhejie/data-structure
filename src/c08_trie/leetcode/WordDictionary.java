package c08_trie.leetcode;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class WordDictionary {
    private Node root;
    public WordDictionary() {
        this.root = new Node();
    }

    public void addWord(String word) {
        Node node = this.root;
        for (int i = 0 ;i < word.length();i++){
            char c = word.charAt(i);
            if (node.next.get(c) == null){
                node.next.put(c,new Node());
            }
            node = node.next.get(c);
        }
        node.isWord = true;
    }
    public boolean search(String word) {
        return search(this.root,word,0);
    }
    private boolean search(Node node,String word,int i){
        boolean existence = true;
        if (i < word.length()){
            char c = word.charAt(i);
            if (c != '.'){
                if (node.next.get(c) == null){
                    return false;
                }
                return search(node.next.get(c),word,++i);
            }else {
                Set<Map.Entry<Character, Node>> entries = node.next.entrySet();
                i++;
                for (Map.Entry<Character, Node> entry:entries){
                    int k = i;
                    existence = search(entry.getValue(), word, k++);
                    if (existence){
                        return true;
                    }
                }
                return false;
            }
        }
        return node.isWord;
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

