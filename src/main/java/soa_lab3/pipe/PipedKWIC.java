package soa_lab3.pipe;

import java.io.*;

public class PipedKWIC {
    public static void main(String[] args) throws FileNotFoundException,IOException {
        String path = "C:/Users/ASUS/Desktop/体系风格实验/管道-过滤器/";
        String name1 ="input.txt";
        String name2 ="input2.txt";

        Pipe in_cs = new Pipe();
        Pipe cs_al = new Pipe();
        Pipe al_ou = new Pipe();

        File file = new File(path,name1);
        FileInputStream in = new FileInputStream(file);
        Input input = new Input(in, in_cs);
        CircularShifter shifter = new CircularShifter(in_cs, cs_al);
        Alphabetizer alpha = new Alphabetizer(cs_al, al_ou);
        //输出到文件
        Output output = new Output(al_ou,new File(path+"output1.txt"));
//        File file = new File(path,name2);
//        FileInputStream in = new FileInputStream(file);
//        Input input = new Input(in, in_cs);
//        CircularShifter shifter = new CircularShifter(in_cs, cs_al);
//        Alphabetizer alpha = new Alphabetizer(cs_al, al_ou);
//        //输出到文件
//        Output output = new Output(al_ou,new File(path+"output2.txt"));

        input.start();
        shifter.start();
        alpha.start();
        output.start();

        input.stop();
        shifter.stop();
        alpha.stop();
        output.stop();
    }
}
