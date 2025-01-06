class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        //step 1 Make differnce array
        int n= s.length();
        int diff[]= new int[n];
        for(int query[]:shifts){
            int L= query[0];
            int R= query[1];
            int direction= query[2];
            int x;
            if(direction==0) x=-1;
            else x=1;
            diff[L]+=x;
            if(R+1<n){
                diff[R+1]-=x;
            }
        }
        //step 2 : cumulative sum banao
        for(int i=1;i<n;i++){
            diff[i]+=diff[i-1];
        }
        char x[]= s.toCharArray();
        // now apply shift
        for(int i=0;i<n;i++){
            int shift= diff[i]%26;
            if(shift<0){
                shift+=26;
            }
            x[i]= (char)(((x[i]-'a')+shift)%26+'a'); 
            /*(x[i]-'a' se int value aayega + shift 
             se add hogi value fir %26 se wrap around) 
             fir +'a' se char banega*/
        }
        return new String(x);
    }
}