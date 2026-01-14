/*Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

 

Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10 */


/*Approach 1: using HashSets to remove duplicates post orderly */
class Solution1 {
    Set<List<Integer>> set= new HashSet<>();
    List<List<Integer>> res= new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<Integer> temp= new ArrayList<>();
        solve(0,nums,temp);
        return res;
    }
    public void solve(int i,int nums[],List<Integer> temp){
        if(i==nums.length){
            List<Integer> copy= new ArrayList<>(temp);
            if(!set.contains(copy)){
                set.add(copy);
                res.add(copy);
            }
            return;
        }
        temp.add(nums[i]);
        solve(i+1,nums,temp);
        temp.remove(temp.size()-1);
        solve(i+1,nums,temp);
    }
}

//appraoch 2: way  faster and cleaner TC:O(2^n *n)
class Solution {
    List<List<Integer>> res= new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums); //this step ensures that, all duplicate values are present in order
        solve(0,new ArrayList<>(),nums);
        return res;
    }

    public void solve(int idx, List<Integer> temp, int nums[]){
        res.add(new ArrayList<>(temp));
        for(int i=idx;i<nums.length;i++){
            //skip duplicates at the same recursion level 
            if(idx!=i && nums[i]==nums[i-1]) continue;
            temp.add(nums[i]);
            solve(i+1,temp,nums);
            temp.remove(temp.size()-1);
        }

    }
}