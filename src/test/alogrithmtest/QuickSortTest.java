package test.alogrithmtest;

import alogrithm.QuickSort;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

public class QuickSortTest {

    public static QuickSort quickSort;

    @BeforeClass
    public static void init(){
        quickSort = new QuickSort();
    }

    @Test
    public void test(){
        int[] a1 = QuickSort.generateArray(1,100,10);
        int[] a2 = Arrays.copyOf(a1,a1.length);
        quickSortTest(a1);
        randomQuickSortTest(a2);
    }

    /**
     * 测试普通版快排并打印执行时间
     * @param array 待排序的数组
     */
    public void quickSortTest(int[] array){
        System.out.println("-------------------快速排序普通版start，待排序数组为-------------------");
        QuickSort.printArray(array);
        long startTime = System.currentTimeMillis();
        quickSort.quickSort(array,0,array.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println("-------------------快速排序普通版 end ，排序后数组为-------------------");
        QuickSort.printArray(array);
        System.out.println("-------------------排序时间为" + (endTime - startTime) +"毫秒");
        System.out.println();

    }

    /**
     * 测试随机划分版快排并打印执行时间
     * @param array
     */
    public void randomQuickSortTest(int[] array){
        System.out.println("-------------------快速排序随机划分版start，待排序数组为-------------------");
        QuickSort.printArray(array);
        long startTime = System.currentTimeMillis();
        quickSort.randomQuickSort(array,0,array.length - 1);
        long endTime = System.currentTimeMillis();
        System.out.println("-------------------快速排序随机划分版 end ，排序后数组为-------------------");
        QuickSort.printArray(array);
        System.out.println("-------------------排序时间为" + (endTime - startTime) +"毫秒");
        System.out.println();
    }
}
