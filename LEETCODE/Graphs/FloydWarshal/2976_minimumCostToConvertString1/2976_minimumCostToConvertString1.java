class Solution {
    final int INF=(int)1e9;
    public void floydWarshall(int dist[][]){
        int n= dist.length;
        for(int via=0;via<n;via++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    dist[i][j]= Math.min(dist[i][j],dist[i][via]+dist[via][j]);
                }
            }
        }
    }
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {

        int dist[][]= new int[26][26];

        for(int i=0;i<26;i++){
            Arrays.fill(dist[i],INF);
            dist[i][i]=0;
        }

        int n= original.length;

        for(int i=0;i<n;i++){
            int u= original[i]-'a';
            int v= changed[i]-'a';
            dist[u][v]= Math.min(dist[u][v],cost[i]);
        }

        floydWarshall(dist);

        long ans=0;

        for(int i=0;i<target.length();i++){
            int u= source.charAt(i)-'a';
            int v= target.charAt(i)-'a';
            if(dist[u][v]==INF) return -1;
            ans+= dist[u][v];
        }

        return ans;
    }
}