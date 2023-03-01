package base1;

import java.util.LinkedList;
import java.util.Queue;

public class Code10_maxWidthOfBinaryTree {
    public static class Node{
        int value;
        Node left;
        Node right;
        public Node(int value) {
            this.value = value;
        }
    }
    public static int maxWidth(Node head){
        if (head == null) {
            return 0;
        }
        int curLevelNodes = 0;
        int max = 0;
        Node curEnd = head; //curEnd表示当前层最右节点是谁
        Node nextEnd = null; //nextEnd表示如果有下一层，下一层最右节点是谁
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.left != null){
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if(cur.right != null){
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if(cur == curEnd){
                max = Math.max(max,curLevelNodes);

                curLevelNodes = 0;
                curEnd = nextEnd; //当前层最右节点来到下一层最右节点
            }
        }
        return max;
    }
}
