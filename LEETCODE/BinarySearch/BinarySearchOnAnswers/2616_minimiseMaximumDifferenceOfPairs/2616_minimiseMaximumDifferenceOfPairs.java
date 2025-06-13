class Solution {
    /*TYPE: MINIMISE THE MAXIMUM: hence, binary search on answers*/
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int n= nums.length;
        //since  minimum 0 answer to hoga hi
        int low=0;
    //at max diffeernce hoga max element- min element 
        int high= nums[n-1]-nums[0];

        int ans=0;

        while(low<=high){
            int mid= low+(high-low)/2;
            if(isPossible(mid,nums,p)){
                ans=mid;
                //agar answer mil gaya hai--> to usko store karo 
                //and check agar, isse choti bhi koi value miol sakti hai kya
                high=mid-1;
            }
            else
            //in this casse, possible nahi hai --> aaage check karo
                low=mid+1;

        }
        return ans;
        
    }
    public boolean isPossible(int diff, int nums[],int p){
        int count=0;
        int i=1;
        int n= nums.length;
        while(i<n){
            if(nums[i]-nums[i-1]<=diff){
                count++;
                i+=2; //since Pair le rahe hai , to agar pair leliya hai so , 
                //we have to move to next to next Index
            }
            else{
                i++;
            }
        }
        return count>=p;
    }
}