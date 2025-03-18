/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/*APPROACH 1: USING STACKS */
class Solution2 {
    public ListNode reverseList(ListNode head) {
        Stack<Integer> st= new Stack<>();
        ListNode temp=head;
        while(temp!=null){
            st.push(temp.val);
            temp=temp.next;
        }
        temp=head;
        while(temp!=null){
            temp.val=st.pop();
            temp=temp.next;
        }
        return head;
    }
}

/*APPROACH 2: USING THREE POINTERS */
class Solution1 {
    public ListNode reverseList(ListNode head) {
      if(head==null || head.next==null) return head;

      ListNode temp= head;
      ListNode prev= null;
      while(temp!=null){
        ListNode front= temp.next;
        temp.next= prev;
        prev=temp;
        temp=front;
      }
      return prev;   
    }
}

/*APPROACH 3 : USING RECURSION */
class Solution {
    public ListNode reverseList(ListNode head) {
      if(head==null || head.next==null) return head;
      ListNode newHead= reverseList(head.next);
      head.next.next=head;
      head.next=null;
      return newHead;
        
    }
}