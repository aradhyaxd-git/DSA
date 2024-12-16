class Solution {
    public long continuousSubarrays(int[] nums) {
        long ans=0;
        TreeMap<Integer,Integer> map= new TreeMap<>(); //ordered map hai, kyuki hum log har baar minimum or maximum ka difference le rahe hai
        int n=nums.length;
        int right=0,left=0;
        while(right<n){
            map.put(nums[right],map.getOrDefault(nums[right],0)+1);
            while(map.lastKey()-map.firstKey()>2){
                int num= nums[left];
                map.put(num,map.get(num)-1);
                if(map.get(num)==0){
                    map.remove(num);
                }
                left++;
            }
            ans+= (right-left+1);
            right++;
        }
        return ans;


    }
}

class Solution2 {
    public long continuousSubarrays(int[] nums) {
        long ans=0;
        PriorityQueue<int[]> minHeap= new PriorityQueue<>((a,b)->a[0]-b[0]);
        PriorityQueue<int[]> maxHeap= new PriorityQueue<>((a,b)->b[0]-a[0]);
        int right=0,left=0;
        int n=nums.length;
        while(right<n){
            maxHeap.offer(new int[]{nums[right],right});
            minHeap.offer(new int[]{nums[right],right});
            while(Math.abs(maxHeap.peek()[0]-minHeap.peek()[0])>2){
                left++;
                while(!minHeap.isEmpty() && minHeap.peek()[1]<left){
                    minHeap.poll();
                }
                while(!maxHeap.isEmpty() && maxHeap.peek()[1]<left){
                    maxHeap.poll();
                }
            }
              ans+=(right-left+1);
              right++;
        }
        return ans;   
    }
}