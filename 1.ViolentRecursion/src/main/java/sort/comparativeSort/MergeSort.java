package sort.comparativeSort;

/**
 * 归并排序[时间复杂度为 N*logN ]：
 * 概括：整体是递归，左边和右边分别排好序，然后merge再让整体变有序
 * 思路：
 * 对于给定的数组 A,从中间分成两半分别为 B 和 C,将 B 和 C分别排好序，
 * 随后准备一个和 A 长度相同的空数组 D,从 B 和 C 的起始位置开始比较（初始指针位置），
 * 将起始位置较小的值放入D数组，随后将较小值所在的数组的指针向下移动一位，再进行比较，
 * 依次类推，总是将比较的较小的值放入D数组，并将对应数组的指针向下移动，直到 B 和 C
 * 其中一个数组已无法继续向下移动，将另一个数组剩余的值依次放入D数组中
 */
public class MergeSort {
    public static void mergeSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process(arr,0, arr.length - 1);
    }
    // arr[L...R] 范围上变成有序的.
    public static void process(int[] arr,int L, int R){
        if(L == R){
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr,L,mid);
        process(arr,mid+1,R);
        merge(arr,L,mid,R);
    }
    public static void merge(int[] arr,int L,int M,int R){
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        while (p2 <= R){
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }


    public static class otherWay{
        public static void mergeSort(int[] array)
        {
            if(array == null)
            {
                return;
            }

            if(array.length > 1)
            {
                int mid = array.length / 2;

                // Split left part
                int[] left = new int[mid];
                for(int i = 0; i < mid; i++)
                {
                    left[i] = array[i];
                }

                // Split right part
                int[] right = new int[array.length - mid];
                for(int i = mid; i < array.length; i++)
                {
                    right[i - mid] = array[i];
                }
                mergeSort(left);
                mergeSort(right);

                int i = 0;
                int j = 0;
                int k = 0;

                // Merge left and right arrays
                while(i < left.length && j < right.length)
                {
                    if(left[i] < right[j])
                    {
                        array[k] = left[i];
                        i++;
                    }
                    else
                    {
                        array[k] = right[j];
                        j++;
                    }
                    k++;
                }
                // Collect remaining elements
                while(i < left.length)
                {
                    array[k] = left[i];
                    i++;
                    k++;
                }
                while(j < right.length)
                {
                    array[k] = right[j];
                    j++;
                    k++;
                }
            }
        }

        // Driver program to test above functions.
        public static void main(String[] args)
        {
            int arr[] = {12, 11, 13, 5, 6, 7};
            int i=0;
            System.out.println("Given array is");

            for(i=0; i<arr.length; i++)
                System.out.print(arr[i]+" ");

            mergeSort(arr);

            System.out.println("\n");
            System.out.println("Sorted array is");

            for(i=0; i<arr.length; i++)
                System.out.print(arr[i]+" ");
        }
    }

}
