package solution;


/**
 * 软测实验1-人民币大写数字转换
 * http://staff.ustc.edu.cn/~shizhu/zlbz/1.txt
 * 具体需求如下：
 * 	1）中文大写金额数字应用壹、贰、叁、肆、伍、陆、柒、捌、玖、拾、佰、仟、万、亿、元、角、分、零、整(正)等字样。
 * 	2）中文大写金额数字到"元"为止的，在"元"之后，应写"整"(或"正")字，在"角"之后，可以不写"整"(或"正")字。
 * 	3）中文大写金额数字前应标明"人民币"字样，大写金额数字有"分"的，"分"后面不写"整"(或"正")字。
 * 	4）大写金额数字应紧接"人民币"字样填写，不得留有空白。
 * 	5）阿拉伯数字小写金额数字中有"0"时，中文大写应按照汉语语言规律、金额数字构成和防止涂改的要求进行书写。
 * 举例如下：
 * 1、阿拉伯数字中间有"0"时，中文大写要写"零"字，如￥1409.50，应写成：人民币壹仟肆佰零玖元伍角。
 * 2、阿拉伯数字中间连续有几个"0"时，中文大写金额中间只写一个"零"字，如￥6007.14，应写成：人民币陆仟零柒元壹角肆分。
 * 3、阿拉伯金额数字万位和元位是"0"，或者数字中间连续有几个"0"，万位、元位也是"0"，但千位、角位不是"0"时，中文大写金额中只写一个零字，也可以不写"零"字。如￥1680.32，应写成：人民币壹仟陆佰捌拾元叁角贰分，又如￥107000.53，应写成：人民币壹拾万零柒仟元伍角叁分。
 * 4、阿拉伯金额数字角位是"0"，而分位不是"0"时，中文大写金额"元"后面应写"零"字。如￥16409.02，应写成人民币：壹万陆仟肆佰零玖元零贰分；又如￥325.04，应写成人民币叁佰贰拾伍元零肆分。
 *
 * @author wqk
 * @date 2019/3/21
 */

public class ConvertAlaboToRMB {

    private final static String errorInput = "输入错误";

    private final static char[] chineseNum = {'零','壹','贰','叁','肆','伍','陆','柒','捌','玖'};

    private final static char[] chineseUnit = {'拾','佰','仟','万','亿'};

    /**
     * 将阿拉伯数字的钱转为大写的人民币
     * @param money 阿拉伯数字钱 以￥开头
     * @return 转换的大写人民币结果
     * @throws Exception 错误信息 主要是输入错误
     */
    public String convertToRMB(String money) throws Exception{
        if(money == null) throw new Exception(errorInput + " input为null");
        if(money.trim().length() == 1 || !money.trim().startsWith("￥")) throw new Exception(errorInput + " 请以￥开头，且输入至少一位数字");
        String RMB = "人民币";
        //是否只有整数部分
        String[] Alabo = money.trim().substring(1).split("\\.");
        if(Alabo.length > 2) throw new Exception(errorInput);
        //整数iPart与小数部分的转换结果jiaoandfen(角和分)
        String iPart = Alabo[0],jiaoandfen = "";
        //处理整数部分
        iPart = convertToRMBUnderYiZhao(iPart);
        RMB += iPart;
        if(iPart.trim().length() > 0) RMB += "元";
        else throw new Exception(errorInput + " 请输入整数部分");
        //如果有小数部分
        if(Alabo.length > 1) jiaoandfen = convertToRMBjiaofen(Alabo[1]);
        if(jiaoandfen.trim().length() < 1) RMB += "整";
        else RMB += jiaoandfen;
        return RMB;
    }

    private boolean isNum(char c){
        return c>='0' && c<='9';
    }

    /**
     * 将1兆(1亿个亿)以下的阿拉伯整数转为大写人民币
     * 1兆以下的数字可以拆分为2个千万，即 x千万+亿+x千万
     * @param Alabo 阿拉伯数字
     * @return 转换的人民币结果
     * @throws Exception
     */
    public String convertToRMBUnderYiZhao(String Alabo) throws Exception{
        if(Alabo == null) throw new Exception(errorInput);
        String result = "";
        int len = Alabo.length();
        if(len > 16) throw new Exception("convertToRMBUnderYiYi() 参数错误，只转换一兆(一亿个亿)以下的阿拉伯数字");
        if(len < 9 ) result = convertToRMBUnderYiYi(Alabo);
        //后八位 即千万的部分
        else {
            String qianWanPart = convertToRMBUnderYiYi(Alabo.substring(len - 8));
            //前面的 亿部分
            String wanPart = convertToRMBUnderYiYi(Alabo.substring(0,len - 8));
            if(wanPart.trim().length() > 0 && !wanPart.equals("零"))
                result += (wanPart + "亿");
            if(qianWanPart.trim().length() > 0){
                if(!qianWanPart.startsWith("零"))
                    result += "零";
                result += qianWanPart;
            }
        }
        return result.startsWith("零") ? result.substring(1) : result;
    }


