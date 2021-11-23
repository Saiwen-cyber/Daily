package soa_lab3.batch;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class BatchKWIC {
    private ArrayList<String[]> kwicList;
    String path = "";
    String outFile ="";
    public BatchKWIC(String path,String filename, String outFile) throws IOException {
        this.outFile = outFile;
        this.path = path;
        kwicList = new ArrayList<String[]>();
        kwicList=read(filename);
        shift();//先循环kwicList
        sort();//再排序kwicList
        display ();//最后输出kwicList到文件中
    }

    private void display() throws IOException {
        File file = new File(path,outFile);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String[] strings:kwicList) {
            for (String str:strings) {
                writer.write(str +" ");
            }
            writer.write( "\n");
        }
        writer.close();
    }

    private void sort() {
        kwicList.sort(Comparator.comparing(o -> o[0]));
    }

    private void shift() {
        ArrayList<String[]> list = new ArrayList<String[]>();
        for (String[] str:kwicList) {
            for (int i = 0; i < str.length; i++) {
                StringBuilder builder = new StringBuilder();
                int j = i;
                for (int times = 0; times < 3; times++) {
                    builder.append(str[j]).append(",");
                    j++;
                    if (j == str.length) {
                        j = 0;
                    }
                }
                list.add(builder.toString().split(","));
            }
        }
        kwicList = list;
    }

    private ArrayList<String[]> read(String filename) throws IOException {
        ArrayList<String[]> list = new ArrayList<String[]>();
        File file = new File(path, filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String str = "";
        while ((str = reader.readLine()) != null) {
            list.add(str.split(" "));
        }
        reader.close();
        return list;
    }

    public static void main(String[] args) throws IOException {
        String path = "C:/Users/ASUS/Desktop/体系风格实验/批处理/";
        String inFile = "input.txt";
        String outFile = "output1.txt";
        String inFile2 = "input2.txt";
        String outFile2 = "output2.txt";
        new BatchKWIC(path,inFile,outFile);
        new BatchKWIC(path,inFile2,outFile2);
    }
}