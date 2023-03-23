package soa_lab3.pipe;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Alphabetizer extends Filter {

    public Alphabetizer(Pipe input, Pipe output) {
        super(input, output);
    }


    @Override
    protected void transform() {
        List<String> lines = new ArrayList<String>();
        CharArrayWriter writer = new CharArrayWriter();
        try {
            int c = -1;
            while((c = input.read()) != -1) {
                writer.write(c);
                //		System.out.print((char) c);
                if(c == 10) {
                    String curLine = writer.toString();
                    lines.add(curLine);
                    writer.reset();
                }
            }


            sort(lines);

            for(String s : lines) {
                output.write(s);
            }

            input.closeReader();
            output.closeWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sort(List<String> lines) { //堆排序
        int size = lines.size();

        for (int i = (size / 2 - 1); i >= 0; i--) {
            siftDown(lines, i, size);
        }

        for (int i = (size - 1); i >= 1; i--) {
            Object tmp = lines.get(0);
            lines.set(0, lines.get(i));
            lines.set(i, (String) tmp);
            siftDown(lines, 0, i);
        }
    }

    private void siftDown(List<String> lines, int root, int bottom) {
        int max_child = root * 2 + 1;

        while (max_child < bottom) {
            if ((max_child + 1) < bottom) {
                if (((String) lines.get(max_child + 1))
                        .compareTo((String) lines.get(max_child)) > 0) {
                    max_child++;
                }
            }

            if (((String) lines.get(root)).compareTo((String) lines
                    .get(max_child)) < 0) {
                Object tmp = lines.get(root);
                lines.set(root, lines.get(max_child));
                lines.set(max_child, (String) tmp);
                root = max_child;
                max_child = root * 2 + 1;
            } else {
                break;
            }
        }
    }

}


