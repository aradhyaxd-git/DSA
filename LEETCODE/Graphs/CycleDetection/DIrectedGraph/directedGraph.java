

/*Complete the function below*/

class Solution {
    // Function to detect cycle in a directed graph.
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        boolean visited[]= new boolean[V];
        boolean inRecursion[]= new boolean[V];
        for(int i=0;i<V;i++){
            if(!visited[i] && isCycleDFS(i,adj,visited,inRecursion)){
                return true;
            }
        }
        return false;
    }
    
    public boolean isCycleDFS(int node, ArrayList<ArrayList<Integer>> adj,boolean visited[],boolean inRecursion[]){
        visited[node]=true;
        inRecursion[node]=true;
        for(int neighbour: adj.get(node)){
            if(!visited[neighbour] && isCycleDFS(neighbour,adj,visited,inRecursion)){
                return true;
            }
            else if(inRecursion[neighbour]==true){
                return true;
            }
        }
        inRecursion[node]=false;
        return false;
    }
}