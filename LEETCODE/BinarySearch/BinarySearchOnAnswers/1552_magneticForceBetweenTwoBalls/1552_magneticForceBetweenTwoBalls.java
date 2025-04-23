/*1552. Magnetic Force Between Two Balls

In the universe Earth C-137, Rick discovered a special form of magnetic force between two 
balls if they are put in his new invented basket. Rick has n empty baskets, the ith basket 
is at position[i], Morty has m balls and needs to distribute the balls into the baskets such 
that the minimum magnetic force between any two balls is maximum.

Rick stated that magnetic force between two different balls at positions x and y is |x - y|.

Given the integer array position and the integer m. Return the required force.

  */

  class Solution {
    public int maxDistance(int[] position, int m) {
        int n= position.length;
        Arrays.sort(position);
        //sort karne se number line mei directly use kar sakte hai

        //bina sorting ke kaam ni krega ye bc

        int minForce=1,maxForce=position[n-1]-position[0];

        int result=0;

        while(minForce<=maxForce){
            int midForce= minForce+ (maxForce-minForce)/2;
            if(isPossibleToPlace(midForce,position,m)){ //possible hai place karna.. answer store karo .. aur dobara se explore karo
                result=midForce;
                minForce=midForce+1;
            }
            else
                maxForce=midForce-1;
        }
        return result;
    }


    public boolean isPossibleToPlace(int force,int position[],int m){
        int prev= position[0];
        int countBalls=1;

        for(int i=1;i<position.length;i++){
            int current= position[i];
            if(current-prev >=force){
                countBalls++;
                prev=current;
            }
            if(countBalls==m) break;
        }

        return countBalls==m;
    }
}