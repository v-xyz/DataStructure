package graph;

import graphBase.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
* ----------------------------图的遍历----------------------------------
* 图的广度优先遍历(BFS)
* 先遍历离自己最近的节点（同一距离级别的节点顺序先后无所谓），然后第二近的所有节点，以此类推
*
* */
public class Code_01_BFS {
    //传入的 node 表示开始进行遍历的头节点
    public static void bfs(Node node){
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        // Set 能避免重复的元素，这里主要是为了避免重复输出某个节点(BFS 不存在重复的节点输出)
        HashSet<Node> set = new HashSet<>();

        //将头节点放入队列中
        queue.add(node);
        set.add(node);

        while (!queue.isEmpty()){
            //将节点出队并打印结果
            Node cur = queue.poll();
            System.out.println(cur.value);
            //找到该节点的相邻后继节点，并将这些节点全部依次入队列
            for (Node next : cur.nexts) {
                //避免重复将已经入队列的节点再入队列
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
