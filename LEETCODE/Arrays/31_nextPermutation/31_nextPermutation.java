class Solution {
    public void swap(int nums[],int i,int j){
        int temp= nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    public void reverse(int nums[],int i,int j){
        while(i<j){
             int temp= nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
            i++; j--;
        }
    }
    public void nextPermutation(int[] nums) {
        int n= nums.length;
        /*Step 1: First find the gola index */
        int gola_index=-1;
        for(int i=n-1;i>0;i--){
            if(nums[i-1]<nums[i]){
                gola_index= i-1;
                break;
            }
        }
        //Ab swap index dhundho.. i.e right side mei next element
        // jo bada hai gola index se

        //ye tabhi possible hai when gola index is not -1
        if(gola_index !=-1){
            int swap_index=-1;
            for(int j=n-1;j>=gola_index+1;j--){
                if(nums[j]>nums[gola_index]){
                    swap_index=j;
                    break;
                }
            }
            swap(nums,gola_index,swap_index);
        }
        //now reverse: gola index ke just baad se 
        reverse(nums,gola_index+1,n-1);
        
    }
}