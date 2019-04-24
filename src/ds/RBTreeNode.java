package ds;

public class RBTreeNode {

    public static RBTreeNode nil = new RBTreeNode(-1,Color.BLACK);

    public int key;
    public Color color;
    public RBTreeNode left;
    public RBTreeNode right;
    public RBTreeNode p;

    /**初始为红色
     * 初始化左右节点为nil
     * @param key
     */
    public RBTreeNode(int key){
        this.key = key;
        this.color = Color.RED;
        this.left = nil;
        this.right = nil;
        this.p = nil;
    }

    public RBTreeNode(int key,Color color){
        this.key = key;
        this.color = color;
        this.left = null;
        this.right = null;
        this.p = null;
    }
}

