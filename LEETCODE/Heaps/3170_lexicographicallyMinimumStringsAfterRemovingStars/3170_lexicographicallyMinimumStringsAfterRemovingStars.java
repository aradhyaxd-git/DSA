public class 3170_lexicographicallyMinimumStringsAfterRemovingStars {
    //TC: O(n log n + n) = O(N log N)
    public String clearStars(String s) {
        int n= s.length();
        StringBuilder st= new StringBuilder();
        //removed to track the leftmost character to removed jo sabse chota hai
        boolean removed[]= new boolean[n];
        /*Custom priority queue banayengay, jo chota character hai wo top par hoga
                BUT --> agar character same hai--> to wahi character remove hojaye
                        -->> which has the highest index in right--> because tbhi lexicograppphically smallest banega
                    
                eg: a a b a *

                a a b a * --> normal pq se --> a b a ayega kyuki character at 0 index remove hoga 

                but our pq se aab aayega which is the right answer */
        PriorityQueue<int[]> pq= new PriorityQueue<>((a,b)->{
            if(a[0]!=b[0]) return Integer.compare(a[0],b[0]);
            return Integer.compare(b[1],a[1]);
        });

        for(int i=0;i<s.length();i++){
            char ch= s.charAt(i);
            if(ch=='*'){
                if(!pq.isEmpty()){
                    int curr[]= pq.poll(); //O(1)
                    int toBeRemovedIdx=curr[1]; //since isko remove karna hai to mark it
                    removed[toBeRemovedIdx]=true;
                }
            }
            else{
                pq.offer(new int[]{ch-'a',i}); //O(log n)
            }
        }

        for(int i=0;i<n;i++){
            char ch= s.charAt(i);
            if(ch!='*' && !removed[i]){
                st.append(ch);
            }
        }

        return st.toString();
    }
}
