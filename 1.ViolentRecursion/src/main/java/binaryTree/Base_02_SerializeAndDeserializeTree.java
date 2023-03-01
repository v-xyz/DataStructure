package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class Base_02_SerializeAndDeserializeTree {
    public static class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;
        public Node(T value){
            this.value = value;
        }
    }

    /**
     * 先序方式序列化
     */
    public static Queue<String> preSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        pres(head,ans);
        return ans;
    }
    public static void pres(Node head, Queue<String> ans){
        //由于节点的值可能会相同，会了针对不同的树都能有效序列化和反序列化，必须把空节点也算作单独的一个个节点
        if (head == null) {
            ans.add(null);
        }else {
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }
    //先序方式反序列化
    public static Node buildByPreQueue(Queue<String> preList){
        if(preList == null || preList.size() == 0){
            return null;
        }
        return preb(preList);
    }
    public static Node preb(Queue<String> preList){
        String value = preList.poll();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = preb(preList);
        head.right = preb(preList);
        return head;
    }

    //由于前序是头左右，后序的序列化方式(左右头)就是将其放入栈中，出栈形成中右左，然后序列化。
    /*
    * 需要注意:二叉树不能通过中序遍历方式进行序列化
    * 比如:
    *       _2
    *      /
    *     1
    *     和
    *     1_
    *       \
    *        2
    * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null }
    * */
    /**
     * 按层序列化,同样不能忽略空
     */
    public static Queue<String> levelSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        }else {
            ans.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()){
                head = queue.poll();
                //左孩子不等于空就既序列化也加队列
                if(head.left != null){
                    ans.add(String.valueOf(head.left.value));
                    queue.add(head.left);
                }else {
                    //左孩子等于空，就只序列化不加队列
                    ans.add(null);
                }
                if(head.right != null){
                    ans.add(String.valueOf(head.right.value));
                    queue.add(head.right);
                }else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    //按层反序列化
    public static Node buildByLevelQueue(Queue<String> levelList){
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        Node head = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        Node node = null;
        while (!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
        return head;
    }
    private static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.valueOf(val));
    }

}
