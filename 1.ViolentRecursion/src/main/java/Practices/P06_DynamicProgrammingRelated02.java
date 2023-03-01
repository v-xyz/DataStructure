package Practices;

import java.util.HashMap;

/**
 * 存在一个数组 arr, 数组中的每个值都对应货币的一种币值，且每种币值的货币都可以使用任意张,
 * 币值都是正数且都在 arr中无重复值,对于给定的金钱数 mon, 在只使用 arr中提供的币值的
 * 情况下，问有多少种方法可以正好凑到 mon 数的钱
 */
public class P06_DynamicProgrammingRelated02 {
    public static int ways1(int[] arr, int mon){
        if (arr == null || arr.length == 0 || mon < 0) {
            return 0;
        }
        return process1(arr,0, mon);
    }
    //定义递归函数 f(i,rest),表示使用 arr[i..]的币值凑到 剩余的 rest金钱的总方法数
    public static int process1(int[] arr, int index, int rest){
        if(rest < 0){
            return 0;
        }
        if(index == arr.length){ //没有货币可以选择
            return rest == 0 ? 1 : 0;
        }

        int ways = 0;
        //num表示使用了多少张同种币值的货币
        for(int num = 0; num * arr[index] <= rest; num++){
            ways += process1(arr,index + 1,rest - num * arr[index]);
        }
        return ways;
    }

    /*
    * 改记忆化搜索【有重复行为：从第几号货币开始选择所差金额为多少】
    * */
    public static int ways2(int[] arr, int mon){
        if (arr == null || arr.length == 0 || mon < 0) {
            return 0;
        }
        // index=3 rest=900 在map中不妨用 key="3_900" 的形式表示
        // HashMap<String,Integer> map = new HashMap<>();
        // 可以用Map来缓存，但有一个将 index=3 rest=900 转换成 key="3_900" 的过程

        //也可以用数组作为缓存表来实现
        int[][] dp = new int[arr.length + 1][mon + 1];
        //一开始所有的过程都没有计算，在缓存表中将初始值设置为 -1
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(arr,0, mon,dp); //带着 dp
    }
    /*
    * 如果 index 和 rest 的参数组合是没算过的，则 dp[index][rest] == -1
    * 如果 index 和 rest 的参数组合是算过的，则 dp[index][rest] > -1
    * */
    public static int process2(int[] arr, int index, int rest, int[][] dp){
        //算过，直接给缓存值
        if(dp[index][rest] != -1){
            return dp[index][rest];
        }

        //如果没算过，在后续return语句前要考虑先将return的值放入缓存中，然后然后这个return的值

        if(rest < 0){
            return 0;
        }
        if(index == arr.length){
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }

        int ways = 0;
        for(int num = 0; num * arr[index] <= rest; num++){
            ways += process2(arr,index + 1,rest - num * arr[index],dp);
        }
        dp[index][rest] = ways;
        return ways;
    }

    /*
    * 改成动态规划
    * 注意: 由于动态规划是做的最细粒化的划分，在[N + 1][mon + 1]上每一个位置都准备了表格值，如果对于哪些
    * 比如 币值为{100,200,500} 总货币要求 100万的情况，因为永远不可能出现 1,2,3这类的值，所以表格会非常庞大
    * 有很多表格的值是无效的，此时记忆化搜索就可以体现它的优势
    * */
    public static int ways3(int[] arr, int mon){
        if (arr == null || arr.length == 0 || mon < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][mon + 1];

//        if(index == N){
//            return rest == 0 ? 1 : 0;
//        }
        dp[N][0] = 1; //dp[N][1...mon] = 0 默认已经是 0

        /*
        * ways += process1(arr,index + 1,rest - num * arr[index]);
        * 计算 index 行的数据要依赖于 index + 1行，所以整体顺序一定是从最后一行从下往上推
        * */
        for (int index = N - 1; index >= 0; index--){
            for(int rest = 0;rest <= mon; rest++){ //也可以从右往左

//              int ways = 0;
//              for(int num = 0; num * arr[index] <= rest; num++){
//                  ways += process1(arr,index + 1,rest - num * arr[index]);
//              }

                int ways = 0;
                for(int num = 0; num * arr[index] <= rest; num++){
                    ways += dp[index + 1][rest - num * arr[index]];
                    // +=dp[...][rest - num * arr[index]] 仍然存在可优化空间
                }

                dp[index][rest] = ways;
            }
        }

        return dp[0][mon];
    }

    //动态规划的进一步优化[记忆化搜素无法做到]
    public static int ways4(int[] arr, int mon){
        if (arr == null || arr.length == 0 || mon < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][mon + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--){
            for(int rest = 0;rest <= mon; rest++){
                for(int num = 0; num * arr[index] <= rest; num++){
                    //ways += dp[index + 1][rest - num * arr[index]];

                    /*
                    * ways + 的过程中一定会加上 num = 0时 ways += dp[index + 1][rest - 0 * arr[index]];即
                    * ways += dp[index + 1][rest]的值
                    *
                    * 所以 dp[index][rest]永远可以获得 dp[index + 1][rest]的值
                    * */
                    dp[index][rest] = dp[index + 1][rest];

                    if(rest - arr[index] >= 0){
                        dp[index][rest] += dp[index][rest - arr[index]];

                    }
                }
            }
        }
        return dp[0][mon];
    }

    public static void main(String[] args) {
        int[] arr = {5,10,50,100};
        int sum = 1000;
        System.out.println(ways1(arr,sum));
        System.out.println(ways2(arr,sum));
        System.out.println(ways3(arr,sum));
    }
}
