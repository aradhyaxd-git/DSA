/*3372. Maximize the Number of Target Nodes After Connecting Trees I

There exist two undirected trees with n and m nodes, with distinct labels in ranges [0, n - 1] and [0, m - 1], respectively.

You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] 
indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an
 edge between nodes ui and vi in the second tree. You are also given an integer k.

Node u is target to node v if the number of edges on the path from u to v is less than or equal to k. Note that a node is always target to itself.

Return an array of n integers answer, where answer[i] is the maximum possible number of nodes target to node i of the first tree if 
you have to connect one node from the first tree to another node in the second tree.

Note that queries are independent from each other. That is, for every query you will remove the added edge before proceeding to the next query.

 

Example 1:

Input: edges1 = [[0,1],[0,2],[2,3],[2,4]], edges2 = [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]], k = 2

Output: [9,7,9,8,8]

Explanation:

For i = 0, connect node 0 from the first tree to node 0 from the second tree.
For i = 1, connect node 1 from the first tree to node 0 from the second tree.
For i = 2, connect node 2 from the first tree to node 4 from the second tree.
For i = 3, connect node 3 from the first tree to node 4 from the second tree.
For i = 4, connect node 4 from the first tree to node 4 from the second tree. */


class Solution {
    
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n= edges1.length+1;
        int res1[]= findCount(edges1,k);
        int res2[]= findCount(edges2,k-1);
        int maxTargetNodes= Arrays.stream(res2).max().getAsInt();
        for(int i=0;i<n;i++){
            res1[i]+= maxTargetNodes;
        }
        return res1;

    }

    public int[] findCount(int edges[][], int d){
        int n= edges.length+1;
        List<List<Integer>> adj= new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int edge[]:edges){
            int u= edge[0];
            int v= edge[1];
            
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int res[]= new int[n];
        for(int i=0;i<n;i++){
            res[i]= bfs(adj,i,d,n);
        }
        return res;

    }

    public int bfs(List<List<Integer>> adj, int curr, int d, int n){
        boolean visited[]= new boolean[n];
        Queue<int[]> queue= new LinkedList<>();
        queue.offer(new int[]{curr,0});
        visited[curr]=true;
        
        int count=0; //count the target nodes

        while(!queue.isEmpty()){
            int current[]= queue.poll();
            int node= current[0];
            int distance= current[1];
            if(distance>d){
                continue;
            }
            count++;
            for(int v: adj.get(node)){
                if(!visited[v]){
                    visited[v]=true;
                    queue.offer(new int[]{v,distance+1}); 
                }
            }
        }
        return count;
    }
}