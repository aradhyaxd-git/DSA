class Solution {
    private List<List<Integer>> ans= new ArrayList<>();
    public void solve(int nums[],int i,List<Integer> temp){
        if(i>=nums.length){
            ans.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[i]);
        solve(nums,i+1,temp);
        temp.remove(temp.size()-1);
        solve(nums,i+1,temp);
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> find= new ArrayList<>();
        solve(nums,0,find);
        return ans;
    }
}


        
    