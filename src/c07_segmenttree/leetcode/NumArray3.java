package c07_segmenttree.leetcode;

public class NumArray3 {
    private int sums[];
    private int data[];
    public NumArray3(int[] nums) {
        data = new int[nums.length];
        for (int i = 0;i < nums.length;i++){
            data[i] = nums[i];
        }
        sums = new int[nums.length + 1];
        sums[0] = 0;
        for (int i = 1;i < sums.length;i ++){
            sums[i] = sums[i - 1] + nums[i - 1];
        }
    }
    public void update(int i, int val) {
        data[i] = val;
        for (int j = i + 1 ;j < sums.length;j ++){
            sums[j] = sums[j - 1] + data[j - 1];
        }
    }
    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }
}
