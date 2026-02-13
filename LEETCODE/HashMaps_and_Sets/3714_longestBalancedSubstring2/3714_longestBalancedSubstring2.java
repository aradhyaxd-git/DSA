class Solution {
    /*  FOR CASE2
        * helper function is used inorder to calculate the max length */
    private int helper(char x[], char ch1, char ch2){

        HashMap<Integer,Integer> map= new HashMap<>();
        int count1=0,count2=0;
        int maxL=0;

        for(int i=0;i<x.length;i++){
            if(x[i]!=ch1 && x[i]!=ch2){
                //for the case if any third character is present then it breaks the logic 
                map= new HashMap<>(); //soo clear the map
                count1=0;
                count2=0;
                continue;
            }
            if(x[i]==ch1) count1++;
            if(x[i]==ch2) count2++;
            if(count1==count2){
                maxL= Math.max(maxL,count1*2);
            }

            int diff= count1-count2;//like contigous array 
            if(map.containsKey(diff)){
                maxL= Math.max(maxL,i-map.get(diff));
            }
            else
                map.put(diff,i);
        }
        return maxL;
    }

    public int longestBalanced(String s) {

        int maxL=0;
        int count=1;
        int n= s.length();
        char x[]=s.toCharArray();
        //for case 1
        for(int i=1;i<n;i++){
            if(x[i]==x[i-1]){
                count++;
            }
            else{
                maxL= Math.max(maxL,count);
                count=1;
            }
        }
        maxL=Math.max(maxL,count);
        //for case 2
        maxL= Math.max(maxL,helper(x,'a','b'));
        maxL= Math.max(maxL,helper(x,'c','b'));
        maxL= Math.max(maxL,helper(x,'a','c'));
        //for case3
        HashMap<String,Integer> map= new HashMap<>();
        int countA=0,countB=0,countC=0;
        for(int i=0;i<n;i++){

            if(x[i]=='a') countA++;
            if(x[i]=='b') countB++;
            if(x[i]=='c') countC++;

            if(countA==countB && countB==countC)
                maxL= Math.max(maxL,countA*3);

            int diffAB= countA-countB;
            int diffAC= countA-countC;

            String key= diffAB+"#"+diffAC;

            if(map.containsKey(key)){
                maxL= Math.max(maxL,i-map.get(key));
            }
            else
                map.put(key,i);
        }
        return maxL;

    }
}