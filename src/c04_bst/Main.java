package c04_bst;

public class Main {
    public static void main(String[] args){
       /* BST<Integer> integerBST = new BST<>();
        integerBST.add(1);
        integerBST.preOrder();*/
        BST2<Integer> integerBST = new BST2<>();
        int[] nums = {6,3,1,2,8,7,9,16};
        for (int i = 0;i < nums.length;i++){
            integerBST.add(nums[i]);
        }

        integerBST.remove(6);
//        integerBST.removeMin();
        integerBST.levelOrder();
//        Integer minimum = integerBST.minimum();
//        System.out.println(minimum);
//        Integer maximum = integerBST.maximum();
//        System.out.println(maximum);
//        integerBST.levelOrder();
//        integerBST.preOrder();
//        integerBST.preOrder();
//        System.out.println(integerBST);
//        integerBST.inOrder();
//        integerBST.postOrder();
//        System.out.println("------------------------");
//        integerBST.preOrderNR();
//        integerBST.levelOrder();
    }
}
