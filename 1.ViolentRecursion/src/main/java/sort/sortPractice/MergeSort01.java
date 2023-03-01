package sort.sortPractice;

/**
 * 题：
 * 在一个数组中，一个数左边比它小的数的总和，叫做数的小和，所有数的小和累加起来，
 * 叫做组小和，求数组小和
 * 例如对于数组 [1,3,4,2,5],
 * 其小和为： 1 + 1 + 3 + 1 + 1 + 3 + 4 + 2 = 16
 *        【3】    【4】【2】            【5】
 */
public class MergeSort01 {
    /*
    * 思路：
    * 考虑这样一个事实：对于有序数组，计算数组的小和将变得非常容易，
    *
    * 再考虑：对于两个有序数组，计算小和只需要循环让第一个有序数组的元素与第二个数组比较，
    * 一旦小于第二个数组中的某个值，第二个数组中后续的其他值也会比这个元素大。
    *
    * 结合以上两点得出解题方法：只需要通过归并排序的思路，将局部不有序数组转为有序数组【
    * 过程中先计算出其小和，再将其看成整体】，然后再对有序数组进行小和计算即可。
    *
    * 实现细节：准备一个 help数组，用来存放最终的数组的有序形式，对于两个有序数组 A 和 B,
    * 如[a1，a2...,an],[b1,b2...bm]，将 A 中所有元素逐个与 B 数组中的元素进行比较，将
    * 较小的值放入 help 数组，例如如果 a1 < b1,则将 a1 放入 help 数组，随后计算小和
    * 【m个a1】，将 A数组的指针向后移动，a2继续与b1进行比较，依次类推，如果 a2 > b1,
    * 则将 b2 放入 help 数组， B数组的指针向下移动，a2 与 b2进行比较，依次类推。
    * 最后得到一个更大的有序数组，以及两个数组间比较的小和，最后更大的有序数组再按照相同
    * 的方式进行比较，用这种递归的方式计算出所有的小和相加即可。
    *
    * */
    public static class SmallSum{
        public static int smallSum(int[] arr){
            if (arr == null || arr.length < 2) {
                return 0;
            }
            return process(arr,0,arr.length - 1);
        }
        // arr[L...R] 既要排好序，也要求小和返回
        public static int process(int[] arr,int L,int R){
            if(L == R){
                return 0;
            }
            int mid = L + ((R - L) >> 1);
            return
                    process(arr,L,mid)+
                    process(arr,mid + 1,R)+
                    merge(arr,L,mid,R);
        }
        public static int merge(int[] arr,int L, int M, int R){
            int[] help = new int[R - L + 1];
            int i = 0;
            int p1 = L;
            int p2 = M + 1;
            int res = 0;
            while (p1 <= M && p2 <= R){
                res += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
                help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
            }
            while (p1 <= M){
                help[i++] = arr[p1++];
            }
            while (p2 <= M){
                help[i++] = arr[p2++];
            }
            for (int j = 0; j < help.length; j++) {
                arr[L + j] = help[j];
            }
            return res;
        }
    }
}
