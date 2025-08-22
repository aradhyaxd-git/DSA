class Solution {
    public int minimumArea(int[][] grid) {
        int m= grid.length;
        int n= grid[0].length;
        
        int rowMax=-1,rowMin=Integer.MAX_VALUE;
        int colMax=-1,colMin=Integer.MAX_VALUE;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    rowMax= Math.max(rowMax,i+1);
                    rowMin= Math.min(rowMin,i+1);
                    colMax=Math.max(colMax,j+1);
                    colMin= Math.min(colMin,j+1);
                }
            }
        }

        return (rowMax-rowMin+1)*(colMax-colMin+1);

        
        
    }
}