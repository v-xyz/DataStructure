package Practices;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 字符串
 */
public class P05_ViolentRecursionRelated03 {
    /*
    * 题目：打印一个字符串的全部子序列
    * 注意：子串要求在字符串中是连续的，子序列不要求连续，但相对顺序不能乱
    * 例如对于 abcd, 其中关于b的子串有：b,bc,bcd 子序列有：b,bc,bd,bcd [不能有ba，相对顺序不能乱]
    * */

    /*
    * 思路：从第一个字符开始，依次按照字符顺序 以 要 和 不要 的二叉树形式展开，最后得到的叶子节点的组成的字符】
    * 样式就是子序列
    * 例如 ab
    *                _    0    _
    *            要a/            \不要a
    *             _1_            _2_
    *         要b/    \不要b  要b/    \不要b
    *          ab     a        b     ""
    * */

    public static List<String> subs(String s){
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(str, 0, ans, path);
        return ans;
    }
    /*
    * str 表示固定不变的字符数组
    * index 表示此时来到的位置，表示该字符到底是要还是不要
    * 如果index来到字符的终止位置(最后一个位置再往后一个位置)，把沿途路径所形成的答案放入 ans 中
    * 之前做出的选择就是 path
    * */
    public static void process1(char[] str, int index, List<String> ans, String path){
        if(index == str.length){
            ans.add(path);
            return;
        }
        //要了当前index位置的字符，然后继续走
        String yes = path + String.valueOf(str[index]);
        process1(str,index + 1, ans, yes);

        //没要当前index位置的字符，然后继续走
        String no = path;
        process1(str, index + 1, ans, no);

        //从以上可以看出实际上是一个深度优先遍历
    }

    /*
    * 题目: 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
    * 比如 aaa, 如果要前两个位置的a,则得到子序列 aa,如果要后两个位置的a,得到的子序列也是 aa,这就会出现重复
    * */
    public static List<String> subsNoRepeat(String s){
        char[] str = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(str,0,set,path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }
    public static void process2(char[] str, int index, HashSet<String> set, String path){
        if(index == str.length){
            set.add(path);
            return;
        }
        String yes = path + String.valueOf(str[index]);
        process2(str,index + 1, set, yes);

        String no = path;
        process2(str, index + 1, set, no);
    }



}
