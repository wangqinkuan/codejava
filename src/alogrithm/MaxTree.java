package alogrithm;

import ds.TreeNode;

/**
 * 题目一.给定一个没有重复的整数数组，这个数组上的
 * 最大树结构定义如下：
 * 1.根是数组中的最大值。
 * 2.左子树是由原数组中最大值的左部分子数组构造的
 * 最大树。
 * 3.同理，右子树是由原数组中最大值的右部分子数组
 * 构造的最大树。
 * 4.根据给定数组构造最大树，并输出该树。
 *
 */
public class MaxTree {

    public static TreeNode maxTree(int[] array){
        return maxTree(array,0,array.length - 1);
    }

    public static TreeNode maxTree(int[] array,int left,int right){
        if(left > right) return null;
        if(left == right) return new TreeNode(array[left]);
        int max = 0,index = 0;
        for(int i = left ;i <= right ;i++){
            if(array[i] > max){
                max = array[i];
                index = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = maxTree(array,left,index - 1);
        root.right = maxTree(array,index + 1,right);
        return root;
    }
}
