package alogrithm;

import ds.TreeNode;

public class MaxDepth {
    public static int maxDepth(TreeNode root){
        if(root == null) return 0;
        else if(root.left == null) return maxDepth(root.right) + 1;
        else if(root.right == null) return maxDepth(root.left) + 1;
        int left = maxDepth(root.left),right = maxDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }
}
