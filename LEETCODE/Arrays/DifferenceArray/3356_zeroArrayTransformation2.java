/*3356. Zero Array Transformation II

You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri, vali].

Each queries[i] represents the following action on nums:

Decrement the value at each index in the range [li, ri] in nums by at most vali.
The amount by which each value is decremented can be chosen independently for each index.
A Zero Array is an array with all its elements equal to 0.

Return the minimum possible non-negative value of k, such that after processing the 
first k queries in sequence, nums becomes a Zero Array. If no such k exists, return -1.

  */

//APPROACH 1: BRUTEFORCE, JO BOLA WAHI KARO
class Solution1 {
    public boolean isZero(int []nums){
      for(int num:nums){
        if(num!=0) return false;
      }
      return true;
    }
    public int minZeroArray(int[] nums, int[][] queries) {
      if(isZero(nums)) return 0;
      int n= nums.length;
      int count=0;
      for(int q[]:queries){
        int startIndex= q[0];
        int endIndex= q[1];
        int value= q[2];
        for(int i=startIndex;i<=endIndex;i++){
            nums[i]= Math.max(0,nums[i]-value);
          }
           count++;
        if(isZero(nums)) break;    
      }
      return isZero(nums)?count:-1;    
    }
}

/*APPROACH 2: DIFFERENCE ARRAY + BINARY SEARCH 

WE use binary search when a problem has a monotonic condition,
    here conidition is.. If k qeureis can make nums zero.. then k-1 can too

WE use difference array technique.. for effecient range updates */


class Solution {
    public boolean checkWithDifference(int nums[], int[][] queries, int k){
        int n=nums.length;
        int diff[]= new int[n];
        for(int i=0;i<=k;i++){
            int start= queries[i][0];
            int end= queries[i][1];
            int value= queries[i][2];
            diff[start]+= value;
            if(end+1<n){
                diff[end+1]-=value;
            }
        }
        int cumSum=0;
        for(int i=0;i<n;i++){
            cumSum+=diff[i];
            diff[i]=cumSum;
            if(nums[i]-diff[i]>0){ //nums[i] zero ni ban paya hai
                return false;
            }
        }
        return true;
    }
    public boolean isZero(int nums[]){
        for(int num:nums){
            if(num!=0) return false;
        }
        return true;
    }
    public int minZeroArray(int[] nums, int[][] queries) {
        int n= nums.length;
        if(isZero(nums)) return 0;
        int Q= queries.length;
        int left=0,right=Q-1,k=-1;
        while(left<=right){
            int mid= (left+right)/2;
            if(checkWithDifference(nums,queries,mid)==true){
                k= mid+1; //possible answer
                right=mid-1;
            }
            else 
                left=mid+1;
        }
        return k;
    }
}