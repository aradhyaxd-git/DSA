class Solution2 {
    /* Intuition: We need to compute the smallest index at which either a row 
                  or a column gets painted completely */
    /*Appraoch: 1) WE store the position of each element in the matrix in a Hashmap
                    -> hashmaps for fast lookups 
                2) We create an array to count rows, and an array to count columns
                    -> Rows array will have exactly columnNO of rows
                    -> Column array will have exactly rowNO of columns
                3) Now, check Up the the array and do everything needed */
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m= mat.length;
        int n= mat[0].length;
        HashMap<Integer,int[]>map = new HashMap<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                map.put(mat[i][j],new int[]{i,j});
            }
        }
        int rowCount[]= new int[m];
        int colCount[]= new int[n];
        Arrays.fill(rowCount,n);
        Arrays.fill(colCount,m);

        for(int i=0;i<arr.length;i++){
            int pos[]= map.get(arr[i]);
            int row= pos[0];
            int col= pos[1];
            rowCount[row]--;
            colCount[col]--;
            if(rowCount[row]==0 || colCount[col]==0)
                return i;
        }
        return -1;
        
    }
}
class Solution1 {
    /* Intuition: We need to compute the smallest index at which either a row 
                  or a column gets painted completely */
    /*Appraoch: 1) Now,Instead of HashMaps, we use 2 more arrays
                2) We create an array to count rows, and an array to count columns
                    -> Rows array will have exactly columnNO of rows
                    -> Column array will have exactly rowNO of columns
                3) Now, check Up the the array and do everything needed */
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m= mat.length;
        int n= mat[0].length;
        int rowIndex[]= new int[m*n+1];
        int colIndex[]= new int[m*n+1];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int number= mat[i][j];
                rowIndex[number]=i;
                colIndex[number]=j;
            }
        }
        int rowCount[]= new int[m];
        int colCount[]= new int[n];
        Arrays.fill(rowCount,n);
        Arrays.fill(colCount,m);

        for(int i=0;i<arr.length;i++){
            int row= rowIndex[arr[i]];
            int col= colIndex[arr[i]];
            rowCount[row]--;
            colCount[col]--;
            if(rowCount[row]==0 || colCount[col]==0)
                return i;
        }
        return -1;
        
    }
}


/*Approach:3 Optimal*/
/*Intuition and approach:
    1) Now, We store the arr array in map instead of grid
        -> WE store all indices in a map.
    2) Find har row , col ko fill hone mei kitna max index lagega 
    3) We find the minimum of all max indexes

    for eg: we have arr= {2,8,7,4,1,3,5,6,9}        mat[][]={{3,2,5} {1,4,6} {8,7,9}}
            Position      Elements      Indices     To get Filled
        ___________________________________________________________
            col=0       3,1,8           5,4,1       Max= 5
            col=1       2,4,7           0,3,2       Max= 3
            col=2       5,6,4           6,7,3       Max= 7
            row=1       3,2,5           5,0,6       max= 6
            row=1       1,4,6           4,3,7       max=7
            row=2       8,7,9           1,2,8       max=8
        __________________________________________________________ 
                                                    Min= 3 i.e the answer here */
class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        HashMap<Integer,Integer> map= new HashMap<>();
        for(int i=0;i<arr.length;i++){
            map.put(arr[i],i);
        }
        int minIndex=Integer.MAX_VALUE;
        int m= mat.length;
        int n= mat[0].length;
        
        for(int i=0;i<m;i++){
            int bestIndex=Integer.MIN_VALUE;
            for(int j=0;j<n;j++){
                int val= mat[i][j];
                int index= map.get(val);
                bestIndex= Math.max(bestIndex,index);
            }
            minIndex= Math.min(minIndex,bestIndex);
        }

        for(int j=0;j<n;j++){
            int bestIndex= Integer.MIN_VALUE;
            for(int i=0;i<m;i++){
                int val= mat[i][j];
                int index= map.get(val);
                bestIndex= Math.max(bestIndex,index);
            }
            minIndex= Math.min(minIndex,bestIndex);
        }
        return minIndex;
    }
}

//Now instead of minIndex, used a prriorityQUeue
class Solution3 {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        HashMap<Integer,Integer> map= new HashMap<>();
        for(int i=0;i<arr.length;i++){
            map.put(arr[i],i);
        }
        PriorityQueue<Integer> pq= new PriorityQueue<>();
        int m= mat.length;
        int n= mat[0].length;
        
        for(int i=0;i<m;i++){
            int bestIndex=Integer.MIN_VALUE;
            for(int j=0;j<n;j++){
                int val= mat[i][j];
                int index= map.get(val);
                bestIndex= Math.max(bestIndex,index);
            }
            pq.offer(bestIndex);
        }

        for(int j=0;j<n;j++){
            int bestIndex= Integer.MIN_VALUE;
            for(int i=0;i<m;i++){
                int val= mat[i][j];
                int index= map.get(val);
                bestIndex= Math.max(bestIndex,index);
            }
            pq.offer(bestIndex);
        }
        return pq.poll();
    }
}