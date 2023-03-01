package baseStructure;

/**
 * 单向和双向链表的Java表示与他们的反转实现
 */
public class Code01_ReverseList {

    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            value = data;
        }
    }
    public static class DoubleNode{
        public int value;
        public DoubleNode last;
        public DoubleNode next;
        public DoubleNode(int data){
            value = data;
        }
    }
    public static Node reverseLinkedList(Node head){
        Node pre = null;
        Node next = null;
        while (head != null){
            next = head.next; //next 用来存储 head.next节点,用来方便随后 head = next 遍历

            head.next = pre; //开始pre为空，后续 pre存储的是上一个 head节点,即next就指向上一个节点，实现反转

            //pre head 分别向后移动一位
            pre = head; // pre 存储 head节点
            head = next; //head 变成 next 中存储的 head.next节点，相当于起始点到了下一个，以此类推
        }
        return pre; //最后一次循环，pre存储的是最后一个结点
    }

    public static DoubleNode reverseDoubleList(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

}
