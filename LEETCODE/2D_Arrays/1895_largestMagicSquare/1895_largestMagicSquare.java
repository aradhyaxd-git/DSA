class Solution {
    int grid[][];
    int rowSum[][];
    int colSum[][];
    int diaSum[][];
    int antiDiaSum[][];
    int m,n;

    public int largestMagicSquare(int[][] grid) {
        this.grid=grid;
        m=grid.length;
        n=grid[0].length;

        rowSum= new int[m][n];
        colSum= new int[m][n];
        diaSum= new int[m][n];
        antiDiaSum=  new int[m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){

                rowSum[i][j]= grid[i][j]+ (j>0? rowSum[i][j-1]:0);
                colSum[i][j]= grid[i][j]+ (i>0? colSum[i-1][j]:0);
                diaSum[i][j]= grid[i][j]+ (i>0 && j>0 ?diaSum[i-1][j-1]:0);
                antiDiaSum[i][j]= grid[i][j] + (i>0 && j<n-1 ?antiDiaSum[i-1][j+1]:0);
            }
        }


        int ans=1;

        for(int k=2;k<=Math.min(m,n);k++){
            for(int row=0;row+k-1<m;row++){
                for(int col=0;col+k-1<n; col++){
                    if(isMagic(row,col,k)){
                        ans=k;
                    }
                }
            }
        }
        return ans;

        
    }
    private boolean isMagic(int row, int col, int k){
        int target= rowSum[row][col+k-1]- (col>0?rowSum[row][col-1]:0);
        //check for all rows

        for(int i=row;i<row+k;i++){
            int sum= rowSum[i][col+k-1]-(col>0? rowSum[i][col-1]:0);
            if(sum!=target) return false;
        }

        for(int j=col;j<col+k;j++){
            int sum= colSum[row+k-1][j]-(row>0? colSum[row-1][j]:0);
             if(sum!=target) return false;
        }

        int diagonalSum= diaSum[row+k-1][col+k-1]-(row>0 && col>0? diaSum[row-1][col-1]:0);
        if(diagonalSum!=target) return false;

        int antiDiagonalSum= antiDiaSum[row+k-1][col]- (row>0 && col+k<n ?antiDiaSum[row-1][col+k]:0);

        if(antiDiagonalSum!=target) return false;

        return true;
    }
}