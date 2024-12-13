class Solution {
    public int[] findOriginalArray(int[] changed) {
        int n=changed.length;
        if(n%2!=0) return new int[0];
        Arrays.sort(changed);
        Map<Integer,Integer> map= new HashMap<>();
        for(int num:changed) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        int result[]= new int[n/2];
        int index=0;
        for(int num:changed){
            if(map.get(num)==0) continue;
            if(map.getOrDefault(num*2,0)==0) return new int[0];
            if(index<n/2)
            result[index++]=num;
            map.put(num,map.get(num)-1);
            map.put(num*2,map.get(num*2)-1);
        }
        return result;
    }
}