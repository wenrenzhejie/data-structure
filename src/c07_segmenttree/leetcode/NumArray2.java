package c07_segmenttree.leetcode;

import c07_segmenttree.Merger;
import c07_segmenttree.SegmentTree;

public class NumArray2 {
    private int sums[];
    public NumArray2(int[] nums) {
        sums = new int[nums.length + 1];
        sums[0] = 0;
        for (int i = 1;i < sums.length;i ++){
            sums[i] = sums[i - 1] + nums[i - 1];
        }
    }
    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }
}
