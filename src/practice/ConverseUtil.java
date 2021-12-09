package practice;

import java.util.Arrays;
import java.util.List;

/**
 * @author fang
 */
public abstract class ConverseUtil {
    public abstract List<char[]> converse(Object obj);
    /**
     * 十六进制不必由十进制转换，统一由二进制转换，给定数组长度就好。
     * @param chars
     * @param length
     * @return
     */
    public char[] hex(char[] chars,int length){
        char[] rechars = new char[length];
        int j = 0,i = 0;
        String chs = String.valueOf(chars);
        String str = "";
        //全部填充0
        Arrays.fill(rechars,'0');

        while (i < length){
            str = chs.substring(j,j+4);
            switch (str){
                case "0000": i++;break;
                case "0001": rechars[i++] = '1';break;
                case "0010": rechars[i++] = '2';break;
                case "0011": rechars[i++] = '3';break;
                case "0100": rechars[i++] = '4';break;
                case "0101": rechars[i++] = '5';break;
                case "0110": rechars[i++] = '6';break;
                case "0111": rechars[i++] = '7';break;
                case "1000": rechars[i++] = '8';break;
                case "1001": rechars[i++] = '9';break;
                case "1010": rechars[i++] = 'A';break;
                case "1011": rechars[i++] = 'B';break;
                case "1100": rechars[i++] = 'C';break;
                case "1101": rechars[i++] = 'D';break;
                case "1110": rechars[i++] = 'E';break;
                case "1111": rechars[i++] = 'F';break;
            }
            j = j+4;
        }
        return rechars;
    }
}
