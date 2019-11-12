package c06_priority_heap;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        MaxHeap<Integer> integerMaxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0;i < 20;i++){
            int j = random.nextInt(100);
            System.out.println(j);
            integerMaxHeap.add(j);
        }
        int size = integerMaxHeap.size();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size;i++){
            Integer integer = integerMaxHeap.extractMax();
            list.add(integer);
        }
        System.out.println(list);
    }
}
