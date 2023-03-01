package binaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的按层遍历【队列】
 *
 * 总结：
 * 二叉树的按层遍历也称为 二叉树的广度优先遍历【BFS】
 * 二叉树的先序，中序和后序遍历 统称为二叉树的深度优先遍历【DFS】,如果说二叉树
 * 的DFS不特别提到哪一个遍历指的就是 先序遍历
 */
public class Base_01_TraversalOfBinaryTree03 {
    public static class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;
        public Node(T value){
            this.value = value;
        }
    }
    public static void level(Node head){
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            if(cur.left != null){
                queue.add(cur.left);
            }
            if(cur.right != null){
                queue.add(cur.right);
            }
        }
    }

    //题目：如何判断二叉树哪一层节点数最多，并且最多节点数是多少。
    //方法一：Map
    public static int maxWidthUseMap(Node head){
        if (head == null) {
            return 0;
        }
        //定义一个队列加入头节点
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // levelMap 表示对应的 Node 在哪一层的信息
        HashMap<Node,Integer> levelMap = new HashMap<>();
        levelMap.put(head,1); //head节点在第一层

        int curLevel = 1; //表示当前正在统计哪一层的宽度
        int curLevelNodes = 0; //表示当前正在统计的层的宽度的值,进去的时候不统计，出来的时候再统计
        int max = 0; //表示所有层的宽度的最大值
        while (!queue.isEmpty()){

            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);

            if (cur.left != null) {
                levelMap.put(cur.left,curNodeLevel + 1); //左孩子层数加 1
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right,curNodeLevel + 1);
                queue.add(cur.right);
            }
            //判断当前层是否没过期
            if(curNodeLevel == curLevel){
                //没过期，当前层节点数增加
                curLevelNodes++;
            }else {
                //过期了,结算上一层节点数
                max = Math.max(max, curLevelNodes);
                curLevel++; //来到下一层
                curLevelNodes = 1; //因为过期了，则当前节点已经来到了下一层，节点数初始值为1
            }
        }
        //最后一层没有新的层去结算，所以要再计算一次最大值
        max = Math.max(max, curLevelNodes);
        return max;
    }

    //方法二：不用Map
    public static int maxWidthNoMap(Node head){
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        Node curEnd = head; //curEnd表示当前层最右节点是谁
        Node nextEnd = null; //nextEnd表示如果有下一层，下一层最右节点是谁
        int max = 0;
        int curLevelNodes = 0;
        while (!queue.isEmpty()){
            Node cur = queue.poll();

            //nextEnd 总是在往右移动
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }

            curLevelNodes++;

            if(cur == curEnd){
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }
}
