package base1;

public class Code22_Sort_MergeSort {
    public static void mergeSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process(arr,0, arr.length - 1);
    }
    private static void process(int[] arr,int L, int R){
        if(L == R){
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr,L,mid);
        process(arr,mid+1,R);
        merge(arr,L,mid,R);
    }
    //在arr数组 L~M M~R上分别有序的前提下，merge方法负责将arr l~R上变的有序
    private static void merge(int[] arr,int L,int M,int R){
        int[] resultArr = new int[R - L + 1];
        int leftIndex = L;
        int rightIndex = M + 1;
        int i = 0;
        while (leftIndex <= M && rightIndex <= R){
            resultArr[i++] = arr[leftIndex] <= arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++];
        }
        while (leftIndex <= M){
            resultArr[i++] = arr[leftIndex++];
        }
        while (rightIndex <= R){
            resultArr[i++] = arr[rightIndex++];
        }
        for (int j = 0; j < resultArr.length; j++) {
            arr[L + j] = resultArr[j];
        }
    }
}
