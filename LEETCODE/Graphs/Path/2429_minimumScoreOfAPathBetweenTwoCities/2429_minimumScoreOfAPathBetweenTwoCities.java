/*2492. Minimum Score of a Path Between Two Cities

You are given a positive integer n representing n cities numbered from 1 to n. 
You are also given a 2D array roads where roads[i] = [ai, bi, distancei] indicates
 that there is a bidirectional road between cities ai and bi with a distance equal 
 to distancei. The cities graph is not necessarily connected.

The score of a path between two cities is defined as the minimum distance of a road in this path.

Return the minimum possible score of a path between cities 1 and n.

Note:

A path is a sequence of roads between two cities.
It is allowed for a path to contain the same road multiple times, 
and you can visit cities 1 and n multiple times along the path.
The test cases are generated such that there is at least one path between 1 and n. */

class Solution {
    public class Pair{
        int node;
        int weight;
        Pair(int node, int weight){
            this.node= node;
            this.weight= weight;
        }
    }
    public int minScore(int n, int[][] roads) {
        List<List<Pair>> adj= new ArrayList<>();
        for(int i=0;i<=n;i++){ //since 1 based indexing hai
            adj.add(new ArrayList<>());
        }
        for(int road[]:roads){
            int u= road[0];
            int v= road[1];
            int w= road[2];
            adj.get(u).add(new Pair(v,w));
            adj.get(v).add(new Pair(u,w));
        }

        boolean visited[]= new boolean[n+1];
        int result[]= new int[1];
        result[0]=Integer.MAX_VALUE;
        dfs(1,adj,result,visited);
        return result[0];   
    }
    public void dfs(int node,List<List<Pair>> adj, int result[], boolean visited[]){
        visited[node]=true;
        for(Pair p: adj.get(node)){
            int neighbour= p.node;
            int w= p.weight; //har baar minimum nikal rhae hai
            
            result[0]= Math.min(result[0],w);
            if(!visited[neighbour]){
                dfs(neighbour,adj,result,visited);
            }    
        }

    }
}