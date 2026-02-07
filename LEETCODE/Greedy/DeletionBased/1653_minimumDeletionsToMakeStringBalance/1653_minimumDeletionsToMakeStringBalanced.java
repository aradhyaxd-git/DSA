class Solution {
    public int minimumDeletions(String s) {

        int bCount=0,deletions=0;

        for(char ch: s.toCharArray()){
            if(ch=='b') bCount++;
            if(ch=='a' && bCount>0)
                deletions= Math.min(deletions+1,bCount);
        }
        return deletions;
        
    }
}