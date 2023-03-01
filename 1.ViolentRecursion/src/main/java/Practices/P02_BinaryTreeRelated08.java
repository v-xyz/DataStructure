package Practices;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一个二叉树的头节点 head 和另外两个节点 a 和 b,返回 a 和 b 的最低公共祖先【往上走最先交汇的节点】
 */
public class P02_BinaryTreeRelated08 {
    static class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
        }
    }

    /*
    * 解法一：
    * 准备一个HashMap,然后以任何一种方式遍历二叉树，在遍历过程中将节点作为 key，将节点的
    * 父节点作为 value 加入到 HashMap中，然后在HashMap中找到 a,b 节点，准备一个
    * HashSet,从a,b中任意一个节点开始，依次将其与其父节点加入到 HashSet中，一直加入
    * Head节点，随后从另外一个节点开始依次判断其以及其父节点是否在 HashSet中，如果在，
    * 找到的第一个节点就是最低公共祖先
    * */
    public static Node lowestAncestor1(Node head, Node o1, Node o2){ // o1,o2代表二叉树上任意两个节点.
        if (head == null) {
            return null;
        }
        HashMap<Node,Node> parentMap = new HashMap<>();
        parentMap.put(head,null);
        fillParentMap(head,parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null){
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)){
            cur = parentMap.get(cur);
        }
        return cur;
    }
    private static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left,head);
            fillParentMap(head.left,parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right,head);
            fillParentMap(head.right,parentMap);
        }
    }

    /*
    * 二叉树递归套路解法:
    * 假设 X 表示二叉树上任意一颗子树上的头节点，有以下三种情况：
    * 1) o1,o2没有一个在 X及其子树上
    * 2) o1,o2只有一个在 X及其子树上
    * 3) o1,o2都在 X及其子树上，此时又分为以下情况：
    *   【1】左树右树上各有一个
    *   【2】左树包含o1,o2
    *   【3】右树包含o1,o2
    *   【4】X 是 o1 或 o2，此时只要判断左子树和右子树存不存在另一个节点[o2或o1]
    *
    * 总结需要三个信息：
    * 1) 两个节点 o1和 o2的最低公共祖先
    * 2) 子树上是否有 o1
    * 3) 子树上是否有 o2
    * */
    public static class Info{
        public Node ans;
        public boolean findO1;
        public boolean findO2;
        public Info(Node a, boolean f1, boolean f2){
            ans = a;
            findO1 = f1;
            findO2 = f2;
        }
    }
    public static Info process(Node X, Node o1, Node o2){
        if (X == null) {
            return new Info(null,false,false);
        }
        Info leftInfo = process(X.left,o1,o2);
        Info rightInfo = process(X.right,o1,o2);

        boolean findO1 = X == o1 || leftInfo.findO1 || rightInfo.findO1;
        boolean findO2 = X == o2 || leftInfo.findO2 || rightInfo.findO2;

        //o1,o2最初的交汇点在哪?
        Node ans = null;
        //由于找的是最初交汇点，如果左树发现答案，则答案与左树保持一致
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        }
        //同理
        if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        }
        //左树和右树都没发现交汇点
        if (ans == null) {
            if (findO1 && findO2){
                ans = X;
            }
        }
        return new Info(ans,findO1,findO2);
    }
    public static Node lowestAncestor2(Node head, Node o1, Node o2){
        return process(head,o1,o2).ans;
    }
}
