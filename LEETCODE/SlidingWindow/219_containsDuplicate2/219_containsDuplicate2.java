/* PROBLEM: STATEMENT
Given an integer array nums and an integer k, return true if there are two 
distinct indices i and j in the array such that nums[i] == nums[j] 
and abs(i - j) <= k. */

class Solution { 
    /* We use Set to store the past */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int i=0,j=0,n=nums.length;
        HashSet<Integer> set= new HashSet<>();
        while(j<n){
            /*STEP 1: given Abs(j-i)<=k  , so we check
                        if our window size increases , we shrink window */
            if(Math.abs(i-j)>k){
                set.remove(nums[i]);
                i++;
            }

            /*STEP 2: WE Check : if set has nums[j] i.e duplicate */
            if(set.contains(nums[j]))
                return true;
            set.add(nums[j]);
            j++;
        }
        return false;
    }
}


/*APPROACH-2 */
class Solution2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++) {
            if (map.containsKey(nums[i])) {
                if (Math.abs(i - map.get(nums[i])) <= k) {
                    return true;
                }
            }
        map.put(nums[i], i);
        }
        return false;
    }
}