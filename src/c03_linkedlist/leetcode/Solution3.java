package c03_linkedlist.leetcode;

public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null){
            return null;
        }
        ListNode listNode = removeElements(head.next, val);
        if (head.val == val){
           /*  1. ListNode listNode = removeElements(head.next, val);*/
            return listNode;
        }else {
           /* 2. ListNode listNode = removeElements(head.next, val);*/
            head.next = listNode;
            return head;
        }
    }
}
