package test.alogrithmtest;

import alogrithm.MinDepth;
import ds.TreeNode;
import org.junit.BeforeClass;
import org.junit.Test;
import util.ArrayUtil;
import util.TreeUtil;

import java.util.List;

public class MinDepthTest {

    public static MinDepth minDepth;

    @BeforeClass
    public static void init(){
        minDepth = new MinDepth();
    }

    @Test
    public void test(){
        TreeNode root = TreeUtil.createTreeByArray(new Integer[]{3,9,20,null,null,15,7});
        TreeUtil.printTree(root);
        System.out.println("DFS："+MinDepth.minDepthDFS(root));
        System.out.println("BFS："+MinDepth.minDepthBFS(root));
    }
}
