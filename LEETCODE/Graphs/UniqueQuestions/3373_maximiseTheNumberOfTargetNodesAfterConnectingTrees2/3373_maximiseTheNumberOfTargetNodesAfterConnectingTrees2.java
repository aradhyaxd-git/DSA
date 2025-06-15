/*3373. Maximize the Number of Target Nodes After Connecting Trees II

There exist two undirected trees with n and m nodes, labeled from [0, n - 1] and [0, m - 1], respectively.

You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] 
indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an
 edge between nodes ui and vi in the second tree.

Node u is target to node v if the number of edges on the path from u to v is even. Note that a node is always target to itself.

Return an array of n integers answer, where answer[i] is the maximum possible number of nodes that are target to node i of the first tree if you had to connect one node from the first tree to another node in the second tree.

Note that queries are independent from each other. That is, for every query you will remove the added edge before proceeding to the next query.

 

Example 1:

Input: edges1 = [[0,1],[0,2],[2,3],[2,4]], edges2 = [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]]

Output: [8,7,7,8,8]

Explanation:

For i = 0, connect node 0 from the first tree to node 0 from the second tree.
For i = 1, connect node 1 from the first tree to node 4 from the second tree.
For i = 2, connect node 2 from the first tree to node 7 from the second tree.
For i = 3, connect node 3 from the first tree to node 0 from the second tree.
For i = 4, connect node 4 from the first tree to node 4 from the second tree.
*/


class Solution {
    /*This problem is a classic graph coloring+ dfs on group of Trees type problem
    
    Actual Redifined statement--> Given 2 trees, we have to connect an edge b/w 2 trees and make a valid path
                                 having only even nodes ... with the new structure  TreeA+ Tree2 
    
    Story point

    1) We use graph coloring (bipartite) 
    2) har node ko hum , ek color assign krdengay (suppose start with 0) --> in each tree, we divide them into 2 halfs based on coloring
            --> color 0 and color 1
    3) after coloring, we will find of Tree1 + max we will get from tree2
    
     */ 

    public List<List<Integer>> makeAdj(int edges[][],int n){
        List<List<Integer>> adj= new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int edge[]:edges){
            int u= edge[0];
            int v= edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        return adj;
    }

    
 

    public void dfs(int curr,int parent, List<List<Integer>> adj , int[]zero_Ya_one_ka_count , int mark[]  ){
        //step 1: mark current node ka countWala Group , agar 0 hai to zeroKaCOunt badha do
        if(mark[curr]==0){
            zero_Ya_one_ka_count[0]++;
        }
        else {
            zero_Ya_one_ka_count[1]++;
        }
        //STEP 2: Traverse neighbour
        for(int v: adj.get(curr)){
            if(v!=parent){ //check krlo, ki wapas parent ke pass to nahi ja rhe hai 
                mark[v]= 1- mark[curr]; //opp color pakda do
                dfs(v,curr,adj,zero_Ya_one_ka_count,mark); //dfs hit krdo
            }
        }
    }


    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int m= edges1.length+1;
        int n= edges2.length+1;
        List<List<Integer>> adj1= makeAdj(edges1,m);
        List<List<Integer>> adj2= makeAdj(edges2,n);
        
        //step 1; make setup for A 
        int markA[]= new int[m];
        Arrays.fill(markA,-1); //intially, sabka marking -1 krdo, means unmarked hai
        markA[0]=0; //0 node ko 0 mark krdo ab , kyuki 
        int zero_Ya_one_ka_count_fromA[]= new int[2];
        dfs(0,-1,adj1,zero_Ya_one_ka_count_fromA, markA);


        int markB[]= new int[n];
        Arrays.fill(markB,-1); //intially, sabka marking -1 krdo, means unmarked hai
        markB[0]=0; //0 node ko 0 mark krdo ab , kyuki 
        int zero_Ya_one_ka_count_fromB[]= new int[2];
        dfs(0,-1,adj2,zero_Ya_one_ka_count_fromB, markB);


        int maxFromTree2= Math.max(zero_Ya_one_ka_count_fromB[0],zero_Ya_one_ka_count_fromB[1]);

        int res[]= new int[m];
        for(int i=0;i<m;i++){
            int marking= markA[i];

            if(marking==0)
                res[i]= zero_Ya_one_ka_count_fromA[0]+ maxFromTree2;
            else 
                res[i]= zero_Ya_one_ka_count_fromA[1]+ maxFromTree2;

        }
        return res;
    }
}