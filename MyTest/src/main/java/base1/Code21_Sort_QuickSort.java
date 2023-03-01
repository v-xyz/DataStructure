package base1;

/**
 * 对于给定的num,
 * 如果 [i] = num , i++
 * 如果 [i] < num 将当前数和小于区的右一个数交换，小于区域右扩,然后 i++
 * 如果 [i] > num, 将当前数和大于区的左一个数交换，大于区域左扩,i位置不变
 * 当 i 与 大于区域的左边界撞上时停止移动
 */
public class Code21_Sort_QuickSort {
    public static void quickSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr,0, arr.length - 1);
    }
    private static void process(int[] arr,int L,int R){
        if(L >= R){
            return;
        }
        swap(arr, L + (int)(Math.random()*(R - L + 1)),R);

        int[] equalArea = devideUtil(arr,L,R);
        process(arr, L, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, R);
    }
    //返回等于区域的左右边界
    private static int[] devideUtil(int[] arr, int L, int R){
        if(L > R){
            return new int[]{ -1 , -1 };
        }
        if(L == R){
            return new int[]{ L , R };
        }
        int lessAreaIndex = L - 1;
        int moreAreaIndex = R; //按理说大于区域是 R + 1, 这里是以 R位置作为 num,先放入大于区域不动（大于区域先左扩一个），最后再安排
        int curIndex = L;
        while (curIndex < moreAreaIndex){
            if(arr[curIndex] < arr[R]){
                lessAreaIndex++;
                swap(arr,curIndex,lessAreaIndex);
                curIndex++;
            }else if(arr[curIndex] > arr[R]){
                moreAreaIndex--;
                swap(arr,curIndex,moreAreaIndex);
            }else {
                curIndex++;
            }
        }
        //此时curIndex来到 moreAreaIndex的第一个位置，但作为比较的R还在最后，将curIndex位置的值与 R交换即可
        swap(arr,curIndex,R);
        return new int[]{lessAreaIndex + 1,curIndex};
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
