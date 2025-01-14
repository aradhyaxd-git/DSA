/*You are given an integer array nums and two integers indexDiff and valueDiff.

Find a pair of indices (i, j) such that:

i != j,
abs(i - j) <= indexDiff.
abs(nums[i] - nums[j]) <= valueDiff, and
Return true if such pair exists or false otherwise. */


class Solution {
    /*Inuition: We need to find nums[i]-nums[j]<= valueDiff, the diff
                must be smaller , hence we take a sorted Set */
    /*Appraoch:
        1) Use floor to find : nearest smaller value close to curr 
            -> if it satisfies Math.abs(curr-floor) then we found a pair
        2) Use ceiling to find: nearest larger value close to curr
            -> if it satisfies Math.abs(ceil-curr) then we found a pair
        3) Shrink window , if size of set exceeds indexDiff */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            long curr = (long) nums[i];
            Long floor = set.floor(curr);
            Long ceil = set.ceiling(curr);
            if (floor != null && Math.abs(curr-floor)<= valueDiff) {
                return true;
            }
            if (ceil != null && Math.abs(ceil - curr) <= valueDiff) {
                return true;
            }
            set.add(curr);
            if (set.size() > indexDiff) {
                set.remove((long) (nums[i-indexDiff]));
            }
        }
        return false;

    }
}