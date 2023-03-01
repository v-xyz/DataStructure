package base1;

import java.util.Stack;

/**
 * 题目：
 * 实现一个特殊的栈，在基本功能的基础上，实现返回栈中最小元素的功能,要求：
 * pop,push,getMin操作的时间复杂度都是 O(1).可以使用现有的栈结构
 */
public class Code05_GetMinStack {
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
            }else if(newNum < stackMin.peek()){
                stackMin.push(newNum);
            }else {

                //如果新push的元素不是最小值，我们把最小值复制一份重新push，这样可以对应于stackData中的高度，
                //同一高度下的stackData，最小值就是 stackMin中栈顶的值，并且与stackData同步pop出栈
                Integer newMin = stackMin.peek();
                stackMin.push(newMin);
            }
            stackData.push(newNum);
        }
    }
}
