package graphBase;

import java.util.ArrayList;

/*
* 图：无向图,有向图,权重. 表示方法：邻接表,邻接矩阵
*
* 以下 GraphBase.Node 是对 '图' 中的 '点' 的数据结构的描述[如果是无向图，则入读in 和 out就是相等的]
* */
public class Node {
    /*
    * 也可以是 String类型，代表这个点的编号或者说id
    * */
    public int value;
    /*
    * in 表示入度【表示有多少个点是连向这个节点的】
    * */
    public int in;
    /*
    * out 表示出度【表示自己有多少边出去】
    * */
    public int out;
    /*
    * nexts 表示自己的直接邻居【由自己节点出发能到的直接邻居有谁，入度不包括在内】,其 size 值为 out
    * */
    public ArrayList<Node> nexts;
    /*
    * edges 表示由这个节点直接出发的边的集合
    * */
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
