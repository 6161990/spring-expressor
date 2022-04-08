package numberGuessing.console;

final class TextOutput {

    private final StringBuffer outputBuffer;

    public TextOutput(String gameModeSelectMessage) {
        outputBuffer = new StringBuffer(gameModeSelectMessage);
    }

    public String flushOutput() {
        String output = outputBuffer.toString();
        outputBuffer.setLength(0);
        return output;
    }

    public void printLines(String... lines) {
        outputBuffer.append(String.join(System.lineSeparator(), lines));
    }


}
