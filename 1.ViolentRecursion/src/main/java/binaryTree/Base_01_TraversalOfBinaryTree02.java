package binaryTree;

import java.util.Stack;

/**
 * 使用非递归的方式实现二叉树的遍历 - 压栈
 * 【任何递归都可以变成非递归的方式】
 */
public class Base_01_TraversalOfBinaryTree02 {
    public static class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;
        public Node(T value){
            this.value = value;
        }
    }
    //先序遍历
    //出栈顺序为 头左右
    public static void pre(Node head){
        System.out.println("pre-order: ");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            //先压入根节点
            stack.add(head);
            while (!stack.isEmpty()){
                //弹出就打印,
                head = stack.pop();
                System.out.println(head.value + " ");

                //如果有右孩子先压右孩子，然后再压左孩子，先右再左
                if(head.right != null){
                    stack.push(head.right);
                }
                if(head.left != null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    //后序遍历
    //先序出栈为 头左右，只需变成 头右左[先压左再压右]，然后反转[放入另一个栈中]变成
    //左右头即可
    public static void pos1(Node head){
        System.out.println("pos-order: ");
        if(head != null){
            Stack<Node> stack1 = new Stack<>();
            Stack<Node> stack2 = new Stack<>();

            stack1.add(head);
            while (!stack1.isEmpty()){

                head = stack1.pop();
                stack2.push(head);

                if(head.left != null){
                    stack1.push(head.left);
                }
                if(head.right != null){
                    stack1.push(head.right);
                }
            }
            while (!stack2.isEmpty()){
                System.out.println(stack2.pop().value + " ");
            }
        }
        System.out.println();
    }
    public static void in(Node head){
        System.out.println("in-order: ");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null){
                //整条左边线依次进栈
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                }else {
                    //弹出
                    head = stack.pop();
                    System.out.println(head.value + " ");
                    //来到右树，继续尝试往左边线进栈
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    //以下方法介绍了如何用一个栈来实现后序遍历
    public static void pos2(Node h){
        System.out.println("pos-order: ");
        if(h != null){
            Stack<Node> stack = new Stack<>();
            stack.push(h);
            Node c = null;
            while (!stack.isEmpty()){
                c = stack.peek();
                if(c.left != null && h != c.left && h != c.right){
                    stack.push(c.left);
                }else if(c.right != null && h != c.right){
                    stack.push(c.right);
                }else {
                    System.out.println(stack.pop().value + " ");
                    h = c;
                }
            }
        }
        System.out.println();
    }
}
