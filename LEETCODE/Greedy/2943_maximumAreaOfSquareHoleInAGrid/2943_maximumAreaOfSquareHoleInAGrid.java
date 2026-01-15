class Solution {
    private int findLongestConsecutive(int A[]){
        int n= A.length;
        int currMax=1,maxi=1;

        for(int i=1;i<n;i++){
            if(A[i]-A[i-1]==1){
                currMax++;
            }
            else
                currMax=1;
            maxi= Math.max(maxi,currMax);
        }
        return maxi;
    }
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {  /*
          PROBLEM KA CORE IDEA:
          ->    Agar k horizontal bars consecutively remove hue,
                toh vertical direction me size = k + 1
          ->    Same for vertical bars.
          ->    Square banane ke liye:
                side = min(horizontal_gap, vertical_gap)
         */

        // Step 0:
        // Bars ko sort karna compulsory
        // Warna consecutive logic ka koi matlab hi nahi

        Arrays.sort(hBars);
        Arrays.sort(vBars);

        //Step 1: find the longest consecutive subarrays in both cases
        int longestConsecutiveHBar= findLongestConsecutive(hBars);
        int longestConsecutiveVBar= findLongestConsecutive(vBars);
           /*
          Step 2 (MOST IMPORTANT):
         
          Agar 3 bars consecutively remove hue,
          toh actual gap = 3 + 1 = 4
         
          Square ka side hamesha MIN hoga
          (square bhai rectangle nahi hota)
         */

        int side= Math.min(longestConsecutiveVBar,longestConsecutiveHBar)+1;
        return side*side;  
    }
}