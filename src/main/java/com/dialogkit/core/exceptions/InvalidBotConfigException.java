package com.dialogkit.core.exceptions;

public class InvalidBotConfigException extends Exception {

    public InvalidBotConfigException(String message) {
        super(message);
    }

    public InvalidBotConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
