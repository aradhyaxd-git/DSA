class Solution {
    public int minStoneSum(int[] piles, int k) {
        int n=piles.length;
        PriorityQueue<Integer> pq= new PriorityQueue<>(Collections.reverseOrder());
        int sum=0;
        for(int i=0;i<n;i++){
            pq.add(piles[i]);
            sum+= piles[i];
        }
        for(int i=0;i<k;i++){
            int maxElement= pq.poll();
            int remove= maxElement/2;
            sum-=remove;
            maxElement-=remove;
            pq.add(maxElement);
        }
        return sum;
    }
}