package Practices;

/**
 *  题目：给定一个树的头节点head,返回这颗二叉树是不是平衡二叉树
 *  [平衡二叉树的左子树和右子树高度差最多为 1，且任意子树也是平衡二叉树]
 *
 *  总结：二叉树的递归套路
 *  1）假设以 X 为头，假设可以向 X 左树和 X 右树要任何信息
 *  2）在上一步的假设下，讨论以 X 为头节点的树，得到答案的可能性
 *  3）列出所有可能性后，确定到底需要向左树和右树要什么样的信息
 *  4）把左树信息和右树信息求全集，就是每一颗子树需要返回的信息 S
 *  5）递归函数都返回 S，每一颗子树都这么要求
 *  6）在代码中考虑如何把左树的信息和右树的信息整合出整棵树的信息
 */
public class P02_BinaryTreeRelated02 {
    static class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;
        public Node(T value){
            this.value = value;
        }
    }

    public static boolean isBalanced(Node head){
        return process(head).isBalanced;
    }

    /*
    * 左，右要求一样，Info表示信息返回的结构体：
    * 1.树是否平衡？
    * 2.树的高度是多少
    * 以上同时适用于左右子树
    * */
    public static class Info{
        public boolean isBalanced;
        public int height;
        public Info(boolean b, int h){
            isBalanced = b;
            height = h;
        }
    }
    //分离出的原子单元，将问题变成再拥有左右两颗树的信息的前提下，计算这个原子单元的信息
    public static Info process(Node X){
        if (X == null) {
            return new Info(true,0);
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);

        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        boolean isBalanced = true;
        if(!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height - rightInfo.height) > 1){
            isBalanced = false;
        }
        return new Info(isBalanced,height);
    }


}
