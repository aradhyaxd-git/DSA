class Solution2 {
    public void setZeroes(int[][] matrix) { //order of matrix= m*n
        int m= matrix.length;
        int n= matrix[0].length;
        int row[]=new int[m];
        for(int i=0;i<m;i++)
        {
            row[i]=0;
        }
        int col[]=new int[n];
        for(int i=0;i<n;i++)
        {
            col[i]=0;
        }
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(matrix[i][j]==0)
                {
                    row[i]=1;
                    col[j]=1;
                }
            }
        }
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if((row[i]==1) || (col[j]==1))
                {
                    matrix[i][j]=0;
                }
            }
        }
        
        
    }
}





class Solution {
    public void setZeroes(int[][] matrix) {//order m*n
    int m= matrix.length;
    int n= matrix[0].length;
    int col0=1;
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            if(matrix[i][j]==0){
                matrix[i][0]=0;
                if(j!=0){
                    matrix[0][j]=0;
                }
                else{
                    col0=0;
                }
            }
        }
    }
    //step 2: update the inner matrix: (1,1) to (m-1,n-1)
    for(int i=1;i<m;i++){
        for(int j=1;j<n;j++){
            if(matrix[i][0]==0 || matrix[0][j]==0){
                matrix[i][j]=0;
            }
        }
    }

    //step 3: update first row if needed
    if(matrix[0][0]==0){
        for(int j=0;j<n;j++){
            matrix[0][j]=0;
        }
    }

    //step4 : update first column if needed

    if(col0==0){
        for(int i=0;i<m;i++){
            matrix[i][0]=0;
        }
    }
    }
}