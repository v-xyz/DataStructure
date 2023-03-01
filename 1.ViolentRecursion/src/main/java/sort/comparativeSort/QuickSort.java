package sort.comparativeSort;

public class QuickSort {
    public static class QuickSort1{
        // A utility function to swap two elements
        static void swap(int[] arr, int i, int j)
        {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        /* This function takes last element as pivot, places
           the pivot element at its correct position in sorted
           array, and places all smaller (smaller than pivot)
           to left of pivot and all greater elements to right
           of pivot */
        static int partition(int[] arr, int low, int high)
        {

            // pivot
            int pivot = arr[high];

            // Index of smaller element and
            // indicates the right position
            // of pivot found so far
            int i = (low - 1);

            for(int j = low; j <= high - 1; j++)
            {

                // If current element is smaller
                // than the pivot
                if (arr[j] < pivot)
                {

                    // Increment index of
                    // smaller element
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
            return (i + 1);
        }

        /* The main function that implements QuickSort
                  arr[] --> Array to be sorted,
                  low --> Starting index,
                  high --> Ending index
         */
        public static void quickSort(int[] arr, int low, int high)
        {
            if (low < high)
            {

                // pi is partitioning index, arr[p]
                // is now at right place
                int pi = partition(arr, low, high);

                // Separately sort elements before
                // partition and after partition
                quickSort(arr, low, pi - 1);
                quickSort(arr, pi + 1, high);
            }
        }
    }

    public static class QuickSort2{
        /**
         * 为了将数组对于指定的数分为小于区，等于区和大于区，准备两个初始下标为 -1 和 数组长度的
         * 小于区域 和 大于区域, 指针 i 从数组下标为 0 的位置开始遍历
         * 1) [i] == num, i++
         * 2) [i] < num, [i] 与 小于区域 右一个交换，小于区域右扩，i++
         * 3) [i] > num, [i] 与 大于区域 左一个交换，大于区域左扩，i停在原地
         */
        //在 arr[L...R] 范围上取最后的 R 按上述规则划分,返回等于区域的左边界和右边界
        public static int[] divide(int[] arr,int L,int R){
            if(L > R){
                return new int[]{ -1 , -1 };
            }
            if(L == R){
                return new int[]{ L , R };
            }
            int less = L - 1; //小于区域
            int more = R; //大于区域
            int index = L; //初始下标
            while (index < more){
                if(arr[index] == arr[R]){
                    index++;
                }else if(arr[index] < arr[R]){
                    swap(arr,index++,++less);
                }else {
                    swap(arr,index,--more);
                }
            }
            swap(arr, index, R);
            return new int[]{less+1,more};
        }
        static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        /*
        * 快排思路：
        * 随机选择数组中一个数（假设为 X）,然后将其放到数组最后一个位置 [a,...b,X]，随后将其在
        * [a,...,b]分为三个区域 [<X区域,=x区域,>X区域]，将 X 放在 > 区域前面的第一个位置,
        * 组成新数组[<X区域,=x区域,X,>X区域], 对 <X区域 和 >X区域 分别按相同方法进行递归即可。
        *
        * */

        public static void quickSort(int[] arr){
            if (arr == null || arr.length < 2) {
                return;
            }
            process(arr,0, arr.length - 1);
        }
        public static void process(int[] arr,int L,int R){
            if(L >= R){
                return;
            }
            swap(arr, L + (int)(Math.random()*(R - L + 1)),R);
            int[] equalArea = divide(arr,L,R);
            process(arr, L, equalArea[0] - 1);
            process(arr, equalArea[1] + 1, R);
        }

    }

}
