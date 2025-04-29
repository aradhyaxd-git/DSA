/*

Course Schedule II

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid 
answers, return any of them. If it is impossible to finish all courses, return an empty array.

  */

  class Solution1 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int indegree[]= new int[numCourses];
        List<List<Integer>> adj= new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            adj.add(new ArrayList<>());
        }
        for(int preq[]: prerequisites){
            int a= preq[0];
            int b= preq[1];
            adj.get(b).add(a);
            indegree[a]++;
        }
        return kahn(adj,indegree,numCourses);     
    }

    public int[] kahn(List<List<Integer>> adj, int[] indegree, int n){
        int count=0;
        Queue<Integer> queue= new LinkedList<>();
        int result[]= new int[n];
        int index=0;
        for(int i=0;i<n;i++){
            if(indegree[i]==0){
                result[index++]=i;
                queue.offer(i);
                count++;
            }
        }

        while(!queue.isEmpty()){
            int u= queue.poll();
            for(int v: adj.get(u)){
                indegree[v]--;
                if(indegree[v]==0){
                    result[index++]=v;
                    queue.offer(v);
                    count++;
                }
            }
        }
        if(count==n)
            return result;
        return new int[]{};
    }
}

class Solution {
    private boolean hasCycle= false;
    private void dfs(List<List<Integer>> adj, int u, boolean visited[] , boolean inRecursion[] , Stack<Integer> st){
        visited[u]=true;
        inRecursion[u]=true;
        for(int v: adj.get(u)){
            if(inRecursion[v]==true){
                hasCycle=true;
                return;
            }
            if(!visited[v]){
                dfs(adj,v,visited,inRecursion,st);
            }
        }
        st.push(u);
        inRecursion[u]=false;
    }
   public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj= new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            adj.add(new ArrayList<>());
        }

        for(int preq[]: prerequisites){
            int a= preq[0];
            int b= preq[1];
            adj.get(b).add(a);
        }

        boolean visited[]= new boolean[numCourses];
        boolean inRecursion[]= new boolean[numCourses];
        Stack<Integer> st= new Stack<>();
        for(int i=0;i<numCourses;i++){
            if(!visited[i])
                dfs(adj,i,visited,inRecursion,st);
        }

        if(hasCycle==true)
            return new int[]{};
        
        int result[]= new int[numCourses];
        int index=0;

        while(!st.isEmpty()){
            result[index++]= st.pop();
        }
        return result;
        
    }
}