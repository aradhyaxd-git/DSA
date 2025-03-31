/*2551. Put Marbles in Bags

You have k bags. You are given a 0-indexed integer array weights where weights[i] is the weight of 
the ith marble. You are also given the integer k.

Divide the marbles into the k bags according to the following rules:

No bag is empty.

If the ith marble and jth marble are in a bag, then all marbles with an index between the ith and 
jth indices should also be in that same bag.

If a bag consists of all the marbles with an index from i to j inclusively, then the cost of the bag is 
weights[i] + weights[j].
The score after distributing the marbles is the sum of the costs of all the k bags.

Return the difference between the maximum and minimum scores among marble distributions.

 

Example 1:

Input: weights = [1,3,5,1], k = 2
Output: 4
Explanation: 
The distribution [1],[3,5,1] results in the minimal score of (1+1) + (3+1) = 6. 
The distribution [1,3],[5,1], results in the maximal score of (1+3) + (5+1) = 10. 
Thus, we return their difference 10 - 6 = 4. */

class Solution {
    public long putMarbles(int[] weights, int k) {
        int n= weights.length;
        int m= n-1;
        int pairSum[]= new int[m];

        for(int i=0;i<m;i++){
            pairSum[i]= weights[i]+weights[i+1];
        }
        Arrays.sort(pairSum);
        long minSum=0,maxSum=0;

        for(int i=0;i<k-1;i++){ // as k-1 pair banengay har baar
            minSum+= pairSum[i];
            maxSum+= pairSum[m-i-1];
        }

        return maxSum-minSum;
    }
}


/* similar based questions: 
 * 1) LC 561 Array partition
 * 2) LC 1877 Minimize maximum Pair Sum in Array
 * 3) LC 2740 Find value of partition 
 */