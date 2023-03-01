package utils;

import java.util.concurrent.ConcurrentHashMap;

public class Skills {
    public static void main(String[] args) {
        int a = 5;
        int b = a * 2 + 1;
        int b2 = (a << 1) | 1;
        int c = a / 2;
        int c2 = a >> 1;
        System.out.println(b2==b); // true
        System.out.println(c2==c); // true

        int dl = 31415926; //代表一个很大的数
        int dr = 99999999; //代表一个很大的数
        //取得 dl,dr 中位数
        int dMid = (dl + dr) / 2;  // dl + dr可能超过int最大值
        int dMid2 = dl + ((dr - dl) >> 1); // 不会越界
        System.out.println(dMid2==dMid); // true

        //---------------------------------------------------------------

        //异或运算 ^
        //异或运算 就是 无进位相加
        /*
        *   6     ^     7
        * 1 1 0       1 1 1
        *
        *       1 1 0
        *       1 1 1
        *       -----  1 + 1 = 2, 不进位, 结果为 0
        *       0 0 1
        *
        * 异或运算 ^ 的特征性质：
        * 0 ^ N == N
        * N ^ N == 0
        * 异或运算 ^ 满足交换律和结合律,即同一批数无论顺序如何异或的结果相同
        *
        * */
        //异或运算题目1：如何不使用额外变量交换两个数
        int A = -145666;
        int B = 441355;
        A = A ^ B;
        B = A ^ B; // B == (A ^ B) ^ B == A ^ (B ^ B) == A ^ 0 == A
        A = A ^ B; // A == (A ^ B) ^ A == (A ^ A) ^ B == 0 ^ B == B
        /*
        * 注意！ A 和 B 在内存中必须是不同对象，否则报错.
        * 所以，对于同一个数组中两个元素的交换，必须保证下标不一致
        * */

        /*
        * 异或运算题目2：
        * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
        * */
        int[] arr = {1,2,2,3,3,3,3,1,6,6,8,8,8};
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            //与题目1原理相同，对每个数进行异或，最后结果就是出现唯一奇数次的那个数
            eor = eor ^ arr[i];
        }
        System.out.println(eor);

        /*
        * 辅助题目3：
        * 怎么把一个int类型的数，将其二进制形式最右侧的 1 提取出来[其他位表示为0]
        * */
        int num = 24;
        /*
        * ~ 取反，所有位置取相反值
        * & 与，所有位置只取相同值
        * */
        int result = num & ((~num) + 1);
        //如果 num 的二进制需要去除最右侧的 1，可以 num ^= result
        System.out.println(result);

        /*
        * 异或运算题目4：
        * 一个数组中，有两种数都出现了奇数次，其他数出现了偶数次，找到这两种数
        *
        * 思路：
        * 假设数组中 A 和 B出现了奇数次，其他出现了偶数次,则 A 与 B 必定不等，有 A ^ B 不为 0
        * 假设 A 与 B 的二进制在第 N 位上一个为 0， 一个为 1， 那么原数组必定可以在该 N 位
        * 分为两大阵营，一个 N 位为 1， 一个 N 位为 0，且每个阵营除出现偶数次数的数外，一定
        * 各有奇数次的 A 和 B,所以对于每个阵营，题目就变为 题目2 中的情况，而为了找到 N 的位置
        * 需要用到 题目3 的知识
        * */
        int[] arr2 = {1,2,1,1,3,3,1,4,4,4};
        int eor2 = 0;
        for (int i = 0; i < arr2.length; i++) {
            eor2 = eor2 ^ arr2[i];
        }
        //eor2一定最后的值是 A ^ B 的结果
        //找到最右边第一个1,代表这个位置 A 与 B 二进制的值不同
        int rightOne = eor2 & ((~eor2) + 1);

        int eor21 = 0;
        for (int i = 0; i < arr2.length; i++) {
            // (arr2[i] & rightOne) != 0 代表该位置二进制值为1，满足条件的为同一组
            if((arr2[i] & rightOne) != 0){
                //对每组进行异或操作，最后一定值为 A
                eor21 ^= arr2[i];
            }
        }
        System.out.println("A:" + eor21 + "  B:" + (eor2 ^ eor21));

    }
}
