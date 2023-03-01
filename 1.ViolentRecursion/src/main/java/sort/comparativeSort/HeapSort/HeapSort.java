package sort.comparativeSort.HeapSort;

/**
 * 根据堆排序可得出堆排序的思路：
 * 1. 先将数组变成大根堆。[HeapBase中的 push方法]
 * 2. 弹出最大值到末尾[HeapBase中的 pop方法],对余下的堆结构继续此操作，直到全部有序
 */
public class HeapSort {
    /**
     * 堆排序的整个排序过程没有用到递归，所以堆排序的一个重要优势是，使用了 O(1)的空间
     * 复杂度，【时间复杂度为 O(N * logN)】
     */
    public static void heapSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }

        //假设用户每一次都给一个新的值并将其调整为大根堆
        //时间复杂度为 O(N * logN)
//        for (int i = 0; i < arr.length; i++) {       //O(N)
//            HeapBase.MyMaxHeap.heapInsert(arr,i);    //O(logN)
//        }

        //对以上注释部分的优化
        //从每个子节点开始，由最底端到头节点的方式逐步将其调整为大根堆
        /*
        * 时间复杂度计算：
        * 首先完全二叉树具有以下性质：
        * 总节点数为 N 的完全二叉树的叶子节点个数为：
        *   1. 当n为奇数时：(N+1)/2
        *   2. 当n为偶数时 : N/2
        *
        * 由此可知，叶子节点因为都是单节点所以总是大根堆，每个对叶子节点的判断时间复杂度为 1
        * 所以对叶子节点判断的总的时间复杂度为　N/2 【省略常数】,同理，对叶子节点的父节点
        * 需要的操作为 2，时间复杂度为 N/4 * 2,
        * 依次类推，总时间复杂度为：N/2 + N/4 * 2 + N/8 * 3 +...
        * 收敛于 O(N)
        *
        * 注意：由于这种方式是直接从最底端的叶子节点开始，肯定是一开始就得到了所有节点，
        * 如果用户是一个一个节点给的，就无法进行这种优化.
         * */
        for (int i = arr.length - 1; i >= 0 ; i--) {
            HeapBase.MyMaxHeap.heapify(arr,i, arr.length);
        }

        int heapSize = arr.length;
        //将 0 位置的最大值和最后一个数交换
        HeapBase.MyMaxHeap.swap(arr,0,--heapSize);

        //时间复杂度为 O(N * logN)
        while (heapSize > 0){
            // heapify 方法将数组再次调整为大根堆结构
            HeapBase.MyMaxHeap.heapify(arr,0,heapSize);
            HeapBase.MyMaxHeap.swap(arr,0,--heapSize);
        }
        /*
        * 传统方法时间复杂度是两次O(N * logN)相加,即最终是 O(N * logN)
        *
        * */
    }
}
