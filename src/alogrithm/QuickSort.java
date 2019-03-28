package alogrithm;

import java.util.Random;

/**
 * 算法作业第一次，作业要求：
 * 实现普通快排算法及其随机划分版本（见课
 * 程 PPT），实验比较两个算法的性能（随机生成
 * 10000-100000 个数字，对其进行排序，分别统
 * 计时间）
 *
 * @author 王沁宽
 * @date 2019/3/28
 */
public class QuickSort {
    /**
     * 普通版快速排序
     * @param array
     * @param start
     * @param end
     */
    public void quickSort(int[] array,int start,int end){
        if (start < end) {
            int q = parition(array,start,end);
            quickSort(array,start,q - 1);
            quickSort(array,q + 1,end);
        }
    }

    /**
     * 划分
     * @param array
     * @param start
     * @param end
     * @return
     */
    private int parition(int[] array,int start,int end){
        int tmp = array[start];
        while (start < end){
            while (start < end && array[end] >= tmp) end--;
            array[start] = array[end];
            while (start < end && array[start] <= tmp) start++;
            array[end] = array[start];
        }
        array[start] = tmp;
        return start;
    }

    /**
     * 随机划分版快排
     * @param array
     * @param start
     * @param end
     */
    public void randomQuickSort(int[] array, int start, int end){
        if (start < end) {
            int q = randomParition(array,start,end);
            randomQuickSort(array,start,q - 1);
            randomQuickSort(array,q + 1,end);
        }
    }
    /**
     * 随机划分
     * @param array
     * @param start
     * @param end
     * @return
     */
    private int randomParition(int[] array,int start,int end){
        int r = new Random().nextInt(end - start + 1) + start;
        int tmp = array[start];
        array[start] = array[r];
        array[r] = tmp;
        return parition(array,start,end);
    }

    /**
     * 打印数组
     * @param array
     */
    public static void printArray(int[] array){
        int len = array.length;
        for(int i = 0 ;i < len ;i++){
            System.out.print(array[i]);
            if(i != len - 1) System.out.print(',');
            else System.out.print('\n');
        }
    }

    /**
     * 生成区间[lowLimit,highLimit]内的整形随机数数组
     * @param lowLimit      生成数下界
     * @param highLimit     生成数上界
     * @param len           生成数组长度
     * @return
     */
    public static int[] generateRandomArray(int lowLimit, int highLimit, int len){
        Random r = new Random();
        int[] array = new int[len];
        for(int i = 0;i < len ;i++) array[i] = r.nextInt(highLimit - lowLimit + 1) + lowLimit;
        return array;
    }

    /**
     * 生成起始位start 长度为len的顺序数组
     * @param start
     * @param len
     * @return
     */
    public static int[] generateOrderedArray(int start,int len){
        int[] array = new int[len];
        for(int i = 0 ;i < len ;i++){
            array[i] = start ++;
        }
        return array;
    }
}
