package base1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的按层遍历【队列】
 * */
public class Code09_TraversalOfBinaryTree02 {
    public static class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;
        public Node(T value){
            this.value = value;
        }
    }
    public static void level(Node head){
        if(head != null){
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()){ //判断是 队列不为空
                head = queue.poll();
                System.out.println(head.value);
                if(head.left != null){
                    head = head.left;
                    queue.add(head);
                }
                if(head.right != null){
                    head = head.right;
                    queue.add(head);
                }
            }
        }
    }
}
