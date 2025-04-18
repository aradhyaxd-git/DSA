class Solution1 {

    //known as the staircase seach
    public boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length;
        int n=matrix[0].length;
        int i=0,j=n-1;
        while(i<m && j>=0){ //means i=0 se start krengay.. kyuki har column sorted hai... j=n-1... like here
                            //1 3 5 7 sorted hai.. to i=0, j=7
             if(matrix[i][j]>target) //now check, mera target agar chota hai..mat[i][j]se to mujhe same column mei 
                                    //peeche jana hoga
                 j--;
            else if(matrix[i][j]<target) //agar target bada hai.. to samne row mei to milega nahi... to neeche jaogay
                 i++;
            else
                return true;
        }
        return false; 
    }
}

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        //1D array ki trah treat krengay 2D array ko

        //now know the concept: kisi bhi particular row ke liye hum logo ko row index= mid/n karna hota hai
                                            //kisi bhi column ke liye idx= mid%n karna hota hai
                                                // where n= no of columns 

        int m= matrix.length;
        int n= matrix[0].length;

        //now we have range i=0 to m*n-1
        int low=0,high=m*n-1;
        while(low<=high){
            int mid= low+(high-low)/2;
            int rowIdx= mid/n;
            int colIdx= mid%n;

            if(matrix[rowIdx][colIdx]==target) return true;

            else if(matrix[rowIdx][colIdx]<target) 
                low= mid+1;
            else
                high=mid-1;
        }
        return false;
    }
}