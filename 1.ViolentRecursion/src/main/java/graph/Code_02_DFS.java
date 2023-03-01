package graph;

import graphBase.Node;

import java.util.HashSet;
import java.util.Stack;

/*
* ----------------------------图的遍历----------------------------------
* 图的深度优先遍历(DFS)
* 每一条路走到底，然后往前看节点有没有分叉，并且包含没有遍历到的节点，则继续走，然后走到底再往前看以此类推
* */
public class Code_02_DFS {
    public static void dfs(Node node){
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();

        stack.add(node);
        set.add(node);
        // 与 BFS不同，是在入栈的时候打印
        System.out.println(node.value);

        while (!stack.isEmpty()){
            Node cur = stack.pop();
            // 对出栈的 cur节点所有后代节点（如果有的话进行遍历）
            for (Node next : cur.nexts) {
                /*
                * 一旦后继节点不存在（或已经用set记录过），就跳过本次for循环，弹出此节点，回到上一个栈中的节点看有没有其他路径，
                * 有的话走其他路径，没有则继续弹出该节点，再回到上一个节点以此类推
                * */
                if (!set.contains(next)) {
                    //如果有后代节点，则将头节点 cur重新再入栈，然后将直接的后继节点入栈
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    //后继节点也是入栈的时候打印
                    System.out.println(next.value);
                    // break 跳出本次for操作，下一次while循环中将上述的后继节点出栈作为头节点，再将其和其后继节点入栈再出栈
                    break;
                }
            }
        }
    }
}
