class Solution {
    public boolean isTrionic(int[] nums) {

        int n= nums.length;

        int i=0;

        while(i+1<n && nums[i]<nums[i+1])
            i++;
        
        if(i==0) return false;

        int startDec=i;

        while(i+1<n && nums[i]>nums[i+1])
            i++;
        
        if(startDec==i) return false;

        int startInc=i;

        while(i+1<n && nums[i]<nums[i+1])
            i++;

        if(i==startInc) return false;

        return i==n-1;

    
        
    }
}