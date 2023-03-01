package practices;

public class P01_FastSlowPoint {
    /*
     * 给定一个单链表头节点，判断该链表是不是回文结构
     * */
    public static class Node{
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }
    public static boolean judge(Node head){
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null){
            n1 = n1.next;
            n2 = n2.next.next;
        }
//        if(n2.next != null){
//            n2 = n2.next;
//        }
        Node n3 = null;
        n2 = n1.next;
        n1.next = null;
        while (n2 !=null){
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n2 = head;
        boolean res = true;
        while (n1.next != null && n2.next != null){

            if(n1.value != n2.value){
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        //最后还需要把链表调整回去
        n1 = n3.next;
        n3.next = null;
        while (n1 != null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }
}
