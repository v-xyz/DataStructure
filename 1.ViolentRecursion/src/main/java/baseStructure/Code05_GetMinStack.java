package baseStructure;

import java.util.Stack;

/**
 * 题目：
 * 实现一个特殊的栈，在基本功能的基础上，实现返回栈中最小元素的功能,要求：
 * pop,push,getMin操作的时间复杂度都是 O(1).可以使用现有的栈结构
 */
public class Code05_GetMinStack {
    /*
     * 思路：
     * 设计两个栈，第一个栈 Data 用来存放元素，第二个栈 min 用来存放最小元素
     * 第一次 pop 时将元素放入两个栈中，之后每次Data pop元素时，都与 min 的栈顶
     * 的元素相比较，将其中较小的值 pop进 min 栈，每次 Data 栈 push出元素时，也将
     * min 栈的栈顶元素同步 push出，这样每一个 min 的栈的每个元素都对应于相同高度下的
     * Data 栈元素的最小值
     * */
    public static class MyStack{
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack(){
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }
        public void push(int newNum){
            if(stackMin.isEmpty()){
                stackMin.push(newNum);
            }else if(newNum < stackMin.peek()) {
                stackMin.push(newNum);
            }else {
                Integer newMin = stackMin.peek();
                stackMin.push(newMin);
            }
            stackData.push(newNum);
        }
        public int pop(){
            if(stackData.isEmpty()){
                throw new RuntimeException("Your stack is empty");
            }
            stackMin.pop();
            return stackData.pop();
        }
    }


}
