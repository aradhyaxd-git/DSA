/*aPPROACH 1;
JO DIA HAI WAHI KRDO: TC:O(N2) */
class Solution1 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n= nums.length;
        int right=0,left=0;
        int res[]= new int[n-k+1];
        int idx=0;
        for(int i=0;i<=n-k;i++){
            int maxi=Integer.MIN_VALUE;
            for(int j=i;j<i+k;j++){
                maxi= Math.max(maxi,nums[j]);
            }
            res[idx++]=maxi;
        }
        return res;
    }
}

//APPROACH 2: USING MONOTONIC DEQUE TC:O(N)
class Solution {
    /*STORY POINTS
    1) Window maintain krengay aur ek monotonic dec dequeu bhi
    2) jaise hi window ka size i>=k-1 hojaga, waise hi ans milega
    3) dequeu ke top par , humesha max element rhega us current window ka
    4) jaise hi i-k 1 se kam hojayega , waise hi usko hata dengay */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n= nums.length;
        Deque<Integer> dq= new ArrayDeque<>();
        int res[]= new int[n-k+1];
        int idx=0;

        for(int i=0;i<n;i++){
            /*Phele dekho, kya dq khali to nahi hai, and agar , window ka size mera jyada hogya hai, to
            dq ke top se jo bhi hai wo hata do.. --> isse, we ensure that , size humesha maintained 
            rahe window ka hamari */
            while(!dq.isEmpty() && dq.peekFirst()<=i-k){
                dq.pollFirst(); // note hum log, phele TOP wale index se hata dengay
            }

            /*Now, we will ensure the monotonic nature of our deque , that is , humesha top par , sabse bade
            element ka hi index aaye, jaise hi  koi aur bada number milega 
                        --> to dq ke back se, sab hata dengay , kyuki un indexes ( values) ka koi bhi ratti bhar 
                            contribution nahi hai */
            while(!dq.isEmpty() && nums[i]>=nums[dq.peekLast()]){
                dq.pollLast();
            }
            ///current index ko dq mei daal do, as a potential answer
            dq.offerLast(i);

            /*Ab humko pta hai, answer tab hi milna chalu huyengay , jab apna i==k-1 hojaye, ya usse jyada */
            if(i>=k-1){
                res[idx++]= nums[dq.peekFirst()]; //dq ke top mei humesha maximum rhega
            }
    
        }
        return res;
    }
}


