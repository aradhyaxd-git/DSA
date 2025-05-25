class Solution {
    //TC: O(2*N)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n= nums2.length;
        Map<Integer,Integer> map= new HashMap<>();
          //{number --> next greater element}
        /*STEP 1: traverse karo right to left, so that we can get next greater element easily
                --> FIrst read the question again and get the analogy
                --> number aur uska next greater element hum log ek map mei store karke rakh lengay

                --> We will use a monotonic decreasing stack, to help fetch the decreasing number in O(1)

                --> agar koi number, bada mil jata hai, to usse chote hum log saare hatate jayengay */
        Stack<Integer> st= new Stack<>();
        int n1= nums1.length;
        for(int i=n-1;i>=0;i--){
            int num= nums2[i];
            while(!st.isEmpty() && st.peek()<=num){ //number bada mila, to saare chote number hata do
                st.pop();
            }
            //agar stack khali hai, to -1 daal do.. which means, right mei bada number nahi mila
            int nextGreater= st.isEmpty()?-1:st.peek();
            map.put(num,nextGreater);
            st.push(num);
        }
        /*STEP 2: Answer array mei daal do direct map se uthake */
        int ans[]= new int[n1];
        

        for(int i=0;i<n1;i++){
            ans[i]= map.get(nums1[i]);
        }

        return ans;
    }
}class Solution {
    //TC: O(2*N)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n= nums2.length;
        Map<Integer,Integer> map= new HashMap<>();
          //{number --> next greater element}
        /*STEP 1: traverse karo right to left, so that we can get next greater element easily
                --> FIrst read the question again and get the analogy
                --> number aur uska next greater element hum log ek map mei store karke rakh lengay

                --> We will use a monotonic decreasing stack, to help fetch the decreasing number in O(1)

                --> agar koi number, bada mil jata hai, to usse chote hum log saare hatate jayengay */
        Stack<Integer> st= new Stack<>();
        int n1= nums1.length;
        for(int i=n-1;i>=0;i--){
            int num= nums2[i];
            while(!st.isEmpty() && st.peek()<=num){ //number bada mila, to saare chote number hata do
                st.pop();
            }
            //agar stack khali hai, to -1 daal do.. which means, right mei bada number nahi mila
            int nextGreater= st.isEmpty()?-1:st.peek();
            map.put(num,nextGreater);
            st.push(num);
        }
        /*STEP 2: Answer array mei daal do direct map se uthake */
        int ans[]= new int[n1];
        

        for(int i=0;i<n1;i++){
            ans[i]= map.get(nums1[i]);
        }

        return ans;
    }
}