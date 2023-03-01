package base1;

import graphDescription.Node;

import java.util.HashSet;
import java.util.Stack;

/*
 * ----------------------------图的遍历----------------------------------
 * 图的深度优先遍历(DFS)
 * 每一条路走到底，然后往前看节点有没有分叉，并且包含没有遍历到的节点，则继续走，然后走到底再往前看以此类推
 * */
public class Code13_GraphDFS {
    public static void dfs(Node head){
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> resultSet = new HashSet<>();
        stack.add(head);
        resultSet.add(head);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next: cur.outArr) {
                if(!resultSet.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    resultSet.add(next);
                    //由于DFS每次只有一条支路，而不是每个next都走，所以要break跳出本次循环
                    //这也是push(cur)的原因，使之后能够回到这条支路之前走过的节点
                    break;
                }
            }
        }
    }
}
