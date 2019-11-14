package c07_segmenttree.leetcode;

import c07_segmenttree.Merger;
import c07_segmenttree.SegmentTree;

public class NumArray {
    private SegmentTree<Integer> segmentTree;
    public NumArray(int[] nums) {
        Integer[] num = new Integer[nums.length];
        for (int i = 0;i < nums.length;i ++){
            num[i] = nums[i];
        }
        if (nums.length > 0){
            segmentTree = new SegmentTree<>(num, new Merger<Integer>() {
                @Override
                public Integer merge(Integer a, Integer b) {
                    return a + b;
                }
            });
        }
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null){
            throw new RuntimeException("所给数据不合法");
        }
        return segmentTree.query(i,j);
    }

    public static void main(String[] args){
        int[]nums = {-2, 0, 3, -5, 2, -1};
        int i = new NumArray(nums).sumRange(0, 5);
        System.out.println(i);
    }
}
