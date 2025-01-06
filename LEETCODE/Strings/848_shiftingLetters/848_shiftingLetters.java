class Solution {
    public String shiftingLetters(String s, int[] shifts) {
        char x[]= s.toCharArray();
        int n= x.length;
        int cumulativeShift=0;
        for(int i=n-1;i>=0;i--){
            cumulativeShift= (cumulativeShift+shifts[i])%26;
            x[i]=(char)((x[i]-'a'+cumulativeShift)%26+'a');
        }
        return new String(x);      
    }
}