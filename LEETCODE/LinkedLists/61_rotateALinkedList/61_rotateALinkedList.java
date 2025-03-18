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

 /*APPROACH : 
      EG: 1-->2-->3-->4-->5-->null   and k=2;
      final look would be: 
        4-->5-->1-->2-->3
      -> find len= 5 

      -> len-k= 3
      -> travel till last and store temp.next= head

      -> now find the location of newLastNode = 3

      head= newLastNode ke next mei 
      newLastNode ka next = null
    */
        
    class Solution {
        public ListNode find(ListNode head, int destiny){
          ListNode temp= head; int count=1;
          while(temp!=null){
            if(count==destiny) return temp;
            count++;
            temp=temp.next;
          }
          return temp;
        }
        public ListNode rotateRight(ListNode head, int k) {
          if(head==null || k==0) return head;
          int len=1;
          ListNode tail= head;
          while(tail.next!=null){
            len++;
            tail=tail.next;
          }
          if(k%len==0) return head;
          k=k%len;
    
          tail.next= head;
          ListNode newLastNode= find(head,len-k);
    
          head= newLastNode.next;
          newLastNode.next= null;
    
          return head;
            
        }
    }