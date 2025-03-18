/*Given the head of a linked list, determine whether the list contains a loop. 
If a loop is present, return the number of nodes in the loop, otherwise return 0. */



//approach 1

class Solution {
    // Function to find the length of a loop in the linked list.
    public int countNodesinLoop(Node head) {
        int timer=1;
        HashMap<Node,Integer> map= new HashMap<>();
        Node temp= head;
        while(temp!=null){
            if(map.containsKey(temp)) return timer-map.get(temp);
            map.put(temp,timer);
            timer++;
            temp=temp.next;
        }
        return 0;
        // Add your code here.
    }
}


/*APPROACH 2: OPTIMAL */
class Solution {
    public int findLength(Node fast, Node slow){
        fast= fast.next;
        int count=1;
        while(fast!=slow){
            fast=fast.next;
            count++;
        }
        return count;
    }
    
    // Function to find the length of a loop in the linked list.
    public int countNodesinLoop(Node head) {
       Node fast= head; Node slow= head;
       while(fast!=null && fast.next!=null){
           fast= fast.next.next;
           slow= slow.next;
           if(fast==slow) return findLength(fast,slow);
       }
        return 0;
        // Add your code here.
    }
}


