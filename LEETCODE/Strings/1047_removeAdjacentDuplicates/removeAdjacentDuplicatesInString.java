class Solution {
        public String removeDuplicates(String s) {
            int n=s.length();
            StringBuilder result= new StringBuilder();
            Stack<Character> st= new Stack<>();
            for(int i=0;i<n;i++){
                if(st.empty() || st.peek()!=s.charAt(i)){
                    st.push(s.charAt(i));
                }
                else
                st.pop();
            }
            while(!st.empty()){
                result.append(st.pop());
            }
            return result.reverse().toString();
        }
    }
            
        
    
