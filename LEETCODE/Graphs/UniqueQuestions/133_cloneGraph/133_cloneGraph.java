/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    /*
    *  Map ka role: 
        original node -> uska ek unique copy store karta hai 

        */
    Map<Node,Node> map= new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node==null) return null;

        return dfs(node);    
    }

    private Node dfs(Node node){
        //same node ko baar baar copy ni kar sakte hai , inorder to avoid cycles i use a hashmap

        /*AGAR , ye node already cloned ho chuka hai , 
        to zrurat to hai nahi kuch karne ki, 
        tum already wahi existing clone node return maardo */
        if(map.containsKey(node)) return map.get(node);

        Node clone= new Node(node.val);
        map.put(node,clone);

        for(Node neighbour: node.neighbors){
            /*dfs will either
                --> make a new cloned node
                --> or map se existing clone dega
                i will trust my recursion and have faith in it ki ye mujhe kaam karke dedega */
            clone.neighbors.add(dfs(neighbour));
        }

        return clone;
    }
}



class Solution2 {
    public Node cloneGraph(Node node) {

        Map<Node,Node> map = new HashMap<>();
        Queue<Node> queue= new LinkedList<>();

        if(node==null) return null;

        //starting node ka clone banao, and add it in map 
        Node clone= new Node(node.val);
        map.put(node,clone);
        queue.offer(node);


        while(!queue.isEmpty()){
            Node curr= queue.poll();

            for(Node neighbour: curr.neighbors){
                //agar neighbour ka clone abhi tak nahi bana hai, to banao aur queue mei daalo
                if(!map.containsKey(neighbour)){
                    map.put(neighbour,new Node(neighbour.val));
                    queue.offer(neighbour);
                }
                //agar clone bana hai 
                map.get(curr).neighbors.add(map.get(neighbour));
            }
        }
        
        return clone;
    }
}

