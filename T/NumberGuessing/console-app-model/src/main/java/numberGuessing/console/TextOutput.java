package numberGuessing.console;

public class TextOutput {

    private StringBuffer outputBuffer;

    public TextOutput(String defaultMessage) {
        this.outputBuffer = new StringBuffer(defaultMessage);
    }

    public void printLines(String... message) {
        outputBuffer.append(String.join(System.lineSeparator(), message));
    }

    public String flushOutput() {
        String output = outputBuffer.toString();
        outputBuffer.setLength(0);
        return output;
    }

}
