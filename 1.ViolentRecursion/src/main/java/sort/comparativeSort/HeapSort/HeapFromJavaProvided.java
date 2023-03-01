package sort.comparativeSort.HeapSort;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapFromJavaProvided {
    public static class MyComp implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }
    public static void main(String[] args) {
        //系统实现的堆的使用
        /*
        * 优先级队列的底层就是堆结构,默认是小根堆[从小到大]
        * */
        PriorityQueue<Integer> pqHeap = new PriorityQueue<>();
        pqHeap.add(5);
        pqHeap.add(7);
        pqHeap.add(3);
        pqHeap.add(0);
        pqHeap.add(2);
        pqHeap.add(5);
        while (!pqHeap.isEmpty()){
            System.out.println(pqHeap.poll());
        }

        //变成大根堆
        PriorityQueue<Integer> pqHeap2 = new PriorityQueue<>(new MyComp());
        pqHeap2.add(5);
        pqHeap2.add(7);
        pqHeap2.add(3);
        pqHeap2.add(0);
        pqHeap2.add(2);
        pqHeap2.add(5);
        while (!pqHeap2.isEmpty()){
            System.out.println(pqHeap2.poll());
        }
    }

    /**
     * 题目：
     * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好序的话，每个元素移动的距离一定
     * 不能超过 k,并且 k 相对于数组长度来说是比较小的。请选择一个合适的排序策略，对这个数组
     * 进行排序
     *
     */
    static class Practice1{
        /*
        * 思路：
        * 假设 k=5 ,对于下标为 0 的数组中的数，其排好序后的数的下标一定不超过 5，那么
        * 可以把前 6 个数[k+1]，放入小根堆中，然后弹出小根堆中的最小值放入数组下标为 0
        * 的位置，然后加入下标为 6 的数，继续弹出最小值并放入数组下标为 1 的位置，
        * 依次进行，直到数组变成有序
        * 时间复杂度为 O(N * logK)
        * */
        public void sortedArrDistanceLessK(int[] arr, int k){
            //默认小根堆
            PriorityQueue<Integer> heap = new PriorityQueue<>();
            int index = 0;
            for(;index < Math.min(arr.length - 1,k); index++){
                heap.add(arr[index]);
            }
            int i = 0;
            for(;index < arr.length; i++, index++){
                arr[i] = heap.poll();
                heap.add(arr[index]);
            }
            //来到最后k个，依次弹出
            while (!heap.isEmpty()){
                arr[i++] = heap.poll();
            }
        }

    }
}
