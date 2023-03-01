package sort.nonComparativeSort;

/**
 * 计数排序只有范围比较窄的情况下才便于使用。其与数据状况强相关
 *
 * 桶排序（包括计数和基数排序）是不基于比较的排序，虽然其时间复杂度可以达到 O(n)
 * 但其对数据状况有要求，如果数据发生状况外的变动，很可能代码将大量重写
 *
 * 在刷题中，除非题目有特殊声明，否则一律使用基于比较的排序
 */
public class BucketSort_CountingSort {
    static void sort(char arr[]) {
        int n = arr.length;

        // The output character array that will have sorted arr
        char output[] = new char[n];

        // Create a count array to store count of inidividul
        // characters and initialize count array as 0
        int count[] = new int[256];
        for (int i = 0; i < 256; ++i)
            count[i] = 0;

        // store count of each character
        for (int i = 0; i < n; ++i)
            ++count[arr[i]];

        // Change count[i] so that count[i] now contains actual
        // position of this character in output array
        for (int i = 1; i <= 255; ++i)
            count[i] += count[i - 1];

        // Build the output character array
        // To make it stable we are operating in reverse order.
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            --count[arr[i]];
        }

        // Copy the output array to arr, so that arr now
        // contains sorted characters
        for (int i = 0; i < n; ++i)
            arr[i] = output[i];
    }
    
}
