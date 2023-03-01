package base1;

import graphDescription.Edge;
import graphDescription.Graph;
import graphDescription.Node;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Code17_GraphPrim {
    public static Set<Edge> primMST(Graph graph){
        HashSet<Node> nodeSet = new HashSet<>();
        HashSet<Edge> edgeSet = new HashSet<>();
        Set<Edge> result = new HashSet<>();

        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>(new EdgeComparator());
        for(Node node : graph.nodes.values()){
            if(!nodeSet.contains(node)){
                nodeSet.add(node);
                for(Edge edge : node.edges){ //保证边不能重复添加
                    if(!edgeSet.contains(edge)){
                        edgeSet.add(edge);
                        edgePriorityQueue.add(edge);
                    }
                }
                while (!edgePriorityQueue.isEmpty()){
                    Edge edge = edgePriorityQueue.poll();
                    Node toNode = edge.endNode;
                    if(!nodeSet.contains(toNode)){
                        nodeSet.add(toNode);
                        result.add(edge);
                        for(Edge nextEdge : toNode.edges){
                            if(!edgeSet.contains(nextEdge)){
                                edgeSet.add(nextEdge);
                                edgePriorityQueue.add(nextEdge);
                            }
                        }
                    }
                }

            }
        }
        return result;
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
}
