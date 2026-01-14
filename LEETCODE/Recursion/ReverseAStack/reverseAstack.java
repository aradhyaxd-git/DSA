// User function Template for Java

class Solution1 {
    static void reverse(Stack<Integer> st) {
        if(st.isEmpty()){
            return;
        }
        
        //step 1: stack ke top pe jo hai wo hata do
        int top= st.pop();
        //step 2: baaki bache huye stack ko bhej do
                    //and faith in recursion ki wo answer leke aajayega
        reverse(st);
        
        Stack<Integer> temp= new Stack<>();
        while(!st.isEmpty()){
            temp.push(st.pop());
        }
        st.push(top);
        while(!temp.isEmpty()){
            st.push(temp.pop());
        }
        
        
    }
}


class Solution {
    static void reverse(Stack<Integer> st) {
        if(st.isEmpty()){
            return;
        }
        
        //step 1: stack ke top pe jo hai wo hata do
        int top= st.pop();
        //step 2: baaki bache huye stack ko bhej do
                    //and faith in recursion ki wo answer leke aajayega
        reverse(st);
        /*
        Stack<Integer> temp= new Stack<>();
        while(!st.isEmpty()){
            temp.push(st.pop());
        }                                YE SAB CODE, BAS
                                        ELEMENT KO BOTTOM MEI KAR 
                                           RAHA HAI INSERT
        st.push(top);
        while(!temp.isEmpty()){
            st.push(temp.pop());
        }*/
        insertAtBottom(st,top);
        
        
    }
    static void insertAtBottom(Stack<Integer> st, int element){
        if(st.isEmpty()){
            st.push(element); 
            return;
        }
        int top=st.pop();
        insertAtBottom(st,element);
        st.push(top);
    }
}
