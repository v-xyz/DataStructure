package baseStructure;

/**
 * 使用数组来实现队列 [栈用数组实现非常简单，这里省略]
 */
public class Code04_RingArray {
    public static class MyQueue{
        private int[] arr;
        private int pushIndex;
        private int pollIndex;
        //size表示数组中的数量
        private int size;
        //limit表示数组能容纳的最大数量
        private final int limit;
        public MyQueue(int len){
            arr = new int[len];
            pushIndex = 0;
            pollIndex = 0;
            size = 0;
            limit = len;
        }
        public void push(int value){
            if(size == limit){
                throw new RuntimeException("栈满了，不能再加了");
            }
            size++;
            arr[pushIndex] = value;
            pushIndex = nextIndex(pushIndex);
        }
        public int pop(){
            if(size == 0){
                throw new RuntimeException("栈空了，不能再拿了");
            }
            size--;
            int ans = arr[pollIndex];
            pollIndex = nextIndex(pollIndex);
            return ans;
        }

        //现在的下标是i,返回下一个位置的下标
        private int nextIndex(int i){
            return i < limit - 1 ? i + 1 : 0;
        }
        public boolean isEmpty(){
            return size == 0;
        }
    }
}
