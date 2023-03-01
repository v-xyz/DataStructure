package graph;

import graphBase.Edge;
import graphBase.Node;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 假设图上任意一条边的权值都为正数，对于给定的出发点，求其到所有节点的最短距离(节点和自身距离为0，如果有节点无法到达，则
 * 表示其距离为正无穷)
 */
public class Code_06_Dijkstra {
    /*
    * 算法实现思路：【此问题的暴力方法是深度优先遍历，但这里介绍更好的 Dijkstra算法】
    * 视频：12-动态规划 1:01:10
    * 假设图中有五个点(A,B,C,D,E),选定起始点为 A
    * 假设有这样一张图：
    *      A     B     C     D     E
    *  A   0     1     7     6    +无穷
    *  B  +无穷   0     2    +无穷  170
    *  C  +无穷  +无穷   0     2    23
    *  D  +无穷  +无穷  +无穷   0    4
    *  E  +无穷  +无穷  +无穷  +无穷  0
    *
    * 先指定一个初始表-A表【表示从 A 出发的表】
    *    A     B     C     D     E
    *    0   +无穷  +无穷  +无穷  +无穷
    * 然后分析从 A 出发到其他节点有几条边，每条边的距离是多少,更新表并封锁A(以后不再考虑A作为出发节点)
    *    A     B     C     D     E
    *    0     1     7     6    +无穷
    * 发现 A -> B 的距离最短 1, 于是选择 B 为出发点，有两条边 B -> C 和 B -> E,发现A->B->C的距离为 1+2 = 3 < 7(A->C)
    * 且 B -> E = 170,此时 A->E = A->B + 170 = 171 < +无穷, 更新表并封锁B
    *    A     B     C     D     E
    *    0     1     3     6    171
    * 发现 A -> C 的距离最短 3, 于是选择 C 为出发点,有两条边 C -> D 和 C -> E,发现A->B->C->D的距离为 1+2+2 = 5 < 7(A->D)
    * 且 C -> E = 23 ,此时 A->E = A->C + 23 = 26 < 171, 更新表并封锁C
    *    A     B     C     D     E
    *    0     1     3     5     26
    * 发现 A -> D 的距离最短 5, 于是选择 D 为出发点,有一条边 D -> E,发现A->B->C->D->E的距离为 1+2+2+4 = 9 < 26
    * 更新表并封锁 D,最后没有点可作为出发点，得到最终表
    *    A     B     C     D     E
    *    0     1     3     5     9
    * 实现思路：可以通过将表中各值放入小根堆，并能保证过程中可以修改小根堆中相应的值
    * 所以系统提供的小根堆无法做到，需要自己实现小根堆.
    * */
    public static HashMap<Node,Integer> dijkstra(Node head, int size){
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head,0);
        HashMap<Node,Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()){
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge: cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur,distance);
        }
        return result;
    }
    public static class NodeRecord{
        public Node node;
        public int distance;
        public NodeRecord(Node node,int distance){
            this.node = node;
            this.distance = distance;
        }
    }

    //参考heapBase2中的算法
    public static class NodeHeap{

        private Node[] nodes;
        // indexMap 记录着对于给定的样本，样本中的每个值在堆中所处的位置
        // 如果 value 是 -1，表示该节点进过堆，但后续被弹出了
        private HashMap<Node,Integer> indexMap;
        private int heapSize;

        private HashMap<Node,Integer> distanceMap;


        public NodeHeap(int heapSize){
            nodes = new Node[heapSize];
            indexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.heapSize = 0;
        }
        public boolean isEmpty(){
            return heapSize == 0;
        }

        //表示一个节点是否进过堆
        public boolean contains(Node key){
            return indexMap.containsKey(key);
        }
        //表示一个节点是否在堆上
        private boolean inHeap(Node node){
            return contains(node) && indexMap.get(node) != -1;
        }

        public void addOrUpdateOrIgnore(Node node,int distance){ //传入的node有一个新的距离是distance
            //如果有一个节点被弹出，那么这个节点就不能动了

            if(inHeap(node)){
                //update为较小的值
                distanceMap.put(node,Math.min(distanceMap.get(node),distance));
                heapInsert(indexMap.get(node));
            }
            //add
            if(!contains(node)){
                nodes[heapSize] = node;
                indexMap.put(node,heapSize);
                distanceMap.put(node,distance);
                heapInsert(heapSize++);
            }
        }
        public NodeRecord pop(){
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            int end = heapSize - 1;
            swap(0,end);
            indexMap.put(nodes[end],-1);
            distanceMap.remove(nodes[end]);
            nodes[end] = null;
            heapify(0,--heapSize);
            return nodeRecord;
        }
        public void resign(Node value){
            int valueIndex = indexMap.get(value);
            heapInsert(valueIndex);
            heapify(valueIndex,heapSize);
        }
        private void heapInsert(int index){
            while (distanceMap.get(nodes[index])<distanceMap.get(nodes[(index - 1)/ 2])){
                swap(index,(index - 1)/ 2);
                index = (index - 1)/ 2;
            }
        }
        private void heapify(int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize){
                int smallest = left + 1 < heapSize &&
                        distanceMap.get(nodes[left + 1])
                                < distanceMap.get(nodes[left]) ? left + 1: left;
                smallest = distanceMap.get(nodes[smallest])
                        < distanceMap.get(nodes[index]) ? smallest : index;
                if(smallest == index){
                    break;
                }
                swap(smallest,index);
                index = smallest;
                left = index * 2 + 1;
            }
        }
        private void swap(int i,int j){
            Node o1 = nodes[i];
            Node o2 = nodes[j];
            nodes[i] = o2;
            nodes[j] = o1;
            indexMap.put(o1,j);
            indexMap.put(o2,i);
        }
    }
}
