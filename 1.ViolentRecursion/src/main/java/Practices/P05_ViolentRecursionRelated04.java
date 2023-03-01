package Practices;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * 题目：求一个字符串中字符的全排列
 * */
public class P05_ViolentRecursionRelated04 {
    /*
     * 对于 abc， 先标记 abc 占据 012 三个位置
     *                                                                       0 1 2
     *                                                  _         _         [a,b,c]         _         _
     *  变动 0 位置,原来0位置的数被交换           a->0(不动)/                    b->0|                       \c->0
     *                                             _[abc]_                  _[bac]_                  _[cba]_
     *  0位置的数不再考虑，变动1位置           b->1(不动/      \c->1    a->1(不动)/      \c->1    b->1(不动)/      \a->1
     *                                          [abc]    [acb]           [bac]   [bca]            [cba]    [cab]
     *  1位置的数不再考虑，变动2位置，发现都保持不动      |        |               |       |                |        |
     *                                          [abc]    [acb]           [bac]   [bca]            [cba]    [cab]
     * */
    public static ArrayList<String> permutation(String str){
        ArrayList<String> res = new ArrayList<>();
        if(str == null || str.length() == 0){
            return res;
        }
        char[] chs = str.toCharArray();
        process(chs,0,res);
        return res;
    }
    //str[i...]往后的字符都有机会来到 i 位置， str[0...i-1]表示已经做好决定的
    public static void process(char[] str, int i, ArrayList<String> ans){
        if(i == str.length){
            ans.add(String.valueOf(str));
        }
        for(int j = i;j< str.length;j++){
            swap(str,i,j);
            process(str,i+1,ans);
            swap(str,i,j);
        }
    }

    /**
     * 题目：求一个字符串中字符的全排列,并要求不出现重复排列
     *
     */
    public static ArrayList<String> permutationNonRepeat(String str){
        ArrayList<String> res = new ArrayList<>();
        if(str == null || str.length() == 0){
            return res;
        }
        char[] chs = str.toCharArray();
        process2(chs,0,res);
        return res;
    }
    public static void process2(char[] str, int i, ArrayList<String> ans){
        if(i == str.length){
            ans.add(String.valueOf(str));
        }
        boolean[] visit = new boolean[26]; //visit[0,1...25]
        for(int j = i;j< str.length;j++){
            if(!visit[str[j] - 'a']){
                visit[str[j] - 'a'] = true;
                swap(str,i,j);
                process2(str,i+1,ans);
                swap(str,i,j);
            }
        }
    }

    private static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }
}
