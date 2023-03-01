package Practices;

/**
 * 从左往右的模型2：
 * 题目：
 * 给定两个长度都为 N 的数组 weights 和 values,
 * weights[i] 和 values[i] 分别代表 i号物品的重量和价值
 * 给定一个正数bag,表示一个载重bag的袋子，你装的物品不能超过这个重量，
 * 要求返回你能装下的最多价值
 */
public class P05_ViolentRecursionRelated06 {
    public static int getMaxValue(int[] w, int[] v, int bag){
        return process(w, v, 0, 0, bag);
    }
    /*
    * index往后的货物自由选择
    * alreadyW 表示 0...index-1上做了货物的选择，使得已经达到的重量是多少
    * bag 表示袋子的总载重
    * */
    public static int process(int[] w, int[] v, int index, int alreadyW, int bag){
        if(alreadyW > bag){
            return -1; //如果返回 -1，代表没有方案
        }
        //如果不返回 -1，认为返回的值是一个真实的价值
        if(index == w.length){
            return 0;//index后没货了
        }
        int p1 = process(w, v, index + 1, alreadyW, bag); //没有要当前index 最大价值
        int p2next = process(w, v, index + 1, alreadyW + w[index], bag);
        int p2 = -1;
        if (p2next != -1){ //后面的过程是有效方案
            p2 = v[index] + p2next;
        }
        return Math.max(p1,p2);
    }
    public static int getMaxValue2(int[] w, int[] v, int bag){
        return process2(w,v,0,bag);
    }
    /*
    * 只剩下rest的空间了
    * index...货物可以自由选择，但是不要超过rest的空间
    *
    * */
    public static int process2(int[] w, int[] v, int index,int rest){
        if(rest <= 0){
            return -1;
        }
        if(index == w.length){
            return 0;
        }
        //有货也有空间
        int p1 = process2(w,v,index+1,rest);//不要index货时的价值

        int p2 = -1;
        int p2next = process2(w,v,index+1,rest-w[index]);
        if(p2next != -1){
            p2 = v[index] + p2next;
        }
        return Math.max(p1,p2);
    }

    /*
    * 发现从 index处开始选择，后续还有 rest空间,求最大价值的的情况, f(index,rest)存在重复解
    * index范围： 0 ~ N [数组的长度]
    * rest范围: 0 ~ bag [袋子的重量]
    * 假设 N=5 bag=20
    *       0               20  <- bag
    *   0
    *   1
    *   2
    *   3
    *   4
    *   5
    * index
    *
    * 最后求 f(0,20)位置的值
    *
    * index == w.length => 0
    *
    *       0               20  <- bag
    *   0
    *   1
    *   2
    *   3
    *   4
    *   5   0   0   ... 0   0
    * index
    * */
    //对照上面表
    public static int dpWay(int[] w, int[] v,int bag){
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        // N 初始值都是 0，从 N - 1行开始，从下面的行往上面的行依次填
        for(int index = N - 1; index >= 0; index--){
            //剩余空间从 0 开始一直填到 bag
            for (int rest = 0; rest <= bag; rest++){

                //int p1 = process2(w,v,index+1,rest);
                /*
                * 注意process2的外层是 static int process2(int[] w, int[] v, int index,int rest)
                * 即 dp[index][rest]
                * */
                int p1 = dp[index+1][rest];

                int p2 = -1;
                //int p2next = process2(w,v,index+1,rest-w[index]);
                /*
                * 要保证 rest - w[index] >= 0
                * */
                if(rest - w[index] >= 0){
                    //int p2next = dp[index + 1][rest - w[index]];
                    p2 = v[index] + dp[index + 1][rest - w[index]];
                }
                dp[index][rest] = Math.max(p1,p2);
            }
        }
        return dp[0][bag];
    }
    /*
    * 总结： 暴力递归问题解此题的时间复杂度为 O(2的n次方)【每个位置都要判断放不放物品】 n表示数组长度
    * 但使用动态规划,只取决于表的大小，即时间复杂度为 O(n*bag)
    * */
}
