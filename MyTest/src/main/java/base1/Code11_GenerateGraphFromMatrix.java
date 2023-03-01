package base1;

import graphDescription.Edge;
import graphDescription.Graph;
import graphDescription.Node;

public class Code11_GenerateGraphFromMatrix {
    /*
     * 输入一个 n*3 的矩阵，即 [weight权重,from节点的编号,to节点的编号]
     * */
    public static Graph createGraph(Integer[][] matrix){
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int fromValue = matrix[i][1];
            int toValue = matrix[i][2];
            Node fromNode  = new Node(fromValue);
            //别忘了初始化Node中的属性
            fromNode.out++;
            Node toNode = new Node(toValue);
            toNode.in++;
            fromNode.outArr.add(toNode);

            Edge edge = new Edge(weight,fromNode,toNode);
            fromNode.edges.add(edge);
            graph.edges.add(edge);
            //判断 from 和 to 是否已经包含在图中,没有的话就添加
            if(!graph.nodes.containsKey(fromValue)){
                graph.nodes.put(fromValue,fromNode);
            }
            if(!graph.nodes.containsKey(toValue)){
                graph.nodes.put(toValue,toNode);
            }

        }
        return  graph;
    }
}
