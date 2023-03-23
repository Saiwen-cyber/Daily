package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 整形转换
 * @author fang
 */
public class IntConverse extends ConverseUtil{

    @Override
    public List<char[]> converse(Object obj) {
        List<char[]> list = new ArrayList<char[]>();
        int num = Integer.valueOf((String.valueOf(obj)));
        char[] binNum;
        if(num < 0){
            binNum = revesrsebin(num);
        }else {
            binNum = bin(num,32);
        }

        char[] hexNum = this.hex(binNum,8);
        list.add(binNum);
        list.add(hexNum);
        return list;

    }

    /**
     * 正数、负数
     * @param num length
     * @return
     */
    public static char[] bin(int num,int length){
        char[] chars = new char[length];
        length = length-1;
        //全部填充0
        Arrays.fill(chars,'0');
        while (num != 0 ){
            int tmp = num%2;
            chars[length--] = (char)(num%2 + '0');
            num = num/2;
        }
        return chars;
    }
    public static char[] revesrsebin(int num){
        //取反
        char[] chars = bin(Math.abs(num),32);
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '0'){
                chars[i] = '1';
            }else {
                chars[i] = '0';
            }
        }
        //加一
        addone(chars);
        return chars;
    }
    //加一
    public static char[] addone(char[] chars){

        boolean flag  = false;
        for (int i = chars.length - 1; i >=0 ; i--) {
            if(chars[i] == '0'){
                if(i ==chars.length - 1 || flag){
                    chars[i] = '1';
                    return chars;
                }
            }else {
                if(i == chars.length - 1 || flag){
                    chars[i] = '0';
                    flag = true;
                }
            }
        }
        return chars;
    }
}
