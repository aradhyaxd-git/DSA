
from collections import defaultdict
class MyCalendarThree:
    def __init__(self):
        self.delta= defaultdict(int)
        
    def book(self, startTime: int, endTime: int) -> int:
        self.delta[startTime]+=1
        self.delta[endTime]-=1
        ongoing,max_k=0,0
        for t in sorted(self.delta):
            ongoing+= self.delta[t]
            max_k= max(max_k,ongoing)
        return max_k
