package c06_priority_heap.leetcode;
import java.util.*;

public class Solution {
    private class Fre implements Comparable<Fre> {
        int i;
        int count;

        public Fre(int i,int count){
            this.i = i;
            this.count = count;
        }
        @Override
        public int compareTo(Fre o) {
            if (this.count < o.count){
                return -1;
            }else if (this.count > o.count){
                return 1;
            }else {
                return 0;
            }
        }

    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0 ;i < nums.length;i++){
            if (map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i]) + 1);
            }else {
                map.put(nums[i],1);
            }
        }
        PriorityQueue<Fre> queue = new PriorityQueue<>();
        if (map.size() == 0){
            return null;
        }
        for (Map.Entry<Integer, Integer> entry:map.entrySet()){
            if (queue.size() < k){
                queue.add(new Fre(entry.getKey(),entry.getValue()));
            }else if (entry.getValue() > queue.peek().count){
                    queue.remove();
                    queue.add(new Fre(entry.getKey(),entry.getValue()));
                }
            }

        List<Integer> list = new ArrayList<Integer>();
        while (!queue.isEmpty()){
            Fre fre = queue.remove();
            list.add(fre.i);
        }
        return list;
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,1,1,2,2,3};
        new Solution().topKFrequent(nums,2);
    }
}
