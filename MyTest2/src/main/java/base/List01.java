package base;

public class List01 {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            value = data;
        } //构造器只需要对值进行初始化
    }

    public static Node reverseLinkedList(Node node){
        //准备两个初始都指向空的分别表示上一个节点和下一个节点的 node
        Node pre = null;
        Node next = null;
        while (node != null){ //对每个node是否为空进行判断，而不是对node.next判断
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre; //最后的时候 node 和 next都会指向空，所以要返回 pre 而不是 node.
    }
    public static class DNode{
        public int value;
        public DNode next;
        public DNode pre;
        public DNode(int data){
            value = data;
        } //构造器只需要对值进行初始化
    }
    public static DNode reverseLinkedList2(DNode head){
        DNode pre = null;
        DNode next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head; //在 head = next 之前
            head = next;
        }
        return pre;
    }
}
