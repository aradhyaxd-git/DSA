class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int m= mat.length;
        int n= mat[0].length;
        HashMap<Integer,List<Integer>> map= new HashMap<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                map.putIfAbsent(i+j,new ArrayList<>());
                map.get(i+j).add(mat[i][j]);
            }
        }
        int result[]= new int[n*m];
        int index=0;
        for(int i=0;i<map.size();i++){
            List<Integer> diagonal= map.get(i);
            if(i%2==0) Collections.reverse(diagonal);
            for(int num:diagonal){
                result[index++]=num;
            }
        }
        return result;
        
    }
}