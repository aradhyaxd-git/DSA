class Solution {
    public List<Integer> intersection(int[][] nums) {
        int m=nums.length;
        Set<Integer> set= new HashSet<>();
        for(int num:nums[0]){
            set.add(num);
        }
        for(int i=1;i<m;i++){
            Set<Integer> temp= new HashSet<>();
            for(int num:nums[i]){
                if(set.contains(num)){
                    temp.add(num);
                }
            }
            set=temp;
        }
        List<Integer> ans= new ArrayList<>(set);
        Collections.sort(ans);
        return ans;
    }
}