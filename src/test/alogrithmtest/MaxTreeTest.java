package test.alogrithmtest;

import alogrithm.MaxDepth;
import alogrithm.MaxTree;
import ds.TreeNode;
import org.junit.Test;
import util.TreeUtil;

public class MaxTreeTest {

    @Test
    public void test(){
        TreeNode maxTree = MaxTree.maxTree(new int[]{3,2,1,6,0,5});
        TreeUtil.printTree(maxTree);

        System.out.println(MaxDepth.maxDepth(TreeUtil.createTreeByArray(new Integer[]{3,9,20,null,null,15,7})));

    }
}
