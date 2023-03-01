package graph;

import graphBase.Edge;
import graphBase.Graph;
import graphBase.Node;

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
* 从并查集的角度考虑，可以便于理解 Kruskal算法
* 视频 暴力递归 1:47:00
* */
public class Code_04_Kruskal {
    public static class UnionFind{
        private HashMap<Node, Node> fatherMap;
        //这里的sizeMap存储的父节点包含的边的数量，而不是父节点的树的深度,如果是深度，初始值为0，并且合并时加一即可
        private HashMap<Node,Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes){
            fatherMap.clear();
            sizeMap.clear();
            for(Node node:nodes){
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }
        private Node findFather(Node n){
            Stack<Node> path = new Stack<>();
            // 如果 n 不以 n本身为父节点
            while (n != fatherMap.get(n)){
                path.push(n);
                //将 n 指向 n的父节点，并在回到开始判断 n是不是以n本身为父节点（是的话就意味着到了最终的父节点，否则就不是最终父节点）
                n=fatherMap.get(n);
                //经过不断的循环以后，一定可以找到父节点，此时循环过后栈中的头节点就一定是最终父节点，而n也是头节点
            }
            while (!path.isEmpty()){
                //循环将栈中的节点出栈（其中包括许多子节点），将这些节点都指向最终的父节点[路径压缩]
                fatherMap.put(path.pop(),n);
            }
            return n;
        }
        public boolean isSameSet(Node a,Node b){
            return findFather(a) == findFather(b);
        }
        public void union(Node a, Node b){
            if(a == null || b == null){
                return;
            }
            Node aDai = findFather(a);
            Node bDai = findFather(b);
            if(aDai != bDai){
                int aSetSize = sizeMap.get(aDai);
                int bSetSize = sizeMap.get(bDai);
                if(aSetSize <= bSetSize){
                    fatherMap.put(aDai,bDai);
                    sizeMap.put(bDai,aSetSize+bSetSize);
                    sizeMap.remove(aDai);
                }else {
                    fatherMap.put(bDai,aDai);
                    sizeMap.put(aDai,aSetSize+bSetSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }
    public static class EdgeComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
    public static Set<Edge> kruskalMST(Graph graph){
        /*
        * 需要注意的是，该算法是基于有向图，所以返回的边也是有向边，如果传入的是无向图（传入的每条边都有相同或相反方向），则返回的每条有向边都少一条相反
        * 方向的有向边，需要后面再补上
        * */
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for(Edge edge : graph.edges){
            priorityQueue.add(edge);
        }
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()){
            Edge edge = priorityQueue.poll();
            if(!unionFind.isSameSet(edge.from, edge.to)){
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }
}
