class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> set= new HashSet<>();
        for(int ban:banned){
            set.add(ban);
        }
        int sum=0,count=0;
        for(int i=1;i<=n;i++){
            if(set.contains(i)) continue;
            if(sum+i> maxSum) break;
            sum+=i;
            count++;
        }
        return count;
        
    }
}