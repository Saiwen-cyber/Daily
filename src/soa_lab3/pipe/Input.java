package soa_lab3.pipe;

import java.io.*;

public class Input extends Filter {

    private InputStreamReader inReader;

    public Input(FileInputStream in, Pipe output) {
        super(null, output);
        inReader = new InputStreamReader(in);
    }

    @Override
    protected void transform() {
        BufferedReader br = new BufferedReader(inReader);
        try {
            String line;
            while((line = br.readLine()) != null) {
                output.write(line + '\n');
            }
            br.close();
            inReader.close();
            output.closeWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

