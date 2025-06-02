/*135. Candy

There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.

 

Example 1:

Input: ratings = [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
Example 2:

Input: ratings = [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
The third child gets 1 candy because it satisfies the above two conditions.
 

Constraints:

n == ratings.length
1 <= n <= 2 * 104
0 <= ratings[i] <= 2 * 104
 */


class Solution1 {
    public int candy(int[] ratings) {
        int n= ratings.length;
        int candies[]= new int[n];
        Arrays.fill(candies,1); //minimum sabko ek candy to deni hi hai

        for(int i=1;i<n;i++){
            //agar neighbour ki rating jyada hai , to usko jyada candies do
            if(ratings[i]>ratings[i-1]){
                candies[i]= candies[i-1]+1;
            }
        }


        for(int i=n-2;i>=0;i--){
            //neighbour ka store karlia hai
            if(ratings[i]>ratings[i+1]){
                candies[i]= Math.max(candies[i],candies[i+1]+1);//neighbour se ek jyada 
            }
        }

        int totalCandies=0;
        for(int c: candies){
            totalCandies+= c;
        }
        
        return totalCandies;
    }
}


//appraoch 2: using single pass 
/*Concept of dip and peak :: refer to notes for better visualization */

class Solution {
    public int candy(int[] ratings) {
        int n= ratings.length;
        int candies=n; //har bache ko min 1 candies deni thi, to sabko candies dedo

        int i=1; // 1 se start karo for slope comparison
        while(i<n){
            if(ratings[i]==ratings[i-1]){ //same slope hai, na dip ha na peak, to skip those, kyuki koi contribution ni hoga
                i++; 
                continue;
            }
            int peak=0;//count increasing slope 
            while(i<n && ratings[i]>ratings[i-1]){
                peak++; //har baar child[i] is better rated so, he deserves +1 candy
                candies+=peak; //peak ka contribution add karo 
                i++;
            }

            int dip=0;//count decreasing slope 
            while(i<n && ratings[i]<ratings[i-1]){
                dip++; //child[i] jo hai wo low rated hai, 
                candies+=dip;
                i++;
            }

            /*subtract overlapping point
            --> jo child, peak ke last mei hai and dip ke start pe , wo 2 baar count hua hoga 
                    ---> isliye Math.min(peak,dip) subtract kardo */
            candies-= Math.min(dip,peak);
        }
        return candies;
        
    }
}