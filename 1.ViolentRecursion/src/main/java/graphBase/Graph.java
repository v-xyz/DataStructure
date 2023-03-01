package graphBase;

import java.util.HashMap;
import java.util.HashSet;

/*
* Graph 是对 '图' 的数据结构的描述
* */
public class Graph {
    /*
    * 点集
    * Integer 表示编号, 比如：编号为 0 的点是.. 编号为 1 的点是..
    * */
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges; //边集

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
