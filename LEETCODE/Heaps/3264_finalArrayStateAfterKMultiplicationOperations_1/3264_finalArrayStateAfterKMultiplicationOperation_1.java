class Solution {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        int n=nums.length;
        PriorityQueue<int[]> pq= new PriorityQueue<>((a,b)->{
            if(a[0]!=b[0]) return Integer.compare(a[0],b[0]);
            return Integer.compare(a[1],b[1]);
        });
        for(int i=0;i<n;i++) pq.offer(new int[]{nums[i],i});
        while(k--!=0){
            int ans[]=pq.poll();
            int value=ans[0];
            int index=ans[1];
            nums[index]=value*multiplier;
            pq.offer(new int[]{value*multiplier,index});
        }
        return nums;
    }
}

class Solution2 {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        int n=nums.length;
        while(k--!=0){
            int index=0,mini=nums[0];
            for(int i=1;i<n;i++){
                if(nums[i]<mini){
                    mini=nums[i];
                    index=i; 
                }
            }
            nums[index]=mini*multiplier;
        }
        return nums;
        
    }
}