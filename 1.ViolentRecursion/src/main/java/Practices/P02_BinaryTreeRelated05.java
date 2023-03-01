package Practices;

import java.util.ArrayList;
import java.util.List;
/**
 * 公司的每个员工都符合 Employee类的描述。整个公司的人员结构可以看作是一颗标准的
 * 没有环的多叉树。树的头节点是公司唯一的老板。除老板之外的每个员工都有唯一的直接上级
 * 叶节点是没有任何下属的基层员工（subordinates列表为空），除基层员工外，每个员工
 * 都有一个或多个直接下级
 *
 * 题目：
 * 派对的最大欢乐值：
 * 公司现在要办party,你可以决定哪些员工来，哪些员工不来，规则：
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来。
 * 2.派对的整体欢乐值是所有到场员工欢乐值的累加。
 * 3.你的目标是让派对的整体欢乐值尽量大。
 * 给定一颗多叉树的头节点boss,请返回派对的最大欢乐值。
 * */
public class P02_BinaryTreeRelated05 {
    //员工信息定义如下【多叉树】：
    static class Employee{
        public int happy; //这名员工带来的欢乐值
        List<Employee> subordinates; //这名员工有哪些直接下级
        public Employee(int happy){
            this.happy = happy;
            subordinates = new ArrayList<>();
        }
    }

    /*
    * 分析：
    *
    * 考虑两种情况，X来或不来。
    * 1.X来
    * 需要如下信息：
    * 1）所有子树在子树根节点不来的情况下，整颗子树的最大欢乐值
    * 于是最后的最大欢乐值就是 X.happy + 所有子树的最大欢乐值的和
    *
    * 2.X不来 X.happy 为 0
    * 1）所有子树在子树根节点不来的情况下，整颗子树的最大欢乐值
    * 2）所有子树在子树根节点来的情况下，整颗子树的最大欢乐值
    * */

    public static int maxHappy(Employee boss){
        if (boss == null) {
            return 0;
        }
        Info all = process(boss);
        return Math.max(all.yes,all.no);
    }

    public static class Info{
        public int yes; //头节点在来的情况下整棵树的最大欢乐值
        public int no;  //头节点在不来的情况下整棵树的最大欢乐值
        public Info(int y, int n){
            yes = y;
            no = n;
        }
    }

    private static Info process(Employee X) {
        if(X.subordinates.isEmpty()){
            return new Info(X.happy,0);
        }
        int yes = X.happy;
        int no = 0;
        for (Employee next: X.subordinates) {
            Info nextInfo = process(next);
            yes += nextInfo.no;
            no += Math.max(nextInfo.yes, nextInfo.no);
        }
        return new Info(yes,no);
    }
}
