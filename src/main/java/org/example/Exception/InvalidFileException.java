package org.example.Exception;

public class InvalidFileException extends Exception {
    public InvalidFileException() {
        super("File is not valid, or doesn't exist");
    }
    public InvalidFileException(String message) {
        super(message);
    }
}
