class Solution {
    public double separateSquares(int[][] squares) {
          /*
          PROBLEM KA CORE IDEA:
          Hume ek horizontal line (y = some value) dhundhni hai
          jiske BELOW ka total area == ABOVE ke total area
         
         Since area changes CONTINUOUSLY with y,
         answer ek REAL NUMBER hoga → Binary Search on ANSWER
         */

         /* IS CHEEZ KO SOLVE KARNE KE LIYE
          WE WILL First calculate totalArea, and kyuki mujhe Top+ bottom dono ka area nikalna hai to mai sirf ek ka nikal dunga */
        double res=0.0;
        double totalArea=0.0;
         /*
          Search space define kar rahe hain:
         low  = sabse neeche ka possible y
         high = sabse upar ka possible y
         
          Kyunki line sirf squares ke vertical span ke beech hi meaningful hai
         */
        double low=Double.MAX_VALUE;
        double high=-Double.MAX_VALUE;

        for(int square[]: squares){
            int x= square[0];
            int y= square[1];
            int l= square[2];
            totalArea+=(double)l*l;
            low = Math.min(low, y);
            high =Math.max(high,y+l);
        }


        while(high-low>1e-5){
            double midY= low+(high-low)/2;
             /*
              Agar midY pe bottom area >= half total area
              → matlab line zyada upar chali gayi
              → try lower y
             */
            if(isPossible(squares,midY,totalArea)){
                //kyuki ye ek potential answer hai, to keep moving below as well
                res=midY;
                high=midY;
            }
            /*
              Agar bottom area kam hai
              → line aur upar le jao
             */
            else
                low=midY;
        }
        return res;
        
    }
     /*
          Ye function check karta hai:
          midY pe draw ki gayi horizontal line ke BELOW
          ka total area >= half of total area ?
    */  

    private boolean isPossible(int squares[][], double midY, double totalArea){
        double bottomArea=0.0;
        
        for(int square[]: squares){
            int y= square[1]; //square ka bottom 
            int l= square[2]; //side length of square
            double bottomY=y;
            double topY=y+l;
            /*CASE 1: 
                midY square ke upar hai 
                hence take full area */

            if(midY>=topY){
                bottomArea+= (double) l*l;
            } 
            /*CASE2:
            midY square ke beech mei hai 
            -> partial area ko contribute karega 

            height= midY-bottomY
            area= height*width */
            else if(midY>bottomY){
                bottomArea+= (double)((midY-bottomY)*l);
            }
        }

        return bottomArea>=totalArea/2.0;
    }
}