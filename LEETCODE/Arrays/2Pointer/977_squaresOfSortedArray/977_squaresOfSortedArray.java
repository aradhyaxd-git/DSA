/*Given an integer array nums sorted in non-decreasing order,
 return an array of the squares of each number sorted in non-decreasing order. */

 class Solution1 {
    public int[] sortedSquares(int[] nums) {
        int n=nums.length;
        for(int i=0;i<n;i++){
            nums[i]=nums[i]*nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }
}


class Solution {
    public int[] sortedSquares(int[] nums) {
        int n=nums.length;
        int i=0,j=n-1;
        int ans[]= new int[n];
        int k=n-1;
        while(k>=0){
            int a= nums[i]*nums[i];
            int b= nums[j]*nums[j];
            if(a>b){
                ans[k--]=a; i++;
            }
            else {
                ans[k--]=b; j--;
            }
        }
        return ans;
    }
}