package graphDescription;

import java.util.ArrayList;

public class Node {
    //不要漏掉这个定义
    public int value; //代表这个Node的编号或者id

    public int out;
    public int in;
    //public ArrayList<Node> inArr; 不需要inArr
    public ArrayList<Node> outArr; //表示从自己出发的直接邻居

    //不要漏掉这个定义
    public ArrayList<Edge> edges; //表示从这个点出发的边的集合

    public Node(int value) {
        this.value = value;
        out = 0;
        in = 0;
        outArr = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
