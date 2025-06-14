/*4. Median of Two Sorted Arrays

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)). */

/*Approach 1: Bruteforce : simple maths 
TC: O(M+N) 
SC: O(M+N) */
class Solution1 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m= nums1.length;
        int n= nums2.length;
        int temp[]= new int[m+n];
        int i=0,j=0;
        int idx=0;
        while(i<m && j<n){
            if(nums1[i]<=nums2[j]){
                temp[idx++]=nums1[i++];
            }
            else{
                temp[idx++]=nums2[j++];
            }
        }
        while(i<m){
            temp[idx++]=nums1[i];
            i++;
        }
        while(j<n){
            temp[idx++]=nums2[j++];
        }

        int size=m+n;
        return size%2==1?temp[size/2]:(temp[size/2]+temp[size/2 -1])/2.0;
    }
}



/*Approach 2: Better
TC: O(M+N) 
SC: O(1) 
Instead of temporary array, we just store the element and idx */
class Solution2 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m= nums1.length;
        int n= nums2.length;
        int size= m+n;

        int idx1= size/2-1; //since we just  need index
        int element1= -1;

        int idx2= size/2; //since we just need index
        int element2=-1;

        int k=0;
        int i=0,j=0;


    
        while(i<m && j<n){
            if(nums1[i]<=nums2[j]){
                if(k==idx1)
                    element1=nums1[i];
                if(k==idx2)
                    element2=nums1[i];
                i++;
            }
            else{
                if(k==idx1)
                    element1=nums2[j];
                if(k==idx2)
                    element2=nums2[j];
                j++;
            }
            k++;
            
        }
        while(i<m){
            if(k==idx1)
                element1=nums1[i];
            if(k==idx2)
                element2=nums1[i];
            i++;
            k++;
        }
        while(j<n){
            if(k==idx1)
                element1=nums2[j];
            if(k==idx2)
                element2=nums2[j];
            j++;
            k++;
        }
        return size%2==1?element2:(element1+element2)/2.0;
    }
}

/*MOST OPTIMAL SOLUTION: TC: O(log(Math.min(nums1,nums2))) */

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m= nums1.length;
        int n= nums2.length;

        if(m>n){ //this ensures ki binary search humemsha chote se choti array par lage taaki complexity kam aye
            return findMedianSortedArrays(nums2,nums1); //agar pheli array badi hai ... swap kar do , taaki pheli choti hojaye 
        }

        int low=0,high=m; //low=0  matlab left mei koi bhi element nahi lia 
                        //high=m   matlab puri array left mei hai

        while(low<=high){
            //mid index par cut kar rhe pheli arrray ko 
            int Px= low+(high-low)/2; //mid from nums1
            int Py=  (m+n+1)/2 - Px; //from nums2 --> usi hisab se dusri array ko bhi cut kar rhe


            //left half wale ke liye
            //agar cut starting par hai --> left mei kuch nahi hai --> min VAlue
            int x1=  (Px == 0)?Integer.MIN_VALUE:nums1[Px-1];
            int x2=  (Py == 0)?Integer.MIN_VALUE:nums2[Py-1];

            //right half wale --> agar cut last mei hai--> right mei kuch nhi hai --> max value
            int x3=  (Px == m)? Integer.MAX_VALUE:nums1[Px];
            int x4=  (Py == n)? Integer.MAX_VALUE:nums2[Py];

            if(x1<= x4 && x2<=x3){
                
                //agar length odd hai , to median humesha left side ka maximum hoga
                if((m+n)%2==1){ //left side ka max
                    return Math.max(x1,x2);
                }
                //yaha par odd wala length hai
                return (Math.max(x1,x2)+Math.min(x3,x4))/2.0;
            }
            
            //agar num1 ke left ka first element bada hai nums2 ke right ke second part se , iska mtlb hum logo ne partition banaaya, wo galat hai 

            //x1 bada hai -->it means that, nums1 ke left mei jyada bade elements aagye hai, to unko hatao--> hence, move left
            if(x1>x4){
                high=Px-1;
            }
            else{ //cut ko right move karddo 
                low=Px+1;
            }
        }
        return -1;
         
        
    }
}