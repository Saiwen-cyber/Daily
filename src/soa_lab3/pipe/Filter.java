package soa_lab3.pipe;

public abstract class Filter implements Runnable {
    protected Pipe input;
    protected Pipe output;
    private boolean is_started = false;

    public Filter(Pipe input, Pipe output) {
        this.input = input;
        this.output = output;
    }
    public void start() {
        if (!is_started) {
            is_started = true;
            Thread thread = new Thread(this);
            thread.start();
        }
    }
    public void stop() {
        is_started = false;
    }
    @Override
    public void run() {
        transform();
    }

    /*
     * This method transforms the data from the input pipe and writes the
     * transformed data into output pipe.
     */
    abstract protected void transform();

}
