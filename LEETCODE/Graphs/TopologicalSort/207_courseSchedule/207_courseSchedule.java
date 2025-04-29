/*207. Course Schedule

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must
 take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

  */

/*APPROACH 1: USING BFS (KAHN'S ALGORITHM )*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj= new ArrayList<>();
        int[] indegree= new int[numCourses];
        //adjacency list intialise kari
        for(int i=0;i<numCourses;i++){
            adj.add(new ArrayList<>());
        }
        // relation banake dalna hai b --> a
        for(int preq[]:prerequisites){
            int a= preq[0];
            int b= preq[1];
            //b-->a  
            adj.get(b).add(a);
            indegree[a]++; //arrow a mei jaa rha so indegree[a]++
        }

        return Kahncheck(adj,numCourses,indegree);   
    }

    private boolean Kahncheck(List<List<Integer>> adj, int n, int indegree[]){
        int count=0;
        Queue<Integer> queue= new LinkedList<>();
        //jiski indegree[0] hai usko queue mei daal do phele hi 
        for(int i=0;i<n;i++){
            if(indegree[i]==0){
                queue.offer(i);
                count++;
            }
        }

        while(!queue.isEmpty()){
            int u= queue.poll();
            for(int v: adj.get(u)){ //u se v mei ja sakte , to waha check karo
                indegree[v]--;
                if(indegree[v]==0){
                    queue.offer(v);
                    count++;
                }
            }
        }
        //if we are able to process all courses , there is no cycle
        return count==n;
    }
}


//aproach 2: using dfs 
class Solution1 {

    public boolean isCycleDFS(int u, List<List<Integer>> adj, boolean visited[], boolean[] inRecursion){
        visited[u]=true; //mark current node as visited 
        inRecursion[u]=true; //mark node as a part of current stack of reucrsion    
        for(int v: adj.get(u)){
            //if neighbour is not visited, do a bfs on it
            if(!visited[v] && isCycleDFS(v,adj,visited,inRecursion))
                return true;
                //if the neighbour is already in recursion cycle , it means we have found a back edge(cycle)
            else if(inRecursion[v]==true)
                return true;
        }

        //back track: remove the visited node from neighbour before processing it back
        inRecursion[u]=false;
        return false; //means no cycle found
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj= new ArrayList<>();
        boolean visited[]= new boolean[numCourses];
        boolean inRecursion[]= new boolean[numCourses];
        for(int i=0;i<numCourses;i++){
            adj.add(new ArrayList<>());
        }


        //build the graph from b-->a 
        for(int preq[]:prerequisites){
            int a= preq[0];
            int b= preq[1];
            adj.get(b).add(a);    
        }

        for(int i=0;i<numCourses;i++){
            if(!visited[i] && isCycleDFS(i,adj,visited,inRecursion))
                return false; //if a cycle is found , then it is not possible to finish all courses 
        }
        return true; //no cycle has been found, it means all courses have been finished
    }
}