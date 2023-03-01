package Practices;

import java.util.Stack;

/**
 * 对于给定的栈，请逆序该栈，注意不能申请额外的数据结构，只能使用递归函数，请写出实现方式
 */
public class P05_ViolentRecursionRelated02 {

    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    //f函数将去掉并返回栈底最后一个数
    public static int f(Stack<Integer> stack){
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }
}
