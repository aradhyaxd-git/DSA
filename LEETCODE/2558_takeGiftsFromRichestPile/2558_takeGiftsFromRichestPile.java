class Solution {
    public long pickGifts(int[] gifts, int k) {
        int n= gifts.length;
        PriorityQueue <Integer> pq= new PriorityQueue<>(Collections.reverseOrder());
        for(int num:gifts){
            pq.add(num);
        }
        while(k!=0){
            int maxi= pq.poll();
            pq.add((int)(Math.sqrt(maxi)));
            k--;
        }
        long sum=0;
        while(pq.size()!=0){
            sum+=pq.poll();
        }
        return sum;
    }
}