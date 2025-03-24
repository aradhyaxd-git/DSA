/*3169. Count Days Without Meetings

You are given a positive integer days representing the total number of days an employee is 
available for work (starting from day 1). You are also given a 2D array meetings of size n 
where, meetings[i] = [start_i, end_i] represents the starting and ending days of meeting i (inclusive).

Return the count of days when the employee is available for work but no meetings are scheduled.

Note: The meetings may overlap.

Example 1:

Input: days = 10, meetings = [[5,7],[1,3],[9,10]]

Output: 2 */

/*APPRAOCH ->   This is a classical example of merge overlapping intervals 
        Procedure: 1) merge the overlapping intervals
                   2) now count the range jismei aa rahaa hai */
class Solution {
   public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,((a,b)->a[0]-b[0]));
        int n=  intervals.length;
        List<int[]> ans= new ArrayList<>();
        for(int i=0;i<n;i++){
            int start= intervals[i][0];
            int end= intervals[i][1];
            if(ans.isEmpty() || ans.get(ans.size()-1)[1]<start){
                ans.add(intervals[i]);
            }
            else{
                ans.get(ans.size()-1)[1]= Math.max(ans.get(ans.size()-1)[1],end);
            }
        }
        return ans.toArray(new int[ans.size()][]);
        
    }
    int ans[][];
    public int countDays(int days, int[][] meetings) {
      ans= merge(meetings);
      int count=0;
      for(int range[]:ans){
        int phela= range[0];
        int dusra= range[1];
        count+= (dusra-phela+1);
      }
      return days-count;  
    }
}