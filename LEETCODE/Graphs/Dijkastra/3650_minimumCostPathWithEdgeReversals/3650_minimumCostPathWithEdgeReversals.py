import heapq
class Solution:
    def minCost(self, n: int, edges: List[List[int]]) -> int:

        adj= [[] for _ in range(n)]
        radj= [[] for _ in range(n)]


        for u,v,w in edges:
            adj[u].append((v,w))
            radj[v].append((u,w))

        INF= float('inf')
        dist=[INF]*n
        dist[0]=0


        pq=[(0,0)]

        while pq:
            d,u=  heapq.heappop(pq)

            if d>dist[u]:
                continue
            
            for v,w in adj[u]:
                if d+w<dist[v]:
                    dist[v]=d+w
                    heapq.heappush(pq,(dist[v],v))
            
            for v,w in radj[u]:
                if d+2*w < dist[v]:
                    dist[v]= d+2*w
                    heapq.heappush(pq,(dist[v],v))

        return -1 if dist[n-1] == INF else dist[n-1]
        