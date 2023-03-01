package base1;

import graphDescription.Edge;
import graphDescription.Graph;
import graphDescription.Node;

import java.util.*;
/*
* ----------------------------最小生成树算法----------------------------------
* 最小生成树是一副连通加权无向图中一棵权值最小的生成树。
* Kruskal算法
此算法可以称为“加边法”，初始最小生成树边数为0，每迭代一次就选择一条满足条件的最小代价边，加入到最小生成树的边集合里。
1. 把图中的所有边按代价从小到大排序；
2. 把图中的n个顶点看成独立的n棵树组成的森林；
3. 按权值从小到大选择边，所选的边连接的两个顶点(ui,vi),(ui,vi)应属于两颗不同的树，则成为最小生成树的一条边，并将这两颗树合并作为一颗树。
4. 重复(3),直到所有顶点都在一颗树内或者有n-1条边为止。
*
* */
public class Code16_GraphKruskal {
    //返回一个边集
    public static Set<Edge> kruskalMST(Graph graph){
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for(Edge edge : graph.edges){
            priorityQueue.add(edge);
        }
        DisjointUnionSetsForGraph disjointUnionSets =
                new DisjointUnionSetsForGraph(graph.nodes.values());
        Set<Edge> results = new HashSet<>();
        while (!priorityQueue.isEmpty()){
            Edge edge = priorityQueue.poll();
            if(!disjointUnionSets.isSameSet(edge.startNode, edge.endNode)){
                results.add(edge);
                disjointUnionSets.union(edge.startNode, edge.endNode);
            }
        }
        return results;
    }
    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
    public static class DisjointUnionSetsForGraph{
        //不需要包装，每个node表示图中的节点
        private HashMap<Node, Node> parentNode = new HashMap<>();
        private HashMap<Node,Integer> sizeMap = new HashMap<>();
        public DisjointUnionSetsForGraph(Collection<Node> nodes){
            for(Node node:nodes){
                parentNode.put(node,node);
                sizeMap.put(node,1);
            }
        }
        private Node findFather(Node input){
            Stack<Node> path = new Stack<>();
            while (input != parentNode.get(input)){
                path.push(input);
                input = parentNode.get(input);
            }
            while (!path.isEmpty()){
                parentNode.put(path.pop(),input);
            }
            return input;
        }
        public boolean isSameSet(Node a,Node b){
            return findFather(a) == findFather(b);
        }
        public void union(Node a, Node b){
            if(a == null || b == null){
                return;
            }
            Node fatherNodeA = findFather(a);
            Node fatherNodeB = findFather(b);
            if(fatherNodeA != fatherNodeB){
                int sizeA = sizeMap.get(fatherNodeA);
                int sizeB = sizeMap.get(fatherNodeB);
                Node big = sizeA >= sizeB ? fatherNodeA : fatherNodeB;
                Node small = big == fatherNodeA ? fatherNodeB : fatherNodeA;

                parentNode.put(small,big);
                sizeMap.remove(small);

                sizeMap.put(big,sizeA + sizeB);
            }
        }
    }
}
