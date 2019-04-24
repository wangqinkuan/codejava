package alogrithm;

/**
 * 使用动态规划算法解决最长公共子序列问题：给定两个序
 * 列X={x1,x2,...,xm}和Y={y1,y2,...,yn}，找出X和Y的最
 * 长公共子序列。
 * 示例：
 * X={a,a,b,c,b,d,a,b},Y={b,d,c,a,b,c}
 *  返回:{a,b,c}
 */
public class LCS {
    /**
     * 构造最长公共子序列
     * @param x
     * @param y
     * @return
     */
    public static int[][] createLCS(String x,String y){
        int xlen = x.length(),ylen = y.length();
        int[][] c = new int[xlen + 1][ylen + 1];
        for (int i = 1;i <= xlen;i++) {
            for (int j = 1;j <= ylen;j++){
                if(x.charAt(i - 1) == y.charAt(j - 1)) c[i][j] = c[i - 1][j - 1] + 1;
                else c[i][j] = Math.max(c[i - 1][j],c[i][j - 1]);
            }
        }
        return c;
    }

    /**
     * 获取最长公共子序列
     * @param c lcs
     * @param x
     * @param y
     * @param i x长度
     * @param j y长度
     * @return
     */
    public static String getLCS(int[][] c, String x, String y, int i, int j){
        String lcs = "";
        if(i == 0 || j == 0) {
            return lcs;
        }
        if(x.charAt(i - 1) == y.charAt(j - 1)){
            lcs += getLCS(c,x,y,i-1,j-1);
            lcs += x.charAt(i - 1);
        }else {
            if(c[i][j-1] > c[i-1][j]) lcs += getLCS(c,x,y,i,j-1);
            else lcs += getLCS(c,x,y,i-1,j);
        }
        return lcs;
    }

    /**
     * 获取xy的最长公共子序列
     * @param x
     * @param y
     * @return
     */
    public static String getLCS(String x,String y){
        return getLCS(createLCS(x,y),x,y,x.length(),y.length());
    }

    /**
     * 构造x与y的最长公共子串
     */
    public static int[][] createLCString(String x,String y){
        int xlen = x.length(),ylen = y.length();
        int[][] c = new int[xlen + 1][ylen + 1];
        for (int i = 1;i <= xlen;i++) {
            for (int j = 1;j <= ylen;j++) if(x.charAt(i-1) == y.charAt(j-1)) c[i][j] = c[i-1][j-1] + 1;
        }
        return c;
    }

    /**
     * 获取最长公共子串
     * @param c
     * @param x
     * @return
     */
    private static String getLCString(int[][] c,String x){
        int max = 0,loci = 0,locj = 0;
        for (int i = 0;i < c.length;i++){
            for (int j = 0;j < c[0].length ; j++){
                if(c[i][j] > max) {
                    max = c[i][j];
                    loci = i;
                    locj = j;
                }
            }
        }
        return x.substring(loci - c[loci][locj],loci);
    }

}


