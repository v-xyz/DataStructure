package sort.nonComparativeSort;

import java.util.Arrays;

/**
 * 基数排序一般只针对正数的十进制数进行排序
 * 时间复杂度 O(n* log10 max) 以10为底的log max, 由于一般 log10 max 不会很大
 * 所以在通常情况下也可看作是 O(n)
 */
public class BucketSort_RadixSort {
    public static class RadixSort1{
        public static void radixSort(int[] arr){
            if(arr == null || arr.length < 2){
                return;
            }

        }
        //计算 arr数组最大数有多少位
        public static int maxbits(int[] arr){
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < arr.length; i++) {
                max = Math.max(max,arr[i]);
            }
            int res = 0;
            while (max != 0){
                res++;
                max /= 10;
            }
            return res;
        }

        /*
        * digit表示在 arr数组在下标 L 到 R上最大值有多少位
        * 该方法是对数组下标 [L,R] 范围内进行排序
        *
        * 思路：找到最大数，把其余数前面用0补齐，准备0-9十个桶
        * 让数组根据个位数字进桶，然后按0-9桶的顺序依次出桶依次放入数组(先进先出，后进后出)，
        * 然后根据十位数字进桶。。依次向最高位进桶与出桶
        * */
        public static void radixSort(int[] arr,int L,int R,int digit){
            //以 10 为基底
            final int radix = 10;
            int i = 0,j = 0;
            //准备等长的数组
            int[] help = new int[R - L + 1];

            for(int d = 1;d <= digit;d++){
                //这里 count数组固定为 10
                int[] count = new int[radix];

                for (i = L; i <= R; i++){
                    j = getDigit(arr[i], d); //拿到个位下标
                    count[j]++; //统计j值分布在count数组 0-9下标的词频
                }

                for (i = 1; i < radix; i++){
                    //依次相加，得到 count[i] = k,表示该位上 <= i 的数有 k 个
                    count[i] = count[i] + count[i - 1];
                }

                /*
                * 从后往前，最后面的数在对应位上一定也是最后出的，
                * 假设最后一个数对应位为 2，
                * 假设 count[2] = k,表示该位上 <= 2 的数有 k 个,
                * 所以 该数一定在第 k - 1 的位置上【数组下标要减一】
                * 而 k - 1 = count[2] - 1，依次类推
                * */
                for (i = R; i >= L; i--){
                    j = getDigit(arr[i], d);
                    help[count[j] - 1] = arr[i];
                    count[j]--; //已经提取了一次以后就要减掉1
                }
                for (i = L, j = 0; i <= R; i++,j++){
                    arr[i] = help[j];
                }
            }
        }

        public static int getDigit(int x,int d){
            return (x / ((int)Math.pow(10,d - 1))) % 10;
        }

    }


    public static class RadixSort2{
        // A utility function to get maximum value in arr[]
        static int getMax(int arr[], int n)
        {
            int mx = arr[0];
            for (int i = 1; i < n; i++)
                if (arr[i] > mx)
                    mx = arr[i];
            return mx;
        }

        // A function to do counting sort of arr[] according to
        // the digit represented by exp.
        static void countSort(int arr[], int n, int exp)
        {
            int output[] = new int[n]; // output array
            int i;
            int count[] = new int[10];
            Arrays.fill(count, 0);

            // Store count of occurrences in count[]
            for (i = 0; i < n; i++)
                count[(arr[i] / exp) % 10]++;

            // Change count[i] so that count[i] now contains
            // actual position of this digit in output[]
            for (i = 1; i < 10; i++)
                count[i] += count[i - 1];

            // Build the output array
            for (i = n - 1; i >= 0; i--) {
                output[count[(arr[i] / exp) % 10] - 1] = arr[i];
                count[(arr[i] / exp) % 10]--;
            }

            // Copy the output array to arr[], so that arr[] now
            // contains sorted numbers according to current digit
            for (i = 0; i < n; i++)
                arr[i] = output[i];
        }

        // The main function to that sorts arr[] of size n using
        // Radix Sort
        static void radixsort(int arr[], int n)
        {
            // Find the maximum number to know number of digits
            int m = getMax(arr, n);

            // Do counting sort for every digit. Note that
            // instead of passing digit number, exp is passed.
            // exp is 10^i where i is current digit number
            for (int exp = 1; m / exp > 0; exp *= 10)
                countSort(arr, n, exp);
        }
    }

}
