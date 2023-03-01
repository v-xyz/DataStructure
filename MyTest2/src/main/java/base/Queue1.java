package base;

public class Queue1 {
    private int[] arr;
    private int pushIndex; //下一个要push的数组的下标
    private int pollIndex; //下一个要poll的数组的下标
    private int size; //记录当前栈中存储了多少值

    public Queue1(int len) {
        arr = new int[len];
        pushIndex = 0;
        pollIndex = 0;
        size = 0;
    }
    public void push(int value){
        if(size == arr.length){
            throw new RuntimeException("栈满了，不可再加");
        }
        arr[pushIndex] = value;
        pushIndex = nextIndex(pushIndex);
        size++;
    }
    public int poll(){
        if(size == 0){
            throw new RuntimeException("栈空了，请先添加值");
        }
        int ans = arr[pollIndex]; //不需要将 arr[pollIndex]设置成0，下一次push时直接覆盖就行
        pollIndex = nextIndex(pollIndex);
        size--;
        return ans;
    }
    private int nextIndex(int index){
        return index >= arr.length ?  0 : index + 1;
    }
}
