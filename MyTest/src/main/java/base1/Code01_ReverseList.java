package base1;

public class Code01_ReverseList {

    public static class Node{
        public int value;
        public Node next; //next不通过构造器指定

        public Node(int value) {
            this.value = value;
        }
    }

//提供head节点实现反转单向链表
    public static Node reverseNode1(Node head){
        Node n1 = head;
        Node n2 = null;
        Node n3 = null;
        while (n1 != null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return n3;
    }


//    public static class Node{
//        public int value;
//        public Node next;
//        public Node last;
//        public Node(int value) {
//            this.value = value;
//        }
//    }
//    public static Node reverseNode2(Node head){
//
//    }
}
