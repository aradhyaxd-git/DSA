    class Solution:
        def maximizeSquareHoleArea(self, n: int, m: int, hBars: List[int], vBars: List[int]) -> int:
            
            def longest_consecutive(arr: List[int])->int:
                arr.sort()
                best=curr=1

                for i in range(1,len(arr)):
                    if arr[i] == arr[i-1]+1:
                        curr+=1
                    else:
                        curr=1
                    best= max(curr,best)
                
                return best
            
            side= min(
                longest_consecutive(hBars),
                longest_consecutive(vBars)
            )+1

            return side*side