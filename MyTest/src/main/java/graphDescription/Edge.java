package graphDescription;

public class Edge {
    //不要漏掉这个定义
    public int weight; //权重

    public Node startNode;
    public Node endNode;

    //全部参数都要外部传入
    public Edge(int weight, Node startNode, Node endNode) {
        this.weight = weight;
        this.startNode = startNode;
        this.endNode = endNode;
    }
}
