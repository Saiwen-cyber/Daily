package practice;

import java.sql.Array;
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
        int num = (int)obj;
        char[] binNum = this.bin(num);
        char[] hexNum = this.hex(binNum,8);
        if(num < 0){
            binNum = this.revesrsebin(binNum);
        }

        list.add(binNum);
        list.add(hexNum);
        return list;

    }

    /**
     * >0、<0
     * @param num
     * @return
     */
    private char[] bin(int num){
        char[] chars = new char[32];
        int i = 31;
        //全部填充0
        Arrays.fill(chars,'0');
        while (num != 0 ){
            chars[i--] = (char)(num%2 + '0');
            num = num/2;
        }
        return chars;
    }
    private char[] revesrsebin(char[] chars){
//        Integer
//        char[] chars = new char[32];

        return null;
    }

    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(34));
        String a = String.valueOf(new ChConverse().converse('A').get(0));
        String b = String.valueOf(new ChConverse().converse('A').get(1));
        System.out.println(a);
        System.out.println("0x"+b);
        System.out.println();
    }
}
