class Solution {
    public boolean isPalindrome(String s,int i,int j){
        char x[]= s.toCharArray();
        while(i<j){
            if(x[i]!=x[j])
                return false;
            i++; j--;
            }
        return true;
    }

    public void backtrack(String s, int index, List<String> current, List<List<String>> result){
        int n= s.length();
        if(index==n){
            result.add(new ArrayList<>(current));
            return;
        }
        for(int i=index;i<n;i++){
            if(isPalindrome(s,index,i)){
                current.add(s.substring(index,i+1));
                backtrack(s,i+1,current,result);
                current.remove(current.size()-1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        List<String> current= new ArrayList<>();
        List<List<String>> result= new ArrayList<>();
        backtrack(s,0,current,result);
        return result;
        
    }
}