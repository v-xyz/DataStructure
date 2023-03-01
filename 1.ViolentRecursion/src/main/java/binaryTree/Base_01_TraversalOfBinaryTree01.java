package binaryTree;

public class Base_01_TraversalOfBinaryTree01 {
    /*
    * 二叉树的结构描述如下,注意 left 和 right只能指向子树或者 null
    *
    * 二叉树的遍历[对于每个基本单元都满足]:
    * 先序遍历: 头左右
    * 中序遍历: 左头右
    * 后序遍历: 左右头
    * */
    static class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;
    }


    public static void f(Node head){
        if (head == null) {
            return;
        }
        //先序遍历
        f(head.left);
        //中序遍历
        f(head.right);
        //后序遍历

        /*
         *                  1
         *              |        |
         *              2        3
         *            |    |   |    |
         *            4    5   6    7
         * 递归序:
         * f(head.left);
         * f(head.right);
         *
         * 1 -> 2 -> 4 -> 4(左,直接返回) -> 4(右,直接返回) -> 2(返回) -> 5 ->
         * 5(左,直接返回) -> 5(右,直接返回) -> 2(返回) -> 1(返回) -> 3 -> 6 ->
         * 6(左,直接返回) -> 6(右,直接返回) -> 3(返回) -> 7 -> 7(左,直接返回) ->
         * 7(右,直接返回) -> 3(返回) -> 1(返回)
         *
         * 先序遍历 —— 第一次遇到： 1,2,4,5,3,6,7
         * 中序遍历 —— 第二次遇到： 4,2,5,1,6,3,7
         * 后序遍历 —— 第三次遇到： 4,5,2,6,7,3,1
         * */
    }

    //先序遍历
    public static void pre(Node head){
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    //中序遍历
    public static void in(Node head){
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    //后序遍历
    public static void pos(Node head){
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }
}
