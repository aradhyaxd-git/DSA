/*2570. Merge Two 2D Arrays by Summing Values

You are given two 2D integer arrays nums1 and nums2.

nums1[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
nums2[i] = [idi, vali] indicate that the number with the id idi has a value equal to vali.
Each array contains unique ids and is sorted in ascending order by id.

Merge the two arrays into one array that is sorted in ascending order by id, 
respecting the following conditions:

Only ids that appear in at least one of the two arrays should be included in the resulting array.
Each id should be included only once and its value should be the sum of the values 
of this id in the two arrays. If the id does not exist in one of the two arrays,
then assume its value in that array to be 0.

Return the resulting array. The returned array must be sorted in ascending order by id. 
Input: nums1 = [[1,2],[2,3],[4,5]], nums2 = [[1,4],[3,2],[4,1]]
Output: [[1,6],[2,3],[3,2],[4,6]]*/

//APPRAOCH 1: TreeMaps in Java.. very basic Use of HashMaps
class Solution1 {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
      TreeMap<Integer,Integer> map= new TreeMap<>();
      for(int num[]:nums1){
        int id= num[0];
        int value= num[1];
        map.put(id,value);
      }
       for(int num[]:nums2){
        int id= num[0];
        int value= num[1];
        map.put(id,map.getOrDefault(id,0)+value);
       }
       int size= map.size();
       int result[][]= new int[size][2];
       int i=0;
       for(Map.Entry<Integer,Integer> entry: map.entrySet()){
        int id= entry.getKey();
        int val= entry.getValue();
        result[i][0]=id;
        result[i][1]=val;
        i++;
       }
       return result;
    }
}

//APPROACH 2: effecient ( two pointers )
class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
      int n= nums1.length;
      int m= nums2.length;
      int i=0,j=0;
      List<int[]> result= new ArrayList<>();
      while(i<n && j<m){
        if(nums1[i][0]>nums2[j][0]){
          result.add(nums2[j]);
          j++;
        }
        else if(nums1[i][0]<nums2[j][0]){
          result.add(nums1[i]);
          i++;
        }
        else{
          result.add(new int[]{nums1[i][0],nums1[i][1]+nums2[j][1]});
          i++; j++;
        }
      }
      while(i<n){
        result.add(nums1[i]); i++;
      }
      while(j<m)
      {
        result.add(nums2[j]); j++;
      }
      return result.toArray(new int[0][]);
    }
}