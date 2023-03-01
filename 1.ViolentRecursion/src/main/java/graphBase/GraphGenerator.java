package graphBase;

public class GraphGenerator {
    /*
    * 输入一个 n*3 的矩阵，即 [weight权重,from节点的编号,to节点的编号]
    * */
    public static Graph createGraph(Integer[][] matrix){
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            //初始化
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];

            Node fromNode = new Node(from);
            Node toNode = new Node(to);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            Edge newEdge = new Edge(weight,fromNode,toNode);
            fromNode.edges.add(newEdge);

            graph.edges.add(newEdge);
            //判断 from 和 to 是否已经包含在图中,没有的话就添加
            if(!graph.nodes.containsKey(from)){
                graph.nodes.put(from,fromNode);
            }
            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(to,toNode);
            }
        }
        return graph;
    }
}
