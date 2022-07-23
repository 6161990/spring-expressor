package com.yoon.boundNotBound;

public class SomeThing {

    Command b;

    public SomeThing(Command b) {
        this.b = b;
    }

    public SomeThing() {

    }

    public SomeThing(boolean b) {
    }

    public boolean someMethod(boolean b) {
        return b;
    }

    public boolean someMethod(Command b) {
        return true;
    }
}
