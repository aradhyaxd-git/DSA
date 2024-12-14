class Solution {
    public int slidingPuzzle(int[][] board) {
        StringBuilder start= new StringBuilder();
        for(int i=0;i<2;i++){
            for(int j=0;j<3;j++){
                start.append(board[i][j]);
            }
        }
        String target="123450";
        int[][] moves = {
            {1, 3},       // Index 0 can move to 1 or 3
            {0, 2, 4},    // Index 1 can move to 0, 2, or 4
            {1, 5},       // Index 2 can move to 1 or 5
            {0, 4},       // Index 3 can move to 0 or 4
            {1, 3, 5},    // Index 4 can move to 1, 3, or 5
            {2, 4}        // Index 5 can move to 2 or 4
        };
        Queue<String> queue= new LinkedList<>();
        Set<String> visited= new HashSet<>();
        queue.offer(start.toString());
        visited.add(start.toString());
        int steps=0;
        //ab BFS lagao
        while(!queue.isEmpty()){
            int size= queue.size();
            for(int i=0;i<size;i++){
                String current= queue.poll();
                if(current.equals(target)) return steps;
                int zeroIndex= current.indexOf('0');
               int[] possibleMoves = moves[zeroIndex];
                for (int j = 0; j < possibleMoves.length; j++) {
                  int move = possibleMoves[j]; // Get the move
                 String nextState = swap(current, zeroIndex, move);
                    if (!visited.contains(nextState)) {
                  visited.add(nextState);
                      queue.offer(nextState);
                    }
                }
            }
        steps++;
        }
        return-1;
    }
    private String swap(String s, int i, int j){
        char x[]=s.toCharArray();
        char temp=x[i];
        x[i]=x[j];
        x[j]=temp;
        return new String(x);
    }

}
