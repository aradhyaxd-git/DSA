class Solution {
    public boolean canMakeSubsequence(String str1, String str2) {
        int n1= str1.length();
        int n2= str2.length();
        int i=0,j=0;
        while(i<n1 && j<n2){
            char ch1= str1.charAt(i);
            char ch2= str2.charAt(j);
            if(ch1==ch2 || ch1+1==ch2 || ch1-25==ch2){
                j++;
            }
            i++;
        }
        return j==n2;   
    }
}