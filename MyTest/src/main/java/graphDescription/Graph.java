package graphDescription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Graph {
//    public ArrayList<Node> nodes;
//    public ArrayList<Edge> edges;

    public HashMap<Integer, Node> nodes; //Integer 表示编号, 比如：编号为 0 的点是.. 编号为 1 的点是..
    public HashSet<Edge> edges; //边集,边集不能重复

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