    /**
     * 将1亿以下的阿拉伯整数转为大写人民币
     * 1亿以下的数字可以拆分为2个千，即 x千xx + 万 + x千xx
     * @param Alabo 阿拉伯数字
     * @return 转换的人民币结果
     * @throws Exception
     */
    private String convertToRMBUnderYiYi(String Alabo) throws Exception{
        if(Alabo == null) throw new Exception("输入为null");
        String result = "";
        int len = Alabo.length();
        if(Alabo == null) throw new Exception(errorInput);
        if(len > 8) throw new Exception("convertToRMBUnderYiYi() 参数错误，只转换一亿以下的阿拉伯数字");
        if(len < 5 ) return convertToRMBUnderTenThousand(Alabo);
        //后四位 即千的部分
        String qianPart = convertToRMBUnderTenThousand(Alabo.substring(len - 4));
        //前面的 万部分
        String wanPart = convertToRMBUnderTenThousand(Alabo.substring(0,len - 4));
        if(wanPart.trim().length() > 0 && !wanPart.equals("零"))
            result += (wanPart + "万");
        if(qianPart.trim().length() > 0){
            if(!qianPart.startsWith("零"))
                result += "零";
            result += qianPart;
        }
        return result;
    }


    /**
     * 将1万以下的阿拉伯整数转为大写人民币
     * @param Alabo 阿拉伯数字
     * @return 转换的人民币结果
     * @throws Exception
     */
    private String convertToRMBUnderTenThousand(String Alabo) throws Exception {
        if(Alabo == null) throw new Exception("输入为null");
        String result = "";
        if(Alabo == null) throw new Exception(errorInput);
        if(Alabo.length() > 4) throw new Exception("convertToRMBUnderTenThousand() 参数错误，只转换一万以下的阿拉伯数字");
        int i = 0,len = Alabo.length();
        while(i < len){
            char numI = Alabo.charAt(i);
            if(!isNum(numI)) throw new Exception("整数部分" + errorInput);
            if(numI != '0'){
                result += chineseNum[numI - '0'];
                if(len - i > 1) result += chineseUnit[len - i - 2];
                i++;
            }else {
                //找到一个不为0的数
                while(i < len){
                    char zeroI = Alabo.charAt(i);
                    if(!isNum(zeroI)) throw new Exception("整数部分" + errorInput);
                    if(zeroI - '0' != 0) {
                        result += chineseNum[0];
                        break;
                    }
                    i++;
                }
            }
        }
        return result;
    }

    /**
     * 把阿拉伯数字小数转为角和分
     * @param fPart 小数部分
     * @return 转换的人民币结果
     * @throws Exception
     */
    public String convertToRMBjiaofen(String fPart) throws Exception{
        if(fPart == null) throw new Exception("输入为null");
        String jiaoandfen = "";
        if(fPart.trim().length() > 2) throw new Exception("小数部分" + errorInput + " 最多输入两位(角和分)");
        //只有角部分
        if(fPart.trim().length() == 1){
            char jiao = fPart.charAt(0);
            if(!isNum(jiao)) throw new Exception("小数部分" + errorInput);
            if(jiao != '0') jiaoandfen += chineseNum[jiao - '0'] + "角";

        }else {
            char jiao = fPart.charAt(0),fen = fPart.charAt(1);
            if(!isNum(jiao) || !isNum(fen)) throw new Exception("小数部分" + errorInput);
            if(jiao == '0'){
                if(fen != '0') jiaoandfen += "零" + chineseNum[fen - '0'] + "分";
            }else {
                jiaoandfen += chineseNum[jiao - '0'] + "角";
                if(fen != '0') jiaoandfen += chineseNum[fen - '0'] + "分";
            }
        }
        return jiaoandfen;
    }
}
