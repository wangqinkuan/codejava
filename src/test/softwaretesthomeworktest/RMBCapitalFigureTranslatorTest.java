package test.softwaretesthomeworktest;

import org.junit.Test;
import softwaretesthomework.RMBCapitalFigureTranslator;

public class RMBCapitalFigureTranslatorTest {
    @Test
    public void translateIntegerTest() {
        test01("1409"); // 壹仟肆佰零玖元
        test01("6007"); // 陆仟零柒元
        test01("107000"); // 壹拾万零柒仟元
        test01("10107000"); // 壹仟零壹拾万零柒仟元
        test01("16409"); // 壹万陆仟肆佰零玖元
        test01("325"); // 叁佰贰拾伍元
    }

    private void test01(String s) {
        System.out.println("输入-->" + s + "\n输出--> " + RMBCapitalFigureTranslator.translateInteger(s));
        System.out.println();
    }

    @Test
    public void translateDecimalTest() {
        test02("23", true); // 贰角叁分
        test02("00", true); // null
        test02("30", true); // 叁角
        test02("02", true); // 贰分
        System.out.println("------------------------");
        test02("23", false); // 贰角叁分
        test02("00", false); // null
        test02("30", false); // 叁角
        test02("02", false); // 零贰分
    }

    private void test02(String s, boolean izero) {
        System.out.println("输入-->" + s + "\n输出--> " + RMBCapitalFigureTranslator.translateDecimal(s, izero));
        System.out.println();
    }

    @Test
    public void translateTest() {
        test03("￥555.10");
        test03("￥000.00");
        test03("￥55555.55");
        test03("￥12345");
    }

    private void test03(String s) {
        System.out.println("输入-->" + s + "\n输出--> " + RMBCapitalFigureTranslator.translate(s));
        System.out.println();
    }
}
