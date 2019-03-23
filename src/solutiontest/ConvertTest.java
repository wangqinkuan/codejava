package solutiontest;

import org.junit.BeforeClass;
import org.junit.Test;
import solution.ConvertAlaboToRMB;

public class ConvertTest {

    public static ConvertAlaboToRMB convert;

    @BeforeClass
    public static void init(){
        convert = new ConvertAlaboToRMB();
    }

    @Test
    public void convertToRMBIntTest(){
        System.out.println("------------------------将一兆(一亿个亿)以下的阿拉伯整数转为大写人民币  begin------------------------");
        test01("23");
        test01("23324324234");
        test01("000023");
        test01("000");
        test01(null);
        test01("7000");
        test01("9001");
        System.out.println("------------------------将一兆(一亿个亿)以下的阿拉伯整数转为大写人民币  end  ------------------------");
        System.out.println();

    }

    private void test01(String iPart){
        try {
            System.out.println("输入---------->" + (iPart == null ? "null" : iPart));
            System.out.println("输出---------->"+convert.convertToRMBUnderYiZhao(iPart));
        }catch (Exception e){
            System.out.println("错误---------->"+e.getMessage());
        }
        System.out.println();
    }


    @Test
    public void convertToRMBjiaofenTest(){
        System.out.println("----------------------------将阿拉伯数字转为角分  begin----------------------------");
        test02("33");
        test02("00");
        test02("03");
        test02("30");
        test02(null);
        test02("555");
        test02("3");
        test02("abc");
        System.out.println("----------------------------将阿拉伯数字转为角分  end  ----------------------------");
        System.out.println();

    }

    private void test02(String dcimal){
        try {
            System.out.println("输入---------->" + (dcimal == null ? "null" : dcimal));
            System.out.println("输出---------->"+convert.convertToRMBjiaofen(dcimal));
        }catch (Exception e){
            System.out.println("错误---------->"+e.getMessage());
        }
        System.out.println();
    }

    @Test
    public void convertTest(){
        System.out.println("----------------------------最终效果测试  begin----------------------------");
        test03("￥33");
        test03("￥000");
        test03("￥00033");
        test03("￥00033");
        test03("6");
        test03("afdsf");
        test03("￥1409.50"); // 人民币壹仟肆佰零玖元伍角
        test03("￥6007.14"); // 人民币陆仟零柒元壹角肆分
        test03("￥107000.53"); // 人民币壹拾万零柒仟元伍角叁分
        test03("￥16409.02"); // 人民币壹万陆仟肆佰零玖元零贰分
        test03("￥325.04"); // 人民币叁佰贰拾伍元零肆分
        test03("￥0.00"); // 人民币零元整
        test03("￥300.00"); // 人民币叁佰元整
        test03("￥301.00"); // 人民币叁佰零壹元整
        test03("￥10107000"); // 人民币壹仟零壹拾万零柒仟元整
        test03("￥101.00.0"); // null
        test03("￥0.54"); // 人民币伍角肆分
        test03("￥0.03"); // 人民币叁分
        test03("￥999999999999.99"); // 人民币玖仟玖佰玖拾玖亿玖仟玖佰玖拾玖万玖仟玖佰玖拾玖元玖角玖分
        test03("￥1000000000000.00"); // null
        test03("￥100000000001.00"); // 人民币壹仟亿零壹元整
        test03("￥100000100001.00"); // 人民币壹仟亿零壹拾万零壹元整
        test03(null);
        System.out.println("----------------------------最终效果测试  end  ----------------------------");
        System.out.println();
    }

    private void test03(String Alabo){
        try {
            System.out.println("输入---------->" + (Alabo == null ? "null" : Alabo));
            System.out.println("输出---------->"+convert.convertToRMB(Alabo));
        }catch (Exception e){
            System.out.println("错误---------->"+e.getMessage());
        }
        System.out.println();
    }
}
