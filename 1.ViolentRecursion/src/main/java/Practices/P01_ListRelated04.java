package Practices;

import java.util.HashMap;

public class P01_ListRelated04 {
    static class Node{
        int value;
        Node next;
        Node rand;
        Node(int val){
            value = val;
        }
    }
    /**
     * 题目:
     * 对于以上单链表描述，rand指针是单链表结构中新增的指针，rand可能指向单链表中
     * 任意一个节点，也可能指向 null,给定一个由 Node节点类型组成的单链表的头节点head,
     * 请实现一个方法完成该链表的复制，并返回的复制的新链表的头节点。
     */

    /*
    * [笔试] 准备一个Map，key用来记录旧节点，value记录新节点，
    * 随后根据旧节点的指针关系找到对应的新节点的关系，构建新的单链表
    * */
    public static Node method1(Node head){
        HashMap<Node,Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null){
            map.put(cur,new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    /*
    * [面试] 往每个旧的节点的next间插入新的节点，然后根据旧节点的 rand
    * 找到新节点间的rand联系，最后再分离出新的节点
    * */
    public static Node method02(Node head){
        if (head == null) {
            return null;
        }
        //1 -> 2
        //1 -> 1' -> 2
        Node cur = head;
        Node next = null;
        while (cur != null){
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        while (cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        while (cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
