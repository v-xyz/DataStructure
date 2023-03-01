package Practices;

/**
 * 题目：[从左往右的尝试模型]
 * 规定 1 和 A对应，2 和 B对应，3 和 C对应，
 * 那么一个数字字符串比如"111"就可以转化为 "AAA","KA" 和 "AK"
 * 给定一个只有数字字符组成的字符串str,返回有多少中转化结果
 */
public class P05_ViolentRecursionRelated05 {
    public static int number(String str){
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(),0);
    }
    /*
    * str[0...i-1]已经转化完了，固定了
    * i之前的位置，如何转化已经做过决定了，不用再关心,只看 i往后有多少中转化的结果
    * */
    public static int process(char[] str, int i){
        if(i == str.length){ //来到终止位置
            return 1; //之前所有转化完成的部分共同构成一种转化结果
        }
        if(str[i] == '0'){ //没有任何字符可以和 ‘0’ 转化
            return 0;
        }
        //str[i]字符不是 '0' 且 i没有到终止位置

        if(str[i] == '1'){
            int res = process(str,i + 1); // i自己作为单独的部分，让 i+1 位置进行递归
            if(i + 1 < str.length){
                res += process(str, i + 2); // i 和 i+1 作为单独的部分，让后 i+2 位置进行递归
            }
            return res;
        }
        if(str[i] == '2'){
            int res = process(str,i + 1);
            // i 和 i+1 作为单独的部分并且没有超过26，后续有多少种方法
            if(i + 1< str.length && str[i + 1] <= '6'){
                res += process(str, i + 2);
            }
            return res;
        }
        /*
         * str[i] 如果是 3 至 9,没有第二种决策，只有一种选择，
         * */
        return process(str, i + 1);
    }

    /*
    * 动态规划过程
    * */
    public static int dpWays(String s){
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];

//        if(i == str.length){
//            return 1;
//        }
        dp[N] = 1;
        /*
        * 发现 i位置的值依赖于 i+1位置和 i+2位置的值，所以要从右往左直到 0的得到值，
        * 从右往左填表
        * */
        for(int i = N-1; i>=0 ;i--){
            //根据process过程改成动态规划
            if(str[i] == '0'){
                dp[i] = 0; //return 0;
            }
            if(str[i] == '1'){
//                int res = dp[i+1]; //process(str,i + 1);
//                if(i + 1 < str.length){
//                    res += dp[i+2];//process(str, i + 2);
//                }
//                dp[i] = res;
                //对以上进一步简化，不要res变量
                dp[i]= dp[i+1];
                if(i + 1 < str.length){
                    dp[i] += dp[i+2];
                }
            }
            if(str[i] == '2'){
//                int res = dp[i+1];//process(str,i + 1);
//                if(i + 1< str.length && str[i + 1] <= '6'){
//                    res += dp[i+2];//process(str, i + 2);
//                }
//                dp[i] = res;
                dp[i]= dp[i+1];
                if(i + 1< str.length && str[i + 1] <= '6'){
                    dp[i] += dp[i+2];
                }
            }else {
                //return process(str, i + 1);
                dp[i] = dp[i+1];
            }
        }
        return dp[0];
    }

}
