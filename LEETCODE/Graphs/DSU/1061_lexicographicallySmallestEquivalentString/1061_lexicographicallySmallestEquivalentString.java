/*You are given two strings of the same length s1 and s2 and a string baseStr.

We say s1[i] and s2[i] are equivalent characters.

For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
Equivalent characters follow the usual rules of any equivalence relation:

Reflexivity: 'a' == 'a'.
Symmetry: 'a' == 'b' implies 'b' == 'a'.
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" are equivalent strings of baseStr = "eed", and "aab" is the lexicographically smallest equivalent string of baseStr.

Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.

 

Example 1:

Input: s1 = "parker", s2 = "morris", baseStr = "parser"
Output: "makkek"
Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
The characters in each group are equivalent and sorted in lexicographical order.
So the answer is "makkek". */



/* APPROACH 1: DFS , TC: m*O(V+E)

This question is a classical String Based Dfs problem 
--> We are given Two strings and 1 Base string.. Kul milake hum logo ko , har index par, 
    sabse minimum character dhundhna hai lexicographically and usko append karke answer mei dalna hai

--> Assume each character is a node and har node ke equivalent jo bhi character aa raha hai us index par
    wo us node se jud kar ek component bana lega ekdm apna... 

--> SO , now we will have connected components for each case... and un sabmei hum log dfs lagake find karelngay
    ki minimum character kaunsa hai .. and we will build our string in each step */

class Solution1 {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        List<List<Character>> adj= new ArrayList<>();
        int n= s1.length();
        int m= baseStr.length();
        char x1[]= s1.toCharArray();
        char x2[]= s2.toCharArray();
        //step1 : 26 size ki arraylist banao charactes ki kyuki saare lower letters hai
        for(int i=0;i<26;i++)
            adj.add(new ArrayList<>());
        //step 2: add adj.get(u-'a') instead of adj.get(u) because...arraylist ka index Integer hota hai
                  //not character, so if i do (adj.get(u)) ... to uski ascii value ke hisab se 98 se start hojaeyga and will give index out of bound
        for(int i=0;i<n;i++){
            char u= x1[i];
            char v= x2[i];
            adj.get(u-'a').add(v);
            adj.get(v-'a').add(u); //dono side add karo kyuku bidirectional edge hai
        }

        //step 3: build our result
        StringBuilder result= new StringBuilder();
        for(int i=0;i<m;i++){
            //step 4: har baar jab dfs call karengay to apni ek alag hi visited array banayengay...  to avoid infinite loop 
            boolean visited[]= new boolean[26];
            char ch= baseStr.charAt(i);
            char minChar= dfs(adj,ch,visited); //adj list se interpretation derive karke , minCHar lete jayengay jo bhi  lexicoggraphically smaller ban rha hai wo
            result.append(minChar);
        }
        //step 5: at last, return our own string
        return result.toString();
    }

    //helper method for dfs
    public char dfs(List<List<Character>> adj, char current_char , boolean visited[]){
        //step1: current char ke index ko visited mark karo
        visited[current_char-'a']=true;
        char minChar= current_char; //initially usi character ko minChar maan lo
        for(char v: adj.get(minChar-'a')){
            if(!visited[v-'a']){
                minChar= (char)Math.min(minChar, dfs(adj,v,visited));
            }
        }
        return minChar;

    }
}

//APPROACH 2: DSU (UNION FIND)

/*Now I realised that, in dfs we are 
 --> Recomputing things for each query 
 --> Using extra space and recursion
 --> Traversing the graph multiple time

 All of these are redundant things

 --> SO instead of using dfs again and again, i am precomputing things to find which character is the smallest representataive of its group
  
  --> result.append((char)(dsu.find(c - 'a') + 'a'));
    --> constant time, no recursion and no extra space

--> so dfs help me understand the structure of the problem, but dsu helped me scale it effeciently */


/*Now the question arises, how will dsu automatically find the smallest member 
    --> in union by rank , we always attach the one with the larger ASSCII value to the one with smaller 
    --> Hence , The smallest leader always becomes the root */
     
class Solution {
    class DSU{
        // note yaha par: dsu by rank nhi lagega o0
        int parent[];
        DSU(){
            parent= new int[26];
            for(int i=0;i<26;i++){
                parent[i]=i;
            }
        }
        public void union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);
            if(xParent==yParent) return;
            if(xParent<yParent){
                parent[yParent]=xParent;
            }
            else 
                parent[xParent]=yParent;
        }
         public int find(int x) {
            if (x == parent[x]) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }
    }
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        DSU dsu= new DSU();
        StringBuilder st= new StringBuilder();
        for(int i=0;i<s1.length();i++){
            int a= s1.charAt(i)-'a';
            int b= s2.charAt(i)-'a';
            dsu.union(a,b);
        }

        for(char ch: baseStr.toCharArray()){
            int root= dsu.find(ch-'a');
            st.append((char)(root+'a'));
        }
        return st.toString();
        
        
    }
}