package base1;
/*
* 堆结构就是用数组实现的完全二叉树结构。
* 对于 i 下标的数，其在完全二叉树中的
 *   左节点下标为： 2 * i + 1
 *   右节点下标为： 2 * i + 2
 *   父节点下标为： (i - 1) / 2
* */
public class Code18_Heap_MaxHeap {
    public static class MaxHeap{
        private int[] heapArray;
        private final int limit;
        private int heapCount;

        public MaxHeap(int limit) {
            heapArray = new int[limit];
            this.limit = limit;
            heapCount = 0;
        }

        public boolean isEmpty(){
            return heapCount == 0;
        }

        //往大根堆中添加值
        public void add(int value){
            if(heapCount == limit){
                throw new RuntimeException("heap is full");
            }
            heapArray[heapCount] = value; //放到后面

            int curIndex = heapCount;
            while (heapArray[curIndex] > heapArray[(curIndex - 1) / 2]){
                swap(heapArray,curIndex,(curIndex - 1) / 2);
                curIndex = (curIndex - 1) / 2; //来到父节点的index继续向父节点比较
            }

            heapCount++;
        }

        //返回大根堆的最大值(头节点)，将余下的堆继续调整为大根堆
        public int pop(){
            int max = heapArray[0];
            swap(heapArray,0,--heapCount); //最后一个位置的值交换到头节点开始调整

            int curIndex = 0;
            int leftIndex = 1; //从第一个左边位置开始。(知道左位置就知道了右位置)
            while (leftIndex < heapCount){
                int rightIndex = leftIndex + 1;
                int largestIndex;
                if(rightIndex < heapCount){
                    largestIndex = heapArray[leftIndex] > heapArray[rightIndex] ? leftIndex : rightIndex;
                }else {
                    largestIndex = leftIndex; //如果 rightIndex越界，那么只有取 leftIndex的值
                }
                if(heapArray[curIndex] < heapArray[largestIndex]){
                    swap(heapArray,curIndex,largestIndex);
                    curIndex = largestIndex;
                    leftIndex = leftIndex * 2 + 1;
                }else {
                    break;
                }
            }
            return max;
        }

        private static void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

    }
}
