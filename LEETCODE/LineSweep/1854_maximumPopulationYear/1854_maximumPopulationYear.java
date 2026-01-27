class Solution {
    public int maximumPopulation(int[][] logs) {
        List<int[]> events= new ArrayList<>();
        for(int log[]: logs){
            events.add(new int[]{log[0],+1});
            events.add(new int[]{log[1],-1});
        }
        Collections.sort(events,(a,b)->{
            if(a[0]==b[0]) return a[1]-b[1];
            return a[0]-b[0];
        });
        int currPopulation=0; int maxPopulation=0,minYear=2055;
        for(int[] event: events){
            currPopulation+= event[1];
            if(currPopulation> maxPopulation){
                maxPopulation= currPopulation;
                minYear= event[0];
            }
        }
        return minYear;
        
    }
}