class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<int[]> pq= new PriorityQueue<>((a,b)->{
            if(a[0]!=b[0]) return Integer.compare(a[0],b[0]);
            return Integer.compare(a[1],b[1]);
        });
        int m= mat.length;
        int n= mat[0].length;
        for(int i=0;i<m;i++){
            int count=0;
            for(int j=0;j<n;j++){
                if(mat[i][j]==1){
                    count++;
                }
            }
            pq.offer(new int[]{count,i});
        }
        int[] result = new int[k];
        for (int i =0;i < k; i++) {
            result[i] = pq.poll()[1]; 
        }
        return result;
    }
}