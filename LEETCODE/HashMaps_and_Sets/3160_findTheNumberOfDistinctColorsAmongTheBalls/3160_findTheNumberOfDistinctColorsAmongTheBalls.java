/*3160. Find the Number of Distinct Colors Among the Balls

You are given an integer limit and a 2D array queries of size n x 2.

There are limit + 1 balls with distinct labels in the range [0, limit]. 
Initially, all balls are uncolored. For every query in queries that is of 
the form [x, y], you mark ball x with the color y. After each query, you need 
to find the number of colors among the balls.

Return an array result of length n, where result[i] denotes the number of colors after ith query. */


 //APPRAOCH 1:  USING HASHMAP + ARRAY 

 class Solution1 {
    public int[] queryResults(int limit, int[][] queries) {
        int n= queries.length; 
        int result[]= new int[n]; //stores results for each queries

        int ballColor[]= new int[limit+1]; //stores the colour of each ball, so that if it is marked we can acces the prev color
        Arrays.fill(ballColor,-1); // initially, each ball is un coloured so mark it as -1

        HashMap<Integer,Integer> colourMap= new HashMap<>();  //Map which stores the colour-> count of balls with that colour
 
        for(int i=0;i<n;i++){
            int ball= queries[i][0]; // ball we are colouring
            int color= queries[i][1]; //new colour 
            if(ballColor[ball]!=-1){ /*STEP !:  IF THE BALL IS ALREADY COLOURED , REMOVE THE PREVIOUS COLOUR*/
                int prevColor= ballColor[ball]; //get previous colour from array
                colourMap.put(prevColor,colourMap.get(prevColor)-1);
                if(colourMap.get(prevColor)==0)
                    colourMap.remove(prevColor);
            }
            /*STEP 2: ASSIGN NEW COLOUR TO THE BALL */
             ballColor[ball]=color;
             /*STEP 3: Increase its counter */
             colourMap.put(color,colourMap.getOrDefault(color,0)+1);
              /*STEP 4: the size of the map will be the number of distinct
                       coloured balls*/
             result[i]= colourMap.size();        
        }
        return result;
    }
}


/*OPTIMISATION
 Instead of using an array, we use a HashMap to reduce space complexity when limit is large.
 This prevents unnecessary memory allocation when only a few balls are used.
 */

class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        int n= queries.length;
        int result[]= new int[n]; //stores results for each queries
        HashMap<Integer,Integer> colourMap= new HashMap<>(); // Maps: color -> number of balls with this color
        HashMap<Integer,Integer> ballMap= new HashMap<>();  //Maps: ball -> current colour of the ball 

        for(int i=0;i<n;i++){
            int ball= queries[i][0]; // the ball we are changing 
            int color= queries[i][1]; // the new colour we are assigning
            /*STEP 1: IF the ball was previously coloured.. update its old colour count*/
            if(ballMap.containsKey(ball)){ 
                int prevColor= ballMap.get(ball); //get previous ball's colour
                colourMap.put(prevColor,colourMap.get(prevColor)-1); //decrement the count
                if(colourMap.get(prevColor)==0) //if the frequency of balls with that colour becomes zero, remove that shi
                    colourMap.remove(prevColor);
            }
            /*STEP 2: ASSIGN NEW COLOUR TO THE BALL */
             ballMap.put(ball,color);
             /*STEP 3: Increase its counter */
             colourMap.put(color,colourMap.getOrDefault(color,0)+1);
             /*STEP 4: the size of the map will be the number of distinct
                       coloured balls*/
             result[i]= colourMap.size();        
        }
        return result;
    }
}