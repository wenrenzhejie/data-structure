package c08_trie.leetcode;

import java.util.TreeMap;

public class MapSum {
    private Node root;
    public MapSum() {
        root = new Node();
    }
    public void insert(String key, int val) {
        Node node = this.root;
        for (int i = 0 ;i < key.length();i++){
            char c = key.charAt(i);
            if (node.next.get(c) == null){
                node.next.put(c,new Node());
            }
            node = node.next.get(c);
        }
        node.value = val;
    }
    public int sum(String prefix) {
        Node node = this.root;
        for (int i = 0 ;i < prefix.length();i++){
            char c = prefix.charAt(i);
            if (node.next.get(c) == null){
                return 0;
            }
            node = node.next.get(c);
        }
        return sum(node);
    }
    private int sum(Node node){
        if (node.next.size() == 0){
            return node.value;
        }else {
            int res = node.value;
            for (Character c:node.next.keySet()){
                res += sum(node.next.get(c));
            }
            return res;
        }
    }

    private class Node{
        private int value;
        private TreeMap<Character,Node> next;
        public Node(int value){
            next = new TreeMap<>();
        }
        public Node(){
            this(0);
        }
    }
}
