/*

You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt.
This area is in the shape of a circle with the center as the location of the bomb.

The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. 
xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri 
denotes the radius of its range.

You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that
lie in its range. These bombs will further detonate the bombs that lie in their ranges.

Given the list of bombs, return the maximum number of bombs that can be detonated if you are 
allowed to detonate only one bomb. */

 

class Solution1 {
    public int maximumDetonation(int[][] bombs) {
        int n= bombs.length;
        List<List<Integer>> adj= new ArrayList<>();
        for(int i=0;i<n;i++)
            adj.add(new ArrayList<>());

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j) continue;

                long x1= bombs[i][0];
                long y1= bombs[i][1];
                long r1= bombs[i][2];

                long x2= bombs[j][0];
                long y2= bombs[j][1];
                long r2= bombs[j][2];

                long distance= (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1);
                if(r1*r1>=distance){
                    adj.get(i).add(j);
                }
            }
        }

        int result=0;
        
        for(int i=0;i<n;i++){ 
                Set<Integer> visited= new HashSet<>();
                dfs(i,visited,adj);
                int count= visited.size();
                result= Math.max(count,result);
            }
        return result;
        
    }

    public void dfs(int node,Set<Integer> visited,List<List<Integer>> adj){
        visited.add(node);
        for(int neighbour:adj.get(node)){
            if(!visited.contains(neighbour)){
                dfs(neighbour,visited,adj);
            }
        }
    }
}



class Solution {
    public int maximumDetonation(int[][] bombs) {
        
        int n= bombs.length;
        List<List<Integer>> adj= new ArrayList<>();
        for(int i=0;i<n;i++)
            adj.add(new ArrayList<>());

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i==j) continue;

                long x1= bombs[i][0];
                long y1= bombs[i][1];
                long r1= bombs[i][2];

                long x2= bombs[j][0];
                long y2= bombs[j][1];
                long r2= bombs[j][2];

                long distance= (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1);
                if(r1*r1>=distance){
                    adj.get(i).add(j);
                }
            }
        }

        int result=0;
        
        for(int i=0;i<n;i++){     
            int count= bfs(i,adj);
            result= Math.max(count,result);
        }
        return result;
    }

    public int bfs(int node,List<List<Integer>>adj){
        Queue<Integer> queue= new LinkedList<>();
        Set<Integer> visited= new HashSet<>();
        queue.offer(node);
        visited.add(node);
        while(!queue.isEmpty()){
            int u= queue.poll();
            for(int v: adj.get(u)){
                if(!visited.contains(v)){
                    visited.add(v);
                    queue.offer(v);
                }
            }
        }
        return visited.size();
    }
}