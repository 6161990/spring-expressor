package numberGuessing.console;

import static numberGuessing.console.AppModel.GAME_MODE_SELECT_MESSAGE;

public class TextOutput {

    private final StringBuffer outputBuffer;

    public TextOutput(String defaultMessage){
        outputBuffer = new StringBuffer(defaultMessage);
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
