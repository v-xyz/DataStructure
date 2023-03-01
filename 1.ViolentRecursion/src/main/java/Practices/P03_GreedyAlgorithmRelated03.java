package Practices;

import java.util.HashSet;

/**
 * 给定一个字符串str,只由 'X' 和 '.' 两种字符构成
 * 'X' 表示墙，不能放灯，也不需要点亮
 * '.' 表示居民点，可以放灯，需要点亮
 * 如果灯放在 i 位置，可以让 i-1 , i , i+1 三个位置被点亮
 * 返回如果点亮 str中所有需要点亮的位置，至少需要几盏灯
 */
public class P03_GreedyAlgorithmRelated03 {
    //暴力方法
    public static int minLight1(String road){
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(),0,new HashSet<>());
    }
    /*
    * str[index....]位置，自由选择放灯不放灯
    * str[0....index-1]位置表示已经做完决定,其中那些放了灯的位置，存在 lights 中
    * 要求选出能照亮所有 '.' 的方案，并且在这些有效的方案中，返回最少需要几盏灯
    * */
    public static int process(char[] str, int index, HashSet<Integer> lights){
        if(index == str.length){//来到终止位置，所有决定都已做完
            //验证所有方案能不能把所有位置都照亮
            for (int i = 0; i < str.length; i++) {
                if(str[i] != 'X'){
                    if(!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i+1)){
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        }else { //str还没结束

            int no = process(str, index + 1, lights);

            int yes = Integer.MAX_VALUE;
            if(str[index] == '.'){
                lights.add(index);
                yes = process(str,index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no,yes);
        }
    }

    /*
    * 方法二：
    * A）如果 i 位置是 'X',则直接跳到 i+1 位置
    * B）如果 i 位置是 '.',则：
    *   【1】如果 i+1 是 'X' ，则在 i 位置上放灯，到 i+2位置再进行判断
    *   【2】如果 i+1 是 '.' ，则需要到 i+2 位置做决定，发现不管是 ..X 还是 ...
    *       都是在 i+1 位置放灯，然后到 i+3 位置在进行判断
    * */
    public static int minLight2(String road){
        char[] str = road.toCharArray();
        int index = 0;
        int light = 0;
        while (index < str.length){
            if(str[index] == 'X'){
                index++;
            }else {
                light++;
                if(index + 1 == str.length){
                    break;
                }else {
                    if(str[index + 1] == 'X'){
                        index += 2;
                    }else {
                        index += 3;
                    }
                }
            }
        }
        return light;
    }
}
