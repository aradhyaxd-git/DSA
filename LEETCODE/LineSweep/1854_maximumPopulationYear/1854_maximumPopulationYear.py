
class Solution:
    def maximumPopulation(self, logs: List[List[int]]) -> int:
        events=[]
        for start,end in logs:
            events.append((start,1))
            events.append((end,-1))
        events.sort(key=lambda x: (x[0],x[1]))
        curr_pop,max_pop,min_year=0,0,2055
        for year,change in events:
            curr_pop+= change
            if curr_pop> max_pop:
                max_pop= curr_pop
                min_year= year
        
        return min_year
