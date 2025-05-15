/*
You are given an undirected weighted graph of n nodes numbered from 0 to n - 1. The graph consists of m edges represented by a 2D
 array edges, where edges[i] = [ai, bi, wi] indicates that there is an edge between nodes ai and bi with weight wi.

Consider all the shortest paths from node 0 to node n - 1 in the graph. You need to find a boolean array answer where answer[i] is
 true if the edge edges[i] is part of at least one shortest path. Otherwise, answer[i] is false.

Return the array answer.

Note that the graph may not be connected. */

class Solution {
    public class Pair{
        int node; int weight;
        Pair(int node, int weight){
            this.node= node;
            this.weight= weight; 
        }
    }
    public boolean[] findAnswer(int n, int[][] edges) {
        List<List<Pair>> adj= new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        } 
        for(int edge[]:edges){
            int u= edge[0],v=edge[1],w=edge[2];
            adj.get(u).add(new Pair(v,w));
            adj.get(v).add(new Pair(u,w));
        }
        int fromSource[]= dijkastra(adj,0);
        int fromDest[]= dijkastra(adj,n-1);

        boolean result[]= new boolean[edges.length]; //since i am comparing for edges

        int shortestPath=fromSource[n-1];


        for(int i=0;i<edges.length;i++){
            int u= edges[i][0];
            int v= edges[i][1];
            int w= edges[i][2];
            long path1= (long)fromSource[u]+w+fromDest[v];
            long path2= (long)fromSource[v]+w+fromDest[u];
            if(path1==shortestPath || path2 == shortestPath)
                result[i]=true;
        }
        return result;
    }

    public int[] dijkastra(List<List<Pair>> adj, int source){
        PriorityQueue<int[]>pq= new PriorityQueue<>((a,b)->Integer.compare(a[1],b[1]));
        int dist[]= new int[adj.size()];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[source]=0;
        pq.offer(new int[]{source,0});
        while(!pq.isEmpty()){
            int curr[]= pq.poll();
            int node= curr[0];
            int currWeight= curr[1];
            for(Pair p: adj.get(node)){
                int nextNode= p.node;
                int nextWeight= p.weight;
                if(dist[nextNode]>currWeight+nextWeight){
                    dist[nextNode]=currWeight+nextWeight;
                    pq.offer(new int[]{nextNode,currWeight+nextWeight});

                }
            }
        }
        return dist;

    }
}