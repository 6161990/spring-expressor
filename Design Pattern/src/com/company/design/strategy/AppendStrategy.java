package com.company.design.strategy;

public class AppendStrategy implements EncodingStrategy {

    @Override
    public String encoding(String message) {
        return "ABCD" + message;
    }
}
