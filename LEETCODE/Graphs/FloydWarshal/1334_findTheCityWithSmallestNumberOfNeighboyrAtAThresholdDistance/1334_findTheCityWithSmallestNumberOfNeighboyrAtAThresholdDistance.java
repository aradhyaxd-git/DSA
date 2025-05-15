/*There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] 
represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

Return the city with the smallest number of cities that are reachable through some path and whose distance is 
at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.

Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.

 

Example 1:



Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
Output: 3
Explanation: The figure above describes the graph. 
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -> [City 1, City 2] 
City 1 -> [City 0, City 2, City 3] 
City 2 -> [City 0, City 1, City 3] 
City 3 -> [City 1, City 2] 
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number. */



/*Topic : Flyod Warshal Algo
 For each city , count how many cities are within the threshold 
 */
class Solution {
    public final int big= (int)1e9; //to avoid Integer overflow
    
    //step 1: floyd warshall 
    public void floydwarshall(int dist[][],int n){
        for(int via=0;via<n;via++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(dist[i][via]<big && dist[via][j]<big){
                        dist[i][j]=Math.min(dist[i][j],dist[i][via]+dist[via][j]);
                    }
                }
            }
        }
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        //step 2: distance array banake usmei almost infnity daal do
        int dist[][]= new int[n][n];

        for(int i=0;i<n;i++){
            Arrays.fill(dist[i],big);
            dist[i][i]=0;

        }
        //step 3: fill the array, dono se same karo as bidirectional hai
        for(int edge[]:edges){
            int u= edge[0],v=edge[1],w=edge[2];
            dist[u][v]=w;
            dist[v][u]=w;
        }
        

        floydwarshall(dist,n);

        /*means abhi tak min jitne cities ke liye hai, wo kisi se rachable nahi hai 
                --> n maan lo, for worst case scenario */

        
        int minReachable=n,city=-1;
        
        for(int i=0;i<n;i++){ //step 4: loop over each city to check har city i ke liye 
                                        ///-> kitni cities reachable hai within threshold
            int reachable=0;
            for(int j=0;j<n;j++){
                if(i!=j && dist[i][j]<=distanceThreshold){
                    reachable++;
                }
            }

            if(reachable<=minReachable ){
                minReachable=reachable;
                city=i; //because tie hai to greater index wala humesha jeetega
            }
        }
        return city;
    }
}