package Practices;

/**
 * 给定两个可能有环也可能无环的单链表，头节点 head1 和 head2, 请实现一个函数
 * 如果两个链表相交，请返回相交的第一个节点，如果不相交则返回 null
 */
public class P01_ListRelated05 {

    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            value = data;
        }
    }

    /**
     * 考虑以下问题：
     * 单链表可能有环也可能无环，判断单链表是否有环，有环返回环的第一个节点，否则返回 null
     *
     * 思路：
     * 方式一：将每个节点都放入set中，从头节点开始遍历，一旦出现重复的节点则表明有环，
     * 返回第一个重复的节点。
     * 方式二：准备快慢指针，假设快指针为 F,慢指针为 S, 快指针一次走两格，满指针一次
     * 走一格，快指针从第二个节点位置出发，慢指针从第一个节点位置出发，他们相交就代表有环，
     * 不相交则代表无环，相交时将慢指针先保持不动，快指针回到链表的头节点，随后快指针一次
     * 也走一格，快慢指针一起移动，他们再次相交的地方就是环的第一个节点.[记住结论即可]
     * */
    //找到链表第一个入环节点，如果无环则返回 null
    public static Node getLoopNode(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node n1 = head.next; //n1表示慢节点
        Node n2 = head.next.next; //n2表示快节点
        while (n1 != n2){
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head;
        while (n1 != n2){
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    /*
    * 回到原始问题，对于无环的单链表，如果它们相交，则相交以后的所有节点必定相同，所以
    * 最后一个节点必定相同，只需要判断最后一个节点是否相等则可以判断出是否相交，为了
    * 找到相交后的第一个节点，假设 A链表长度为 20，B链表长度为 18，两者之差为2，则只
    * 需要 A链表从下标为2，B链表从下标为0的地方两者同时开始遍历并比较 next 的Node值
    * 是否相等即可，第一个相等的节点就是相交的节点。
    * */
    public static Node noLoop(Node head1, Node head2){
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        if(cur1 != cur2){
            return null;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;

        n = Math.abs(n);
        while (n != 0){
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /*
    * 由于单链表只有一个next指针，如果一个有环单链表和无环单链表想相交，则该无环单链表
    * 将变为有环单链表，也就是说一个有环单链表和一个无环单链表不可能相交，下面考虑两个
    * 有环单链表的情况。
    * 1) 不相交
    * 2) 相交，入环节点是同一个节点
    *         1'
    *         |
    *    1 -> 2 -> 3   同一个入环节点 2
    *         |    |
    *         5 <- 4
    *
    * 3) 相交，入环节点不是同一个节点
    *              1'
    *              |
    *    1 -> 2 -> 3   入环节点为 2 和 3
    *         |    |
    *         5 <- 4
    *
    * 对于 2) 的情况，发现可以无视环，将其看作无环链表的相交问题，只需要将第一个入环
    * 节点看作是最后节点即可。
    * 对于 3) 的情况，首先找到两个入环节点，只需要将任意一个入环节点在环中遍历，如果
    * 包括了另一个入环节点，证明相交,返回任意的入环节点，如果没找到，证明不相交，
    * 是 1) 的情况，返回 null
    * */
    //loop1 和 loop2表示各个链表第一个入环节点
    public static Node bothLoop(Node head1, Node loop1 ,Node head2, Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        if(loop1 == loop2){
            //与上述无环节点相交代码相同
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;

            n = Math.abs(n);
            while (n != 0){
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else {
            cur1 = loop1.next;
            while (cur1 != loop1){
                if(cur1 == loop2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    //经过上述几个方法的阐述，解决原始问题的主方法如下：
    public static Node getIntersectNode(Node head1, Node head2){
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if(loop1 == null || loop2 == null){
            return noLoop(head1,head2);
        }
        if (loop1 != null || loop2 != null) {
            return bothLoop(head1,loop1,head2,loop2);
        }
        return null;
    }
}
