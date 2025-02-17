/*HOW did i indentify it was backtracking ??
    -> Subset + Permutation 
    -> Do -> explore and then undo */
    class Solution1 {
        public int numTilePossibilities(String tiles) {
            int n= tiles.length();
            boolean used[] = new boolean[n]; //check if current character is used.. if already , skip it
            Set<String> set= new HashSet<>();  // to avoid duplicated
            StringBuilder current= new StringBuilder();
    
            solve(tiles,used,set,current);
    
            return set.size()-1;   //-1 because ek empty bhi aayega set mei 
        }
    
        public void solve(String tiles, boolean used[], Set<String> set, StringBuilder current){
            set.add(current.toString()); //current ko process karke string mei add karlo
            for(int i=0;i<tiles.length();i++){  //i=0 se start karo
                 if(used[i]) continue;      //agar already used hai.. then continue
                 used[i]=true;      //if Not: STEP1: DO 
                                    //Step 2: APPEND and EXPLORE
                 current.append(tiles.charAt(i));
                 solve(tiles,used,set,current);
                 //NOW WE HAVE EXPLORED, then undo it
                 used[i]=false;
                 current.deleteCharAt(current.length()-1);
            }
        }
    }
    
    
    /*APPROACH 2: Now Instead of using too much space, we observe that we only need
    frequnices in our answer, not the actual perumatations and etc.. */
    class Solution {
        public int count;
        public int numTilePossibilities(String tiles) {
            int n= tiles.length();
            int freq[]= new int[26];
            for(char ch: tiles.toCharArray()){
                freq[ch-'A']++;
            }
            //STORE FREQUENCIES FOR EACH CHAR
            solve(freq);
            return count-1;
        }
    
        public void solve(int freq[]){
            count++;
            for(int i=0;i<26;i++){
                if(freq[i]==0) continue;
    
                freq[i]--; //DO 
                solve(freq); //EXPLORE
                freq[i]++; //UNDO
            }
        }
    }