class Solution:
    def findJudge(self, n: int, trust: List[List[int]]) -> int:
        outdegree=[0]*(n+1)
        indegree=[0]*(n+1)

        for t in trust:
            banda1,banda2=t[0],t[1]
            outdegree[banda1]+=1
            indegree[banda2]+=1

        for i in range(1,n+1):
            if indegree[i]==n-1 and outdegree[i]==0:
                return i
        
        return -1