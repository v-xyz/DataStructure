package Practices;

import java.util.Stack;

public class P01_ListRelated02 {
    /*
    * 给定一个单链表头节点，判断该链表是不是回文结构
    * */
    public static class Node{
        public int value;
        public Node next;
        public Node(int data){
            value = data;
        }
    }
    /*
    * 由于栈弹出的是逆序的结构，所以可以将节点放入栈中再弹出比较 [笔试]
    * 该方法需要 n 个额外空间
    * */
    public static boolean method01(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null){
            if (head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /*
    * 能不能用更少的空间呢？可以用接近 n / 2 的空间
    * 思路：
    * 用快慢指针，奇数定位到中点，偶数定位到上中点，然后把右半部分加到栈里去
    * 栈中弹出就会是右半部分的逆序的形式，统计栈中个数，并从头节点开始遍历，每个元素都与
    * 栈中弹出的比较，如果每个都相等，则是回文。
    *
    * 更进一步的优化？ 只需要 O(1) 的空间
    * 思路：
    * 彻底不用容器。同样用快慢指针定位到中点，将右半部分指针指向中点，中点指针指向空
    * 比如
    *       1 -> 2 -> 3 -> 2 -> 1 -> null
    * 变成
    *       1 -> 2 -> 3 <- 2 <- 1
    *                 |
    *                null
    * 以上对于偶数个数也适用，只要发现左右有一个指向 null 则停止比较即可。
    * 然后从左右分别向中点遍历，并互相比较,返回结果后，需要将右半部分指针指向调整为原始形式
     * */
    public static boolean method02(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null){
            n1 = n1.next;
            n2 = n2.next.next;
        }

        //1 2 3 4 5 6
        //    *
        //        *
        //排除以上特殊情况
        if (n2.next != null) {
            n2 = n2.next;
        }

        //经过上述快慢指针，n1指向中点，n2指向最后的节点
        //          n1             n2
        //1 -> 2 -> 3 -> 3 -> 2 -> 1

        n2 = n1.next; //n2存储右半部分第一个节点
        //          n1   n2
        //1 -> 2 -> 3 -> 3 -> 2 -> 1

        n1.next = null; //将中点的下一个节点指向 null
        //          n1 n2
        //1 -> 2 -> 3  3 -> 2 -> 1
        //          |
        //          null

        Node n3 = null;
        while (n2 != null){
            /*
            * 步骤概述：n2 存储 n1 的下一个节点，n3 存储 n2 的下一个节点,
            * 把 n2 的下一个节点指向改为 n1 n2.next = n1
            * n1 来到 n2 位置, n2 来到 n3 位置
            * */

            n3 = n2.next;
            //          n1 n2   n3
            //1 -> 2 -> 3  3 -> 2 -> 1

            n2.next = n1;
            //          n1   n2   n3
            //1 -> 2 -> 3 <- 3 -> 2 -> 1

            n1 = n2;
            //               n2   n3
            //1 -> 2 -> 3 <- 3 -> 2 -> 1
            //               n1

            n2 = n3;
            //                    n3
            //1 -> 2 -> 3 <- 3 -> 2 -> 1
            //               n1   n2

            //到第二次循环的一开始 n3 = n2.next;
            //               n1   n2   n3
            //1 -> 2 -> 3 <- 3 -> 2 -> 1

        }
        //经过上述步骤
        //                         n1  n3
        //1 -> 2 -> 3 <- 3 <- 2 <- 1  null
        //          |                  n2
        //         null

        n3 = n1; //n3 存储最后一个节点位置
        //                         n1
        //1 -> 2 -> 3 <- 3 <- 2 <- 1  null
        //                         n3  n2

        n2 = head;
        //n2                       n1
        //1 -> 2 -> 3 <- 3 <- 2 <- 1  null
        //                         n3

        boolean res = true;
        // n2 , n1 逐步向中间遍历并比较
        while (n1 != null && n2 != null){
            if(n1.value != n2.value){
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        //经过上述步骤
        //          n2   n1        n3
        //1 -> 2 -> 3 <- 3 <- 2 <- 1  null
        //          |
        //         null

        n1 = n3.next;
        //          n2        n1   n3
        //1 -> 2 -> 3 <- 3 <- 2 <- 1  null
        //          |
        //         null

        n3.next = null;
        //          n2        n1   n3
        //1 -> 2 -> 3 <- 3 <- 2    1 -> null
        //          |
        //         null

        while (n1 != null){
            n2 = n1.next;
            //               n2   n1   n3
            //1 -> 2 -> 3 <- 3 <- 2    1 -> null
            //          |
            //         null

            n1.next = n3;
            //               n2   n1   n3
            //1 -> 2 -> 3 <- 3    2 -> 1 -> null
            //          |
            //         null

            n3 = n1;
            //               n2   n1
            //1 -> 2 -> 3 <- 3    2 -> 1 -> null
            //          |         n3
            //         null


            n1 = n2;
            //               n2
            //1 -> 2 -> 3 <- 3    2 -> 1 -> null
            //          |    n1   n3
            //         null

            //到第二次循环的一开始 n2 = n1.next;
            //          n2   n1   n3
            //1 -> 2 -> 3 <- 3    2 -> 1 -> null
            //          |
            //         null

        }
        return res;
    }
}
