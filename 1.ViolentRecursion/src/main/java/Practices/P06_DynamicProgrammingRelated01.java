package Practices;

/**
 * 对于一个题，先找到暴力递归方法，如果发现有重复解，如果不讲究组织，就用记忆化搜索[缓存]，如果做
 * 精细化组织，则考虑用动态规划.通常，如果决策过程中无枚举行为【只依赖有限的子状态】，则记忆化搜索与
 * 动态规划的时间复杂度没有区别（这种情况下，笔试过程中为了省时间可以不用改动态规划）
 * 动态规划过程中，捕捉可变参数一定不要是整型以上（比如可变的数组作为参数）
 *
 * 题目：
 * 假设有排成一行的 N 个位置，记为 1~N, N 一定大于或等于 2
 * 开始时机器人在其中的 M 位置上（ M 一定是 1~N 中的一个 ）
 * 如果机器人来到 1 位置， 那么下一步只能往右来到 2 位置
 * 如果机器人来到 N 位置， 那么下一步只能往左来到 N-1 位置
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走
 * 规定机器人必须走 K 步，最终能来到 P 位置（ P也是 1~N 中的一个 ）的方法有多少种
 * 给定四个参数 N, M, K, P,返回方法数
 */
public class P06_DynamicProgrammingRelated01 {

    public static int ways1(int N, int M, int K, int P){
        //参数无效直接返回 0
        if(N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N){
            return 0;
        }
        //总共 N 个位置，从 M 点出发，还剩 K 步，返回最终能到达的 P 的方法数
        return walk(N, M, K, P);
    }
    /*
    * N: 位置为 1 ~ N，固定参数
    * cur: 当前在 cur位置，可变参数
    * rest: 还剩 rest 步没有走，可变参数
    * P: 最终目标位置是 P,固定参数
    * 该函数的含义：只能在 1~N 位置上移动，当前在 cur 位置，走完 rest 步之后，停在 P位置的方法
    * */
    public static int walk(int N, int cur, int rest, int P){
        /*
        * 没有剩余步数了，当前的 cur 位置就是最后的位置
        * 如果顺利到达 P位置，代表之前的步数有效，返回一种方法，否则返回 0 代表之前步数无效
        * */
        if(rest == 0){
            return cur == P ? 1 : 0;
        }
        /*
        * 如果还有 rest 步要走，而当前的 cur 位置在 1 位置上，那么当期这步只能从 1 走向 2
        * */
        if(cur == 1){
            return walk(N, 2, rest - 1, P);
        }
        //同理，只能走向 N - 1 位置
        if(cur == N){
           return  walk(N, N - 1, rest - 1, P);
        }
        /*
        * 如果还有 rest 步要走，而当前的 cur 位置在中间位置，那么当前这步可以走向左，
        * 也可以走向右，走向左和走向右是截然不同的方法，所以总方法数要加上
        * */
        return walk(N, cur + 1, rest - 1, P) + walk(N, cur - 1, rest - 1, P);
    }

    //由于可能在某种情况下重复出现 比如来到 3 位置，还有 2 步可以走的情况，递归会重复的去计算这些值，
    //考虑设计一个缓存，使得下次出现同样的情况，直接去缓存中取值
    //注意: 有些暴力递归过程种不会产生重复解，所以不需要缓存
    public static int ways2(int N, int M, int K, int P){
        if(N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N){
            return 0;
        }
        int[][] dp = new int[N+1][K+1];
        for(int row = 0; row <= N; row++){
            for(int col = 0; col <= K; col++){
                dp[row][col] = -1; //初始状态设置为 -1
            }
        }
        return walk2(N, M, K, P, dp);
    }
    public static int walk2(int N, int cur, int rest, int P, int[][] dp){
        if(dp[cur][rest] != -1){
            return dp[cur][rest];
        }
        //每一次都先加缓存再返回
        if(rest == 0){
            dp[cur][rest] = cur == P ? 1 : 0;
            return dp[cur][rest];
        }
        if(cur == 1){
            dp[cur][rest] = walk2(N, 2, rest - 1, P, dp);
            return dp[cur][rest];
        }
        if(cur == N){
            dp[cur][rest] = walk2(N, N - 1, rest - 1, P, dp);
            return dp[cur][rest];
        }
        dp[cur][rest] = walk2(N, cur + 1, rest - 1, P,dp) + walk2(N, cur - 1, rest - 1, P,dp);
        return dp[cur][rest];
    }
    /*
     * 利用出上面的缓存形式，我们来推导出动态规划。
     * 我们将上述的 dp[cur][rest] 看成是一个二维表，假设 N=7, M=2, K=5, P=3
     * 有以下二维表：
     *      0   1   2   3   4   5  <- rest
     *  1
     *  2
     *  3
     *  4
     *  5
     *  6
     *  7
     *  cur
     *
     *  rest == 0
     *  =>
     *  dp[cur][rest] = cur == P ? 1 : 0; 【P=3】
     *  表明 第一列为 0 时，只有行数等于 P 也就是 3时，值才为 1，其他都是 0
     *  =>
     *      0   1   2   3   4   5  <- rest
     *  1   0
     *  2   0
     *  3   1
     *  4   0
     *  5   0
     *  6   0
     *  7   0
     *  cur
     *
     * cur == 1
     * =>
     * dp[cur][rest] = walk2(N, 2, rest - 1, P, dp);
     * 表明：第一行的值 [1,rest] 等于 [2,rest - 1]，即第一行的值等于左下方的值
     *
     * cur == N [N = 7]
     * =>
     * dp[cur][rest] = walk2(N, N - 1, rest - 1, P, dp);
     * 表明：最后一行的值 [7,rest] 等于 [6,rest - 1],即最后一行的值等于左上方的值
     *
     * dp[cur][rest] = walk2(N, cur + 1, rest - 1, P,dp) + walk2(N, cur - 1, rest - 1, P,dp);
     * 同理，不是第一行和最后一行的值，等于左上方和左下方值的和
     * 于是填充表：
     *      0   1   2   3   4   5  <- rest
     *  1   0   0   1   0   3   0
     *  2   0   1   0   3   0   9
     *  3   1   0   2   0   6   0
     *  4   0   1   0   3   0   10
     *  5   0   0   1   0   4   0
     *  6   0   0   0   1   0   5
     *  7   0   0   0   0   1   0
     *  cur
     *
     * 由于 M=2, K=5, 找到 [2.5] = 9，最后答案为 9
     *
     * 有以下结论：
     * 所有动态规划的问题都来自于暴力递归
     *
     * 这道题有两个可变参数 cur 和 rest, 所以是一张二维表，
     * 对于一个可变参数，就是一张一维表，三个可变参数，就是三维表
     * 以下四种模型可以使用动态规划:
     * 1) 从左往右的尝试模型
     * 2) 范围上的尝试模型
     * 3) 多样本位置全对应的尝试模型
     * 4) 寻找业务限制的尝试模型
     */
}
