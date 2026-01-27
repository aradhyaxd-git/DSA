class MyCalendarThree {
    TreeMap<Integer,Integer> map;
    public MyCalendarThree() {
        map= new TreeMap<>();
        
    }
    
    public int book(int startTime, int endTime) {
        map.put(startTime,map.getOrDefault(startTime,0)+1);
        map.put(endTime,map.getOrDefault(endTime,0)-1);
        int maxK=0;
        int ongoing=0;
        for(int delta: map.values()){
            ongoing+=delta;
            maxK= Math.max(maxK,ongoing);
        }
        return maxK;
    }
}