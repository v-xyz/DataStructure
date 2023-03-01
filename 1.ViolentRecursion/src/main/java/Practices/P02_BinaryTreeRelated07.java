package Practices;

import java.util.LinkedList;

/**
 * 给定一个二叉树的头节点 head,返回这棵树是不是完全二叉树
 *
 */
public class P02_BinaryTreeRelated07 {
    static class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
        }
    }

    /*
    * 思路[方法一]：
    * 【这里不使用前面的递归套路】
    * 先进行广度优先遍历【按层遍历】，对于每个节点进行以下判断：
    * 1.如果有右孩子但是没有左孩子，则一定不是完全二叉树
    * 2.遇到左右孩子不双全的节点，则后序遇到的所有节点，都必须是叶子节点
    * */

    public static boolean isCBT1(Node head){
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        boolean leaf = false; //是否遇到过左右两个孩子不双全的节点,一旦为true就不再关闭
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            l = head.left;
            r = head.right;
            //如果遇到了不双全的节点之后，又发现当前节点不是叶节点
            if(
                    /*
                    * 在发现有有左右两个不双全的节点的前提下(leaf 为true)
                    * 发现还有左孩子或右孩子
                    * */
                    (leaf && (l != null || r != null))
                    ||
                    //有右无左
                    (l == null && r != null)){
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    /*
    * 使用二叉树的递归套路思路：
    * 结果整体是完全二叉树要分为两个大的类别包括四种情况：
    * 1）二叉树无缺口，即满二叉树。
    *   此时只要要左树和右树的：1）是否是满二叉树。 2）树的高度
    *   如果左右树都是满二叉树并且高度相同，则整体的树就是满二叉树
    * 2)二叉树有缺口，分为三种具体情况
    *   [1] - 左树是完全二叉树[2,3,4,8]，右树是满二叉树[3.6.7]
    *         左树的高度要比右树的高度大一个
    *                   1【代表X节点】
    *              |        |
    *              2        3
    *            |    |   |    |
    *            4    5   6    7
    *            |
    *            8
    *         此时需要的信息：1）左树是否是完全二叉树 2）右树是否是满二叉树 3）左树和右树的高度
    *   [2] - 左树是满二叉树[2,3,4,8,9]，右树是满二叉树[3.6.7]
    *         左树的高度要比右树的高度大一个
    *                  1【代表X节点】
    *              |        |
    *              2        3
    *            |    |   |    |
    *            4    5   6    7
    *            |    |
    *            8    9
    *         此时需要的信息：1）是否是满二叉树 3）树的高度
    *   [3] - 左树是满二叉树[2,3,4,8,9]，右树是完全二叉树[3.6.7.10]
    *         左树的高度与右树的高度一致
    *                  1【代表X节点】
    *              |        |
    *              2        3
    *            |    |   |    |
    *            4    5   6    7
    *            |    |   |
    *            8    9   10
    *         此时需要的信息：1）左树是否是满二叉树 2）右树是否是完全二叉树 3）左树和右树的高度
    *
    * 如果以上四种情况都不满足，则一定不是完全二叉树
    * 总结出需要要的信息：
    * 1）树是不是完全二叉树
    * 2）树是不是满二叉树
    * 3）树的高度
    *
    * */

    public static class Info{
        public boolean isFull;
        public boolean isCBT;
        public int height;
        public Info(boolean isFull, boolean isCBT, int height){
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }
    public static Info process(Node head){
        if (head == null) {
            return new Info(true,true,0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
        if(isFull){
            isCBT = true;
        }else {
            //整体不全是满二叉树，但两个都必须至少是完全二叉树才有讨论的必要，否则为false
            if (leftInfo.isCBT && rightInfo.isCBT){
                if(leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1){
                    isCBT = true;
                }
                if(leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1){
                    isCBT = true;
                }
                if(leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height){
                    isCBT = true;
                }
            }
        }
        return new Info(isFull,isCBT,height);
    }
}
