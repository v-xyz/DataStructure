package base1;

import java.util.Arrays;

public class Code23_Sort_CountingSort {

    public static int[] sort(int[] arr){
        int[] result = new int[arr.length];
        int[] count = new int[findMax(arr) + 1];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        System.out.println(Arrays.toString(count));

        //不稳定
//        for(int i=0, j=0; i< count.length; i++){
//            while (count[i]-- > 0){
//                result[j++] = i;
//            }
//        }

        //以下方法可以变稳定
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i-1];
        }
        System.out.println(Arrays.toString(count));
        for(int i = arr.length - 1; i >= 0; i--){
            result[--count[arr[i]]] = arr[i];
        }

        return result;
    }
    private static int findMax(int[] arr){
        int max = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] > max)
            {
                max = arr[i];
            }
        }
        return max;
    }
}
