package sort.comparativeSort.HeapSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 针对 HeapInWhichConditionYouNeedToCreateByYourself 中的问题，
 * 我们需要实现自己的堆结构。
 */
public class HeapBase2 {
    public static class MyHeap<T>{
        //因为没有 T[], 所以用 ArrayList<T> 来表示
        private ArrayList<T> heap;
        // indexMap 记录着对于给定的样本，样本中的每个值在堆中所处的位置
        private HashMap<T,Integer> indexMap;
        private int heapSize;
        // comparator表示必须明确对于任意所给对象比较大小的标准是什么，否则操作无法进行
        private Comparator<? super T> comparator;

        public MyHeap(Comparator<? super T> comparator){
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
            this.comparator = comparator;
        }
        public boolean isEmpty(){
            return heapSize == 0;
        }
        public int size(){
            return heapSize;
        }
        public boolean contains(T key){
            return indexMap.containsKey(key);
        }
        public void push(T value){
            heap.add(value);
            indexMap.put(value,heapSize);
            heapInsert(heapSize++);
        }
        public T pop(){
            T ans = heap.get(0);
            int end = heapSize - 1;
            swap(0,end);
            heap.remove(end);
            indexMap.remove(ans);
            heapify(0,--heapSize);
            return ans;
        }
        public void resign(T value){
            int valueIndex = indexMap.get(value);
            heapInsert(valueIndex);
            heapify(valueIndex,heapSize);
        }
        private void heapInsert(int index){
            while (comparator.compare(
                    heap.get(index),heap.get((index - 1)/ 2)) < 0){
                swap(index,(index - 1)/ 2);
                index = (index - 1)/ 2;
            }
        }
        private void heapify(int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize){
                int smallest = left + 1 < heapSize &&
                        comparator.compare(
                                heap.get(left + 1),heap.get(left)) < 0 ? left + 1: left;
                smallest = comparator.compare(
                        heap.get(smallest),heap.get(index)) < 0 ? smallest : index;
                if(smallest == index){
                    break;
                }
                swap(smallest,index);
                index = smallest;
                left = index * 2 + 1;
            }
        }
        private void swap(int i,int j){
            T o1 = heap.get(i);
            T o2 = heap.get(j);
            heap.set(i,o2);
            heap.set(j,o1);
            indexMap.put(o1,j);
            indexMap.put(o2,i);
        }
    }
}
