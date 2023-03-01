package base1;
/**
 * 使用数组来实现队列 [栈用数组实现非常简单，这里省略]
 */
public class Code04_RingArray {
    public static class MyQueue{
        /*
        * 数组实现队列的核心是:
        * 刚开始初始化添加时，直接添加即可，index会不断增加，如果在数组尾部弹出数据，则直接弹出即可，
        * 如果从数组头部弹出数据，此时需要记录从头部弹出数据的位置，index加满以后需要回到头部(已经弹出的地方)继续添加数据
        * 所以才需要两个index
        * */
        private int[] arr;
        //limit表示数组能容纳的最大数量
        private final int limit;
        //size表示数组中已存的数量
        private int size;

        private int pushIndex;
        private int pollIndex;

        public MyQueue(int len){
            arr = new int[len];
            pushIndex = 0;
            pollIndex = 0;
            size = 0;
            limit = len;
        }

        public void push(int value){
            if(size == limit){
                throw new RuntimeException("不可再添加，栈已满");
            }
            arr[pushIndex] = value;
            pushIndex = nextIndex(pushIndex);
            size++;
        }
        public int pop(){
            if(size == 0){
                throw new RuntimeException("栈空了，不能再拿了");
            }
            int ans = arr[pollIndex];
            pollIndex = nextIndex(pollIndex);
            size--;
            return ans;
        }

        public int nextIndex(int index){
            return index < limit - 1 ? index++ : 0; //加满了从头开始加
        }
    }
}
