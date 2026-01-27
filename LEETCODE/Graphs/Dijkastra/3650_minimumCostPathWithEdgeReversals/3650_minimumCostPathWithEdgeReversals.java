class Solution {
    public int minCost(int n, int[][] edges) {
        List<List<int[]>> adj= new ArrayList<>();
        List<List<int[]>> radj= new ArrayList<>();

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
            radj.add(new ArrayList<>());
        }

        for(int edge[]:edges){
            int u= edge[0];
            int v= edge[1];
            int w= edge[2];

            adj.get(u).add(new int[]{v,w});
            radj.get(v).add(new int[]{u,w});
            
        }

        long dist[]= new long[n];
        Arrays.fill(dist,Long.MAX_VALUE);

        dist[0]=0;

        PriorityQueue<long[]> pq= new PriorityQueue<>((a,b)->Long.compare(a[0],b[0]));
        pq.offer(new long[]{0,0});


        while(!pq.isEmpty()){
            long curr[]= pq.poll();
            long d= curr[0];
            int u= (int)curr[1];
            

            if(d>dist[u]) continue;

            for(int neighbour[]:adj.get(u)){
                int v= neighbour[0];
                int w= neighbour[1];

                if(d+w<dist[v]){
                    dist[v]=d+w;
                    pq.offer(new long[]{dist[v],v});
                }
            }

                for(int neighbour[]:radj.get(u)){
                int v= neighbour[0];
                int w= neighbour[1];

                if(d+ 2L*w < dist[v]){
                    dist[v]= d+ 2L*w;
                    pq.offer(new long[]{dist[v],v});
                }
            }
        }
        long ans= dist[n-1];
        return ans==Long.MAX_VALUE?-1:(int)ans;
        
    }
}