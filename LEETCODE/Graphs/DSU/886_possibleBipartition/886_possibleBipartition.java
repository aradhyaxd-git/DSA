/*We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.

Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.

 

Example 1:

Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: The first group has [1,4], and the second group has [2,3].
Example 2:

Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Explanation: We need at least 3 groups to divide them. We cannot put them in two groups.
 

Constraints:

1 <= n <= 2000
0 <= dislikes.length <= 104
dislikes[i].length == 2
1 <= ai < bi <= n */



//approach 1: using bfs + graph coloring
class Solution1 {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<List<Integer>> adj= new ArrayList<>();
        //  for(int i=0;i<n;i++){
        //     adj.add(new ArrayList<>());
        // }  YE nahi hoga, kyuki 1 to n numbering hai

        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
        }
        //step 1: make adjacency list : dual nature hoga, agar 1 2 ko hate krta to 2 1 ko hate krega
        for(int dis[]:dislikes){
            int u= dis[0]; 
            int v= dis[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        //step 2: declare color array and shuru mei sabko -1 dedo : for unvisited state
        int color[]= new int[n+1];
        Arrays.fill(color,-1);
        for(int i=1;i<=n;i++){
            if(color[i]== -1){ //agar colored nhi hai , to check karo 
                if(!isCheckBipartite(adj,i,color)){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isCheckBipartite(List<List<Integer>> adj, int u,int color[]){
        Queue<Integer> queue= new LinkedList<>();
        color[u]=1; //  we have taken 1 for red and 0 for blue color
        queue.offer(u); //queue mei daall dia
        while(!queue.isEmpty()){
            int node= queue.poll();
            for(int v:adj.get(node)){
                if(color[v]==color[node]) return false; //agar same color hai iske neighbour ka , level wise processing mei, to wo bipartite nahi hoga 
                if(color[v]==-1){ //agar univsited hai to ye kro
                    color[v]=1-color[node]; //1-color[node] is a quick formula: yaha par agar 0 tha to 1 hojayuega and 1 tha to 0 hojayega
                    queue.offer(v);
                }
            }
        }
        return true;
    }
}

//now approach 2: dsu , yaha hum log union krte jayengay and check karlengay agar koi bhi same group mei hua dushman to return false;
class Solution {
    int parent[];
    int rank[];
    public int find(int x){
        if(parent[x]==x) return x;
        return parent[x]=find(parent[x]);
    }
    public void union(int x, int y){
        int xParent= find(x);
        int yParent= find(y);

        if(xParent==yParent) return;
        if(rank[xParent]<rank[yParent]){
            parent[xParent]=yParent;
        }
        else if(rank[xParent]>rank[yParent]){
            parent[yParent]=xParent;
        }
       else{
            parent[xParent]=yParent;
            rank[yParent]++;
        }
    }
    public boolean possibleBipartition(int n, int[][] dislikes) {
        parent= new int[n+1];
        rank= new int[n+1];
        for(int i=0;i<=n;i++){
            parent[i]=i;
        }
         List<List<Integer>> adj= new ArrayList<>();
        //  for(int i=0;i<n;i++){
        //     adj.add(new ArrayList<>());
        // }  YE nahi hoga, kyuki 1 to n numbering hai

        for(int i=0;i<=n;i++){
            adj.add(new ArrayList<>());
        }
        //step 1: make adjacency list : dual nature hoga, agar 1 2 ko hate krta to 2 1 ko hate krega
        for(int dis[]:dislikes){
            int u= dis[0]; 
            int v= dis[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        for(int u=1;u<=n;u++){
            for(int v: adj.get(u)){
                if(find(u)==find(v)) return false;
                //now check
                if(adj.get(u).size()>0)
                //union v with the first enemy of u, to keep all enemies in the same group
                union(adj.get(u).get(0),v);
            }
        }
        return true;
    }
}