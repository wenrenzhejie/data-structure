package c08_trie;

public class Main {
    public static void main(String[] args){
        Trie trie = new Trie();
       /* trie.add("panda");
        trie.add("pan");
        trie.add("dog");
        System.out.println(trie.contains("dog"));
        System.out.println(trie.contains("pan"));
        System.out.println(trie.contains("panda"));
        System.out.println(trie.contains("pand"));
        */
       trie.addR("panda");
       trie.addR("pan");
       trie.addR("dog");
        System.out.println(trie.containsR("dog"));
        System.out.println(trie.containsR("pan"));
        System.out.println(trie.containsR("panda"));
        System.out.println(trie.containsR("pand"));

    }
}
