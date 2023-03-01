package Practices;

/**
 * 暴力递归就是尝试：
 * 1.把问题转换为规模缩小了的同类问题的子问题
 * 2.有明确的不需要递归的条件
 * 3.有当得到了子问题结果之后的决策过程
 * 4.不记录每一个子问题的解
 *
 *
 */
/*
 * n层汉诺塔问题的移动思路【初始在左边】：
 * 1. 1 至 (n-1) 层移到中间【大步骤】
 * 2. n 层从左边移动到右边【小步骤】
 * 3. 1 至 (n-1) 层从中间移动到右边【大步骤】
 */
public class P05_ViolentRecursionRelated01 {
    public static void hanoi1(int n){
        leftToRight(n);
    }
    //把 1 至 n 层圆盘从 左 -> 右
    public static void leftToRight(int n){
        if(n == 1){
            System.out.println("Move 1 from left to right");
            return;
        }
        leftToMid(n-1);
        System.out.println("Move "+ n +" from left to right");
        midToRight(n-1);
    }
    //把 1 至 n 层圆盘从 左 -> 中
    public static void leftToMid(int n){
        if(n == 1){
            System.out.println("Move 1 from left to mid");
            return;
        }
        leftToRight(n-1);
        System.out.println("Move "+ n +" from left to mid");
        rightToMid(n-1);
    }
    public static void midToRight(int n){
        if(n == 1){
            System.out.println("Move 1 from mid to right");
            return;
        }
        midToLeft(n-1);
        System.out.println("Move "+ n +" from mid to right");
        leftToRight(n-1);
    }
    public static void rightToMid(int n){
        if(n == 1){
            System.out.println("Move 1 from right to mid");
            return;
        }
        rightToLeft(n-1);
        System.out.println("Move "+ n +" from right to mid");
        leftToMid(n-1);
    }
    public static void rightToLeft(int n){
        if(n == 1){
            System.out.println("Move 1 from right to left");
            return;
        }
        rightToMid(n-1);
        System.out.println("Move "+ n +" from right to left");
        midToLeft(n-1);
    }
    public static void midToLeft(int n){
        if(n == 1){
            System.out.println("Move 1 from mid to left");
            return;
        }
        midToRight(n-1);
        System.out.println("Move "+ n +" from mid to left");
        rightToLeft(n-1);
    }

    /*
    * 解法2
    * */
    public static void hanoi2(int n){
        if(n > 0){
            func(n,"left","right","mid");
        }
    }
    //不区分左中右，看成目标是 from -> to, other代表另外一个
    public static void func(int n, String from, String to, String other){
        if(n == 1){
            System.out.println("Move 1 from " + from + " to " + to);
        }else {
            func(n-1,from,other,to);
            System.out.println("Move" + n + " from " + from + " to " + to);
            func(n-1,other,to,from);
        }
    }

}
