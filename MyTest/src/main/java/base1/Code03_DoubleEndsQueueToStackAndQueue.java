package base1;
/**
 * 使用双向链表来实现栈和队列
 */
public class Code03_DoubleEndsQueueToStackAndQueue {
    //加上泛型是因为pop的时候如果是int类型用0表示null的形式不合适，而且Node只是对具体值的包装，返回的应该是具体值
    static class Node<T>{
        Node<T> pre;
        Node<T> next;
        T value;

        public Node(T value) {
            this.value = value;
        }
    }
    //创建一个能够实现从头部进，从头部出，从尾部进，从尾部出的双向队列作为工具类，并以这个工具类实现栈和队列
    public static class MyUtils<T>{
        public Node<T> head; //头部
        public Node<T> tail; //尾部
        //传入一个值，然后从头部加节点
        public void addFromHead(T value){
            //以这个值创建一个新节点
            Node<T> cur = new Node<>(value);

            if(head == null){
                head = cur;
                tail = cur;
            }else {
                head.pre = cur;
                cur.next = head;

                head = cur; //不要忘记将 cur 作为新的 head，因为工具类定义 head 始终为头节点，
            }
        }
        public T popFromHead(){
            if (head == null){
                return null;
            }
            Node<T> cur = head;
            //也要考虑只有一个元素的情况
            if(head == tail){
                head = null;
                tail = null;
            }else {

                  //先取消前后指向再往下走
//                head.next = null;
//                cur.next.pre = null;
//                head = cur.next;

                //也可以这样写，先往下走，再取消指向
                head = head.next;
                cur.next = null;
                head.pre = null;
            }

            return cur.value;
        }
    }
    //底部方法同头部方法，省略
}
