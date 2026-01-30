class Solution:
    def minimumCost(self, source: str, target: str, original: List[str], changed: List[str], cost: List[int]) -> int:
        INF= 10**9

        dist=[[INF]*26 for _ in range(26)]

        for i in range(26):
            dist[i][i]=0

        for u,v,c in zip(original,changed,cost):
            x= ord(u)-ord('a')
            y= ord(v)-ord('a')
            dist[x][y]= min(dist[x][y],c)
        
        for via in range(26):
            for i in range(26):
                for j in range(26):
                    if dist[i][via]+dist[via][j]<dist[i][j]:
                        dist[i][j]=dist[i][via]+dist[via][j]

        

        ans=0
        for i in range(len(source)):
            u= ord(source[i])-ord('a')
            v= ord(target[i])-ord('a')

            if dist[u][v] == INF:
                return -1

            ans+=dist[u][v]
        
        return ans
        