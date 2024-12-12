class Solution {
    public long maxKelements(int[] nums, int k) {
        long sum=0;
        PriorityQueue<Integer> pq= new PriorityQueue<>(Collections.reverseOrder());
        for(int num:nums) pq.add(num);
        while(k--!=0){
            int maxi= pq.poll();
            sum+=maxi;
            pq.add((int)(Math.ceil(maxi/3.0)));   
        }
        return sum;
    }
}