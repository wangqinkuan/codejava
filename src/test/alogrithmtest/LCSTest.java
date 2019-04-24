package test.alogrithmtest;

import alogrithm.LCS;
import org.junit.BeforeClass;
import org.junit.Test;
import util.ArrayUtil;

public class LCSTest {
    public static LCS lcs;

    @BeforeClass
    public static void init(){
        lcs = new LCS();
    }

    @Test
    public void test(){
        String x = "ABCBDAB",y = "BDCABA";
        System.out.println("串x:"+x+"\n串y:"+y);
        ArrayUtil.printArray2Dim(LCS.createLCS(x,y));
        System.out.println("结果:"+LCS.getLCS(x,y));
    }
}
