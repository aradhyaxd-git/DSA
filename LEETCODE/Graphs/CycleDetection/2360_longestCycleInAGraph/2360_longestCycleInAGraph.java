/*2360. Longest Cycle in a Graph
You are given a directed graph of n nodes numbered from 0 to n - 1, 
where each node has at most one outgoing edge.

The graph is represented with a given 0-indexed array edges of size n, indicating 
that there is a directed edge from node i to node edges[i]. If there is no outgoing 
edge from node i, then edges[i] == -1.

Return the length of the longest cycle in the graph. If no cycle exists, return -1.

A cycle is a path that starts and ends at the same node.

  */

//CONCEPT: CYCLE DETECTION IN A DIRECTED GRAPH USING DFS
class Solution {
    int result=-1;
    public int longestCycle(int[] edges) {
        int n= edges.length;
        boolean visited[]=new boolean[n]; // to keep track of visited node
        boolean inRecursion[]= new boolean[n]; // dfs se cycle detect krte to yehi lete hai
        int count[]= new int[n];// count array isliye le rhe hai, inorder to store the depth or to track
        //dfs mei kitna andar gaye hai.. it is indirectly used to store the longest cycle length
        Arrays.fill(count,1); // initially, har node mei jaane mei 1 hi lagta hai.. like jaha se bhi dfs hit krogay
        //waha se aage jaane mei ek time lagega hi
        for(int i=0;i<n;i++){ //directly isliye use ni kia , kyuki graph mera disconnected bhi ho sakta hai
        // isliye har baaar dfs call krengay
            if(!visited[i]){
                dfs(i,edges,visited,inRecursion,count);
            }
        }
        return result;   
    }

    void dfs(int u,int edges[],boolean visited[],boolean inRecursion[],int count[]){
        if(u==-1) return; //question mei dia hai ki agar edge = -1 hai.. to wo connect ni hoga
        inRecursion[u]=true;
        visited[u]=true;
        int v= edges[u]; // since question ke hisab se edge[i]=j.. means index i se hum j ja sakte hai
        if(v!=-1 && !visited[v]){ // means un visited hai, and v mera -1 nahi hai , it means dfs call kar sakte
            count[v]= count[u]+1;
            dfs(v,edges,visited,inRecursion,count);
        }
        else if(v!=-1 && inRecursion[v]==true){ // it means, cycle detect hui hai
            result= Math.max(result,count[u]-count[v]+1); // jab jab cycle detect hoti hai tab tak result ko update karte rho
        }
        inRecursion[u]=false;
    }
}