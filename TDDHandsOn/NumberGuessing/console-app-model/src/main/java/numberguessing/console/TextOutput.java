package numberguessing.console;

final class TextOutput {

    private final StringBuffer outputBuffer;

    public TextOutput(String selectModeMessage) {
        this.outputBuffer = new StringBuffer(selectModeMessage);
    }

    public String flushOutput() {
        String flushOutput = outputBuffer.toString();
        outputBuffer.setLength(0); // 버퍼 비워주기
        return flushOutput;
    }

    public void printLines(String... lines) {
        outputBuffer.append(String.join(System.lineSeparator(), lines)); // ""-> 라인 별로 joining
    }

}
