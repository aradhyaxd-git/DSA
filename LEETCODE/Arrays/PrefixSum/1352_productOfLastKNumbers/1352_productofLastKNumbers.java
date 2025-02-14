/* 
1352. Product of the Last K Numbers

Design an algorithm that accepts a stream of integers and retrieves the product of 
the last k integers of the stream.

Implement the ProductOfNumbers class:

ProductOfNumbers() Initializes the object with an empty stream.

void add(int num) Appends the integer num to the stream.

int getProduct(int k) Returns the product of the last k numbers in the 
current list. You can assume that always the current list has at least k numbers.

The test cases are generated so that, at any time, the product of any contiguous 
sequence of numbers will fit into a single 32-bit integer without overflowing. */


//APPROACH 1: BRUTEFORCE: JO bola hai wahi kardo
class ProductOfNumbers1 {
    public List<Integer> nums;
    int n;
    public ProductOfNumbers1() {
        nums= new ArrayList<>();
        n=0;
    }
    public void add(int num) {
        nums.add(num);    
    }
    public int getProduct(int k) {
        int product=1;
        n= nums.size();
        for(int i=n-k;i<n;i++){
            product*=nums.get(i);
        }
        return product; 
    }
}

/*Approach 2: Optimal TC:0(1) */

/*What we did?? 
Procedure :   -> SUppose take : [2,3,5,10,4]   k=2
                list will be:   [2,6,30,300,1200]
                ___________________________________
                our result will be List ke last index pe jo Hai / list ke index[n-k-1] par
                -->   1200/30 = 40

    Base Case 1:    If We Ever encountered 0 like [4,5,2,0,0,1]   k=3
                    We will update product to 1 again and clear all the entries when we encounter 0
                    so if k > nums.size() then we simply return 0

    Base Case 2:    If  k == nums.size()  then we simply return the ans like
                        [2,6,30,300,1200]   so if n=5 , we return 1200
                        */

class ProductOfNumbers {
    public List<Integer> list;
    int product;

    public ProductOfNumbers() {
        list= new ArrayList<>();
        product=1;
        
    }
    
    public void add(int num) {
        if(num==0){
            list= new ArrayList<>();
            product=1;
            return;
        }
        product=product*num;
        list.add(product);
    }
    
    public int getProduct(int k) {
        int n= list.size();
        if(n<k) return 0;
        int ans= list.get(n-1);
        if(n==k) return ans;

        return ans/list.get(n-k-1);
        
        
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
 
