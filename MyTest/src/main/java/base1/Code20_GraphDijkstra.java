package base1;

import graphDescription.Edge;
import graphDescription.Node;

import java.util.HashMap;

/**
 * 对于图上给定的出发点，求到图其他节点的最小距离
 */
public class Code20_GraphDijkstra {
    public static HashMap<Node,Integer> dijkstra(Node head, int size){
        MinHeap nodeHeap = new MinHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head,0);
        HashMap<Node,Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()){
            Info info = nodeHeap.pop(); //从小根堆弹出最小的
            Node cur = info.node;
            int distance = info.minDistance;
            for (Edge edge: cur.edges) {
                /*
                * 以 cur作为桥连点，看看能不能更新 endNode的记录，
                * 如果 endNode的记录的记录之前就没有，就add
                * 如果 endNode的记录的记录之前就有，且出发节点到 endNode距离更短，就update
                * 如果 endNode的记录的记录之前就有,且距离不是更短，就ignore
                * */
                nodeHeap.addOrUpdateOrIgnore(edge.endNode, edge.weight + distance);
            }
            result.put(cur,distance);
        }
        return result;

    }
    //Info类代表一条 node + 最小distance组成的信息记录
    public static class Info{
        public Node node; //当前节点是什么
        public int minDistance;  //出发节点到当前节点的最小距离是多少
        public Info(Node node,int distance){
            this.node = node;
            this.minDistance = distance;
        }
    }
    /*
    * 之前的堆结构是int作为节点，int表示的值作为比较单位，即自己作为节点也是自己作为比较单位
    * 但是在本次堆结构中，是图的 Node作为节点，distance作为比较单位，所以需要额外的Map来
    * 实现堆结构
    * */
    public static class MinHeap{

        private Node[] heapArray;
        private final int limit;
        private int heapCount;
        private HashMap<Node,Integer> minDistanceMap; //表示从给定的节点出发到 node的最小距离(value)的Map

        /*
         * MinHeap需要具有这样一个功能:能够找到并比较堆中某个节点的distance值并更新堆中该节点的distance
         * 所以需要一个Map结构帮助找到节点在堆中所处的位置,这就是 indexMap的作用
         *
         * indexMap 记录着对于给定的样本，样本中的每个值在堆中所处的位置
         * 如果 value 是 -1，表示该节点进过堆，但后续被弹出了
         * */
        private HashMap<Node,Integer> indexMap;

        public MinHeap(int size){
            heapArray = new Node[size];
            this.limit = size;
            heapCount = 0;
            minDistanceMap = new HashMap<>();
            indexMap = new HashMap<>();
        }
        public boolean isEmpty(){
            return heapCount == 0;
        }
        //isInHeap 方法专门判断节点是否在堆中
        public boolean isInHeap(Node node){
            if (indexMap.containsKey(node)){
                if(indexMap.get(node) != -1){
                    return true;
                }
            }
            return false;
        }
        public void addOrUpdateOrIgnore(Node node,int distance){ //传入的node有一个新的距离是distance
            if(heapCount == limit){
                throw new RuntimeException("heap is full");
            }
            if(isInHeap(node)){
                //更新最小距离
                minDistanceMap.put(node,Math.min(minDistanceMap.get(node),distance));
                //此时该位置的节点有可能变得更小，所以不管是不是更小都要在该位置进行一次小根堆位置的调整[只可能更小，不可能变大]
                adjustHeapFromIndex(indexMap.get(node));
            }else {
                heapArray[heapCount] = node;
                minDistanceMap.put(node,distance);
                indexMap.put(node,distance);
                adjustHeapFromIndex(heapCount);
                heapCount++;
            }

        }
        private void adjustHeapFromIndex(int index){
            while (minDistanceMap.get(index) < minDistanceMap.get((index - 1) / 2)){
                swap(heapArray,index,(index - 1) / 2);
                index = (index - 1) / 2;
            }
        }
        private void swap(Node[] arr, int i,int j){
            Node o1 = arr[i];
            Node o2 = arr[j];
            arr[i] = o2;
            arr[j] = o1;
            //indexMap也要更新
            indexMap.put(o1,j);
            indexMap.put(o2,i);
        }

        public Info pop() {
            Info oldTop = new Info(heapArray[0], minDistanceMap.get(heapArray[0]));

            swap(heapArray,0,heapCount - 1);
            //删除相关信息
            indexMap.put(heapArray[heapCount - 1], -1);
            minDistanceMap.remove(heapArray[heapCount - 1]);
            heapArray[heapCount - 1] = null;
            heapCount--;

            int curIndex = 0;
            int leftIndex = 1; //从第一个左边位置开始。(知道左位置就知道了右位置)
            while (leftIndex < heapCount){
                int rightIndex = leftIndex + 1;
                int minIndex;
                if(rightIndex < heapCount){
                    minIndex = minDistanceMap.get(heapArray[leftIndex]) < minDistanceMap.get(heapArray[rightIndex]) ? leftIndex : rightIndex;
                }else {
                    minIndex = leftIndex; //如果 rightIndex越界，那么只有取 leftIndex的值
                }
                if(minDistanceMap.get(heapArray[curIndex]) > minDistanceMap.get(heapArray[minIndex])){
                    swap(heapArray,curIndex,minIndex);
                    curIndex = minIndex;
                    leftIndex = leftIndex * 2 + 1;
                }else {
                    break;
                }
            }
            return oldTop;
        }
    }
}
