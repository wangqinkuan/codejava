package alogrithm;

import ds.Color;
import ds.RBTreeNode;

/**
 * 根据性质， 实现红黑树的插入。
 * 示例： 输入[41,38,31,12,19,8] ，输出如下
 *  38
 * / \
 * 19 41
 * / \
 * 12 31
 * / \
 * N N
 * / \
 *  8
 * N
 * N
 * / \
 * N N
 */
public class RBTree {

    public static RBTreeNode createRBTree(int[] array){
        if(array.length < 1) return null;
        RBTreeNode root = new RBTreeNode(array[0]);
        root.color = Color.BLACK;
        for(int i = 1;i < array.length ;i++ ){
            root = insertRBTree(root,new RBTreeNode(array[i]));
        }
        return root;
    }

    /**
     * 红黑树的插入
     * @param root
     * @param z
     * @return 插入后新的根节点
     */
    public static RBTreeNode insertRBTree(RBTreeNode root,RBTreeNode z){
        RBTreeNode y = RBTreeNode.nil,x = root;
        while(x != RBTreeNode.nil){
            y = x;
            if(z.key < x.key) x = x.left;
            else x = x.right;
        }
        z.p = y;
        if(y == RBTreeNode.nil) root = z;
        else if(z.key < y.key) y.left = z;
        else y.right = z;
        return RBInsertFixUp(root,z);
    }

    /**
     * 红黑树的左旋
     * @param x
     * @param root
     * @return 旋转后新的根节点
     */
    public static RBTreeNode leftRotate(RBTreeNode root,RBTreeNode x){
        if(x.right != RBTreeNode.nil){
            RBTreeNode y = x.right;
            x.right = y.left;
            if(y.left != RBTreeNode.nil) y.left.p = x;
            y.p = x.p;
            if(x.p == RBTreeNode.nil) root = y;
            else if (x == x.p.left) x.p.left = y;
            else x.p.right = y;
            y.left = x;
            x.p = y;
        }
        return root;
    }

    /**
     * 红黑树的右旋
     * @param root
     * @param y
     * @return 旋转后新的根节点
     */
    public static RBTreeNode rightRotate(RBTreeNode root,RBTreeNode y){
        if(y.left != RBTreeNode.nil){
            RBTreeNode x = y.left;
            y.left = x.right;
            if(x.right != RBTreeNode.nil) x.right.p = y;
            x.p = y.p;
            if(y.p == RBTreeNode.nil) root = x;
            else if (y == y.p.left) y.p.left = x;
            else y.p.right = x;
            x.right = y;
            y.p = x;
        }
        return root;
    }

    /**
     * 红黑树插入修正
     * @param root
     * @param z
     * @return 修正后新的根节点
     */
    public static RBTreeNode RBInsertFixUp(RBTreeNode root,RBTreeNode z){
        //不断修正直至父节点为black
        while(z.p.color == Color.RED){
            //case A 插入z的父节点为爷爷节点的左子节点
            if(z.p == z.p.p.left){
                //y为uncle节点
                RBTreeNode y = z.p.p.right;
                //case1A uncle为red
                if(y.color == Color.RED){
                    //将z的父与uncle都置为black，爷爷置为red，随后将z变为爷爷继续操作
                    z.p.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.p.p.color = Color.RED;
                    z = z.p.p;
                }
                //case2A uncle为black && 插入的z为其父节点的右子节点
                else if(z == z.p.right){
                    z = z.p;
                    root = leftRotate(root,z);
                }
                //case3A uncle位black && 插入的z为其父节点的左子节点
                else {
                    z.p.color = Color.BLACK;
                    z.p.p.color = Color.RED;
                    root = rightRotate(root,z.p.p);
                }
            }
            //case B 插入z的父节点为爷爷节点的右子节点
            else {
                RBTreeNode y = z.p.p.left;
                //case1B uncle为red
                if(y.color == Color.RED){
                    z.p.color = Color.BLACK;
                    y.color = Color.BLACK;
                    z.p.p.color = Color.RED;
                    z = z.p.p;
                }
                //case2B uncle为black && 插入的z为其父节点的右子节点
                else if(z == z.p.right){
                    z.p.color = Color.BLACK;
                    z.p.p.color = Color.RED;
                    root = leftRotate(root,z.p.p);
                }
                //case3B uncle位black && 插入的z为其父节点的左子节点
                else {
                    z = z.p;
                    root = rightRotate(root,z);
                }
            }
        }
        root.color = Color.BLACK;
        return root;
    }
}
