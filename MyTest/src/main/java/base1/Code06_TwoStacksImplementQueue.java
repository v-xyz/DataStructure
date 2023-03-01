package base1;

import java.util.Stack;

/**
 * 用栈实现队列
 *
 * 这里说一下如何用队列去实现栈【因为相对简单，所以省略了代码】，思路：
 * 定义两个队列 A,B ,先将元素放入 A队列中，然后用户一旦需要出栈，
 * 将A队列除最后一个元素以外的其他所有元素出队列并依次放入B队列中，
 * 然后将A队列的最后一个元素返回给用户，表示出栈，随后A队列变为空，
 * 此时再将A,B队列的指向互换，B队列变为A队列，A队列变为B队列，以此类推
 */
public class Code06_TwoStacksImplementQueue {
    public static class MyQueue{
        private Stack<Integer> stackForPush;
        private Stack<Integer> stackForPop;

        public MyQueue() {
            stackForPush = new Stack<>();
            stackForPop = new Stack<>();
        }
        private void pushToPop(){
            if(stackForPop.isEmpty()){
                while (!stackForPush.isEmpty()){
                    stackForPop.push(stackForPush.pop());
                }
            }
        }
        public void add(int value){
            stackForPush.push(value);

        }
        public int poll(){
            if(stackForPush.empty() && stackForPop.empty()){
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackForPop.pop();
        }
        public int peek(){
            if(stackForPush.empty() && stackForPop.empty()){
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackForPop.peek();
        }
    }
}
