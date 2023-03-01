package Practices;

/**
 * 题目：给定一个二叉树的头节点 head,任何两个节点之间都存在距离，返回整颗二叉树的
 * 最大距离
 */
public class P02_BinaryTreeRelated03 {
    static class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;
        public Node(T value){
            this.value = value;
        }
    }

    public static int maxDistance(Node head){
        return process(head).maxDistance;
    }

    /*
    * 分析：
    * 假设头节点 head 为 X,那么存在以下两种情况：
    * 1）最大距离与 X 无关，在左子树或右子树产生最大距离
    * 2）最大距离与 X 有关，即左子树与右子树离 X 最远的点的相距的距离【左右树的高度+1】
    *
    * 总结出信息 S为：
    * 左右树要求的信息一样，要求：
    * 1）整颗树的最大距离
    * 2）树的高度
    * */
    public static class Info{
        public int maxDistance;
        public int height;
        public Info(int dis, int h){
            maxDistance = dis;
            height = h;
        }
    }

    public static Info process(Node X){
        if (X == null) {
            return new Info(0,0);
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);

        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        /*
        * 最大距离取
        *   左右树中的最大距离的较大值
        *   和
        *   左右树之和加1[最大距离与X有关]
        * 比较过后的较大值
        * */
        int maxDistance = Math.max(
                Math.max(leftInfo.maxDistance,rightInfo.maxDistance),
                leftInfo.height + rightInfo.height + 1);
        return new Info(maxDistance,height);
    }
}
