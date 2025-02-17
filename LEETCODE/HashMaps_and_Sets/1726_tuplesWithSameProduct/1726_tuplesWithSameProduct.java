/*1726. Tuple with Same Product 

Given an array nums of distinct positive integers, return the number of tuples 
(a, b, c, d) such that a * b = c * d where a, b, c, and d 
are elements of nums, and a != b != c != d.

 

Example 1:

Input: nums = [2,3,4,6]
Output: 8
Explanation: There are 8 valid tuples:
(2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
(3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
*/

//APPROACH 1: BRuteForce 

class Solution1 {
    public int tupleSameProduct(int[] nums) {
        int n= nums.length;
        int tuples=0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int k=0;k<n;k++){
                    for(int l=k+1;l<n;l++){
                        if(i!=k && i!=l && j!=k && j!=l){
                            int p1= nums[i]*nums[j];
                            int p2= nums[k]*nums[l];
                            if(p1==p2) tuples++;
                        }
                    }
                }
            }
        }
         return (tuples/2)*8;
        
    }
}

//OPTIMAL APPROACH:
/* [1,2,3,4,6,12] 
    -> WE will store all the multiplication with frequnecies in it:
    -> IF the Frequency is more than once: it means that we have a valid pair
        -> TO choose : WE can CHoose in nC2 ways.... where n= number of frqeunecies
        -> Atlast, *8 as 8 permutations are possible in every case */
class Solution {
    public int tupleSameProduct(int[] nums) {
        int n= nums.length;
        HashMap<Integer,Integer> map= new HashMap<>();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                map.put(nums[i]*nums[j],map.getOrDefault(nums[i]*nums[j],0)+1);
            }
        }
        int result=0;
        for(int freq: map.values()){
            if(freq>1){
                result+= (freq*(freq-1))/2;
            }
        }
        return result*8;
    }
}
