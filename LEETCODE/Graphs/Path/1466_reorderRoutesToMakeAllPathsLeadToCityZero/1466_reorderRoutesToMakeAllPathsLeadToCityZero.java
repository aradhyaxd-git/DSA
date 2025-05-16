/*Medium
Topics
Companies
Hint
There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between 
two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads 
in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach city 0 after reorder.

  */


/*INTUITION + STORY 
 --> Given problem statement ke hisab se , hum logo ko reorder karna hai
 --> Problem tab aati hai jab hum logo dusre edge par ni ja saktee hai
        -->isko solve karne ke liye undirected graph bana do
    --> now, undirected graph mei pta kaise chalega konsa edge asli hai, konsa nakli??
        --> isko solve karne ke liye 1 mark krdo agar asli hai, 0 agar nakli hai

    --> dfs call karo, aur check krlo agar u-->v ja rhe means asli hai.. aur hum logo ko reverse karna padega
        to count++ kardo */
    
class Solution1 {
    int count;
    public int minReorder(int n, int[][] connections) {
        List<List<int[]>> adj= new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int connect[]:connections){
            int u= connect[0];
            int v= connect[1];
            adj.get(u).add(new int[]{v,1}); //asli edge
            adj.get(v).add(new int[]{u,0}); //nakli edge
        }
        int parent=-1; //parent waha jaha se aaye hai, instead of visited array parent variable hi lelo
        dfs(0,parent,adj);
        return count;
    }
    public void dfs(int u,int parent,List<List<int[]>> adj){
        for(int curr[]:adj.get(u)){
            int v= curr[0];
            if(parent!=v){
              int weight= curr[1];
              if(weight==1) count+=1;
              dfs(v,u,adj);
            }
        }
    }
}

//approach 2: bfs
class Solution {
    public int minReorder(int n, int[][] connections) {
        List<List<int[]>> adj= new ArrayList<>();
        for(int i=0;i<n;i++) adj.add(new ArrayList<>());
        for(int connect[]:connections){
            int u= connect[0];
            int v= connect[1];
            adj.get(u).add(new int[]{v,1}); //asli edge
            adj.get(v).add(new int[]{u,0}); //nakli edge
        }
        int count=0;
        Queue<Integer> queue= new LinkedList<>();
        queue.offer(0);
        boolean visited[]= new boolean[n];
        visited[0]=true;

        while(!queue.isEmpty()){
            int curr= queue.poll();
            for(int neighbour[]:adj.get(curr)){
                int v= neighbour[0];
                int isAsli= neighbour[1];
                if(!visited[v]){
                    if(isAsli==1) count++;
                    visited[v]=true;
                    queue.offer(v);
                }
            }
        }
        return count;
    }
}

