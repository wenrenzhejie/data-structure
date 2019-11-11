package c05_set_map;

import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

public class Main {
    public static void main(String[] args){
        Set<Integer> set = new LinkedListSet<Integer>();
        for (int i = 0;i < 5;i++){
            set.add(i);
        }
        set.remove(3);
        set.add(3);
        set.add(3);
        System.out.println(set);
    }
}
