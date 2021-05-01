package com.garden.taskgarden;

public class NullTaskException extends Exception {
    public NullTaskException(String errorMessage) {
        super(errorMessage);
    }
}