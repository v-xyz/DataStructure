package base1;

public class Code19_HeapSort {
    public static void heapSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for (int i = arr.length - 1; i >= 0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }
        int heapCount = arr.length;
        swap(arr,0,--heapCount);
        while (heapCount > 0){
            adjustHeap(arr,0,heapCount);
            heapCount--;
            swap(arr,0,heapCount);
        }
    }
    //adjustHeap是将头节点不是大根堆，但子节点是大根堆的完全二叉树调整为大根堆结构
    private static void adjustHeap(int[] arr, int curIndex, int heapCount) {
        int leftIndex = curIndex * 2 + 1;
        while (leftIndex < heapCount){
            int rightIndex = leftIndex + 1;
            int largestIndex;
            if(rightIndex < heapCount){
                largestIndex = arr[leftIndex] > arr[rightIndex] ? leftIndex : rightIndex;
            }else {
                largestIndex = leftIndex; //如果 rightIndex越界，那么只有取 leftIndex的值
            }
            if(arr[curIndex] < arr[largestIndex]){
                swap(arr,curIndex,largestIndex);
                curIndex = largestIndex;
                leftIndex = leftIndex * 2 + 1;
            }else {
                break;
            }
        }
    }
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
