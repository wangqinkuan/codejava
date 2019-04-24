package test.alogrithmtest;

import alogrithm.RBTree;
import ds.RBTreeNode;
import org.junit.Test;
import util.TreeUtil;

public class RBTreeTest {
    @Test
    public void insertTest(){
        RBTreeNode root = RBTree.createRBTree(new int[]{41,38,31,12,19,8});
        TreeUtil.drawRBTree(root);
    }
}
