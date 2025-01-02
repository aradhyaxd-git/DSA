class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int prefix=0,c=0;
        for(int i=0;i<n;i++)
        {
            prefix+=nums[i];
            int remainder=prefix%k;
            if(remainder<0)
            {
                remainder+=k;
            }
            c+= map.getOrDefault(remainder,0);
            map.put(remainder,map.getOrDefault(remainder,0)+1);
        }
        return c;
    }
}

        

        