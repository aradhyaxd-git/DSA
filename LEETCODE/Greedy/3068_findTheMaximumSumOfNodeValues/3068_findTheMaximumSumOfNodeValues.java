class Solution {
    /*Approach 1: Using Xor wali properties 

    TYPE: XOR + Optimization ||  Pairwise operations || Tree/Graph where subset of edges or nodes are "flippable"

    Intuition : 
    --> Note: Yaha is question mei XOR humesha PAIRS par lag raha hai

    --> What if we act greedy and try to obtain the maximum sum as possibe

    --> nums ke har item mei xorr with k karo, and check karo agar wo value badh rahi hai
            --> badh rhi hai to profitCount add krdo and sum mei add krdo... agar value ghat rhi hai to badi value hi add karo
            ==> jab value ghat rhi to nuksan ho raha hai... har time nuksan  update karo */
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long sum=0;
        long minNuksan= Integer.MAX_VALUE;
        int profitCount=0;
        for(int num:nums){
            if((num^k) > num){
                profitCount++;
                sum+= (num^k);
            }
            else{
                sum+= num;
            }
            minNuksan=(long) Math.min(minNuksan,Math.abs((num^k)-num));
        }
        return profitCount%2==0?sum:sum-minNuksan; 
    }
}


/*Approach 2: THIS IS ALSO A GREEDY ONE

--> First add all the normal numbers to form a sum
--> WE will make an arrayList callled fayda, to store the fayda/nuksan after converting the number after xor operation 

--> fayde ko descending order mei sort kardo

--> now take max fayda everytime */
class Solution2{
        public long maximumValueSum(int[] nums, int k, int[][] edges) {
            List<Integer> fayda= new ArrayList<>();
            long newSum=0; int n= nums.length;
            for(int i=0;i<n;i++){
                newSum+=nums[i];
                fayda.add((nums[i]^k)-nums[i]);
            }

            Collections.sort(fayda,Collections.reverseOrder());
            for(int i=0;i<n-1;i+=2){
                int pairNum1= fayda.get(i);
                int pairNum2= fayda.get(i+1);
                long sumPair= pairNum1+ pairNum2;
                if(sumPair<0){
                    break;
                }
                newSum+=sumPair;
            }
            return newSum;
        }
}