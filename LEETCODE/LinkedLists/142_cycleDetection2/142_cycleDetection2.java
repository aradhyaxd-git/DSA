/*142. Linked List Cycle II

Given the head of a linked list, return the node where the cycle begins.
 If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list
 that can be reached again by continuously following the next pointer.
  Internally, pos is used to denote the index of the node that tail's
  next pointer is connected to (0-indexed). It is -1 if there is no cycle. 
  Note that pos is not passed as a parameter.

Do not modify the linked list. */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution1 {
    public ListNode detectCycle(ListNode head) {
        ListNode temp=head;
        Set<ListNode> set= new HashSet<>();
        while(temp!=null){
            if(set.contains(temp)) return temp;
            set.add(temp);
            temp=temp.next;
        }
        return null;
    }
}


public class Solution {
    public ListNode detectCycle(ListNode head) {
      ListNode slow= head; ListNode fast= head;
      while(fast!=null && fast.next!=null){
        slow= slow.next;
        fast= fast.next.next;
        if(slow==fast) {
          ListNode entry= head;
          while(entry!=slow){
            slow=slow.next;
            entry=entry.next;
          }
          return entry;
        }
      }
      return null;
        
    }
}