package baseStructure;

/**
 * 使用双向链表来实现栈和队列
 */
public class Code03_DoubleEndsQueueToStackAndQueue {
    public static class Node<T>{
        public T value;
        public Node<T> last;
        public Node<T> next;
        public Node(T data){
            value = data;
        }
    }
    //双向列表实现双向队列作为工具类,注意需要考虑刚开始集合为空的情况
    public static class DoubleEndsQueue<T>{
        //表示一个头指针的概念
        public Node<T> head;
        //表示一个尾指针的概念
        public Node<T> tail;

        /*
        * 提供四个方法：
        * 从头部进，从头部出，从尾部进，从尾部出
        * */
        //提供从从头部加节点的方法
        public void addFromHead(T value){
            //每次添加的节点作为当前节点
            //value包装成一个Node
            Node<T> cur = new Node<>(value);

            if (head == null) {
                /*
                * 头部为空则表示队列一个值也没有，此时的value是第一个进来的值
                * */
                head = cur;
                tail = cur;
            }else {
                /*
                * 不为空表示队列里面已经有值
                * */
                //当前节点的next指针指向原来的head节点
                cur.next = head;
                //head的上一个节点指向cur
                head.last = cur;
                //将cur作为新的head
                head = cur;
            }
        }

        //从尾部加节点的方法[同上]
        public void addFromBottom(T value){
            Node<T> cur = new Node<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            }else {
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
        }

        //从头部出的方法
        public T popFromHead(){
            if(head==null){
                return null;
            }
            Node<T> cur = tail;

            //队列中只有一个元素
            if(head == tail){
                //弹出以后队列为空，将 head 和 tail都设为空
                head = null;
                tail = null;
            }else {
                //将 head的下一个节点作为头节点
                head = head.next;
                //取消cur【原head】指向下一个节点的指针【设为空】
                cur.next = null;
                //取消现head的上一个节点的指针
                head.last = null;
            }
            return cur.value;
        }

        //从尾部出的方法[同上]
        public T popFromBottom(){
            if(head==null){
                return null;
            }
            Node<T> cur = head;

            //队列中只有一个元素
            if(head == tail){
                head = null;
                tail = null;
            }else {
                tail = tail.last;
                cur.last = null;
                tail.next = null;
            }
            return cur.value;
        }
        public boolean isEmpty(){
            return head == null;
        }
    }

    //通过以上类DoubleEndsQueue 双向链表的队列实现来实现栈
    public static class MyStack<T>{
        private DoubleEndsQueue<T> queue;
        public MyStack(){
            queue = new DoubleEndsQueue<>();
        }
        public void push(T value){
            queue.addFromHead(value);
        }
        public T pop(){
            return queue.popFromBottom();
        }
        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    //通过以上类DoubleEndsQueue 双向链表的队列实现来实现队列
    public static class MyQueue<T>{
        private DoubleEndsQueue<T> queue;
        public MyQueue(){
            queue = new DoubleEndsQueue<>();
        }
        public void push(T value){
            queue.addFromHead(value);
        }
        public T pop(){
            return queue.popFromHead();
        }
        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }
}
