package pattern.strategy;

import java.util.Base64;

public class Base64Strategy implements EncodingStrategy {
    @Override
    public String encoding(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
