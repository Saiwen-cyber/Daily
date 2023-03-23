package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fang
 */
public class FloatConverse extends ConverseUtil{

    @Override
    public List<char[]> converse(Object obj) {
        List<char[]> list = new ArrayList<char[]>();
        String num = (String)obj;
        char[] binNum = this.bin(num);
        char[] hexNum = this.hex(binNum,8);

        list.add(binNum);
        list.add(hexNum);
        return list;
    }
    private char[] bin(String num){
        char[] rechars;
        Floatchar fc ;
        String sign = "0", m="", e="";
        //如果负数更改符号位，并且把去掉"-"
        if(num.startsWith("-")){
            sign = "1";
            num = num.substring(1);
        }
        //分开整数以及小数部分
        String[] strs = num.split("\\.");
        int numl = Integer.parseInt(strs[0]);
        strs[1] = "0."+strs[1];
        float numr = Float.parseFloat(strs[1]);
        int expNum;

        //判断整数是否为0
        if(strs[0].equals("0")){
            fc =  floatToChars(numr,false);
            expNum = fc.exp;
            m = String.valueOf(fc.chars);
        }else {
            fc =  floatToChars(numr,true);
            //取整数部分的二进制串;
            char[] chars1 = IntConverse.bin(numl,32);
            String tmpStr = String.valueOf(chars1);
            //指数
            int tmpAddr = tmpStr.indexOf('1') + 1;
            expNum = chars1.length -  tmpAddr;

            //将整数部分的尾数转换成字符串暂存mstr
            String mstr =String.valueOf(Arrays.copyOfRange(chars1,tmpAddr,chars1.length));
            m = (mstr + String.valueOf(fc.chars)).substring(0,23);
        }
        //8位阶码
        e = String.valueOf(IntConverse.bin(expNum + 127,8));
        rechars = (sign+e+m).toCharArray();
        return rechars;
    }

    static class Floatchar{
       char[] chars;
       int exp;
    }

    /**
     * @param num
     * @param flag flag为true表示为有整数部分
     * @return
     */
    public Floatchar floatToChars(float num, boolean flag){
        Floatchar fc = new Floatchar();
        //尾数，比23大。  确定精度
        char[] chars = new char[32];
        Arrays.fill(chars,'0');
        int i = 0;

        while (num > 0 && num < 1&& i < chars.length ){
                num = num * 2;
                if(num >= 1){
                    num = num - 1;
                    chars[i++] = '1';
                }else{
                    chars[i++] = '0';
                }
        }
        //没有整数部分需要返回阶码。
        int start = 0;
        if(!flag){
            String tmpStr = String.valueOf(chars);
            int addr = tmpStr.indexOf('1') + 1;
            fc.exp = addr * (-1);
            start = addr;
        }
            chars = Arrays.copyOfRange(chars,start,24+start);
            char end2 = chars[23];
            chars = Arrays.copyOfRange(chars,0,24);
            //加一
            if(end2 == '1'){
                IntConverse.addone(chars);
            }
            fc.chars = chars;
        return fc;
    }
}
