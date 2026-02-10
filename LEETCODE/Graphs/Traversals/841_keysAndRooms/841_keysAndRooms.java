class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        int n= rooms.size();
        Set<Integer> pocket= new HashSet<>();
        Set<Integer> visited= new HashSet<>();

        pocket.add(0);

        while(pocket.size()>0){

            Iterator<Integer> it = pocket.iterator();
            int i = it.next();
            it.remove();

            if(visited.contains(i)) continue;

            visited.add(i);
            for(int j: rooms.get(i)){
                if(!visited.contains(j)){
                    pocket.add(j);
                }
            }
        }

        return visited.size()==n;
        
    }
}

class Solution {
    public void dfs(List<List<Integer>> adj,int source, boolean visited[]){
        visited[source]=true;
        for(int v:adj.get(source)){
            if(!visited[v]){
                dfs(adj,v,visited);
            }
        }
    }
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        //it is more like an adjacency list... so visited array populate karlo fir agar koi bhi unvisited hai then return false
        int n= rooms.size();
        boolean visited[]= new boolean[n];
        dfs(rooms,0,visited);
        for(boolean v: visited){
            if(v==false) return false;
        }
        return true;
        
    }
}