/*1514. Path with Maximum Probability

You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list 
where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of
 success of traversing that edge succProb[i].

Given two nodes start and end, find the path with the maximum probability of success to go from 
start to end and return its success probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from 
the correct answer by at most 1e-5.

  */
//APPROACH: DIJKASTRA IMPLEMENTATION VARIATION
class Solution {
    public class Pair{
        int node;
        double prob;
        Pair(int node,double prob){
            this.node=node;
            this.prob=prob;
        }
    }
    //comparator class banante hai fir comparaotr banate hai, just to compare values
    public class MaxHeapComparator implements Comparator<Pair>{
        public int compare(Pair a,Pair b){
            return Double.compare(b.prob,a.prob);
        }
    }


    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<List<Pair>> adj= new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<edges.length;i++){
            int u= edges[i][0];
            int v= edges[i][1];
            double prob= succProb[i];
            adj.get(u).add(new Pair(v,prob));
            adj.get(v).add(new Pair(u,prob));
        }
        double result[]= new double[n];
        Arrays.fill(result,0.0);
        result[start_node]=1.0;

        PriorityQueue<Pair> pq= new PriorityQueue<>(new MaxHeapComparator());
        pq.offer(new Pair(start_node,1.0));

        while(!pq.isEmpty()){
            Pair curr= pq.poll();
            int node= curr.node;
            double probability= curr.prob;
            if(probability<result[node]) continue;
            for(Pair neighbour: adj.get(node)){
                int v= neighbour.node;
                double prob= neighbour.prob;
                double new_prob= probability*prob;
                if(new_prob>result[v]){
                    result[v]=new_prob;
                    pq.offer(new Pair(v,new_prob));
                }
            }
        }
        return result[end_node];

    }
}

