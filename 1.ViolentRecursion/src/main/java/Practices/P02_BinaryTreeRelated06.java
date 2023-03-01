package Practices;

/**
 * 给定一个二叉树的头节点，判断这颗树是不是满二叉树。
 */
public class P02_BinaryTreeRelated06 {
    static class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
        }
    }

    public static boolean isFull(Node head){
        if (head == null) {
            return true;
        }
        Info all = process(head);
        // 1 << n 表示 1 左移 n 位，即 2 的 n次方
        return (1 << all.height) - 1 == all.nodes;//Math.pow(2, all.height) - 1 == all.nodes;
    }

    /*
    * 假设一个二叉树是满二叉树，则其具有以下性质：
    * 对于一个高度为 L, 节点数为 N 的满二叉树，满足： 2 的 L 次方 - 1 = N
    *
    * 所以总结出信息 Info为：
    * 左右树要求的信息一样，要求：
    * 1）树的高度
    * 2）树的节点数
    * */
    public static class Info{
        public int height;
        public int nodes;
        public Info(int h, int n){
            height = h;
            nodes = n;
        }
    }
    public static Info process(Node X){
        if (X == null) {
            return new Info(0,0);
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info(height,nodes);
    }
}
