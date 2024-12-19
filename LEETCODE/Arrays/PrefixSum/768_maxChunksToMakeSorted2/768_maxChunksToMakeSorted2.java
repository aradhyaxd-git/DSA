class Solution {
    public int findMaxChunks(int[] arr){
        int n=arr.length;
        int cumSum=0,sum=0,chunks=0;
        for(int i=0;i<n;i++){
            cumSum+=arr[i];
            sum+=i;
            if(cumSum==sum){
                chunks++;
            }
        }
        return chunks;
    }
    public int maxChunksToSorted(int[] arr) {
        int n= arr.length;
        Integer indices[]= new Integer[n];
        for(int i=0;i<n;i++){
            indices[i]=i;
        }
        Arrays.sort(indices,(i,j)->arr[i]-arr[j]);
        int positionArray[]= new int[n];
        for(int i=0;i<n;i++){
            positionArray[indices[i]]=i;
        }
        int ans= findMaxChunks(positionArray);
        return ans;
    }
}