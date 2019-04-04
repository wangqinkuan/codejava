package alogrithm;

import ds.TreeNode;

import java.util.Stack;

/**
 * 给定一个二叉搜索树的根结点 root, 返回树中任意两节点的差的最小值。
 *
 * 示例：
 *
 * 输入: root = [4,2,6,1,3,null,null]
 * 输出: 1
 * 解释:
 * 注意，root是树结点对象(TreeNode object)，而不是数组。
 *
 * 给定的树 [4,2,6,1,3,null,null] 可表示为下图:
 *
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 *
 * 最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
 * 注意：
 *
 * 二叉树的大小范围在 2 到 100。
 * 二叉树总是有效的，每个节点的值都是整数，且不重复。
 */
public class MinDiffInBST {
    /**
     * 解决方案:非递归中序遍历
     * @param root
     * @return
     */
    public static int minDiffInBST(TreeNode root) {
        int minDiff = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode last = null;
        while( root != null || !stack.empty() ) {
            while(root != null ){
                stack.push(root);
                root = root.left;
            }
            if(!stack.empty()){
                TreeNode top = stack.pop();
                if(last != null)  minDiff = Math.min(minDiff,top.val - last.val);
                last = top;
                root = top.right;
            }
        }
        return minDiff;
    }



}
