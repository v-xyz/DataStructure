package Practices;

/**
 * 题目:
 * N皇后问题是指在 N * N 的棋盘上要摆 N 个皇后，要求任何两个皇后不同行，不同列，
 * 也不在同一条斜线上，给定一个整数 n, 返回 n皇后的摆法有多少种。
 * n = 1,返回 1
 * n = 2 或 3，怎么摆都不满足要求，返回 0，
 * n = 8,返回 92
 */
public class P05_ViolentRecursionRelated08 {
    /*
    * 在一行上摆皇后，解决同行问题，然后注意不共列，最后注意不共斜线，
    * 假设在第一行摆 (X0,Y0),第二行(X1,Y1)...第N行(X[n-1],Y[n-1])
    * 假设有某一行任何位置都摆不下去，则证明上一行摆的方式无法产生有效解，
    * 退回到上一行重新摆一个满足之前条件新位置，然后继续摆下一行，如果都
    * 不满足，则证明上上行摆的方式不对，依次类推
    * */

    public static int num1(int n){
        if(n < 1){
            return 0;
        }
        int[] record = new int[n]; //record[i] 表示 i行的皇后放在了第几列
        return process1(0, record, n);
    }

    /*
    * record[0...i-1]的皇后，任何两个皇后都不同行，不同列，不共斜线，
    * record[0...i-1]表示之前行放了的皇后位置
    *
    * 现在来到了第i行， n 代表整体有多少行
    *
    * 返回值代表的是：摆完所有的皇后，合理的摆法有多少种
    * */
    public static int process1(int i, int[] record, int n){
        if(i == n){ //终止行
            return 1;
        }
        int res = 0;

        //当前行在 i 行，尝试 i 行所有的列 -> j
        for(int j = 0;j < n; j++){
            /*
            * 当前 i 行的皇后放在 j 列会不会和之前 (0..i-1)行的皇后，不共行，不共列且不共斜线
            * 如果是，认为有效
            * 如果不是，认为无效
            * */
            if(isValid(record, i, j)){
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }
    /*
    * record[0..i-1]需要看，record[i...]不需要看
    * 返回 i行皇后放在j列是否有效的 boolean值
    * */
    public static boolean isValid(int[] record, int i, int j){
        for(int k = 0;k < i; k++){
            //任何一个皇后在 k行 record[k]列位置 (k,record[k])
            //现在传入的是在 i行 j列            (i ,    j   )
            if(j == record[k] //[表示不在一列上]（初始定义已经保证不在一行上）
                    ||
                    //列相减 == 行相减 [表示不在一条斜线上]
                    Math.abs(record[k] - j) == Math.abs(i - k)){
                return false;
            }
        }
        return true;
    }
    //在时间复杂度无法改进的情况下，对于执行效率有以下优化
    /*
    * 以 8 皇后举例
    * 定义 8 列，假设放了皇后的位置为 1，没放皇后的位置为 0，假设一开始随机两个位置放了皇后
    *       0   1   2   3   4   5   6   7
    * ------------------------------------------
    *       0   0   1   0   0   0   0   0
    *       1       0
    *       0       0
    *       0       0
    *       0       0
    *       0       0
    *       0       0
    *       0       0
    * 可以看到对于第一列和第三列 有 01000000 和 10000000 可以看作一个二进制数，不够位数补齐 0 即可
    * 可以看出有三个限制条件：之前皇后已经放在了哪些列，之前皇后放的左斜线和右斜线
    * 假设皇后还没开始放，准备这仨个条件的二进制（由于没开始放都为 0, | 左边为补齐内容）
    * 列的限制       0... 00|00000000
    * 左斜线的限制    0... 00|00000000
    * 右斜线的限制    0... 00|00000000
    *
    * 假设第一个皇后放在第一列第一行
    *           0   1   2   3   4   5   6   7
    * ------------------------------------------
    *           1   0   0   0   0   0   0   0
    *       x   x   x   0   0   0   0   0   0    x为下一次放皇后限制的位置
    * 列的限制变为（1代表限制位置）
    *       0... 00|10000000
    * 左斜线的限制就相当于左移一位
    *       0... 01|00000000
    * 右斜线的限制就相当于右移一位
    *       0... 00|01000000
    *
    * colLim 列的限制
    * leftDiaLim 左斜线的限制
    * rightDiaLim 右斜线的限制
    * */
    //注意不能超过32皇后问题
    public static int num2(int n){
        if(n < 1 || n > 32){
            return 0;
        }
        /*
        * -1 的二进制在 32位上都是 1
        * (1 << n) - 1 表示二进制上右边 n 个 位置都是 1
        * 比如 n = 3
        * 0000 0001 1左移3位
        * 0000 1000 8
        * 0000 0111 7 右边有三个 1
        *
        * limit划定了问题的规模，指定以后固定不变
        * */
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit,0,0,0);
    }

    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim){
        if(colLim == limit){ // base case
            return 1;
        }

        /*
        * colLim | leftDiaLim | rightDiaLim -> 总限制
        * 比如
        *     0... 00|10000000   |
        *     0... 01|00000000   |
        *     0... 00|01000000
        * ->  0... 01|11000000
        *
        * 然后取反的结果
        *     1... 10|00111111
        * 表示为 1 的位置可以摆皇后，为 0 的位置不能摆,但是由于前面无效补齐的位也变成 1了，
        * 所以要将前面的补齐位的 1 变成 0
        * 所以使用 limit ,即
        *       0... 00|11111111    & (&操作所有位置只取相同值)
        *       1... 10|00111111
        *->     0000000|00111111  最后的 1 表示皇后能放置的位置
        *
        * */
        //所有候选皇后的位置都在pos上
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));


        int mostRightOne = 0;
        int res = 0;
        while (pos != 0){
            /*
             * 有了可以放置的位置以后，只需要每次提取最右侧的 1 来
             * */
            mostRightOne = pos & (~pos + 1); //取出最右侧的 1

            pos = pos - mostRightOne;
            res += process2(limit,
                    colLim | mostRightOne,
                    /*
                    *       0...0|00100000 第一行
                    *       0...0|00000001 第二行
                    * 左限制：
                    *       0...0|10000010
                    * */
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }
}
