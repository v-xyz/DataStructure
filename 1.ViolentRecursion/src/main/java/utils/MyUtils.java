package utils;

import sort.comparativeSort.ShellSort;

import java.util.Arrays;

//对数器验证相关算法的正确性
public class MyUtils {
    //工具方法：生成一个长度也随机，值也随机的数组
    public static int[] generateRandomArray(int maxSize,int maxValue){
        // Math.random() 的范围为： [0,1) 左闭右开
        // 对于 (int)(Math.random() * N) 会返回 [0,N-1] 范围内随机一个整数
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            // 用一个随机值减去另外一个随机值 生成 [-?,+?] 的随机，不减就是 [0,+?]的随机
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
    }
    //工具方法：复制数组
    public static int[] copyArray(int[] arr){
        if(arr == null){
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }
    //工具方法：比较两个不同数组是否完全一样
    public static boolean isEqual(int[] arr1,int[] arr2){
        if(arr1 == null && arr2 == null){
            return true;
        }
        if((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)){
            return false;
        }
        if(arr1.length != arr2.length){
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if(arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    //工具方法：打印数组
    public static void printArray(int[] arr){
        if(arr == null){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]+ " ");
        }
        System.out.println();
    }

    //绝对正确的方法【这里用官方排序算法作为例子】
    public static void comparator(int[] arr){
        Arrays.sort(arr);
    }

    //主要测试过程的演示
    public static void main(String[] args) {
        int testTime = 500000; //设置测试次数
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            //下面测试内容可能对于不同的算法有不同的方式
            int[] arr = generateRandomArray(maxSize,maxValue);
            int[] arr2 = copyArray(arr);
            ShellSort.sort(arr);
            comparator(arr2);

            if(!isEqual(arr,arr2)){
                succeed = false;
                printArray(arr);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "correct" : "wrong");
        int[] arr = generateRandomArray(maxSize,maxValue);
        printArray(arr);
        ShellSort.sort(arr);
        printArray(arr);
    }
}
