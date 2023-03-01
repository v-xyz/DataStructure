package graphBase;

/*
* GraphBase.Edge 表示对 '边' 的数据结构的描述
* 这里的 GraphBase.Edge 是对有向图的边的结构描述,无向图只需要将 from to 合并即可
* */
public class Edge {
    /*
    * weight 表示权重
    * */
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
