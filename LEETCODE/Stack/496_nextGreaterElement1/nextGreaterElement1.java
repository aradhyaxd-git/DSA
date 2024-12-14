public class nextGreaterElement1 {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            Map<Integer,Integer> map= new HashMap<>();
            Stack<Integer> st= new Stack<>();
            int n=nums1.length;
            int ans[]= new int[n];
            for(int num:nums2){
                while(!st.empty() && st.peek()<num){
                    map.put(st.pop(),num);
                }
                st.push(num);
            }
            for(int i=0;i<n;i++){
                ans[i]= map.getOrDefault(nums1[i],-1);
            }
            return ans;
        }
    }
