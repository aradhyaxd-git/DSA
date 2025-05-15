/*You are given an integer n, which indicates that there are n courses labeled from 1 to n. 
You are also given a 2D integer array relations where relations[j] = [prevCoursej, nextCoursej] denotes that course prevCoursej has to be completed before course nextCoursej (prerequisite relationship). Furthermore, you are given a 0-indexed integer array time where time[i] denotes how many months it takes to complete the (i+1)th course.

You must find the minimum number of months needed to complete all the courses following these rules:

You may start taking a course at any time if the prerequisites are met.
Any number of courses can be taken at the same time.
Return the minimum number of months needed to complete all the courses.

Note: The test cases are generated such that it is possible to complete every course (i.e., the graph is a directed acyclic graph). */


class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<List<Integer>> adj= new ArrayList<>();
        for(int i=0;i<n;i++)
            adj.add(new ArrayList<>());
        
        int indegree[]= new int[n]; //har course ke baad kaunsa course allowed hai
        for(int r[]:relations){
            int u= r[0]-1;
            int v= r[1]-1;
            adj.get(u).add(v); //course v depends on you
            indegree[v]++;
        }
        
        //topo sort using Kahn algorithm
        Queue<Integer> queue= new LinkedList<>();
        int maxTime[]= new int[n]; //ye nikalega , kitna time lagega max
        for(int i=0;i<n;i++){
            if(indegree[i]==0){ //agar indegree=0 hai, it means ispe koi dependent nahi hai
                maxTime[i]=time[i];
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int u= queue.poll();
            for(int v: adj.get(u)){
                indegree[v]--;
                //agar koi course v ko complete karne ke liye u pe dependency hai
                //toh v ka finish time tbhi milega , jab u complete hoga
                maxTime[v]=Math.max(maxTime[v],maxTime[u]+time[v]);
                if(indegree[v]==0){
                    queue.offer(v);          
                }
            }
        }

        return Arrays.stream(maxTime).max().getAsInt();

    
    }
}