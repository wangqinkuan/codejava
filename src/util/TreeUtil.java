package util;

import ds.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

    /**
     * 通过数组创建树
     * @param array
     * @return
     */
    public static TreeNode createTreeByArray(Integer[] array){
        if(array.length == 0) return null;
        TreeNode[] tree = new TreeNode[array.length];
        for(int i = 0;i < array.length;i++ ) tree[i] = array[i] == null ? null : new TreeNode(array[i]);
        for(int i  = 0;i < tree.length >> 1;i ++ ){
            if(tree[i] != null){
                int rightIndex = (i+1) << 1,leftIndex = rightIndex - 1;
                if(array[rightIndex] != null) tree[i].right = tree[rightIndex];
                if(array[leftIndex] != null) tree[i].left = tree[leftIndex];
            }
        }
        return tree[0];
    }

    /**
     * 通过Integer数组创建BST
     * 输入的数组以及生成的树的结构见题目描述
     * @return BST的root
     */
    public static TreeNode createBSTByArray(Integer[] array){
        if( array.length == 0 ) return null;
        if( array[0] == null) return null;
        TreeNode root = new TreeNode(array[0]);
        for (int i = 1;i < array.length ;i++){
            if(array[i] != null) insertBST(root,array[i]);
        }
        return root;
    }

    /**
     * 向BST中插入值v
     * @param root
     * @param v
     */
    private static void insertBST(TreeNode root , int v){
        if(root != null) {
            if (v > root.val){
                if (root.right == null) root.right = new TreeNode(v);
                else insertBST(root.right,v);
            } else {
                if (root.left == null) root.left = new TreeNode(v);
                else insertBST(root.left,v);
            }
        }
    }

    /**
     * 先序遍历
     */
    public static List<Integer> preOrder(TreeNode root){
        List res = new ArrayList<>();
        if(root != null){
            res.add(root.val);
            res.addAll(preOrder(root.left));
            res.addAll(preOrder(root.right));
        }
        return res;
    }

    /**
     * 中序
     */
    public static List<Integer> inOrder(TreeNode root){
        List res = new ArrayList<>();
        if(root != null){
            res.addAll(inOrder(root.left));
            res.add(root.val);
            res.addAll(inOrder(root.right));
        }
        return res;
    }

    /**
     * 后序
     */
    public static List<Integer> postOrder(TreeNode root){
        List res = new ArrayList<>();
        if(root != null){
            res.addAll(postOrder(root.left));
            res.addAll(postOrder(root.right));
            res.add(root.val);
        }
        return res;
    }

    /**
     * 打印树的先序中序后续
     * @param root
     */
    public static void printTree(TreeNode root){
        System.out.print("先序遍历结果:");
        ArrayUtil.printArray(preOrder(root));
        System.out.print("中序遍历结果:");
        ArrayUtil.printArray(inOrder(root));
        System.out.print("后序遍历结果:");
        ArrayUtil.printArray(postOrder(root));
    }
}
