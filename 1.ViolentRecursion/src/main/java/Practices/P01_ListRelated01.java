package Practices;

import baseStructure.Code01_ReverseList;

/**
 * 快慢指针
 */
public class P01_ListRelated01 {

    /*
    * 1) 输入链表头节点，奇数长度返回中点，偶数长度返回上中点 (快指针控制着步数)
    */


    //提供的解决思路：单链表，快慢指针
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            value = data;
        }
    }
    // 12 34 56 7
    public static Node method01(Node head){
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /*
     * 如果偶数长度返回下中点呢?
     *
     */
    public static Node method02(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node slow = head.next; //相当于使长度变成奇数
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /*
     * 3) 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     */
    public static Node method03(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /*
     * 4) 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     */
    public static Node method04(Node head){
        if(head == null || head.next == null){
            return null;
        }
        if (head.next.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /*
    * 除了快慢指针以外，也可以将Node全部放入 ArrayList中，根据 ArrayList的size来
    * get对应位置的 Node，但需要额外的空间存储
    * 笔试用这个方法快，面试用快慢指针
    * */
}
