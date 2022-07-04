package pattern.strategy;

public class AppendStrategy implements EncodingStrategy {

    @Override
    public String encoding(String message) {
        return "ABCD" + message;
    }
}
