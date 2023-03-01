package base1;

import graphDescription.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
 * ----------------------------图的遍历----------------------------------
 * 图的广度优先遍历(BFS)
 * 先遍历离自己最近的节点（同一距离级别的节点顺序先后无所谓），然后第二近的所有节点，以此类推
 *
 * */
public class Code12_GraphBFS {
    public static void bfs(Node head){
        if (head == null) {
            return ;
        }
        HashSet<Node> resultSet = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        resultSet.add(head); //先添加head

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            //resultSet.add(cur);
            for (Node next: cur.outArr) {

                if(resultSet.contains(next)){ //注意避免queue的重复添加,因为是图
                    queue.add(next);
                }
                resultSet.add(next); //在遍历中添加
            }
        }
    }
}
