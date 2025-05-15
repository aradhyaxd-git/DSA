/*You are given a 0-indexed 2D integer array pairs where pairs[i] = [starti, endi]. An arrangement of pairs is valid if for
 every index i where 1 <= i < pairs.length, we have endi-1 == starti.

Return any valid arrangement of pairs.

Note: The inputs will be generated such that there exists a valid arrangement of pairs.

 

Example 1:

Input: pairs = [[5,1],[4,5],[11,9],[9,4]]
Output: [[11,9],[9,4],[4,5],[5,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 9 == 9 = start1 
end1 = 4 == 4 = start2
end2 = 5 == 5 = start3
Example 2:

Input: pairs = [[1,3],[3,2],[2,1]]
Output: [[1,3],[3,2],[2,1]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 3 == 3 = start1
end1 = 2 == 2 = start2
The arrangements [[2,1],[1,3],[3,2]] and [[3,2],[2,1],[1,3]] are also valid.
Example 3:

Input: pairs = [[1,2],[1,3],[2,1]]
Output: [[1,2],[2,1],[1,3]]
Explanation:
This is a valid arrangement since endi-1 always equals starti.
end0 = 2 == 2 = start1
end1 = 1 == 1 = start2
  */



//appraoch: Heizolher algo O(E) time 
// dfs+ backtracking concept using stack 
class Solution {
    /*Problem demands pairs ke form mei u-> v, hence directed graph hai... saare pairs ko aise order mei arrange karna hai
        --> such tthat sabhi ka use exactly once ho... aur wo valid path banegea
        --> ek node ki outdegree-idegree=1, ie startNode 

    Now , Heirholzer Algo mei --> dfs use krte hai edges par ek ek karke , and backtrack krte hai path build krte hai */

    public int[][] validArrangement(int[][] pairs) {
        //step 1: indegree and outdgree calculate karne ke liye map lelo
        Map<Integer,Integer> outdegree= new HashMap<>();
        Map<Integer,Integer> indegree= new HashMap<>();
        //step 2: make adjacency list
        Map<Integer,List<Integer>> adj= new HashMap<>();
 
        for(int pair[]:pairs){
            int u= pair[0];
            int v= pair[1];
            adj.computeIfAbsent(u,x->new ArrayList<>()).add(v);
            outdegree.put(u,outdegree.getOrDefault(u,0)+1);
            indegree.put(v,indegree.getOrDefault(v,0)+1);
        }
        
        int startNode=pairs[0][0]; //suppose karo ki startNode begin mei hai
        for(int node: adj.keySet()) //now check the condition for startNode at eulerian path
        {
            if(outdegree.getOrDefault(node,0)-indegree.getOrDefault(node,0)==1){
                startNode= node;
                break; //jaise hi startNode mil jaye wese hi break kardo

            }
        }
        

        //step3 : ab dfs karo


        /*NOTE: yaha par normal bfs/dfs nahi ho rahah hai , as bfs gauarenteee ni deta ki edges exactly ek baar visit ho
                Heirholzer naturally ye gaurantee deta hai , jab bhi hum log edge explore krte hai , to usse graph se hta dia jata hai  , isliye visited[] array ka concept ni hai */

        List<Integer> path= new ArrayList<>(); //store euler path
        Stack<Integer> st= new Stack<>();
        st.push(startNode);

        while(!st.isEmpty()){
            int curr= st.peek(); // current node ke outgoing edge ko check krengay, jab tak kisi node se jane ka rasta bacha hia, tab tak whi node process hoga
            //agar current ke pass outgoing edge hai, means jab tak kuch paths bache hai us node mei, hum dfs krengay
            //this ensures ki hum log ekdm deep ja rhe hai jab tak dead end nhi aa jata hai
            if(adj.containsKey(curr) && !adj.get(curr).isEmpty()){
                int next= adj.get(curr).remove(adj.get(curr).size()-1);
                st.push(next);
            }
            else {//ekdm deep phoch gaye, ab backtrack karke path banao
                path.add(curr);
                st.pop();
            }
        }
        Collections.reverse(path); //since path , reverse order mei bana hoga. kyuki hum log ekdm deep gaye hai 
        int ans[][]= new int[path.size()-1][2];
        //ans store karne ka format jo hai, waise hi dengay
        for(int i=0;i<path.size()-1;i++){
    
            ans[i][0]=path.get(i);
            ans[i][1]= path.get(i+1);
        }

        return ans;

        
    }
}