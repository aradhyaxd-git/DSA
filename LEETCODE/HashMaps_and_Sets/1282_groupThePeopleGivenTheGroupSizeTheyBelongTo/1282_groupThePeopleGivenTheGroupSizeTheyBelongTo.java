/*1282. Group the People Given the Group Size They Belong To

There are n people that are split into some unknown number of groups. Each person is labeled with a 
unique ID from 0 to n - 1.

You are given an integer array groupSizes, where groupSizes[i] is the size of the group that person
 i is in. For example, if groupSizes[1] = 3, then person 1 must be in a group of size 3.

Return a list of groups such that each person i is in a group of size groupSizes[i].

Each person should appear in exactly one group, and every person must be in a group. If there are
 multiple answers, return any of them. It is guaranteed that there will be at least one valid 
 solution for the given input. */

 class Solution {

    /*THIS IS A CLASSIC EXAMPLE OF HASHMAP GROUPING PATTERN 
    -->WE are grouping elements based on some property here, groupSize using a map<Integer,List<Integer>>
    --> It is a variant of

            -> Greedy: As soon as a group reaches its required size, we finalise it 
            -> Bucket Based processing : Each bucket(map entry) collects items until it is full
    */

    /*Brute force idea:
        I could try to check every time if there's a group of the same size already formed or not, and 
        maintain a bunch of lists. But that gets messy and involves a lot of checking/searching.

    Redundancy noticed:
        I’m repeatedly trying to group people by the same size. That means there's a lot of repeated effort looking for existing groups.

    Optimized approach:
        I realized I could just group by size using a map — this avoids scanning or searching and organizes people as I go.
        As soon as a group reaches its full size, I finalize and remove it, keeping the map clean and the logic efficient. */


    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> ans= new ArrayList<>(); //for storing the answer
        HashMap<Integer,List<Integer>> map= new HashMap<>();
        int n= groupSizes.length; 
        for(int i=0;i<n;i++){
            int groupSize= groupSizes[i]; //for each groupSize ... we store the ith element in that group
            List<Integer> group= map.get(groupSize);
            if(group==null){ //if the group is not formed .. make the group, make the key as GroupSize with the value of group
                group= new ArrayList<>();
                map.put(groupSize,group);
            }
            group.add(i);
            if(groupSize==group.size()){
                ans.add(group);
                map.remove(groupSize);
            }
        }
        return ans;
        
    }
}