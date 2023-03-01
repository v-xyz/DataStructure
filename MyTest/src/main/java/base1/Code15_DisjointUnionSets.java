package base1;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

//并查集
public class Code15_DisjointUnionSets {
    //定义 Node 作为包装类
    public static class Node<T>{
        T value;
        public Node(T value){
            this.value = value;
        }
    }
    public static class DisjointUnionSets<T>{
        public HashMap<T,Node<T>> nodeMap = new HashMap<>(); //将 T 包装成 Node
        public HashMap<Node<T>,Node<T>> parentNode = new HashMap<>(); //记录最终父亲节点
        public HashMap<Node<T>,Integer> sizeMap = new HashMap<>(); //key 存储最终父节点, value记录当前整个集合的深度

        public DisjointUnionSets(List<T> values){
            for (T value : values) {
                Node<T> node = new Node<>(value);
                nodeMap.put(value,node);
                parentNode.put(node,node);
                sizeMap.put(node,1);
            }
        }
        private Node<T> findFather(Node<T> input){
            //使用栈
            Stack<Node<T>> stack = new Stack<>();

            //input不是自己的父节点，证明没来到最终父节点
            while (input != parentNode.get(input)){
                stack.push(input);
                //input来到自己的父节点位置，但还不一定是最终父节点，所以继续判断
                input = parentNode.get(input);
            }
            /*
            * 经过以上判断 input 来到最终父节点位置，stack 存储的是路径上的所有除最终父节点以外的节点，且它们的父节点都不是最终父节点
            * 依次将它们的父节点调整为最终父节点，路径压缩的实现
            * */
            while (!stack.isEmpty()){
                parentNode.put(stack.pop(),input);
            }
            return input;
        }
        public boolean isSameSet(T a, T b){
            if(!nodeMap.containsKey(a) || !nodeMap.containsKey(b)){
                return false;
            }
            Node<T> nodeA = nodeMap.get(a);
            Node<T> nodeB = nodeMap.get(b);
            return findFather(nodeA) == findFather(nodeB);
        }
        public void union(T a, T b){
            if(!nodeMap.containsKey(a) || !nodeMap.containsKey(b)){
                return;
            }
            Node<T> nodeA = nodeMap.get(a);
            Node<T> nodeB = nodeMap.get(b);
            Node<T> fatherNodeA = findFather(nodeA);
            Node<T> fatherNodeB = findFather(nodeB);
            if(fatherNodeA != fatherNodeB){ //确保不是同一个集合才需要合并
                int sizeA = sizeMap.get(fatherNodeA);
                int sizeB = sizeMap.get(fatherNodeB);

                //使用三目运算符找到较大和较小深度节点
                Node<T> big = sizeA >= sizeB ? fatherNodeA : fatherNodeB;
                Node<T> small = big == fatherNodeA ? fatherNodeB : fatherNodeA;

                parentNode.put(small,big);
                sizeMap.remove(small);

                sizeMap.put(big,sizeA + sizeB); //a,b两个深度相加
            }


        }
    }
}
