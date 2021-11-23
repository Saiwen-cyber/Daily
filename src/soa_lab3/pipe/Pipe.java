package soa_lab3.pipe;

import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.IOException;

public class Pipe {
    private PipedWriter pipedWriter;
    private PipedReader pipedReader;

    public Pipe() throws IOException {
        pipedWriter = new PipedWriter();
        pipedReader = new PipedReader();
        // let this piped writer to be connected to the piped reader
        pipedWriter.connect(pipedReader);
    }
    // Writes char to the piped output stream
    public void write(String str) throws IOException {
        pipedWriter.write(str);
    }

    //  Reads the next character of data from this piped stream
    public int read() throws IOException {
        return pipedReader.read();
    }

    public void closeWriter() throws IOException {
        pipedWriter.flush();
        pipedWriter.close();
    }

    public void closeReader() throws IOException {
        pipedReader.close();
    }
}
