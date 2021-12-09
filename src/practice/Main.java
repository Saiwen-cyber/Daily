package practice;

import org.openjdk.jol.vm.VM;

import java.util.Scanner;

/**
 * @author fang
 * 用户输入，并且判断用户输入的是字符、整形、或浮点数
 */

public class Main {

    @SuppressWarnings("restriction")
    public void run() {
        //输入对象保存在check中，获取实际地址以及十六进制编码
        Object check = 0;

        ConverseUtil converseUtil ;
        while(true) {
            System.out.print("请输入您要查询的数据：");
            Scanner in = new Scanner(System.in);
            String instr = in.next();

            //判断单个字符、浮点数、整形。
            if(instr.startsWith("'") && instr.endsWith("'") && instr.length() == 3){
                converseUtil = new ChConverse();
                instr = instr.substring(1,2);
            } else if(instr.contains(".")) {
                try {
                    Float.valueOf(instr);
                }catch (Exception e){
                    System.out.println("输入错误，请重新输入！" + e.getMessage());
                }
                converseUtil = new FloatConverse();
            }else{
                try {
                    Integer.valueOf(instr);
                }catch (Exception e){
                    System.out.println("输入错误，请重新输入！" + e.getMessage());
                }
                converseUtil = new IntConverse();
            }

            String binStr = String.valueOf(converseUtil.converse(instr).get(0));
            String hexStr = String.valueOf(converseUtil.converse(instr).get(1));

            System.out.print("二进制转换结果：");
            for (int i = 0; i < binStr.length() - 3; i=i+4) {
                System.out.print(binStr.substring(i,i+4)+ " ");
            }
            System.out.println();
            System.out.println("十六进制转换结果：0x"+hexStr);

            if(converseUtil.getClass().equals( ChConverse.class)){
                check = instr.toCharArray()[0];
            }else if(converseUtil.getClass().equals(IntConverse.class)){
                check = Integer.valueOf(instr);
            }else if(converseUtil.getClass().equals(FloatConverse.class)){
                check = Float.valueOf(instr);
            }
            String address = Long.toHexString(VM.current().addressOf(check));
            String hexResult = Integer.toHexString(check.hashCode());
            System.out.println("实际数据地址："+address);
            System.out.println("实际十六进制表示："+hexResult);
        }
    }

    public static void main(String[] args) {
       new Main().run();
    }
}
