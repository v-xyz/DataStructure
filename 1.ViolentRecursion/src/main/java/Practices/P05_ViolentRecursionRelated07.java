package Practices;

/**
 * 题目：
 * 给定一个整型数组arr,代表数值不同的纸牌排成一条线，玩家A 和 玩家B 依次拿走每张纸牌，
 * 规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家A和玩家B每次
 * 都会寻求对自己有利的最优方案，请返回最后获胜者的分数
 *
 * 比如 70 100 1 4 , 玩家A先拿的情况下，考虑到拿70会让玩家B拿到100，所有A会选择拿 4
 * 70 100 1 , 玩家B不管拿到何值都会让玩家A拿到 100，所以玩家B拿70，最后玩家A拿100，
 * 玩家B最后拿1，
 * 得到获胜者分数为玩家A: 104
 */
public class P05_ViolentRecursionRelated07 {
    /*
    * 定义一个递归函数 f(arr,L,R) 表示 先手在 arr的【L,R】上拿纸牌的最优解
    * 再定义一个递归函数 S(arr,L,R) 表示 后手在 arr的【L,R】上拿纸牌的最优解
    * 两个递归函数一定有某种联系可以让他们最后变成一个递归,在这里就是先手拿完牌后，后手就变成了先手
    *
    * base case 为最后只剩一张牌的情况(l==R)
    * -对于先手， if(l==R){return arr[L];}
    * -对于后手， if(l==R){return 0;}
    *
    * 对于先手的情况，有 [后手拿牌一定也是最优解]
    * 1) arr[L] + S(arr,L+1,R)
    * 2) arr[R] + S(arr,L,R-1)
    * 先手一定会拿对自己最好的值，所以先手的最优解为 1) 和 2) 的较大值
    *
    * 对于后手的情况，有 [先手拿完对应位置的牌后，后手就变成先手]
    * 1) 先手拿走L位置的牌，则后手拿牌就相当于 f(arr,L+1,R)
    * 2) 先手拿走R位置的牌，则后手拿牌就相当于 f(arr,L,R-1)
    * 由于对手一定会让你拿最小的牌，所以后手的最优解只能是 1) 和 2) 的较小值
    * */
    public static int win1(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr,0, arr.length-1),s(arr,0, arr.length - 1));
    }
    public static int f(int[] arr, int L, int R){
        if(L == R){
            return arr[L];
        }
        return Math.max(arr[L] + s(arr, L + 1, R),arr[R] + s(arr, L, R - 1));
    }
    public static int s(int[] arr, int L, int R){
        if(L == R){
            return 0;
        }
        return Math.min(f(arr, L + 1, R),f(arr, L, R - 1));
    }

    /*
    * 从上述暴力递归过程中发现在 [L,R]区间上， 区间[L+1,R]和[L,R-1]都是表明规模在缩小
    * L 和 R的范围都是 0 ~ n-1, 所以 f 和 s 都是正方形表
    * 由于 R永远 <= L,所以两个表中左下半区没用
    * 假定 arr : [1,7,9,10]
    * 主函数求 Math.max(f(arr,0, arr.length-1),s(arr,0, arr.length - 1));
    * 所以需要得到两张表 ? 位置的值
    *
    *       0   1   2   3   -> R
    *   0               ?
    *   1   X
    *   2   X   X
    *   3   X   X   X
    *   L   X   X   X   X  ...  X
    *
    *   f:
    *   L == R  return arr[L];
    *   =>
    *       0   1   2   3   -> R
    *   0   1           ?
    *   1   X   7
    *   2   X   X   9
    *   3   X   X   X   10
    *   L   X   X   X   X  ...  X
    *
    *   s:
    *   L == R  return 0;
    *   =>
    *       0   1   2   3   -> R
    *   0   0           ?
    *   1   X   0
    *   2   X   X   0
    *   3   X   X   X   0
    *   L   X   X   X   X  ...  X
    *
    *   f：
    * Math.max(arr[L] + s(arr, L + 1, R),arr[R] + s(arr, L, R - 1))
    * => f表中的有效位置依赖于 s 表中相同位置的左边值和下边值
    * 所以 f表中的 ?  依赖于 s表中的 * 位置,同理对 s 表中的 ? 也同理
    *
    *       0   1   2   3   -> R
    *   0   0       *   ?
    *   1   X   0       *
    *   2   X   X   0
    *   3   X   X   X   0
    *   L   X   X   X   X  ...  X
    * 为了得到上述 * 位置的值，必须得到 另一个表中 \ 位置的值
    *       0   1   2   3   -> R
    *   0   0   \       ?
    *   1   X   0   \
    *   2   X   X   0   \
    *   3   X   X   X   0
    *   L   X   X   X   X  ...  X
    * 以此类推，必须要知道表中每一条类似于上述这种对角线的值，才能知道最终 ? 部分的值
    * */
    public static int win2(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];

//        if(L == R){
//            return arr[L];
//        }
        for(int i = 0; i < N; i++){ //中间的对角线
            f[i][i] = arr[i];
            //s[i][i] = 0; //默认就是 0,省略
        }

        for(int i = 1; i < N; i++){ //中间依次往右的对角线
            //刚开始行在 L=0 位置，列在 R=i 位置 【i从1开始】，就像上面 \
            int L = 0;
            int R = i;
            while (L < N && R < N){
                //想办法填好 [row][col]位置

//                return Math.max(arr[L] + s(arr, L + 1, R),arr[R] + s(arr, L, R - 1));
                f[L][R] = Math.max(arr[L] + s[L + 1][R],arr[R] + s[L][R - 1]);

//                return Math.min(f(arr, L + 1, R),f(arr, L, R - 1));
                s[L][R] = Math.min(f[L + 1][R],f[L][R - 1]);

                //依次找到斜下方对角线的所有值
                L++;
                R++;
            }
        }

        return Math.max(f[0][N-1],s[0][N-1]);
    }
}
