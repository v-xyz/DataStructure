package base1;

import java.util.Stack;

/**
 * 使用非递归的方式实现二叉树的遍历 - 压栈
 */
public class Code08_TraversalOfBinaryTree {
    public static class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;
        public Node(T value){
            this.value = value;
        }
    }
    public static void pre(Node head){
        System.out.println("先序遍历");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()){
                Node node = stack.pop();
                System.out.println(node);
                if(node.right != null){
                    stack.push(node.right);
                }
                if(node.left != null){
                    stack.push(node.left);
                }
            }
        }
    }
    public static void in(Node head){

    }
}
