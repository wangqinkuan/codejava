package alogrithm;

import ds.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最小深度，分别用 BFS 和
 * DFS 实现。
 * 说明: 最小深度是从根节点到最近叶子节点的最短
 * 路径上的节点数量。叶子节点是指没有子节点的节
 * 点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *    3
 *   / \
 *  9  20
 *  /   \
 * 15   7
 * 返回它的最小深度 2
 */
public class MinDepth {
    /**
     * 深度优先
     * @param root
     * @return
     */
    public static int minDepthDFS(TreeNode root){
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;
        else if(root.left == null) return minDepthDFS(root.right) + 1;
        else if(root.right == null) return minDepthDFS(root.left) + 1;
        else return Math.min(minDepthDFS(root.left),minDepthDFS(root.right)) + 1;
    }

    /**
     * 广度优先
     * @param root
     * @return
     */
    public static int minDepthBFS(TreeNode root){
        if(root == null) return 0;
        int dep = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            dep ++;
            int len = queue.size();
            for (int i = 0 ;i < len ;i ++){
                TreeNode treeNode = queue.remove();
                if(treeNode.left == null && treeNode.right == null) return dep;
                if(treeNode.left != null) queue.add(treeNode.left);
                if(treeNode.right != null) queue.add(treeNode.right);
            }
        }
        return dep;
    }
}
