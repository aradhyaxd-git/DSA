/*There is an infrastructure of n cities with some number of roads connecting these cities. Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.

The network rank of two different cities is defined as the total number of directly connected roads to either city. If a road is directly connected to both cities, it is only counted once.

The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.

Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.

 

Example 1:



Input: n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
Output: 4
Explanation: The network rank of cities 0 and 1 is 4 as there are 4 roads that are connected to either 0 or 1. The road between 0 and 1 is only counted once.
Example 2:



Input: n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
Output: 5
Explanation: There are 5 roads that are connected to cities 1 or 2.
Example 3:

Input: n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
Output: 5
Explanation: The network rank of 2 and 5 is 5. Notice that all the cities do not have to be connected. */


/*appraoch 1: Using Adjacency List(Set) to store graph connection 
Main Idea is THE network rank between any 2 roads is the total number of roads connected to either of them 
And if they are directly connected then subtract 1( inorder to remove the the double checking)
 Rank nikalne ke liye bas dekhhna hai */
//time complexity: O(V^2) ( AGAR set ki jagah normal adjacency list lete to fir n3 ko approach krti time complexity)
class Solution1 {
    public int maximalNetworkRank(int n, int[][] roads) {
        int maxi=Integer.MIN_VALUE;
        List<Set<Integer>> adj= new ArrayList<>(); //step 1: make Hashsets to avoid any duplicate connections and also to retrieve data in O(1) time (.conntains method)
        for(int i=0;i<n;i++){
            adj.add(new HashSet<>()); //create an empty set for each city
        }
        for(int road[]:roads){
            int u= road[0];
            int v= road[1];
            adj.get(u).add(v); //kyuki bi directional edge hai
            adj.get(v).add(u);
        }
        int maxRank=0;

        //step 3: check all the unique cities to compute their network rank 
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                //i_rank is the number of roads connected to city i
                int i_rank= adj.get(i).size(); //size isliye lia hai ki , total itne components jude hai isse like 0--> 1,3 ..so 2 is the size
                int j_rank= adj.get(j).size();
                //rank --> total roads connected to each city

                int rank= i_rank+j_rank; //total  rank will be i_rank + j_rank                                                              
                if(adj.get(i).contains(j)){
                    rank= rank-1; //means 2 baar count hua to ek baar kam hoga
                }
                maxi=Math.max(maxi,rank);
            }
        }
        return maxi;   
    }
}

/*approach 2:
now, instead of an adjacency list, we directly caclulate the degree of each of the items using an array Degree + connection matrix */

//inorder to calculate the things which are connected twice... we use a 2D array to findthings

class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        int maxi=Integer.MIN_VALUE;
        int degree[]= new int[n];
        boolean isConnected[][]= new boolean[n][n];
        for(int road[]:roads){
            int u= road[0];
            int v= road[1];
            degree[u]++;
            degree[v]++;
            isConnected[u][v]=true;
            isConnected[v][u]=true;
        }
        int maxRank=0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int rank= degree[i]+degree[j];                                                           
                if(isConnected[i][j]){
                    rank= rank-1; //means 2 baar count hua to ek baar kam hoga
                }
                maxi=Math.max(maxi,rank);
            }
        }
        return maxi;   
    }
}
        
