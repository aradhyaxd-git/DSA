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

 //APPROACH 1: USING HASHMAPS
 public class Solution1 {
    public boolean hasCycle(ListNode head) {
      HashMap<ListNode,Integer> map= new HashMap<>();
      ListNode temp= head;
      while(temp!=null){
        if(map.containsKey(temp)) return true;
        map.put(temp,1);
        temp= temp.next;
      }
      return false;
        
    }
}

//APPROACH 2 : USING TORTOISE AND HARE ALGORITHM
public class Solution {
    public boolean hasCycle(ListNode head) {
       ListNode slow= head; ListNode fast= head;
      while(fast!=null && fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
        if(slow==fast) return true;
      }
      return false;
    }
}    
    