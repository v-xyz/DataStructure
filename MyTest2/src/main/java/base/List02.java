package base;

public class List02 {
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            value = data;
        }
    }
    //public static Node removeValue(Node head,int num){
//        Node node = null;
//        while (head != null){
//            node = head.next;
//            if(head.value == num){
//                head.next = null;
//                head = node;
//            }else {
//                head = node;
//            }
//        }
//        return head;
    //}
}
