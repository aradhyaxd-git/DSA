package LEETCODE.503_nextGreaterElement2;
    class Solution {
        public int[] nextGreaterElements(int[] nums) {
            int n=nums.length;
            Stack<Integer> st= new Stack<>();
            int ans[]= new int[n];
            Arrays.fill(ans,-1);
            for(int i=0;i<2*n;i++){
                int currentIndex=i%n;
                while(!st.isEmpty() && nums[currentIndex]>nums[st.peek()]){
                    int index= st.pop();
                    ans[index]=nums[currentIndex];
                }
                if(i<n){
                    st.push(currentIndex);
                }
            }
            return ans;
    
            
        }
    }

