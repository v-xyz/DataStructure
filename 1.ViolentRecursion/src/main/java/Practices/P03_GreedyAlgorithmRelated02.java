package Practices;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目的开始时间和结束时间，要求会议室进行的宣讲的场次最多，返回最多的宣讲场次。
 */
public class P03_GreedyAlgorithmRelated02 {
    //暴力方法
    public static class Program{
        public int start;
        public int end;
        public Program(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public static int bestArrange1(Program[] programs){
        if (programs == null || programs.length == 0) {
            return 0;
        }
        return process(programs,0,0); //最开始的时候安排0的会，时间点来到0点
    }
    /*
    * 还剩什么会议都放在 programs里
    * done 表示之前已经安排了多少会议
    * timeLine 表示目前来到的时间点是多少
    *
    * process方法表示：目前来到 timeLine的时间点，已经安排了 done多的会议，剩下的会议
    * 可以自由安排，剩下的会议放在 programs里. 最后返回能安排的最多会议数量
    * */
    public static int process(Program[] programs, int done, int timeLine){
        if (programs.length == 0){ //没有会议可以自由选择，返回此时安排的会议数量
            return done;
        }
        int max = done;
        //当前安排的会议是什么会，每一个都枚举
        for(int i = 0; i< programs.length; i++ ){
            if(programs[i].start >= timeLine){ //开始时间晚于现在的时间点，表示能够安排这个会
                Program[] next = copyButExcept(programs,i);
                max = Math.max(max, process(next,done + 1, programs[i].end));
            }
        }
        return max;
    }
    //创建新数组来复制
    public static Program[] copyButExcept(Program[] programs, int i){
        Program[] ans = new Program[programs.length - 1];
        int index = 0;
        for (int j = 0; j < programs.length; j++) {
            if(j != i){
                ans[index++] = programs[j];
            }
        }
        return ans;
    }

    /*
    * 总是先安排结束时间早的会议，同时将其他不满足条件的排除掉，依次安排会议
    * */
    public static int bestArrange2(Program[] programs){
        Arrays.sort(programs,new ProgramComparator());
        int timeLine = 0;
        int result = 0; //表示安排了多少会议
        for (int i = 0; i < programs.length; i++) {
            if(timeLine <= programs[i].start){
                result++;
                timeLine = programs[i].end;
            }
        }
        return result;
    }
    public static class ProgramComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end; //根据谁的结束时间早排序
        }
    }
}
