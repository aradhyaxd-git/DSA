/*Given an undirected graph with V vertices and E edges, represented as a 2D vector edges[][], 
where each entry edges[i] = [u, v] denotes an edge between vertices u and v, determine whether
 the graph contains a cycle or not. */



class Solution {
    // Function to detect cycle in an undirected graph.
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        // Code here
        int n= adj.size();
        boolean visited[]= new boolean[n];
        for(int i=0;i<n;i++){
            if(!visited[i] && isCycleDFS(i,adj,visited,-1))
                return true;
        }
        return false;
    }
    
    public boolean isCycleDFS(int node,ArrayList<ArrayList<Integer>> adj, boolean[] visited,int parent){
        visited[node]=true;
        for(int neighbour: adj.get(node)){
            if(neighbour==parent) continue;
            if(visited[neighbour])
                return true;
            if(isCycleDFS(neighbour,adj,visited,node))
                return true;
        }
        return false;
    }
}