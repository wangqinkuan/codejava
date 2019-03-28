package softwaretesthomework;

/*
http://staff.ustc.edu.cn/~shizhu/zlbz/1.txt
具体需求如下：
	1）中文大写金额数字应用壹、贰、叁、肆、伍、陆、柒、捌、玖、拾、佰、仟、万、亿、元、角、分、零、整(正)等字样。
	2）中文大写金额数字到"元"为止的，在"元"之后，应写"整"(或"正")字，在"角"之后，可以不写"整"(或"正")字。
	3）中文大写金额数字前应标明"人民币"字样，大写金额数字有"分"的，"分"后面不写"整"(或"正")字。
	4）大写金额数字应紧接"人民币"字样填写，不得留有空白。
	5）阿拉伯数字小写金额数字中有"0"时，中文大写应按照汉语语言规律、金额数字构成和防止涂改的要求进行书写。
举例如下：
1、阿拉伯数字中间有"0"时，中文大写要写"零"字，如￥1409.50，应写成：人民币壹仟肆佰零玖元伍角。
2、阿拉伯数字中间连续有几个"0"时，中文大写金额中间只写一个"零"字，如￥6007.14，应写成：人民币陆仟零柒元壹角肆分。
3、阿拉伯金额数字万位和元位是"0"，或者数字中间连续有几个"0"，万位、元位也是"0"，但千位、角位不是"0"时，中文大写金额中只写一个零字，也可以不写"零"字。如￥1680.32，应写成：人民币壹仟陆佰捌拾元叁角贰分，又如￥107000.53，应写成：人民币壹拾万零柒仟元伍角叁分。
4、阿拉伯金额数字角位是"0"，而分位不是"0"时，中文大写金额"元"后面应写"零"字。如￥16409.02，应写成人民币：壹万陆仟肆佰零玖元零贰分；又如￥325.04，应写成人民币叁佰贰拾伍元零肆分。
 */

import java.util.LinkedList;

/**
 * 软测实验1-人民币大写数字转换
 * @author southday
 * @date 2019/3/21
 */
public class RMBCapitalFigureTranslator {
    private static final String IZERO_REGEX = "^￥0(\\.\\d{1,2})?";
    private static final String REGEX = "^￥[1-9]\\d{0,11}(\\.\\d{1,2})?";
    private static final String[] capital = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾"};
    private static final String[] unit = {"元", "万", "亿"};
    private static final String[] degree = {"", "拾", "佰", "仟"};
    private static final String prompt = "请输入格式为：\"￥xxxxxxxxxxxx.yy\"的字符串：\n"
            + "- x...x最多有12位，若整数部分值不为0，则最高位（左高->右低）不能以0开始；\n"
            + "- .yy不是必须存在的，其可以为：.y0 .0y .00 的格式；";

    private enum ErrorCode {
        INPUT_NULL,
        FORMAT_ERROR
    }

    /**
     * 打印提示信息
     * @param number 用户输入的字符串
     * @param code 提示码，不同的code对应不同的提示信息
     */
    private static void printPrompt(String number, ErrorCode code) {
        System.out.println("---------------------------------------------------------------------------");
        if (code == ErrorCode.INPUT_NULL) {
            System.out.println("转换失败：输入为空字符串，number = \"\"");
        } else if (code == ErrorCode.FORMAT_ERROR) {
            System.out.println("转换失败，输入格式错误，number = \"" + number + "\"");
        }
        System.out.println(prompt);
        System.out.println("---------------------------------------------------------------------------");
    }

    /**
     * 输入一个数字字符串，转换为人民币大写形式
     * @param number 代表要转换的数字（如果有小数点，则精确到两位小数），如：123 123.04 12045.39
     * @return
     */
    public static String translate(String number) {
        if (number == null || number.trim().length() == 0) {
            printPrompt(number, ErrorCode.INPUT_NULL);
            return null;
        }
        int rp = 0;
        String dpart = null;
        String ipart = null;
        String result = null;
        if (number.matches(IZERO_REGEX)) {
            rp = number.indexOf('.');
            if (rp != -1)
                dpart = translateDecimal(number.substring(rp + 1), true);
            result = dpart == null ? "零元整" : dpart;
        } else if (number.matches(REGEX)) {
            rp = number.indexOf('.');
            if (rp != -1) {
                dpart = translateDecimal(number.substring(rp + 1), false);
                ipart = translateInteger(number.substring(1, rp));
            } else {
                ipart = translateInteger(number.substring(1));
            }
            result = dpart == null ? ipart  + "整" : ipart + dpart;
        } else {
            printPrompt(number, ErrorCode.FORMAT_ERROR);
        }
        return result == null ? null : "人民币" + result;
    }

    /**
     * 转换整数部分
     * @param ipart 整数部分的数值
     * @return
     */
    public static String translateInteger(String ipart) {
        StringBuilder sb = new StringBuilder(ipart);
        char[] s = sb.reverse().toString().toCharArray();
        sb.delete(0, sb.length());
        LinkedList<String> stack = new LinkedList<>();
        boolean needZero = false; // 是否写"零"
        boolean changeOrder; // 标记：单位与"零"是否改变先后顺序，见例子：107000，应写为：壹拾万零柒仟元
        boolean allZero; // 一个区间（每4个位一个区间）内的数值是否全为0
        int lastNZIndex = 0; // 上一个非零数的角标
        for (int k = 0; k < s.length; k += 4) {
            changeOrder = false;
            allZero = true;
            for (int i = 0; i < 4 && k+i < s.length; i++) {
                if (s[k+i] == '0') {
                    // needZero == true 并且本次角标与上一次非零数角标要在同一区间（每4个位一个区间）
                    if (needZero && (k+i)/4 == lastNZIndex/4) {
                        sb.append("零");
                        needZero = false;
                    } else if (needZero && (k+i)/4 != lastNZIndex/4) { // 说明单位与"零"的先后顺序需要交换
                        changeOrder = true;
                    }
                } else {
                    sb.append(degree(i) + capital(s[k+i])); // 需要反转
                    needZero = true;
                    lastNZIndex = k + i;
                    allZero = false;
                }
            }
            sb.reverse();
            if (!allZero)
                sb.append(unit(k));
            if (changeOrder)
                sb.append("零");
            stack.push(sb.toString());
            sb.delete(0, sb.length());
        }
        while (!stack.isEmpty())
            sb.append(stack.pop());
        return sb.toString();
    }

    /**
     * 转换小数部分
     * @param dpart 小数部分的数值
     * @param izero 整数部分是否为0值
     * @return
     */
    public static String translateDecimal(String dpart, boolean izero) {
        char[] s = dpart.toCharArray();
        StringBuilder sb = new StringBuilder();
        if (s[0] != '0') {
            sb.append(capital(s[0]) + "角");
            if (s[1] != '0')
                sb.append(capital(s[1]) + "分");
        } else if (s[1] != '0') {
            sb.append((izero ? "" : "零") + capital(s[1]) + "分");
        }
        return sb.length() == 0 ? null : sb.toString();
    }

    /**
     * 根据字符'0'-'9'获取对应的人民币大写字符
     * @param c
     * @return
     */
    private static String capital(char c) {
        return capital[c - '0'];
    }

    /**
     * 根据角标i转为对应的阶：{"", "拾", "佰", "仟"};
     * @param i
     * @return
     */
    private static String degree(int i) {
        return degree[i % 4];
    }

    /**
     * 根据角标转为对应的单位：{"元", "万", "亿"};
     * @param i
     * @return
     */
    private static String unit(int i) {
        return unit[i / 4];
    }
}
