/*215. Kth Largest Element in an Array

Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?

 

Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5  */

class Solution {
    /* we create a min heap of size k
        -> we add the smallest k elements in the heap first
        -> now for index k to n-1
            : if current element is larger than the smallest one---> we remove smallest one
                ---> add the larger element
            NOW we have kth largest at the top of min heap 

            TC: O(K) to build heap , O(n-k)*logK for the rest operations */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq= new PriorityQueue<>();
        int n=nums.length;
        for(int i=0;i<k;i++)
        {
            pq.add(nums[i]);
        }
        for(int i=k;i<n;i++)
        {
            if(pq.peek()<nums[i])
            {
                pq.poll();
                pq.add(nums[i]);
            }
        }
        return pq.peek();
    }
}


//APPRAOCH 2; USING MAX HEAP ( SLOWER)


class Solution1{
     public int findKthLargest(int[] nums, int k) {
        int n= nums.length;
        PriorityQueue<Integer> pq= new PriorityQueue<>(Collections.reverseOrder());
        for(int num:nums) pq.offer(num);
        int res=0;
        while(k-- !=0){
            res= pq.poll();
        }
        return res;
     }

}