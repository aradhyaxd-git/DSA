/*797. All Paths From Source to Target
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, 
find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i
(i.e., there is a directed edge from node i to node graph[i][j]).
  */

  class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result= new ArrayList<>();
        List<Integer> temp= new ArrayList<>();
        int n= graph.length;
        int source=0;
        int target=n-1;
        dfs(source,target,graph,temp,result);
        return result;
    }
    public void dfs(int source,int target,int graph[][],List<Integer> temp,List<List<Integer>> result){
        temp.add(source);
        if(source==target){
            result.add(new ArrayList<>(temp));
        }
        else{
            for(int neighbour:graph[source]){
                dfs(neighbour,target,graph,temp,result);
            }
        }
        temp.remove(temp.size()-1);

    }
}