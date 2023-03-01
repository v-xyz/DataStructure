package graph;

import graphBase.Graph;
import graphBase.Node;

import java.util.*;

/*
* ----------------------------有向无环图排序问题----------------------------------
* 拓扑排序（Topological Sorting）是一个有向无环图（DAG, Directed Acyclic Graph）的所有顶点的线性序列。
* 拓扑排序通常用来“排序”具有依赖关系的任务。比如，如果用一个DAG图来表示一个工程，其中每个顶点表示工程中的一个任务，
* 用有向边 表示在做任务 B 之前必须先完成任务 A。故在这个工程中，任意两个任务要么具有确定的先后关系，
* 要么是没有关系，绝对不存在互相矛盾的关系（即环路）。
* */
public class Code_03_TopLogicalSorting {
    /*
    * 拓扑排序的思路是：找到入度为0的节点，然后加入结果的 list集（也可以打印）并删除该节点和该节点所有边，
    * 随后对图上剩下的节点依次进行上述的判断
    * */
    public static List<Node> sortedTopology(Graph graph){
        HashMap<Node,Integer> inMap = new HashMap<>();
        // 入度为 0 的点才能进这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        for(Node node : graph.nodes.values()){
            // inMap加入所有节点以及其入度值，以便后续更新
            inMap.put(node,node.in);
            if(node.in == 0){
                zeroInQueue.add(node);
            }
        }
        // 拓扑排序的结果依次加入 resultLst
        List<Node> resultLst = new ArrayList<>();
        while (!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            resultLst.add(cur);
            for(Node next : cur.nexts){
                //更新节点在 inMap中的入度 in的值
                inMap.put(next,inMap.get(next)-1);
                //如果有任何子节点入度变为0，则加入list
                if(inMap.get(next) == 0){
                    zeroInQueue.add(next);
                }
            }
        }
        return resultLst;
    }
}
