/*Alien Dictionary
A new alien language uses the English alphabet, but the order of letters is unknown. 
You are given a list of words[] from the alien language’s dictionary, where the words are
 claimed to be sorted lexicographically according to the language’s rules.

Your task is to determine the correct order of letters in this alien language based on the
 given words. If the order is valid, return a string containing the unique letters in 
 lexicographically increasing order as per the new language's rules. If there are multiple
  valid orders, return any one of them.

However, if the given arrangement of words is inconsistent with any possible letter ordering, 
return an empty string ("").

A string a is lexicographically smaller than a string b if, at the first position where they 
differ, the character in a appears earlier in the alien language than the corresponding 
character in b. If all characters in the shorter word match the beginning of the longer
 word, the shorter word is considered smaller.

Note: Your implementation will be tested using a driver code. It will print true if your
 returned order correctly follows the alien language’s lexicographic rules; otherwise, 
 it will print false.

Examples:

Input: words[] = ["baa", "abcd", "abca", "cab", "cad"]
Output: true
Explanation: A possible corrct order of letters in the alien dictionary is "bdac".
The pair "baa" and "abcd" suggests 'b' appears before 'a' in the alien dictionary.
The pair "abcd" and "abca" suggests 'd' appears before 'a' in the alien dictionary.
The pair "abca" and "cab" suggests 'a' appears before 'c' in the alien dictionary.
The pair "cab" and "cad" suggests 'b' appears before 'd' in the alien dictionary.
So, 'b' → 'd' → 'a' → 'c' is a valid ordering.
Input: words[] = ["caa", "aaa", "aab"]
Output: true
Explanation: A possible corrct order of letters in the alien dictionary is "cab".
The pair "caa" and "aaa" suggests 'c' appears before 'a'.
The pair "aaa" and "aab" suggests 'a' appear before 'b' in the alien dictionary. 
So, 'c' → 'a' → 'b' is a valid ordering. */



class Solution {
    
    private List<Integer> topo(List<List<Integer>> adj,int n){
        Queue<Integer> queue= new LinkedList<>();
        int indegree[]= new int[n];
        for(int u=0;u<n;u++){
            for(int v: adj.get(u)){
                indegree[v]++;
            }
        }
        for(int i=0;i<n;i++){
            if(indegree[i]==0){
                queue.offer(i);
            }
        }
        
        List<Integer> result= new ArrayList<>();
        while(!queue.isEmpty()){
            int u= queue.poll();
            result.add(u);
            for(int v: adj.get(u)){
                indegree[v]--;
                if(indegree[v]==0){
                    queue.offer(v);
                }
            }
        }
        return result;
    }
    
    
    public String findOrder(String[] dict, int k) {
        // Write your code here
        List<List<Integer>> adj= new ArrayList<>();
        for(int i=0;i<k;i++){
            adj.add(new ArrayList<>());
        }
        
        int n= dict.length;
        
        for(int i=0;i<n-1;i++){
            String s1= dict[i];
            String s2= dict[i+1];
            int len= Math.min(s1.length(), s2.length());
             boolean foundDifference = false;
            for (int j = 0; j < len; j++) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    adj.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
                    foundDifference = true;
                    break;
                }
            }
             if (!foundDifference && s1.length() > s2.length()) {
                return "";
             }
        }
        
         List<Integer> topoSort = topo(adj, k);
        
        // If topoSort does not contain k elements, a cycle exists
        if (topoSort.size() != k) {
            return "";
        }
        
        StringBuilder ans = new StringBuilder();
        for (int character : topoSort) {
            ans.append((char) (character + 'a'));
        }
        
        return ans.toString();
    }
}