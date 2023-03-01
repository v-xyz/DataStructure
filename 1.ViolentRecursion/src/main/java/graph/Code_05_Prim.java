package graph;

import graphBase.Edge;
import graphBase.Graph;
import graphBase.Node;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*
* ----------------------------最小生成树算法----------------------------------
* Prim 算法描述：
从任意点开始，解锁相邻的所有边，从边中选择一个最小的边，找到边的另一个端点，判断其是否是之前重复的点，
* 不是则继续从另一个端点开始，解锁所有相邻的边，从边中选择一个最小的边，找到边的另一个端点，判断其是否是之前重复的点
* ...

  * 由于 Prim 是一个点一个点的计算，不涉及两个集合的合并，所以不需要并查集
  * 视频： 暴力递归 2:00:00
* */
public class Code_05_Prim {
    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
    public static Set<Edge> primMST(Graph graph){
        PriorityQueue<Edge> priorityQueue =
                new PriorityQueue<>(new EdgeComparator());
        // set 表示哪些点被解锁出来了
        HashSet<Node> nodeSet = new HashSet<>();
        Set<Edge> result = new HashSet<>();
        // uniqueEdges保证相同的边不会重复添加到 priorityQueue中
        HashSet<Edge> edgeSet = new HashSet<>();
        for(Node node : graph.nodes.values()){
            // Prim 算法对起始点没有要求，所以从给的图中的点开始遍历
            if(!nodeSet.contains(node)){
                nodeSet.add(node);
                //由一个点解锁所有相邻的边
                for(Edge edge : node.edges){
                    if(!edgeSet.contains(edge)){
                        edgeSet.add(edge);
                        priorityQueue.add(edge);
                    }
                }

                while (!priorityQueue.isEmpty()){
                    //弹出解锁的边中最小的边
                    Edge edge = priorityQueue.poll();
                    //可能的一个新的点
                    Node toNode = edge.to;
                    //不包含则表示是新的点
                    if(!nodeSet.contains(toNode)){
                        nodeSet.add(toNode);
                        result.add(edge);
                        for(Edge nextEdge : toNode.edges){
                            if(!edgeSet.contains(nextEdge)){
                                edgeSet.add(nextEdge);
                                priorityQueue.add(nextEdge);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
