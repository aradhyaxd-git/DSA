class Solution {
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