class Solution2 {
    public int maxChunksToSorted(int[] arr) {
        int n=arr.length;
        int prefix[]=Arrays.copyOf(arr,n);
        int suffix[]= Arrays.copyOf(arr,n);
        for(int i=1;i<n;i++){
            prefix[i]=Math.max(prefix[i],prefix[i-1]);
        }
        for(int i=n-2;i>=0;i--){
            suffix[i]=Math.min(suffix[i],suffix[i+1]);
        }
        int chunks=0;
        for(int i=0;i<n;i++){
            int pheleKaMax= i>0?prefix[i-1]:-1;
            int badkaMin= suffix[i];
            if(pheleKaMax<badkaMin){
                chunks++;
            }
        }
        return chunks;      
    }
}


class Solution1 {
    public int maxChunksToSorted(int[] arr) {
        int cumSum=0;
        int sum=0;
        int chunks=0;
        //hum logo ko pata hai array already sorted hai answer wali , so we will use array index 
        for(int i=0;i<arr.length;i++){
            cumSum+=arr[i];
            sum+=i;
            if(cumSum==sum){
                chunks++;
            }
        }
        return chunks;
    }
}


class Solution {
    public int maxChunksToSorted(int[] arr) {
        int n= arr.length;
        int maxTillNow=-1;
        int chunks=0;
        for(int i=0;i<n;i++){
            maxTillNow= Math.max(maxTillNow,arr[i]);
            if(maxTillNow==i){
                chunks++;
            }
        }
        return chunks;
    }
}