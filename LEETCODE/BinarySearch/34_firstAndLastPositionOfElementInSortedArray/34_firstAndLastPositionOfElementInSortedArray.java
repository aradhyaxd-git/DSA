class Solution {
    public int[] searchRange(int[] nums, int target) {
        int firstIdx= leftSearch(nums,target);
        int lastIdx= rightSearch(nums,target);
        return new int[]{firstIdx,lastIdx};
    }

    public int leftSearch(int nums[],int target){ //for first occurence --> left mei jana hoga ismei
        int ans=-1;
        int low=0,high=nums.length-1;
        while(low<=high){
            int mid= low+(high-low)/2;
            if(nums[mid]>=target){ //left mei dhundh rahe hai answer... so 2>=2 ... if yes go on left to find smaller
            ///we have to lowball it to lure it
                if(nums[mid]==target) //ans mil gaya, lekin or chota dhundhna hai
                    ans=mid;
                high=mid-1;
            }
            else
                low=mid+1;

        }
        return ans;
    }

        
    public int rightSearch(int nums[],int target){ //for first occurence --> left mei jana hoga ismei
        int ans=-1;
        int low=0,high=nums.length-1;
        while(low<=high){
            int mid= low+(high-low)/2;
            if(nums[mid]<=target){ //right mei dhundh rahe hai answer... so 2<=2 ... if yes go on right,to find isse bada index milega
            ///we have to lowball it to lure it
                if(nums[mid]==target) //ans mil gaya, lekin or chota dhundhna hai
                    ans=mid;
                low=mid+1;
            }
            else
                high=mid-1;
          }
            return ans;
    }
}