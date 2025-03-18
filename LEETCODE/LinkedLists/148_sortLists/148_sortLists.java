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
class Solution1 {
    public ListNode sortList(ListNode head) {
        if(head==null|| head.next==null) return head;
        ListNode temp= head;
        List<Integer> list= new ArrayList<>();
        while(temp!=null){
            list.add(temp.val);
            temp= temp.next;
        }
        Collections.sort(list);
        ListNode dummy= new ListNode(0);
        ListNode current= dummy;
        for(int value: list){
            current.next = new ListNode(value);
            current= current.next;
        }
        return dummy.next;
    }
}
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
class Solution {

  public ListNode findMiddle(ListNode head){
    if(head==null || head.next==null) return head;
    ListNode fast= head;
    ListNode slow= head;
    while(fast.next!=null && fast.next.next!=null){ //imp here : NOTE: we used fast.next && fast.next.next 
      fast= fast.next.next;
      slow= slow.next;
    }
    return slow;
  }

    public ListNode sortList(ListNode head) {
      //WE WILL USE MERGE SORT HERE
      if(head==null || head.next==null) return head;

      // Step1: Find middle and split the list into two halves
        ListNode middle = findMiddle(head);
        ListNode rightHead = middle.next;
        middle.next = null; // **break the linked list into two halves**

        // Step2: Sort both halves recursively
        ListNode leftHead = sortList(head);
        rightHead = sortList(rightHead);
        //Step3: merge both
      return merge(leftHead,rightHead);  
    }

    public ListNode merge(ListNode head1, ListNode head2){


      ListNode t1= head1;
      ListNode t2= head2;
      ListNode dummyHead = new ListNode(-1);
      ListNode temp= dummyHead;

      while(t1!=null && t2!=null){
        if(t1.val<t2.val){
          temp.next=t1;
          temp=t1;
          t1=t1.next;
        }
        else{
          temp.next= t2;
          temp=t2;
          t2=t2.next;
        }
      }
      if(t1!=null) temp.next=t1;
      if(t2!=null) temp.next=t2;

      return dummyHead.next;
    }
}