package soa_lab3.pipe;

import java.io.*;

public class Output extends Filter {

    private FileWriter outWriter;
    /**
     * @param input 输入管道
     */
    public Output(Pipe input,File fOutput) throws IOException {
        super(input, null);
        outWriter = new FileWriter(fOutput);
    }

    @Override
    protected void transform() {
        BufferedWriter bw = new BufferedWriter(outWriter);
        try {
            int c = -1;
            while((c = input.read()) != -1) {
                bw.write(c);
            }
            input.closeReader();
            bw.flush();
            bw.close();
            outWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

