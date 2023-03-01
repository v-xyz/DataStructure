package base;

import java.util.Stack;

public class MyStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;
    public MyStack(){
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }
    public void push(int value){
        stackData.push(value);
        if(stackMin.isEmpty()){
            stackMin.push(value);
        }else if(value <= stackMin.peek()){
            stackMin.push(value);
        }else {
            stackMin.push(stackMin.peek());
        }
    }
    public int poll(){
        if(stackData.isEmpty()){
            throw new RuntimeException("栈空了");
        }
        stackMin.pop();
        return stackData.pop();
    }
    public int getMin(){
        return stackMin.pop();
    }
}
