package 预处理;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    String path = "D:/Program Files/Dev-Cpp/MinGW64/x86_64-w64-mingw32/include/";
    String path2 = "D:/C++project/demo1011/";
    String srcfileName = "test.c";
    List<String> list = new ArrayList<String>();
    //源文件的长度。
    int len = 0;
    public void initprocess() throws IOException {
        File file = new File(path2+srcfileName);
        if (!file.exists()){
            System.out.println("源文件不存在");
        }
        //创建BufferedReader读取文件内容
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line1;
        while ((line1=br.readLine())!=null) {
            list.add(line1);
        }
        br.close();
        len = list.size();
        for (int i = 0; i < len; i++) {
            //引进头文件
            String line = list.get(i);
            if (line.contains("#include")){
                String[] strs1 = line.split("<");
                String[] strs2 = strs1[1].split(">");
                process(strs2[0]);
                list.set(i,"") ;
            }
            if(line.contains("#define")){
                String[] strs = line.split(" ");
                pro_define(strs[1],strs[2],i);
                list.set(i,"") ;
            }
            if (line.contains("//")){
                pro_note1(i);;
            }
            if (line.contains("/*")||line.contains("*/")){
                pro_note2(i);
            }
        }
        display();
    }

    private void pro_note1(int index) {
        String []strs = list.get(index).split("//");
        list.set(index,strs[0]);
    }
    private void pro_note2(int index) {
        for (int i = index; i < len; i++) {
            String li = list.get(i);
            if (li.contains("/*")){
                int addr = li.indexOf("/*");
                System.out.println(li.substring(0,addr));
                list.set(i,li.substring(0,addr));
                continue;
            }
            if (li.contains("*/")){
                int addr = li.indexOf("*/")+2;
                System.out.println(li.substring(addr,li.length()));
                list.set(i,li.substring(addr,li.length()));
                break;
            }
                list.set(i,"");
        }
    }

    //将头文件贴进源列表
    public void process(String headFile) throws IOException {
        //创建BufferedReader读取文件内容
        BufferedReader br = new BufferedReader(new FileReader(path+headFile));
        String line;
        while ((line=br.readLine())!=null) {
            list.add(line);
        }
        br.close();
    }

    //更改源文件define
    public void pro_define(String str,String restr,int index) throws IOException {
        for (int i = index; i < len; i++) {
            String line = list.get(i);
            String []temps = line.split(" ");
            for (int j = 0; j < temps.length; j++) {
                if (temps[j].equals(str)){
                    temps[j] = restr;
                }

                String reline = String.join(" ", temps);
                list.set(i,reline);
//                System.out.println(reline);
            }
        }
    }
    //去除虚字符
    public void display() throws IOException {

        String tempPath = srcfileName.substring(0,srcfileName.lastIndexOf("."))+".i";
        System.out.println(tempPath);
        File tempFile = new File(path2+tempPath);
        if (!tempFile.exists()){
            tempFile.createNewFile();}
        //创建BufferedWriter对象并向文件写入内容
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        //清空文件
        bw.write("");
        for (int i = len; i < list.size(); i++) {
            String line = list.get(i);
            bw.write(line+"\n");
        }
        for (int i = 0; i < len; i++) {
            String line = list.get(i);
            bw.write(line+"\n");
        }

        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().initprocess();
    }
}
