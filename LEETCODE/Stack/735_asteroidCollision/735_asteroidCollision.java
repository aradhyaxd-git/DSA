class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st= new Stack<>();
        for(int ast:asteroids){
            while(!st.empty() && ast<0 && st.peek()>0){
                int sum= ast+st.peek();
                //agar mera sum positive hai to, skip this step
                if(sum>0) ast=0;
                else if(sum<0) st.pop(); //sum negative hua to stack ke top wala aadmi uda do
                else { //sum=0 wali condition hogi ye , ismei dono ko uda do
                ast=0;
                st.pop();
                }
            }
            if(ast!=0) st.push(ast);
        }
        int size= st.size()-1;
        int ans[]= new int[st.size()];
        while(!st.empty()){
            ans[size--]= st.pop();
        }
        return ans;
    }
}