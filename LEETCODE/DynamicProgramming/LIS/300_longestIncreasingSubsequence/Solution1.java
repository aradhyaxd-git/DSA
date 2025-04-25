package DynamicProgramming.LIS.300_longestIncreasingSubsequence;

public class Solution1 {
    public int lengthOfLIS(int[] nums) {

        int n= nums.length;

        int dp[][]= new int[n][n+1];
        for(int row[]:dp)
            Arrays.fill(row,-1);
        return solve(0,-1,nums,dp);

        
    }

    public int solve(int i,int prev_index, int nums[],int dp[][]){
        int len=Integer.MIN_VALUE;
        if(i==nums.length) return 0;
        if(dp[i][prev_index+1]!=-1) return dp[i][prev_index+1]; //we have shifted the co ordinate for getting our answer ie. what ever we are getting at any particular location
            //we have shifted that by 1
        int not_take= 0+solve(i+1,prev_index,nums,dp);
        int take=0;
        if(prev_index == -1 || nums[i]>nums[prev_index]){
            take= 1+ solve(i+1,i,nums,dp);
        }
        len= Math.max(len,Math.max(take,not_take));
        return dp[i][prev_index+1]=len;
    }
}

class Solution2 {
    public int lengthOfLIS(int[] nums) {
        int n= nums.length;
        int dp[][]= new int[n+1][n+1];
        //no need to explicity write base cases as automatically bhari hi hogi dp 0 se

        //now , what are the changing paramters ,,-> index, prev_index

        for(int i=n-1;i>=0;i--){
            // for(int j=n-1;j>=-1;j--) ye hona chahiye tha but ni hoga, i-1 se chalu hoga

            for(int prev_idx= i-1; prev_idx>=-1;prev_idx--){

        //          int not_take= 0+solve(i+1,prev_index,nums,dp);
        //         int take=0;
        //         if(prev_index == -1 || nums[i]>nums[prev_index])
        //              take= 1+ solve(i+1,i,nums,dp);
                    int not_take= 0+dp[i+1][prev_idx+1]; //+1 kyuki shift kia hai prev_idx wala

                    int take=0;
                    if(prev_idx == -1 || nums[i]>nums[prev_idx])
                        take= 1+ dp[i+1][i+1];

                    dp[i][prev_idx+1]= Math.max(dp[i][prev_idx+1],Math.max(take,not_take));

            }
        }

        return dp[0][-1+1];
    }
}


class Solution3 {
    public int lengthOfLIS(int[] nums) {
        int n= nums.length;
        int curr[]= new int[n+1];
        int next[]=new int [n+1];
        
        // int not_take= 0+dp[i+1][prev_idx+1]; //+1 kyuki shift kia hai prev_idx wala

        //             int take=0;
        //             if(prev_idx == -1 || nums[i]>nums[prev_idx])
        //                 take= 1+ dp[i+1][i+1];

        //             dp[i][prev_idx+1]= Math.max(dp[i][prev_idx+1],Math.max(take,not_take));
         for(int i=n-1;i>=0;i--){
            for(int prev_idx= i-1; prev_idx>=-1;prev_idx--){
                    int not_take= 0+next[prev_idx+1]; //+1 kyuki shift kia hai prev_idx wala
                    int take=0;
                    if(prev_idx == -1 || nums[i]>nums[prev_idx])
                        take= 1+ next[i+1];

                    curr[prev_idx+1]= Math.max(curr[prev_idx+1],Math.max(take,not_take));
            }
            next= curr.clone();
        }
        return curr[0];
    }
}


//APPROACH 4: LESS INTUTIVE
class Solution4 {
    public int lengthOfLIS(int[] nums) {
        int n= nums.length;

        int dp[]= new int[n];
        Arrays.fill(dp,1);

        int maxi=1;
        for(int i=0;i<n;i++){
            for(int prev=0;prev<i;prev++){
                if(nums[prev]<nums[i]){
                    dp[i]= Math.max(dp[i],1+dp[prev]);
                }
            }
            maxi= Math.max(maxi,dp[i]);
        }

        return maxi;

        
    }
}

//final approach: using binary  search
class Solution {
   public  int lowerBound(List<Integer> arr, int target) {
    int low = 0, high = arr.size();
    while (low < high) {
        int mid = (low + high) / 2;
        if (arr.get(mid) < target)
            low = mid + 1;
        else
            high = mid;
    }
    return low;
   }
    public int lengthOfLIS(int[] nums) {
        int n= nums.length;
        List<Integer> list= new ArrayList<>();
        list.add(nums[0]);
        for(int i=0;i<n;i++){
            if(nums[i]>list.get(list.size()-1)){
                list.add(nums[i]);
            }
            else{
                int findIdx= lowerBound(list,nums[i]);
                list.set(findIdx,nums[i]);
            }
        }

        return list.size();
        
    }
} {
    
}
