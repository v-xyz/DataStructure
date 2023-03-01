package sort.comparativeSort;

import java.util.Arrays;

public class InsertSort {
    static void sort(int arr[])
    {
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i]; //key用来存数组 i位置的值
            int j = i - 1;


            while (j >= 0 && arr[j] > key) {
                //将 j 位置的值赋值给 j + 1位置
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
    public static void main(String[] args) {
        int a[] = { 97,76,65,38,13,27,49 };
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
