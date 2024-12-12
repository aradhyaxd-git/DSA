class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int m=mat.length;
        int n=mat[0].length;
        Map<Integer,PriorityQueue<Integer>> map= new HashMap<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                map.putIfAbsent(i-j,new PriorityQueue<>());
                map.get(i-j).add(mat[i][j]);   
            }
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                mat[i][j]=map.get(i-j).poll();
            }
        }
        return mat;
    }
}


//Approach 2 
class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int m=mat.length;
        int n=mat[0].length;
        Map<Integer,List<Integer>> map= new HashMap<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                map.putIfAbsent(i-j,new ArrayList<>());
                map.get(i-j).add(mat[i][j]);   
            }
        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                mat[i][j]=map.get(i-j).remove(0);
            }
        }
        return mat;
    }
}