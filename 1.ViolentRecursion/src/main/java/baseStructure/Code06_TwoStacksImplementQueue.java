package baseStructure;

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
    /*
    * 思路：
    * 使用两个栈分别为 push 和 pop栈，并遵循两条规则：
    * 1) 从 push栈将数据 push进入 pop栈时，pop栈必须为空
    * 2) 从 push栈将数据 push进入 pop栈时，必须一次性将所有数据全部 push进 pop栈
    *
    * 例如用户输入 1，2，3.另一个栈中是 3，2，1.但一旦中途再输入4，如果不遵循上述规则，
    * 就会出问题
    * */
    public static class TwoStacksQueue{
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStacksQueue() {
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }

        //push栈向 pop栈倒入数据
        private void pushToPop(){
            //必须 pop栈为空
            if(stackPop.isEmpty()){
                while (!stackPush.isEmpty()){
                    stackPop.push(stackPush.pop());
                }
            }
        }
        public void add(int pushInt){
            stackPush.push(pushInt);
            //pushToPop();
        }
        public int poll(){
            if(stackPush.empty() && stackPop.empty()){
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.pop();
        }
        public int peek(){
            if(stackPush.empty() && stackPop.empty()){
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }
    }



}
