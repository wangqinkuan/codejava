package test.alogrithmtest;


import alogrithm.MinDiffInBST;
import ds.TreeNode;
import org.junit.BeforeClass;
import org.junit.Test;
import util.ArrayUtil;
import util.TreeUtil;


public class MinDiffInBSTTest {
    public static MinDiffInBST minDiffInBST;

    @BeforeClass
    public static void init(){
        minDiffInBST = new MinDiffInBST();
    }

    @Test
    public void test(){

        for(int i = 0 ;i < 10 ;i++){

            Integer[] a = ArrayUtil.generateRandomIntegerArray(1,100,5,false);

            System.out.print("生成随机数:");
            ArrayUtil.printArray(a);

            TreeNode r = TreeUtil.createBSTByArray(a);

            System.out.print("先序遍历结果:");
            ArrayUtil.printArray(TreeUtil.preOrder(r));

            System.out.print("中序遍历结果:");
            ArrayUtil.printArray(TreeUtil.inOrder(r));

            System.out.print("后续遍历结果:");
            ArrayUtil.printArray(TreeUtil.postOrder(r));

            System.out.print("算法结果:");
            System.out.println(MinDiffInBST.minDiffInBST(r));

            System.out.println();
        }
    }

}
