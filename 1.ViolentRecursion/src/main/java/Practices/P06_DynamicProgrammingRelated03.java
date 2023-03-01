package Practices;

import java.util.HashMap;

/**
 * 题目：
 * 给定一个字符串str,给定一个字符串类型的数组arr.
 * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，
 * 目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str="babac", arr={"ba","c","abcd"}
 * 至少需要两张贴纸"ba"和"abcd",因为使用这两张贴纸，把每个字符单独剪开，
 * 含有2个a,2个b,1个c.是可以拼出str的，所以返回2
 */
public class P06_DynamicProgrammingRelated03 {
    /*
    * 暴力递归思路:
    * 每一轮都对每张贴纸枚举，并在结果上再对每张贴纸枚举
    * 过程中要注意各种边界
    * */

//前期思路
//    public static int minStickers(String rest,String[] arr){
//        if(rest.equals("")){
//            return 0;
//        }
//        int next = 0;
//        //搞定rest的第一张贴纸是什么
//        for(String first : arr){  //每一个都认为是第一张贴纸
//            //步骤： rest - first -> nextRest
//            //int cur = minStickers1(nextRest,arr);
//            //next = Math.min(next,cur)
//        }
//        return next + 1;
//    }

    public static int minStickers1(String[] stickers, String target){
        int n = stickers.length;

        //map 表示对贴纸的描述，n表示行上的一个个贴纸，26列表示26个英文字母，0位置第一列表示 a, 1位置第二列表示 b...
        //于是对应位置的值表示某一个具体贴纸（某一行）某个字母(某一列)出现的次数
        int[][] map = new int[n][26];

        //初始化 map, 将 stickers的一个个完整贴纸结构，变成剪碎了的统计每个贴纸每个字母数量的二维数组结构
        for (int i = 0; i < n; i++) {
            char[] str = stickers[i].toCharArray();
            for(char c : str){
                map[i][c - 'a']++;
            }
        }

        HashMap<String, Integer> dp = new HashMap<>(); //使用 HashMap 作为缓存
        dp.put("",0);
        return process1(dp,map,target);
    }
    //rest 表示剩余的目标
    public static int process1(HashMap<String, Integer> dp, int[][] map, String rest){
        if (dp.containsKey(rest)){
            return dp.get(rest);
        }
        int ans = Integer.MAX_VALUE; //ans表示搞定 rest 使用的最少的贴纸数量,这里表示一开始需要无穷大的数量
        int n = map.length;

        //restMap 替代 rest 变成词频
        int[] restMap = new int[26];
        char[] target = rest.toCharArray();
        for (char c : target){
            restMap[c - 'a']++;
        }
        for (int i = 0; i < n; i++) { //i号贴纸
            //循环中枚举当前第一张贴纸是谁

            //在map[i][j]字符 比如 "aabbbcccddeef"，假如使用 "xyz" 去试，则会一直有 rest 字符为 "aabbbcccddeef"
            //最终会陷入死循环，于是我们规定每次只先去试包含 rest中第一个字符的贴纸，其他贴纸先跳过，利用这样的贪心算法
            //避免以上情况的出现
            if(map[i][target[0] - 'a'] == 0){
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) { //j枚举a~z字符
                if(restMap[j] > 0){
                    for (int k = 0; k < Math.max(0,restMap[j] - map[i][j]); k++) {
                        sb.append((char) ('a' + j)); //还剩多少个就在 sb中添加多少个
                    }
                }
            }
            //经历过上述过程，i号贴纸中的所有词频已经在 rest中被去除，现在的字符串变成 sb
            String s = sb.toString();

            int tmp = process1(dp, map, s); //把现在的字符串再传入process进行递归
            if(tmp != -1){
                // 1 + tmp 表示第一贴纸加后续的方案
                //ans取之前的 ans 和现在的方案中的最小值
                ans = Math.min(ans,1 + tmp);
            }
        }
        dp.put(rest,ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(rest);
    }
/**
 * 面试中设计暴力递归过程的原则:
 * 1) 每一个可变参数类型，一定不要比 int类型更加复杂
 * 2) 如果违反了 1),必须保证是唯一可变参数，比如这道题
 * 3) 如果 1)违反，2不违反，比如这道题，只需要做到记忆化搜素即可
 * 4) 可变参数的个数，能少则少。
 * 一定要逼自己找到符合上述原则的暴力尝试，如果找到的暴力尝试不符合原则，马上舍弃并寻找新的!
 */

}
