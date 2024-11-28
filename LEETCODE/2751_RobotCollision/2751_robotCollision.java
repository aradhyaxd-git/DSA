class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n=positions.length;
        // now, we create an array to Store the actual indexes of robots 
        Integer[] actualIndex= new Integer[n];
        for(int i=0;i<n;i++){
            actualIndex[i]=i;
        }// now , we sort the array using a comparator operator
        Arrays.sort(actualIndex,(i,j)->Integer.compare(positions[i],positions[j]));
        Stack<Integer> stack= new Stack<>(); //as we need a stack for simulation type shit
        for(int currentIndex:actualIndex){
            if(directions.charAt(currentIndex)=='R') stack.push(currentIndex);
            else {
                while(!stack.empty() && healths[currentIndex]>0){
                    int topIndex= stack.pop(); // get the last R
                    if(healths[topIndex]>healths[currentIndex]){
                        healths[topIndex]-=1;
                        healths[currentIndex]=0;
                        stack.push(topIndex);
                    }
                    else if(healths[topIndex]<healths[currentIndex]){
                        healths[currentIndex]-=1;
                        healths[topIndex]=0;
                    }
                    else {
                        healths[currentIndex]=0;
                        healths[topIndex]=0;
                    }
                }
            }
        }
        List<Integer> ans = new ArrayList<>(); //arraylist to get answers
        for(int i=0;i<n;i++){
            if(healths[i]>0) ans.add(healths[i]);
        }
        return ans;

        
    }
}