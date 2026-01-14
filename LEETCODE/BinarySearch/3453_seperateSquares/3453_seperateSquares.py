class Solution:
    def separateSquares(self, squares: List[List[int]]) -> float:

        def isPossible(midY):
            bottom_area=0.0

            for square in squares:
                y= square[1]
                l= square[2]
                bottomY=y
                topY= y+l
                if midY>=topY:
                    bottom_area+= l*l
                elif midY>bottomY:
                    bottom_area+= (midY-bottomY)*l
            
            return bottom_area>=total_area/2.0

        res=0.0
        total_area= 0.0
        low= float('inf')
        high= float('-inf')

        for square in squares:
            x,y,l= square
            total_area += l*l
            low= min(low,y)
            high= max(high,y+l)

        while high-low>1e-5:

            midY= low+(high-low)/2

            if isPossible(midY):
                res= midY
                high=midY
            else:
                low= midY
        
        return res
    
    
