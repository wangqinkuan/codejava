package util;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ArrayUtil {

    /**
     * 打印二维数组
     * @param array
     */
    public static void printArray2Dim(int[][] array){
        int len = array.length;
        for(int i = 0 ;i < len ;i++){
            printArray(array[i]);
        }
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
     * 打印数组
     * @param array
     */
    public static void printArray(Integer[] array){
        int len = array.length;
        for(int i = 0 ;i < len ;i++){
            System.out.print(array[i]);
            if(i != len - 1) System.out.print(',');
            else System.out.print('\n');
        }
    }

    /**
     * 打印数组
     * @param array
     */
    public static void printArray(List<Integer> array){
        int len = array.size();
        for(int i = 0 ;i < len ;i++){
            System.out.print(array.get(i));
            if(i != len - 1) System.out.print(',');
            else System.out.print('\n');
        }
    }

    /**
     * 生成区间[lowLimit,highLimit]内长度为len的整形随机数数组
     * @param lowLimit
     * @param highLimit
     * @param len
     * @param repeat  是否允许重复值 true 表示有重复值
     * @return
     */
    public static int[] generateRandomArray(int lowLimit, int highLimit, int len, boolean repeat){
        if(repeat) return generateRandomArrayWithRepeat(lowLimit,highLimit,len);
        else return generateRandomArrayWithOutRepeat(lowLimit,highLimit,len);
    }

    /**
     * 生成区间[lowLimit,highLimit]内长度为len的随机Integer数组
     * @param lowLimit
     * @param highLimit
     * @param len
     * @param repeat  是否允许重复值 true 表示有重复值
     * @return
     */
    public static Integer[] generateRandomIntegerArray(int lowLimit,int highLimit,int len,boolean repeat){
        int[] array = generateRandomArray(lowLimit,highLimit,len,repeat);
        if(array == null) return null;
        else {
            Integer[] result = new Integer[array.length];
            for(int i = 0;i < array.length;i++) result[i] = array[i];
            return result;
        }
    }


    /**
     * 生成区间[lowLimit,highLimit]内的整形随机数数组
     * @param lowLimit      生成数下界
     * @param highLimit     生成数上界
     * @param len           生成数组长度
     * @return
     */
    public static int[] generateRandomArrayWithRepeat(int lowLimit, int highLimit, int len){
        if(highLimit < lowLimit) return null;
        Random r = new Random();
        int[] array = new int[len];
        for(int i = 0;i < len ;i++) array[i] = r.nextInt(highLimit - lowLimit + 1) + lowLimit;
        return array;
    }

    /**
     * 生成长度为len的从lowLimit到highLimit的不重复的随机数组
     * @param lowLimit
     * @param highLimit
     * @return
     */
    public static int[] generateRandomArrayWithOutRepeat(int lowLimit,int highLimit,int len){
        int l = highLimit - lowLimit + 1;
        if(l <= 0) return null;
        int[] array = generateOrderedArray(lowLimit,l);
        Random r = new Random();
        for (int i = 0;i < l ;i++){
            int j = r.nextInt(l);
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
        return Arrays.copyOfRange(array,0,len);
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
