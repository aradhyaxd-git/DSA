/* Partition Labels
You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part. For example, the string "ababcc" can be partitioned into ["abab", "cc"], but partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.

Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.

Return a list of integers representing the size of these parts. 
 

Example 1:

Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
*/

/*TYPE: GREEDY + INTERVAL BASED */
class Solution { 
    public List<Integer> partitionLabels(String s) {
      List<Integer> result= new ArrayList<>();
      int n= s.length();
      //step 1: count the last index of occurence of each character
      int[] mapIndex= new int[26];
      for(int i=0;i<n;i++){
        char ch= s.charAt(i);
        int index= ch-'a'; // index milega 26 size ki array mei
        mapIndex[index]=i; // jo character tha iska last index i tha
      }
      int i=0;
      while(i<n){
        char ch= s.charAt(i);
        int end= mapIndex[ch-'a']; //jaise hi kisi character par aaya hu to i par khada hu, uskka end nikalo
        int j=i;
        while(j<end){
            end= Math.max(end, mapIndex[s.charAt(j)-'a']);
            j++;
        }
        result.add(j-i+1); //partition ki length;
        i=j+1;
      }
      return result;
    }
}

class Solution1 {
    public List<Integer> partitionLabels(String s) {
      List<Integer> result= new ArrayList<>();
      int n= s.length();
      //step 1: count the last index of occurence of each character
      int i=0;
      while(i<n){
        char ch= s.charAt(i);
        int end= s.lastIndexOf(ch); //jaise hi kisi character par aaya hu to i par khada hu, uskka end nikalo
        int j=i;
        while(j<end){
            end= Math.max(end,s.lastIndexOf(s.charAt(j)));
            j++;
        }
        result.add(j-i+1); //partition ki length;
        i=j+1;
      }
      return result;
    }
}


 