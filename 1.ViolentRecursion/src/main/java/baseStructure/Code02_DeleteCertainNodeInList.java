package baseStructure;

/**
 * 题目：
 * 给定一个确定头节点的链表，要求:
 * 将链表中所有节点值与输入的值相等的节点全部删除【包括头节点】
 */
public class Code02_DeleteCertainNodeInList {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            value = data;
        }
    }
    public static Node removeValue(Node head,int num){ //num代表输入的值
        //首先确定头节点是不是与输入的值相等,如果相等就指定下一个值为头节点，然后循环判断.
        //head来到第一个值等于 num 的头节点
        while (head != null){
            if(head.value != num){
                break;
            }
            head = head.next;
        }
        /*
        * 定义 pre的意义在于，由于cur是一直往下走用来判断节点值是否与输入值不等的,
        * 我们需要 pre去记录上一个不等于输入值的节点位置，如果cur处的值不等，就让pre
        * 移动到 cur处，如果 cur处的值等于输入值，意味着该节点需要从整个节点中删除，
        * 于是将pre的下一个节点指向cur下一个未知的节点,pre原来的next,即cur，的连接就会断开，
        * 于是从头节点出发的引用就再也无法引用到该相等的节点，在垃圾回收中，这样的节点
        * 就会被gc回收掉。
        * 最后cur继续移动到下一个位置，直到再次找到不等的节点，让pre跳到cur节点
        * [注意 pre,cur指向的是同一个head节点，意味着操作的也是同一个head节点]
        * */
        Node pre = head;
        Node cur = head;
        while (cur != null){
            if(cur.value == num){
                pre.next = cur.next;
            }else {
                //如果 cur的值和输入的值不等，pre来到cur位置
                pre = cur;
            }
            //cur往下一个位置走
            cur = cur.next;
        }
        return head;
    }
}
