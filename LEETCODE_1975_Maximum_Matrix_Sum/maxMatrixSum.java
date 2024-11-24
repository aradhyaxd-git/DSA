class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int n=matrix.length;
        long sum=0;
        int countNegatives=0;
        int smallestAbsoluteNegative=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                sum+= Math.abs(matrix[i][j]);
                if(matrix[i][j]<0) {
                    countNegatives++;
                }
                smallestAbsoluteNegative= Math.min(smallestAbsoluteNegative,Math.abs(matrix[i][j]));
            }
        }
        if(countNegatives%2==0) return sum;
        else return sum-2*smallestAbsoluteNegative;
    }
}