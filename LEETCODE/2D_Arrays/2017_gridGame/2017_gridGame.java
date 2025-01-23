class Solution {
    public long gridGame(int[][] grid) {
        int n= grid[0].length;
        long firstRowRemainingSum=0;
        for(int i=0;i<n;i++){
            firstRowRemainingSum+=grid[0][i];
        }
        long secondRowRemainingSum=0,minimizedRobot2Sum=Long.MAX_VALUE;
        for(int robot1Col=0 ; robot1Col<n ; robot1Col++){
            firstRowRemainingSum-= grid[0][robot1Col];
            long bestof2 = Math.max(firstRowRemainingSum,secondRowRemainingSum);
            minimizedRobot2Sum= Math.min( minimizedRobot2Sum , bestof2);
            secondRowRemainingSum+= grid[1][robot1Col];
        }
        return minimizedRobot2Sum;     
    }
}