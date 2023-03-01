package Practices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

/**
 * 贪心算法的求解过程：
 * 1）分析业务
 * 2）根据业务逻辑找到不同的贪心策略【局部求最优解】
 * 3）对于能举出反例的策略直接跳过，不能举出反例的要证明有效性【困难】
 */

/**
 * 贪心算法的解题思路：
 * 1）实现一个不依靠贪心策略的解法 X [可以使用暴力方法]
 * 2）脑补出贪心策略A,贪心策略B,贪心策略C
 * 3）用解法X和对数器，用实验的方式得知哪个贪心策略正确
 * 4）不要去纠结贪心策略的证明
 *
 * 注：堆和排序是完成贪心策略最常用的技巧
 */

/**
 * 假设存在一个存放字符串的数组，要求如果一一把数组中的字符串拼接起来，
 * 求字典序最小的拼接结果下，一个个字符串在数组中的排序规则,并返回最小的拼接结果
 * 关于字典序：因为 "a" < "b",所以 "abc" < "bs",拼接后 "abcbs" < "bsabc"
 * 再比如 b 与 ba 比较，b要与 ba比较必须补齐，加上0 即 b0 与 ba 比较，0认为比 a 还小
 * 所以 b0 < ba 即 b < ba 但拼接后 "bba" > "bab"
 */
public class P03_GreedyAlgorithmRelated01 {
    /*
    * 暴力枚举
    * */
    public static String lowestString1(String[] strs){
        if (strs == null || strs.length == 0) {
            return "";
        }
        ArrayList<String> all = new ArrayList<>();
        HashSet<Integer> use = new HashSet<>();
        process(strs, use, "", all);
        String lowest = all.get(0);
        for (int i = 1; i < all.size(); i++) {
            if(all.get(i).compareTo(lowest) < 0){
                lowest = all.get(i);
            }
        }
        return  lowest;
    }
    //strs存放着所有的字符串，已经使用过的字符串在use里登记了，不要再使用了
    //之前使用过的字符串拼接成 -> path, 用all收集所有可能的拼接结果
    public static void process(String[] strs, HashSet<Integer> use, String path, ArrayList all){
        if(use.size() == strs.length){
            all.add(path);
        }else {
            for (int i = 0; i < strs.length; i++) {
                if(!use.contains(i)){
                    use.add(i);
                    process(strs, use, path + strs[i], all);
                    use.remove(i);
                }
            }
        }
    }

    /*
     * 方法二：
     * 对于两个字符串 X 和 Y , 如果 X 拼接上 Y 的字典序 (XY) <=  Y 拼接上 X 的字典序 (YX)
     * 则 X 在前，否则 Y 在后
     * */
    public static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }
    public static String lowestString2(String[] strs){
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs,new MyComparator());
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }



}
