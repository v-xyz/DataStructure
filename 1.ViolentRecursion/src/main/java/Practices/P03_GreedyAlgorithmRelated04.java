package Practices;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入：正数数组costs,正数数组profits,正数K,正数M
 * costs[i] 表示 i号项目的花费
 * profits[i] 表示 i号项目的纯利润
 * K表示你只能串行的最多做 k 个项目
 * M表示你初始的资金
 * 说明：每做完一个项目，马上获得收益，可以支持你做下一个项目，不能并行的做项目
 * 输出：你最后获得的最大钱数
 */
public class P03_GreedyAlgorithmRelated04 {
    /*
    * 准备一个小根堆和一个大根堆；小根堆按项目花费排序，大根堆按项目利润排序
    * 先去小根堆找到满足初始资金 M 的所有项目,并将他们放入大根堆，找到堆顶（即最大利润）
    * 的元素弹出，做堆顶项目；随后将利润与M 加在一起表示现在拥有的资金，再去小根堆中找到
    * 满足资金的所有项目将其放入大根堆，再弹出堆顶元素，以此类推。
    *
    * */
    public static class Program{
        int p;
        int c;
        public Program(int p, int c){
            this.p = p;
            this.c = c;
        }
    }
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital){
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxCostComparator());
        for (int i = 0; i < profits.length; i++) {
            minCostQ.add(new Program(profits[i],capital[i]));
        }
        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= w){
                maxProfitQ.add(minCostQ.poll());
            }
            if(maxProfitQ.isEmpty()){
                return w;
            }
            w += maxProfitQ.poll().p;
        }
        return w;
    }
    public static class MinCostComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }
    public static class MaxCostComparator implements Comparator<Program>{
        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }
    }
}
