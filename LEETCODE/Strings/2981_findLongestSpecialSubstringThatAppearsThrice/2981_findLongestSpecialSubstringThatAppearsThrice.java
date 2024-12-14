class Solution { //APPROACH 1
    public int maximumLength(String s) {
        int n=s.length();
        int matrix[][]= new int[26][n+1];
        char prev= s.charAt(0);
        int length=0;
        for(int i=0;i<n;i++){
            char curr= s.charAt(i);
            if(curr==prev){
                length+=1;
                matrix[curr-'a'][length]+=1;
            }
            else {
                length=1;
                matrix[curr-'a'][length]+=1;
                prev=curr;
            }
        }
        int ans=0;
        for(int i=0;i<26;i++){
            int cumSum=0;
            for(int j=n;j>=1;j--){
                cumSum+=matrix[i][j];
                if(cumSum>=3){
                    ans= Math.max(ans,j);
                }
            }
        }
        return ans==0?-1:ans;   
    }
}

//APPROACH 2
class Solution {
    public int maximumLength(String s) {
        int n= s.length();
        int substringLength=0;
        Map<Pair<Character,Integer>,Integer> map= new HashMap<>();
        for(int i=0;i<n;i++){
            char ch= s.charAt(i);
            substringLength=0;
            for(int j=i;j<n;j++){
                if(ch==s.charAt(j)){
                    substringLength++;
                    Pair<Character,Integer> key= new Pair<>(ch,substringLength);
                    map.put(key,map.getOrDefault(key,0)+1);
                }
                else {
                    break;
                }
            }
        }
        int result=0;
        for(Map.Entry<Pair<Character,Integer>,Integer> entry: map.entrySet()){
            int len= entry.getKey().getValue();
            int count= entry.getValue();
            if(count>=3 && len>result){
                result= len;
            }
        }
        return result==0?-1:result;
        
    }
}


//APPRAOCH 3
class Solution {
    public int maximumLength(String s) {
        int n=s.length();
        int matrix[][]= new int[26][n+1];
        char prev= s.charAt(0);
        int length=0;
        for(int i=0;i<n;i++){
            char curr= s.charAt(i);
            if(curr==prev){
                length+=1;
                matrix[curr-'a'][length]+=1;
            }
            else {
                length=1;
                matrix[curr-'a'][length]+=1;
                prev=curr;
            }
        }
        int ans=0;
        for(int i=0;i<26;i++){
            int cumSum=0;
            for(int j=n;j>=1;j--){
                cumSum+=matrix[i][j];
                if(cumSum>=3){
                    ans= Math.max(ans,j);
                }
            }
        }
        return ans==0?-1:ans;

        
    }
}