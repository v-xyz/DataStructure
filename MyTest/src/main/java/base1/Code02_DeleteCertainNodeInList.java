package base1;
/**
 * 题目：
 * 给定一个确定头节点的链表，要求:
 * 将链表中所有节点值与输入的值相等的节点全部删除【包括头节点】
 */
public class Code02_DeleteCertainNodeInList {
    static class Node{
        Node next;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }


    static Node delete(Node head, int input){
        while (head != null){ //为什么不只判断一次，而是要用循环，因为前几个节点都有可能等于 input
            if (head.value != input){
                break;
            }
            head = head.next;
        }
        //此时pre和cur的值一定不等于 input
        Node pre = head;
        Node cur = head;
        while (cur != null){
            if(cur.value == input){
                pre.next = cur.next;
            }else {
                pre = cur; //pre 先来到 cur位置，然后cur再往后走
            }
            cur = cur.next;
        }
        return head;
    }

}

