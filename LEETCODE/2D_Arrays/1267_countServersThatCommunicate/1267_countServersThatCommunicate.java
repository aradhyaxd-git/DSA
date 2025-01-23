class Solution {
    public int countServers(int[][] grid) {
        int m= grid.length;
        int n= grid[0].length;
        int row[]= new int[m];
        int col[]= new int[n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    col[j]++;
                    row[i]++;
                }
            }
        }
        int count=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    if(row[i]>1 || col[j]>1){
                        count++;
                    }
                }
            }
        }
        return count;
    }
}


class Solution2 {
    public int countServers(int[][] grid) {
        int m= grid.length;
        int n= grid[0].length;
        int row[]= new int[m];
        int col[]= new int[n];
        int count1=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    count1++;
                    row[i]++;
                    col[j]++;
                }
            }
        }
        int isolated=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    if(row[i]==1 && col[j]==1){
                        isolated++;
                    }
                }
            }
        }
        return count1-isolated;
    
    }
}