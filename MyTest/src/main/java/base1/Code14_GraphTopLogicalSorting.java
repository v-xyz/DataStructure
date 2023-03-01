package base1;

import graphDescription.Graph;
import graphDescription.Node;

import java.util.*;

/*
 * ----------------------------拓扑排序----------------------------------
 * 拓扑排序（Topological Sorting）是一个有向无环图（DAG, Directed Acyclic Graph）的所有顶点的线性序列。
 * 拓扑排序通常用来“排序”具有依赖关系的任务。比如，如果用一个DAG图来表示一个工程，其中每个顶点表示工程中的一个任务，
 * 用有向边 表示在做任务 B 之前必须先完成任务 A。故在这个工程中，任意两个任务要么具有确定的先后关系，
 * 要么是没有关系，绝对不存在互相矛盾的关系（即环路）。
 * */
public class Code14_GraphTopLogicalSorting {
    public static List<Node> sortedTopology(Graph graph){
        HashMap<Node,Integer> inValuesMap = new HashMap<>();
        Queue<Node> allStartNode = new LinkedList<>();
        List<Node> resultLst = new ArrayList<>();
        for (Node node: graph.nodes.values()) {
            inValuesMap.put(node, node.in);
            if(node.in == 0){
                allStartNode.add(node);
                //resultLst.add(node);
            }
        }
        while (!allStartNode.isEmpty()){
            Node cur = allStartNode.poll();
            resultLst.add(cur);
            for (Node next: cur.outArr) {
                int inValue = inValuesMap.get(next) - 1;
                inValuesMap.put(next,inValue);
                if(inValue == 0){
                    allStartNode.add(next);
                    //resultLst.add(next);
                }
            }
        }
        return resultLst;
    }
}
