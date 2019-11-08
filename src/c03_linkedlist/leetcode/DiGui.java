package c03_linkedlist.leetcode;

public class DiGui {
    public int sum(int[] arr){
        return sum(arr,0);
    }
    private int sum(int[] arr,int index){
        if (index == arr.length - 1){
            return arr[index];
        }
        return arr[index] + sum(arr,index+1);
    }

    public static void main(String[] args){
        int sum = new DiGui().sum(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(sum);
    }
}
