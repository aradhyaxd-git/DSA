/*
1971. Find if Path Exists in Graph

There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 
(inclusive). The edges in the graph are represented as a 2D integer array edges, where each 
edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. 
Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path
 from source to destination, or false otherwise.
 */


 class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
      
        List<List<Integer>> adj= new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int edge[]:edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue= new LinkedList<>();
        boolean visited[]= new boolean[n];
        visited[source]=true;
        queue.offer(source);
        while(!queue.isEmpty()){
            int node= queue.poll();
            if(node==destination) return true;
            for(int v: adj.get(node)){
                if(!visited[v]){
                    visited[v]=true;
                    queue.offer(v);
                }
            }
        }
        return false;
    }
}