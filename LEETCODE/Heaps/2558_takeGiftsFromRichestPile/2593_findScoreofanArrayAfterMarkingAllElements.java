import java.util.Arrays.*;
class Solution {
    public long findScore(int[] nums) {
        long score=0;
        int n=nums.length;
        int pairs[][]=new int[n][2];
        for(int i=0;i<n;i++){
            pairs[i][0]=nums[i];
            pairs[i][1]=i;
        }
        Arrays.sort(pairs,(a,b)->{
            if(a[0]!=b[0]) return Integer.compare(a[0],b[0]);
            return Integer.compare(a[1],b[1]);
        });
        boolean visited[]= new boolean[n];
        for(int i=0;i<n;i++){
            int value= pairs[i][0];
            int index= pairs[i][1];
            if(!visited[index]){
                score+=value;
                visited[index]=true;
                if(index-1 >=0){
                    visited[index-1]=true;
                }
                if(index+1 <n){
                    visited[index+1]=true;
                 }
            }
        }
        return score;   
    }
}


//APPROACH_2
import java.util.Arrays.*;
class Solution {
    public long findScore(int[] nums) {
        long score=0;
        int n=nums.length;
        PriorityQueue<int[]> pq= new PriorityQueue<>((a,b) -> {
            if(a[0]!=b[0]) return a[0]-b[0];
            return a[1]-b[1];
        });
        for(int i=0;i<n;i++){
            pq.add(new int[]{nums[i],i});
        }
        boolean visited[]= new boolean[n];
        for(int i=0;i<n;i++){
            int current[]= pq.poll();
            int value= current[0];
            int index= current[1];
            if(!visited[index]){
                score+=value;
                visited[index]=true;
                if(index-1 >=0){
                    visited[index-1]=true;
                }
                if(index+1 <n){
                    visited[index+1]=true;
                 }
            }
        }
        return score;   
    }
}

//APPROACH 3
class Solution {
    public long findScore(int[] nums) {
        long res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            int start = i;
            while (i + 1 < nums.length && nums[i + 1] < nums[i]) {
                i++;
            }
            for (int j = i; j >= start; j -= 2) {
                res += nums[j];
            }
        }
        return res;
    }
}