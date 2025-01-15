class Solution {
    public int minOperations(int[] nums, int k) {
        /*Intuition of this problem:
        2   ->  0 1 0
        1   ->  0 0 1
        3   ->  0 1 1
        4   ->  1 0 0
        ________________ ^
                1 0 0 
    and k=1 is  0 0 1
    we take Xor ______
                1 0 1   so the answer is the total 1's in the answer*/
        int totalxor=0;
        for(int num:nums)
            totalxor ^=num;
        return Integer.bitCount(totalxor^k);
        
    }
}