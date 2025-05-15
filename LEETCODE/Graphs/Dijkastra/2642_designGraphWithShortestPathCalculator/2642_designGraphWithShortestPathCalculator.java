/*There is a directed weighted graph that consists of n nodes numbered from 0 to n - 1. The edges of the graph are 
initially represented by the given array edges where edges[i] = [fromi, toi, edgeCosti] meaning that there is an edge
 from fromi to toi with the cost edgeCosti.

Implement the Graph class:

Graph(int n, int[][] edges) initializes the object with n nodes and the given edges.

addEdge(int[] edge) adds an edge to the list of edges where edge = [from, to, edgeCost]. It is guaranteed that 
there is no edge between the two nodes before adding this one.

int shortestPath(int node1, int node2) returns the minimum cost of a path from node1 to node2. If no path exists
, return -1. The cost of a path is the sum of the costs of the edges in the path. */



/**
 * Your Graph object will be instantiated and called as such:
 * Graph obj = new Graph(n, edges);
 * obj.addEdge(edge);
 * int param_2 = obj.shortestPath(node1,node2);
 */


//appraoch 1: using dijkastra
 class Graph {
    List<List<int[]>> adj= new ArrayList<>();

    public Graph(int n, int[][] edges) {
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }

        for(int edge[]:edges){
            int u= edge[0];
            int v= edge[1];
            int cost= edge[2];
            adj.get(u).add(new int[]{v,cost});
        }
    }
    
    public void addEdge(int[] edge) {
        int u= edge[0];
        int v= edge[1];
        int cost= edge[2];
        adj.get(u).add(new int[]{v,cost});
        
    }
    
    public int shortestPath(int node1, int node2) { //source= node1, destination=node2
        int V= adj.size();
        PriorityQueue<int[]> pq= new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.offer(new int[]{node1,0});
        int dist[]= new int[V];
       
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[node1]=0;

        while(!pq.isEmpty()){
            int curr[]= pq.poll();
            int node= curr[0];
            int cost= curr[1];
            if(dist[node]<cost) continue;
            for(int neighbour[]: adj.get(node)){
                int newNode= neighbour[0];
                int newCost= neighbour[1];
                if(dist[newNode]>newCost+cost){
                    dist[newNode]=newCost+cost;
                    pq.offer(new int[]{newNode,dist[newNode]});
                }
            }
        }
        return dist[node2]==Integer.MAX_VALUE?-1:dist[node2];
    
    }
}


//approach 2: using floyd warshall algorithm

class Graph1 {

    int dist[][];
    int n;
    final int big= (int)1e9;

    public void floydWarshal(){
        for(int via=0;via<n;via++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(dist[i][via]<big && dist[via][j]<big){
                        dist[i][j]= Math.min(dist[i][j],dist[i][via]+dist[via][j]);
                    }
                }
            }
        }
    }

    public Graph1(int n, int[][] edges) {
        this.n= n;
        dist= new int[n][n];

        for(int i=0;i<n;i++){
            Arrays.fill(dist[i],big);
            dist[i][i]=0; //khud ki khud se distance 0 hogi
        }

        for(int edge[]:edges){
            int u=edge[0];
            int v= edge[1];
            int cost= edge[2];
            dist[u][v]= cost;
        }
        floydWarshal();
        
    }
    
    public void addEdge(int[] edge) {
        int u= edge[0];
        int v= edge[1];
        int cost= edge[2];
        if(dist[u][v]>cost){
            dist[u][v]=cost;
        }
        floydWarshal();
        
    }
    
    public int shortestPath(int node1, int node2) {
        return dist[node1][node2]>=big?-1:dist[node1][node2];
        
    }
}
