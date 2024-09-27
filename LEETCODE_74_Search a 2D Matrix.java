class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowno= matrix.length;
        int columnno = matrix[0].length;
        int start = 0, end = rowno*columnno -1;
        while (start <= end) {
             int mid = start + (end - start) / 2;
            int row= mid/columnno;
            int column= mid%columnno;
            if (matrix[row][column] == target) {
                return true;
            } else if (matrix[row][column] < target) {
               start= mid+1;
            } else {
                end=mid-1;   
            }
        }
        return false;
    }
}