package sort.comparativeSort.HeapSort;

public class HeapBase {
    /*
    * 堆结构：
    * 堆结构就是用数组实现的完全二叉树结构。
    * 对于以下二叉树的下标表示：
    *                         0
    *                  1              2
    *              3       4      5
    * 数组表示为 [0,1,2,3,4,5]
    * 可见，对于 i 下标的数，其在完全二叉树中的
    *   左节点下标为： 2 * i + 1
    *   右节点下标为： 2 * i + 2
    *   父节点下标为： (i - 1) / 2
    * 注意：如果起始位置不是 0，而是从 1 开始，则以上下标调整为
    *                         1
    *                  2              3
    *              4       5      6        7
    *   左节点下标为： 2 * i
    *   右节点下标为： 2 * i + 1
    *   父节点下标为： i / 2
    * 为什么一些情况下要从 1 而不是 0 开始呢?
    * 因为可以用位运算来代替算术运算提高运算效率
    *   左节点下标 位运算 为： i << 1
    *   右节点下标 位运算 为： (i << 1 | 1)
    *   父节点下标 位运算 为： i >> 1
    *
    * 堆结构分为大根堆和小根堆，完全二叉树中如果每颗子树的最大值都在顶部就是大根堆，
    * 最小值都在顶部就是小根堆
    *
    * */
    public static class MyMaxHeap{
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }
        public boolean isEmpty(){
            return heapSize == 0;
        }
        public boolean isFull(){
            return heapSize == limit;
        }

        /*
        * 考虑这样一种情况：对于不限长的数组，假设我们将数组前 heapSize 个数看成是完全二叉树
        * 对于用户给定的任意的数字，如何将这些数字以大根堆的形式放入数组中呢？
        * 思路：
        * 初始 heapSize为0，数组为空，每次进来一个数，heapSize加一，同时将该数放入数组中，
        * 随着数组下标不断增加，可用 (i - 1) / 2 计算出任意节点的父节点的位置，于是每次
        * 放入数组中数时都计算出其父节点的值，并与其父节点的值进行比较，如果比父节点大，则将
        * 两数交换，同时继续往上寻找祖父节点，再进行比较，依次类推
        * */
        public void push(int value){
            if(heapSize == limit){
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap,heapSize++);
        }
        /*
        * 假如用户需要返回大根堆的最大值，并且将该最大值从原数组中删除，要求剩下的
        * 数组也是大根堆。
        * 思路：
        * 大根堆的最大值一定是数组下标为 0 的位置，返回以后将数组最后一个元素【假设为A】
        * 放入下标为 0 的位置，同时 heapSize 减一，随后根据左右节点下标的计算公式
        * 先算出左节点和右节点值比较大的那一个节点，与0位置的节点A比较并交换，随后A继续计算
        * 左右中较大的值与A进行比较，如果比A大继续将较大值的位置与A交换，依次类推，直到A大于
        * 左右节点中的较大值为止.
        *
        * */
        public int pop(){
            int ans = heap[0];
            swap(heap,0,--heapSize);
            heapify(heap,0,heapSize);
            return ans;
        }

        public static void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]){
                swap(arr,index,(index - 1) / 2);
                index = (index - 1) / 2;
            }
        }
        //heapify方法是将头节点不是大根堆，但子节点是大根堆的完全二叉树调整为大根堆结构
        public static void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize){
                int largest = left + 1 < heapSize &&
                        arr[left+1]>arr[left] ? left + 1: left;
                largest = arr[largest] > arr[index] ? largest : index;
                if(largest == index){
                    break;
                }
                swap(arr,largest,index);
                index = largest;
                left = index * 2 + 1;
            }
        }

        public static void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
